/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package lostandfoundapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.function.IntConsumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PendingRespondPanel extends JPanel {

    private final int matchId;

    private JLabel statusLabel;
    private JButton btnInterested;
    private JButton btnDismiss;

    // Callbacks for connecting the buttons to the API later
    private IntConsumer interestedHandler;
    private IntConsumer dismissHandler;

    private final Color green = new Color(0, 102, 51);
    private final Color darkGreen = new Color(0, 77, 38);
    private final Color cream = new Color(248, 248, 218);
    private final Color textColor = new Color(45, 55, 45);
    private final Color mutedColor = new Color(105, 115, 105);

    public PendingRespondPanel(int matchId) {
        this.matchId = matchId;

        buildUI();
        setBackground(new Color(248, 250, 245));
        setBorder(null);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(600, 110));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
    }

    // Keep this constructor for compatibility with older code.
    public PendingRespondPanel() {
        this(0);
    }

    public int getMatchId() {
        return matchId;
    }

    // Set these handlers when connecting to the API.
    public void setInterestedHandler(IntConsumer handler) {
        this.interestedHandler = handler;
    }

    public void setDismissHandler(IntConsumer handler) {
        this.dismissHandler = handler;
    }

    private void buildUI() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 228, 218)
                ),
                new EmptyBorder(15, 18, 15, 18)
            )
        );

        // Heading and description
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(
            new javax.swing.BoxLayout(
                headingPanel,
                javax.swing.BoxLayout.Y_AXIS
            )
        );

        JLabel titleLabel = new JLabel(
            "Is this item yours?"
        );

        titleLabel.setFont(
            new Font("SansSerif", Font.BOLD, 18)
        );

        titleLabel.setForeground(darkGreen);
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel descriptionLabel = new JLabel(
            "Choose an action to respond to this match."
        );

        descriptionLabel.setFont(
            new Font("SansSerif", Font.PLAIN, 12)
        );

        descriptionLabel.setForeground(mutedColor);
        descriptionLabel.setAlignmentX(LEFT_ALIGNMENT);

        headingPanel.add(titleLabel);
        headingPanel.add(javax.swing.Box.createVerticalStrut(5));
        headingPanel.add(descriptionLabel);

        add(headingPanel, BorderLayout.NORTH);

        // Action buttons
        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 10, 12)
        );

        buttonPanel.setOpaque(false);

        btnInterested = new JButton("This Might Be Mine");
        styleButton(btnInterested, green, Color.WHITE);

        btnDismiss = new JButton("Dismiss");
        styleButton(
            btnDismiss,
            new Color(242, 245, 240),
            textColor
        );

        btnInterested.addActionListener(e -> handleInterested());
        btnDismiss.addActionListener(e -> handleDismiss());

        buttonPanel.add(btnInterested);
        buttonPanel.add(btnDismiss);

        add(buttonPanel, BorderLayout.CENTER);

        // Feedback shown after a local action
        statusLabel = new JLabel(" ");
        statusLabel.setFont(
            new Font("SansSerif", Font.PLAIN, 12)
        );
        statusLabel.setForeground(mutedColor);
        statusLabel.setBorder(
            new EmptyBorder(2, 0, 0, 0)
        );

        add(statusLabel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(600, 125));
        setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 140)
        );
    }

    private void styleButton(
        JButton button,
        Color background,
        Color foreground
    ) {
        button.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        button.setMargin(
            new Insets(10, 16, 10, 16)
        );

        button.setPreferredSize(
            new Dimension(190, 40)
        );
    }

    private void handleInterested() {

        if (interestedHandler != null) {
            interestedHandler.accept(matchId);
        } else {
            // Temporary UI feedback until the API is connected
            statusLabel.setText(
                "Interest selected. Server response not submitted yet."
            );
        }
    }

    private void handleDismiss() {

        if (dismissHandler != null) {
            dismissHandler.accept(matchId);
        } else {
            // Temporary UI feedback until the API is connected
            statusLabel.setText(
                "Dismiss selected. Server response not submitted yet."
            );
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
