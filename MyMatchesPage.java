
package lostandfoundapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;

public class MyMatchesPage extends JPanel {

    private final Color GREEN = new Color(0, 102, 51);
    private final Color DARK_GREEN = new Color(0, 76, 38);
    private final Color CREAM = new Color(248, 248, 218);
    private final Color LIGHT_GREEN = new Color(232, 242, 230);
    private final Color TEXT = new Color(45, 55, 45);
    private final Color MUTED = new Color(110, 120, 112);

    private JPanel contentPanel;
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private JButton refreshButton;
    private JLabel countLabel;

    public MyMatchesPage() {
        buildUI();
        loadMyMatches();
    }
    
    private int readUserId(JSONObject object, String... keys) {
        for (String key : keys) {
            if (!object.isNull(key) && object.has(key)) {
                Object value = object.opt(key);

                if (value instanceof Number) {
                    return ((Number) value).intValue();
                }

                if (value instanceof JSONObject) {
                    JSONObject nested = (JSONObject) value;
                    int id = nested.optInt("user_id",
                            nested.optInt("id", 0));

                    if (id > 0) {
                        return id;
                    }
                }
            }
        }

        return 0;
    }

private MatchActionPanel.Role determineReporterRole(JSONObject match) {

    JSONObject item = match.optJSONObject("item");

    if (item == null) {
        return MatchActionPanel.Role.UNKNOWN;
    }

    String type = item.optString("type", "")
            .trim()
            .toLowerCase(java.util.Locale.ROOT);

    switch (type) {
        case "lost":
            return MatchActionPanel.Role.LOST_REPORTER;

        case "found":
            return MatchActionPanel.Role.FOUND_REPORTER;

        default:
            return MatchActionPanel.Role.UNKNOWN;
    }
}

    private String getCategoryGuidance(JSONObject match) {

        JSONObject item = match.optJSONObject("item");

        if (item == null) {
            return "Compare the item's actual features with the report.";
        }

        JSONObject category = item.optJSONObject("category");

        if (category != null) {
            String description = category.optString("description", "").trim();

            if (!description.isEmpty()) {
                return description;
            }

            String guidance = category.optString("verification_guidance", "")
                    .trim();

            if (!guidance.isEmpty()) {
                return guidance;
            }
        }

        // Some APIs return category as a string rather than an object.
        String categoryName = item.optString("category", "").trim();

        if (!categoryName.isEmpty()
                && !"null".equalsIgnoreCase(categoryName)) {
            return "Category: " + categoryName
                    + ". Compare the item's distinctive features "
                    + "with the report.";
        }

        return "Compare the item's distinctive features, "
                + "identifying details, and other information "
                + "against the original report.";
    }

