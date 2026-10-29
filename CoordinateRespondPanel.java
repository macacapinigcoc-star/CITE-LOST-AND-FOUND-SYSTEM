package lostandfoundapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CoordinateRespondPanel extends JPanel {

    // Customize the theme here.
    private static final Color GREEN = new Color(0, 102, 51);
    private static final Color DARK_GREEN = new Color(0, 75, 38);
    private static final Color BACKGROUND = new Color(248, 248, 218);
    private static final Color CARD = Color.WHITE;
    private static final Color TEXT = new Color(55, 65, 55);
    private static final Color MUTED = new Color(115, 125, 115);
    private static final Color LIGHT_GREEN = new Color(231, 243, 233);

    private JButton btnReady;
    private Runnable onReadyToHandover;

    public CoordinateRespondPanel() {
        initializeUI();
    }

    private void initializeUI() {

        setLayout(new GridBagLayout());
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(222, 228, 215), 1
            ),
            new EmptyBorder(30, 35, 30, 35)
        ));

        card.setPreferredSize(new Dimension(650, 530));

        // Status badge.
        JLabel badge = new JLabel("  OWNERSHIP VERIFIED  ");
        badge.setOpaque(true);
        badge.setBackground(LIGHT_GREEN);
        badge.setForeground(DARK_GREEN);
        badge.setFont(new Font("SansSerif", Font.BOLD, 11));
        badge.setBorder(new EmptyBorder(8, 8, 8, 8));
        badge.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Icon.
        JLabel icon = new JLabel("\u2713", SwingConstants.CENTER);
        icon.setFont(new Font("SansSerif", Font.BOLD, 32));
        icon.setForeground(GREEN);
        icon.setOpaque(true);
        icon.setBackground(LIGHT_GREEN);
        icon.setPreferredSize(new Dimension(65, 65));
        icon.setMaximumSize(new Dimension(65, 65));
        icon.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Heading and description.
        JLabel title = new JLabel("Coordinate Return");
        title.setFont(new Font("SansSerif", Font.BOLD, 27));
        title.setForeground(DARK_GREEN);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
            "<html><div style='width:500px;'>"
            + "Your ownership has been verified. The next step is "
            + "to coordinate the safe return of the item with "
            + "the designated school staff."
            + "</div></html>"
        );
        description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        description.setForeground(MUTED);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(badge);
        card.add(Box.createVerticalStrut(20));
        card.add(icon);
        card.add(Box.createVerticalStrut(18));
        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(description);
        card.add(Box.createVerticalStrut(24));

        // Handover instructions.
        JLabel instructionsTitle = new JLabel(
            "Prepare for the handover"
        );
        instructionsTitle.setFont(
            new Font("SansSerif", Font.BOLD, 15)
        );
        instructionsTitle.setForeground(TEXT);
        instructionsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(instructionsTitle);
        card.add(Box.createVerticalStrut(12));

        card.add(createStep(
            "1",
            "Contact the designated school staff",
            "Confirm the available time and location for the return."
        ));

        card.add(Box.createVerticalStrut(12));

        card.add(createStep(
            "2",
            "Agree on the handover arrangements",
            "Follow the school's instructions for collecting the item."
        ));

        card.add(Box.createVerticalStrut(12));

        card.add(createStep(
            "3",
            "Bring your identification",
            "Bring your student ID or other requested identification."
        ));

        card.add(Box.createVerticalStrut(22));

        // Handover action.
        btnReady = createButton(
            "Ready to Handover",
            GREEN
        );
        btnReady.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnReady.addActionListener(e -> {
            if (onReadyToHandover != null) {
                onReadyToHandover.run();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Please coordinate with the designated "
                        + "school staff to arrange the handover. "
                        + "No server status was changed.",
                    "Coordinate Return",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        card.add(btnReady);

        add(card);
    }

    private JPanel createStep(
            String number, String heading, String description) {

        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JLabel numberLabel = new JLabel(number, SwingConstants.CENTER);
        numberLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        numberLabel.setForeground(GREEN);
        numberLabel.setOpaque(true);
        numberLabel.setBackground(LIGHT_GREEN);
        numberLabel.setPreferredSize(new Dimension(36, 36));

        JLabel textLabel = new JLabel(
            "<html><b>" + heading + "</b><br>"
            + "<span style='color:#737D73;'>" + description
            + "</span></html>"
        );
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textLabel.setForeground(TEXT);

        row.add(numberLabel, BorderLayout.WEST);
        row.add(textLabel, BorderLayout.CENTER);

        return row;
    }

    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        button.setBorder(new EmptyBorder(13, 20, 13, 20));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));

        return button;
    }

    // Connect this panel to your application workflow.
    public void setOnReadyToHandover(Runnable action) {
        this.onReadyToHandover = action;
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
