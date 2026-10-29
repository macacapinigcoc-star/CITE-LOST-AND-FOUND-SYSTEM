package lostandfoundapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

    public class MatchActionPanel extends JPanel {

        private static final Color GREEN = new Color(0, 102, 51);
        private static final Color DARK_GREEN = new Color(0, 75, 38);
        private static final Color CREAM = new Color(248, 248, 218);
        private static final Color PALE_GREEN = new Color(231, 243, 233);
        private static final Color TEXT = new Color(55, 65, 55);
        private static final Color MUTED = new Color(105, 115, 105);

        public enum Role {
            LOST_REPORTER,
            FOUND_REPORTER,
            UNKNOWN
        }

        public interface Actions {
            void onInterested();
            void onDismiss();
            void onConfirmMatch();
            void onConfirmReturned();
        }

        private final String status;
        private final Role role;
        private final String categoryGuidance;
        private final Actions actions;

        private boolean returnConfirmationSubmitted;

        public MatchActionPanel(
                String status,
                Role role,
                String categoryGuidance,
                boolean returnConfirmationSubmitted,
                Actions actions) {

            this.status = status == null
                    ? ""
                    : status.trim().toLowerCase(java.util.Locale.ROOT);

            this.role = role == null ? Role.UNKNOWN : role;
            this.categoryGuidance = categoryGuidance == null
                    || categoryGuidance.trim().isEmpty()
                    ? "Follow the school's item verification procedures."
                    : categoryGuidance.trim();

            this.returnConfirmationSubmitted = returnConfirmationSubmitted;
            this.actions = actions;

            setLayout(new BorderLayout());
            setBackground(CREAM);
            setBorder(new EmptyBorder(12, 12, 12, 12));

            render();
        }

        private void render() {
            removeAll();

            JPanel content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setBackground(CREAM);

            switch (status) {
                case "pending":
                    buildPending(content);
                    break;

                case "verification_pending":
                    buildVerification(content);
                    break;

                case "verified":
                    buildReturn(content);
                    break;

                case "resolved":
                    buildResolved(content);
                    break;

                default:
                    addText(content,
                            "Match status: " + readableStatus(status),
                            14, true, DARK_GREEN);
                    addText(content,
                            "No action is currently available for this status.",
                            12, false, MUTED);
                    break;
            }

            add(content, BorderLayout.CENTER);
            revalidate();
            repaint();
        }

        // -----------------------------------------------------
        // PENDING: reporter-specific actions
        // -----------------------------------------------------

        private void buildPending(JPanel panel) {

            addText(panel, "Potential Match", 19, true, DARK_GREEN);
            addText(panel,
                    role != Role.LOST_REPORTER
                            ? "This item may be yours. Review the match and "
                                + "express your interest if it looks familiar."
                            : role != Role.FOUND_REPORTER
                                ? "This item may belong to the person who "
                                    + "reported it missing."
                                : "Review this potential match.",
                    13, false, MUTED);

            panel.add(Box.createVerticalStrut(12));

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            buttons.setBackground(CREAM);

            String interestText;
            if (role != Role.LOST_REPORTER) {
                interestText = "This might be mine";
            } else {
                interestText = "This may belong to them";
            }

            JButton interested = createButton(interestText, GREEN);
            interested.addActionListener(e -> {
                if (actions != null) {
                    actions.onInterested();
                }
            });

            JButton dismiss = createButton("Dismiss", new Color(105, 105, 105));
            dismiss.addActionListener(e -> {
                if (actions != null) {
                    actions.onDismiss();
                }
            });

            buttons.add(interested);
            buttons.add(dismiss);
            panel.add(buttons);
        }

        // -----------------------------------------------------
        // PENDING VERIFICATION: confirm the match
        // -----------------------------------------------------

        private void buildVerification(JPanel panel) {

            addText(panel, "Verify the Item", 19, true, DARK_GREEN);
            addBadge(panel, "PENDING VERIFICATION");

            addText(panel,
                    "Both reporters expressed interest. Meet in a safe place "
                    + "and compare the actual item against its report before "
                    + "confirming the match.",
                    13, false, TEXT);

            addText(panel, "Suggested details to check", 14, true, DARK_GREEN);
            addText(panel, categoryGuidance, 13, false, MUTED);

            String roleText;
            switch (role) {
                case FOUND_REPORTER:
                        roleText = "Your role: Lost-item reporter. Meet the "
                            + "person who found the item. Verify its "
                            + "distinctive features before confirming "
                            + "that it belongs to you.";
                    break;

                case LOST_REPORTER:
                    
                        roleText = "Your role: Found-item reporter. Meet the "
                            + "person who reported the loss. Compare their "
                            + "identifying details with the actual item "
                            + "before agreeing to return it.";
                    break;

                default:
                    roleText = "Verify the item with the other reporter "
                            + "before confirming the match.";
                    break;
            }

            addText(panel, roleText, 13, false, TEXT);
            panel.add(Box.createVerticalStrut(12));

            JButton confirm = createButton("Confirm Match", GREEN);
            confirm.setAlignmentX(Component.LEFT_ALIGNMENT);
            confirm.addActionListener(e -> {
                if (actions != null) {
                    actions.onConfirmMatch();
                }
            });

            panel.add(confirm);
        }

        // -----------------------------------------------------
        // VERIFIED: coordinate return
        // -----------------------------------------------------

        private void buildReturn(JPanel panel) {

            addText(panel, "Coordinate Return", 19, true, DARK_GREEN);
            addBadge(panel, "Handover Pending");

            addText(panel,
                    "Both reporters agree the item matches. Arrange a safe "
                    + "meetup and return the item directly to its owner.",
                    13, false, TEXT);

            addText(panel, "Before the handover", 14, true, DARK_GREEN);
            addBullet(panel, "Agree on a convenient, safe meeting place.");
            addBullet(panel, "Bring the actual item and check it together.");
            addBullet(panel,
                    "Confirm the return only after the item has changed hands.");

            panel.add(Box.createVerticalStrut(12));

            if (returnConfirmationSubmitted) {
                addText(panel,
                        "Return confirmation submitted",
                        14, true, DARK_GREEN);
                addText(panel,
                        "Waiting for the other reporter to confirm the return. "
                        + "The match remains handover pending until both reporters "
                        + "have confirmed.",
                        13, false, MUTED);
            } else {
                JButton returned = createButton(
                        "Confirm Item Returned", GREEN);

                returned.setAlignmentX(Component.LEFT_ALIGNMENT);
                returned.addActionListener(e -> {
                    if (actions != null) {
                        actions.onConfirmReturned();
                    }
                });

                panel.add(returned);
                panel.add(Box.createVerticalStrut(5));
                addText(panel,
                        "Each reporter confirms separately. "
                        + "The match stays handover pending until both confirm.",
                        12, false, MUTED);
            }
        }

        // -----------------------------------------------------
        // RESOLVED: read-only confirmation
        // -----------------------------------------------------

        private void buildResolved(JPanel panel) {

            addBadge(panel, "COMPLETED");
            addText(panel, "Item Returned Successfully",
                    19, true, DARK_GREEN);

            addText(panel,
                    "Both reporters confirmed that the handover is complete. "
                    + "This match is now resolved.",
                    13, false, TEXT);
        }

        // -----------------------------------------------------
        // UI HELPERS
        // -----------------------------------------------------

        private void addText(
                JPanel panel,
                String text,
                int size,
                boolean bold,
                Color color) {

            JLabel label = new JLabel(
                    "<html><div style='width:600px;'>"
                    + escapeHtml(text).replace("\n", "<br>")
                    + "</div></html>");

            label.setFont(new Font(
                    "SansSerif",
                    bold ? Font.BOLD : Font.PLAIN,
                    size));

            label.setForeground(color);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setBorder(new EmptyBorder(3, 0, 5, 0));
            panel.add(label);
        }

        private void addBadge(JPanel panel, String text) {
            JLabel badge = new JLabel("  " + text + "  ");
            badge.setFont(new Font("SansSerif", Font.BOLD, 11));
            badge.setForeground(DARK_GREEN);
            badge.setOpaque(true);
            badge.setBackground(PALE_GREEN);
            badge.setBorder(new EmptyBorder(6, 5, 6, 5));
            badge.setAlignmentX(Component.LEFT_ALIGNMENT);

            panel.add(badge);
            panel.add(Box.createVerticalStrut(10));
        }

        private void addBullet(JPanel panel, String text) {
            addText(panel, "\u2022  " + text, 13, false, TEXT);
        }

        private JButton createButton(String text, Color color) {
            JButton button = new JButton(text);
            button.setFont(new Font("SansSerif", Font.BOLD, 13));
            button.setForeground(Color.WHITE);
            button.setBackground(color);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(
                    Cursor.HAND_CURSOR));
            button.setBorder(new EmptyBorder(10, 15, 10, 15));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            return button;
        }

        private String readableStatus(String value) {
            return value.replace('_', ' ');
        }

        private String escapeHtml(String value) {
            return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
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