    // Build all components using Java code
    private void buildUI() {

        setLayout(new BorderLayout(0, 0));
        setBackground(CREAM);
        setBorder(new EmptyBorder(24, 28, 24, 28));

        // Header
        JPanel header = new JPanel(new BorderLayout(12, 8));
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("My Matches");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(DARK_GREEN);

        JLabel subtitle = new JLabel(
            "Review possible matches and respond to an item."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);
        subtitle.setBorder(new EmptyBorder(6, 0, 0, 0));

        countLabel = new JLabel("Loading matches...");
        countLabel.setFont(new Font("Arial", Font.BOLD, 12));
        countLabel.setForeground(GREEN);
        countLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        heading.add(title);
        heading.add(subtitle);
        heading.add(countLabel);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 13));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBackground(GREEN);
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setOpaque(true);
        refreshButton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );
        refreshButton.setMargin(new Insets(10, 18, 10, 18));
        refreshButton.addActionListener(e -> loadMyMatches());

        header.add(heading, BorderLayout.CENTER);
        header.add(refreshButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Scrollable vertical list
        listPanel = new VerticalListPanel();
        listPanel.setBackground(CREAM);
        listPanel.setBorder(new EmptyBorder(4, 4, 12, 4));

        scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(CREAM);
        scrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.getVerticalScrollBar().setUnitIncrement(18);

        // Retain showPanel() support without putting contentPanel
        // inside the list of match cards.
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    // Retained for future page switching
    private void showPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void loadMyMatches() {

        refreshButton.setEnabled(false);
        countLabel.setText("Loading matches...");

        try {
            int userId = Session.getUserId();

            if (userId <= 0) {
                countLabel.setText("No active user session.");

                JOptionPane.showMessageDialog(
                    this,
                    "No logged-in user found.",
                    "My Matches",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String response = lostandfoundapp.apis.apis.getItemMatches(userId);
            String trimmed = response.trim();

            JSONArray matches;

            if (trimmed.startsWith("[")) {
                matches = new JSONArray(response);
            } else {
                JSONObject result = new JSONObject(response);

                if (result.opt("data") instanceof JSONArray) {
                    matches = result.getJSONArray("data");
                } else if (
                    result.opt("item_matches") instanceof JSONArray
                ) {
                    matches = result.getJSONArray("item_matches");
                } else {
                    matches = new JSONArray();
                }
            }

            listPanel.removeAll();

            if (matches.length() == 0) {
                countLabel.setText("0 matches");
                listPanel.add(createEmptyState());
            } else {
                countLabel.setText(
                    matches.length()
                    + (matches.length() == 1
                        ? " match found"
                        : " matches found")
                );

                for (int i = 0; i < matches.length(); i++) {
                    JSONObject match = matches.getJSONObject(i);
                    match.optJSONObject("item");
                    JPanel entry = createMatchEntry(match);
                    listPanel.add(entry);
                    listPanel.add(Box.createVerticalStrut(18));
                }
            }

            listPanel.revalidate();
            listPanel.repaint();

            scrollPane.revalidate();
            scrollPane.repaint();

            scrollPane.getVerticalScrollBar().setValue(0);

        } catch (Exception e) {
            countLabel.setText("Unable to load matches");

            JOptionPane.showMessageDialog(
                this,
                "Failed to load matches:\n" + e.getMessage(),
                "My Matches",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            refreshButton.setEnabled(true);
        }
    }


   
    private JPanel createMatchEntry(org.json.JSONObject match) {

    // =====================================================
    // 1. EXTRACT MATCH DATA
    // =====================================================

    int matchId = match.optInt(
            "id",
            match.optInt("match_id", 0)
    );

    String status = match.optString("status", "pending")
            .trim()
            .toLowerCase(java.util.Locale.ROOT);

    JSONObject item = match.optJSONObject("item");

    String itemName = "Matched Item";
    String location = "Location not available";
    String date = "Date not available";

    if (item != null) {
        itemName = item.optString("name", "Matched Item");
        location = item.optString(
                "location", "Location not available");
        date = item.optString(
                "incident_date", "Date not available");
    }

    // =====================================================
    // 2. OUTER MATCH CARD
    // =====================================================

    JPanel outerCard = new JPanel();
    outerCard.setLayout(new javax.swing.BoxLayout(
            outerCard, javax.swing.BoxLayout.Y_AXIS));

    outerCard.setBackground(Color.WHITE);
    outerCard.setBorder(
            javax.swing.BorderFactory.createLineBorder(
                    new Color(220, 226, 220), 1));

    outerCard.setAlignmentX(Component.LEFT_ALIGNMENT);
    outerCard.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

    // =====================================================
    // 3. ITEM DETAILS
    // =====================================================

    JPanel detailsPanel = new JPanel(new BorderLayout(12, 5));
    detailsPanel.setBackground(Color.WHITE);
    detailsPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    18, 20, 18, 20));

    JPanel detailsText = new JPanel();
    detailsText.setLayout(new javax.swing.BoxLayout(
            detailsText, javax.swing.BoxLayout.Y_AXIS));
    detailsText.setBackground(Color.WHITE);

    JLabel lblItemName = new JLabel(itemName);
    lblItemName.setFont(new Font("SansSerif", Font.BOLD, 18));
    lblItemName.setForeground(new Color(45, 75, 50));

    JLabel lblLocation = new JLabel("Location: " + location);
    lblLocation.setFont(new Font("SansSerif", Font.PLAIN, 13));
    lblLocation.setForeground(new Color(90, 90, 90));

    JLabel lblDate = new JLabel("Date: " + formatIncidentDate(date));
    lblDate.setFont(new Font("SansSerif", Font.PLAIN, 13));
    lblDate.setForeground(new Color(90, 90, 90));

    JLabel lblStatus = new JLabel(
            "Status: " + formatMatchStatus(status));

    lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
    lblStatus.setForeground(getStatusColor(status));

    detailsText.add(lblItemName);
    detailsText.add(Box.createVerticalStrut(8));
    detailsText.add(lblLocation);
    detailsText.add(Box.createVerticalStrut(4));
    detailsText.add(lblDate);
    detailsText.add(Box.createVerticalStrut(8));
    detailsText.add(lblStatus);

    detailsPanel.add(detailsText, BorderLayout.CENTER);

    // =====================================================
    // 4. RESPONSE SECTION
    // =====================================================

    JPanel responseSection = new JPanel(new BorderLayout());
    responseSection.setBackground(new Color(248, 250, 245));
    responseSection.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 0, 0, 0,
                            new Color(225, 230, 220)),
                    BorderFactory.createEmptyBorder(
                            12, 12, 12, 12)));

    if (matchId <= 0) {

        JLabel lblNoId = new JLabel(
                "Unable to load response: missing match ID.");

        lblNoId.setForeground(Color.RED);
        responseSection.add(lblNoId, BorderLayout.CENTER);

    } else {

        // Determine perspective and guidance ONCE.
        MatchActionPanel.Role role =
                determineReporterRole(match);

        String guidance = getCategoryGuidance(match);

        // Use the confirmation timestamp belonging to this reporter.
        boolean currentUserReturnConfirmed = false;

        if (role == MatchActionPanel.Role.LOST_REPORTER) {
            currentUserReturnConfirmed =
                    !match.isNull("lost_user_confirmed_at");

        } else if (role == MatchActionPanel.Role.FOUND_REPORTER) {
            currentUserReturnConfirmed =
                    !match.isNull("found_user_confirmed_at");
        }

        // =================================================
        // 5. MATCH ACTION CALLBACKS
        // =================================================

        MatchActionPanel.Actions actions =
                new MatchActionPanel.Actions() {

    @Override
    public void onInterested() {
        System.out.print(matchId);
        handleInterested(matchId);
    }

    @Override
    public void onDismiss() {
        handleDismiss(matchId);
    }

    @Override
    public void onConfirmMatch() {

        int answer = JOptionPane.showConfirmDialog(
                MyMatchesPage.this,
                "Have you met the other reporter and compared "
                + "the actual item with the report?",
                "Confirm Match",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (answer == JOptionPane.YES_OPTION) {
            handleConfirmMatch(matchId);
        }
    }

    @Override
    public void onConfirmReturned() {

        int answer = JOptionPane.showConfirmDialog(
                MyMatchesPage.this,
                "Confirm that the item has physically been "
                + "returned to its owner?",
                "Confirm Item Returned",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (answer == JOptionPane.YES_OPTION) {
            handleConfirmReturned(matchId);
        }
    }
    
};

        // =================================================
        // 6. RENDER THE CORRECT STATUS PANEL
        // =================================================

        MatchActionPanel response = new MatchActionPanel(
                status,
                role,
                guidance,
                currentUserReturnConfirmed,
                actions
        );

        response.setBorder(null);
        response.setBackground(new Color(248, 250, 245));

        responseSection.add(response, BorderLayout.CENTER);
    }

    // =====================================================
    // 7. ASSEMBLE THE CARD
    // =====================================================

    outerCard.add(detailsPanel);
    outerCard.add(responseSection);

    return outerCard;
}
    
    private String formatIncidentDate(String value) {

    if (value == null
            || value.isBlank()
            || "Date not available".equals(value)) {
        return "Date not available";
    }

    try {
        java.time.LocalDate date =
                java.time.OffsetDateTime.parse(value)
                        .toLocalDate();

        return date.format(
                java.time.format.DateTimeFormatter.ofPattern(
                        "MMM dd, yyyy",
                        java.util.Locale.ENGLISH));

    } catch (java.time.format.DateTimeParseException e) {
        return value;
    }
}
    
private void handleInterested(int matchId) {

    int userId = Session.getUserId();
    
     
    if (userId <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "Your session has expired. Please log in again.",
                "Authentication Required",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    if (matchId <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "Invalid match ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    JSONObject payload = new JSONObject();
    payload.put("user_id", userId);
    payload.put("response", "interested");
    

    try {
    

        // Replace this with your existing API client's PATCH method.
        String response = lostandfoundapp.apis.apis.respondToMatch(matchId, payload);

        JOptionPane.showMessageDialog(
                this,
                "Your interest has been submitted successfully!",
                "Match Response",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Refresh the matches from your backend.
//        loadMatches();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to submit your response: " + e.getMessage(),
                "Request Failed",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    private void handleDismiss(int matchId) {
        // Call your existing dismiss API.
    }

    private void handleConfirmMatch(int matchId) {
        int userId = Session.getUserId();
    
     
    if (userId <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "Your session has expired. Please log in again.",
                "Authentication Required",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    if (matchId <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "Invalid match ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }
    JSONObject payload = new JSONObject();
    payload.put("user_id", userId);
    try{
    String response = lostandfoundapp.apis.apis.confirmVerification(matchId, payload);

    JOptionPane.showMessageDialog(
                this,
                "Your interest has been submitted successfully!",
                "Match Response",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Refresh the matches from your backend.
//        loadMatches();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to submit your response: " + e.getMessage(),
                "Request Failed",
                JOptionPane.ERROR_MESSAGE
        );
    }
        // Call your existing confirm-match API.
    }

    private void handleConfirmReturned(int matchId) {
        // Call your existing return-confirmation API.
        
         int userId = Session.getUserId();
    
     
    if (userId <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "Your session has expired. Please log in again.",
                "Authentication Required",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    if (matchId <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "Invalid match ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }
     JSONObject payload = new JSONObject();
       payload.put("user_id", userId);
    try{
    String response = lostandfoundapp.apis.apis.confirmHandover(matchId, payload);

    JOptionPane.showMessageDialog(
                this,
                "Your interest has been submitted successfully!",
                "Match Response",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Refresh the matches from your backend.
//        loadMatches();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to submit your response: " + e.getMessage(),
                "Request Failed",
                JOptionPane.ERROR_MESSAGE
        );
        }
        
    }

        private String formatMatchStatus(String status) {
            if (status == null || status.isEmpty()) {
                return "Unknown";
            }

            String[] words = status.replace('_', ' ').split("\\s+");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                if (result.length() > 0) {
                    result.append(' ');
                }

                result.append(
                        Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1));
            }

            return result.toString();
        }

        private Color getStatusColor(String status) {
            switch (status) {
                case "pending":
                    return new Color(180, 120, 20);

                case "verification_pending":
                    return new Color(190, 120, 20);

                case "handover_pending":
                    return new Color(39, 120, 69);

                default:
                    return new Color(100, 100, 100);
            }
        }


    private JPanel createEmptyState() {

        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setLayout(new BoxLayout(empty, BoxLayout.Y_AXIS));
        empty.setBorder(new EmptyBorder(65, 20, 65, 20));
        empty.setAlignmentX(Component.LEFT_ALIGNMENT);
        empty.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 220)
        );

        JLabel icon = new JLabel("✓");
        icon.setFont(new Font("Arial", Font.BOLD, 42));
        icon.setForeground(GREEN);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("No matches yet");
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(DARK_GREEN);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel message = new JLabel(
            "Possible matches will appear here."
        );
        message.setFont(new Font("Arial", Font.PLAIN, 13));
        message.setForeground(MUTED);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        empty.add(icon);
        empty.add(Box.createVerticalStrut(12));
        empty.add(title);
        empty.add(Box.createVerticalStrut(8));
        empty.add(message);

        return empty;
    }

    private String firstNonEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return "";
    }

    // Scrollable panel that tracks viewport width but not height
    private static class VerticalListPanel
            extends JPanel
            implements javax.swing.Scrollable {

        VerticalListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return new Dimension(900, 550);
        }

        @Override
        public int getScrollableUnitIncrement(
            java.awt.Rectangle visibleRect,
            int orientation,
            int direction
        ) {
            return 20;
        }

        @Override
        public int getScrollableBlockIncrement(
            java.awt.Rectangle visibleRect,
            int orientation,
            int direction
        ) {
            return Math.max(visibleRect.height - 30, 30);
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
