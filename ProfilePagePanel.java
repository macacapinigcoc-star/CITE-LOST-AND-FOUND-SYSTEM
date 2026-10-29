package lostandfoundapp;

import lostandfoundapp.apis.apis; // Change if your API class is in another package.

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class ProfilePagePanel extends JPanel {

    private final Color GREEN = new Color(0, 102, 51);
    private final Color DARK_GREEN = new Color(0, 75, 38);
    private final Color BACKGROUND = new Color(242, 248, 242);
    private final Color CREAM = new Color(246, 246, 214);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(55, 65, 55);

    private boolean isEditing = false;

    private String originalName;
    private String avatarInitial;

    private String originalEmail;
    private String originalStudentId;
    private String originalYearLevel;
    private String originalContactNo;

    private JLabel lblFullName;
    private JLabel lblNameValue;
    private JLabel lblEmailValue;
    private JLabel lblStudentIdValue;
    private JLabel lblYearLevelValue;
    private JLabel lblContactNoValue;

    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtStudentId;
    private JTextField txtYearLevel;
    private JTextField txtContactNo;

    private JButton btnEdit;
    private JButton btnCancel;

    private JPanel detailsPanel;
    private CardLayout detailsLayout;

    public ProfilePagePanel(){
        initializeUI();

        if (Session.isLoggedIn()) {
            loadSessionData();
            showViewMode();
        } else {
            setFormEnabled(false);
            JOptionPane.showMessageDialog(
                this,
                "No active session. Please log in again.",
                "Session Error",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =====================================================
    // CUSTOM UI
    // =====================================================

    private void initializeUI() {

        setLayout(new BorderLayout());
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(25, 30, 25, 30));

        // Main heading.
        JLabel lblTitle = new JLabel("MY PROFILE");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblTitle.setForeground(GREEN);

        JLabel lblSubtitle = new JLabel(
            "Manage your personal information"
        );
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(100, 110, 100));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(
            new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );
        titlePanel.add(lblTitle);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(lblSubtitle);

        add(titlePanel, BorderLayout.NORTH);

        // Center card.
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel profileCard = new JPanel(new BorderLayout(0, 20));
        profileCard.setBackground(CREAM);
        profileCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(220, 225, 200), 1
            ),
            new EmptyBorder(25, 30, 25, 30)
        ));

        profileCard.setPreferredSize(new Dimension(620, 570));

        // Profile header.
        JPanel profileHeader = new JPanel(new FlowLayout(
            FlowLayout.LEFT, 18, 0
        ));
        profileHeader.setOpaque(false);
        
        String name = Session.getName();
        
        JLabel avatar = new JLabel(String.valueOf((name != null && !name.trim().isEmpty())
                ? name.trim().charAt(0)
                : 'U'), SwingConstants.CENTER);
        avatar.setFont(new Font("SansSerif", Font.BOLD, 30));
        avatar.setForeground(WHITE);
        avatar.setOpaque(true);
        avatar.setBackground(GREEN);
        avatar.setPreferredSize(new Dimension(75, 75));

        lblFullName = new JLabel("User");
        lblFullName.setFont(
            new Font("SansSerif", Font.BOLD, 23)
        );
        lblFullName.setForeground(DARK_GREEN);

        JLabel lblAccountType = new JLabel("Student Account");
        lblAccountType.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );
        lblAccountType.setForeground(new Color(100, 110, 90));

        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.setLayout(
            new BoxLayout(namePanel, BoxLayout.Y_AXIS)
        );
        namePanel.add(lblFullName);
        namePanel.add(Box.createVerticalStrut(5));
        namePanel.add(lblAccountType);

        profileHeader.add(avatar);
        profileHeader.add(namePanel);

        profileCard.add(profileHeader, BorderLayout.NORTH);

        // Details: switch between view and edit.
        detailsLayout = new CardLayout();
        detailsPanel = new JPanel(detailsLayout);
        detailsPanel.setOpaque(false);

        detailsPanel.add(createViewPanel(), "VIEW");
        detailsPanel.add(createEditPanel(), "EDIT");

        profileCard.add(detailsPanel, BorderLayout.CENTER);

        // Buttons.
        btnEdit = createButton("Edit Profile", GREEN);
        btnCancel = createButton("Cancel", new Color(100, 100, 100));

        btnCancel.setVisible(false);

        btnEdit.addActionListener(e -> {
            if (isEditing) {
                saveProfileChanges();
            } else {
                startEditing();
            }
        });

        btnCancel.addActionListener(e -> cancelEditing());

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 10, 0)
        );
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnEdit);

        profileCard.add(buttonPanel, BorderLayout.SOUTH);

        centerWrapper.add(profileCard);
        add(centerWrapper, BorderLayout.CENTER);
    }

    private JPanel createViewPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        lblNameValue = createValueLabel();
        lblEmailValue = createValueLabel();
        lblStudentIdValue = createValueLabel();
        lblYearLevelValue = createValueLabel();
        lblContactNoValue = createValueLabel();

        addDetailRow(panel, "Full Name", lblNameValue, 0);
        addDetailRow(panel, "Email Address", lblEmailValue, 1);
        addDetailRow(panel, "Student ID", lblStudentIdValue, 2);
        addDetailRow(panel, "Year Level", lblYearLevelValue, 3);
        addDetailRow(panel, "Contact Number", lblContactNoValue, 4);

        return panel;
    }

    private JPanel createEditPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        txtName = createTextField();
        txtEmail = createTextField();
        txtStudentId = createTextField();
        txtYearLevel = createTextField();
        txtContactNo = createTextField();

        addDetailRow(panel, "Full Name", txtName, 0);
        addDetailRow(panel, "Email Address", txtEmail, 1);
        addDetailRow(panel, "Student ID", txtStudentId, 2);
        addDetailRow(panel, "Year Level", txtYearLevel, 3);
        addDetailRow(panel, "Contact Number", txtContactNo, 4);

        return panel;
    }

    private void addDetailRow(
            JPanel panel,
            String labelText,
            JComponent valueComponent,
            int row) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = row;
        gbc.insets = new Insets(9, 0, 9, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(GREEN);

        gbc.gridx = 0;
        gbc.weightx = 0.35;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        gbc.insets = new Insets(9, 0, 9, 0);

        valueComponent.setPreferredSize(new Dimension(260, 38));
        panel.add(valueComponent, gbc);
    }

    private JLabel createValueLabel() {

        JLabel label = new JLabel("—");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(TEXT);
        label.setOpaque(true);
        label.setBackground(WHITE);
        label.setBorder(new EmptyBorder(8, 10, 8, 10));

        return label;
    }

    private JTextField createTextField() {

        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(190, 205, 190)
            ),
            new EmptyBorder(5, 9, 5, 9)
        ));

        return field;
    }

    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // =====================================================
    // SESSION DATA
    // =====================================================

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private void loadSessionData() {

        String name = safe(Session.getName());
        String email = safe(Session.getEmail());
        String studentId = safe(Session.getStudentId());
        String yearLevel = safe(Session.getYearLevel());
        String contactNo = safe(Session.getContactNo());

        lblFullName.setText(name.isEmpty() ? "User" : name);

        lblNameValue.setText(name);
        lblEmailValue.setText(email);
        lblStudentIdValue.setText(studentId);
        lblYearLevelValue.setText(yearLevel);
        lblContactNoValue.setText(contactNo);

        txtName.setText(name);
        txtEmail.setText(email);
        txtStudentId.setText(studentId);
        txtYearLevel.setText(yearLevel);
        txtContactNo.setText(contactNo);
    }

    // =====================================================
    // VIEW / EDIT MODE
    // =====================================================

    private void showViewMode() {

        isEditing = false;
        detailsLayout.show(detailsPanel, "VIEW");

        btnEdit.setText("Edit Profile");
        btnCancel.setVisible(false);

        revalidate();
        repaint();
    }

    private void showEditMode() {

        isEditing = true;
        detailsLayout.show(detailsPanel, "EDIT");

        btnEdit.setText("Save Changes");
        btnCancel.setVisible(true);

        revalidate();
        repaint();
    }

    private void startEditing() {

        if (!Session.isLoggedIn()) {
            showSessionError();
            return;
        }

        loadSessionData();

        originalName = txtName.getText();
        originalEmail = txtEmail.getText();
        originalStudentId = txtStudentId.getText();
        originalYearLevel = txtYearLevel.getText();
        originalContactNo = txtContactNo.getText();

        showEditMode();
    }

    private void cancelEditing() {

        txtName.setText(originalName);
        txtEmail.setText(originalEmail);
        txtStudentId.setText(originalStudentId);
        txtYearLevel.setText(originalYearLevel);
        txtContactNo.setText(originalContactNo);

        loadSessionData();
        showViewMode();
    }

    // =====================================================
    // UPDATE PROFILE THROUGH API
    // =====================================================

    private void saveProfileChanges() {

        if (!Session.isLoggedIn() || Session.getUserId() <= 0) {
            showSessionError();
            return;
        }

        String newName = txtName.getText().trim();
        String newEmail = txtEmail.getText().trim();
        String newStudentId = txtStudentId.getText().trim();
        String newYearLevel = txtYearLevel.getText().trim();
        String newContactNo = txtContactNo.getText().trim();

        if (newName.isEmpty()
                || newEmail.isEmpty()
                || newStudentId.isEmpty()
                || newYearLevel.isEmpty()
                || newContactNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please fill in all fields.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!newEmail.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid email address.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JSONObject data = new JSONObject();
        data.put("name", newName);
        data.put("email", newEmail);
        data.put("student_id", newStudentId);
        data.put("year_level", newYearLevel);
        data.put("contact_no", newContactNo);

        btnEdit.setEnabled(false);
        btnCancel.setEnabled(false);

        try {
            int userId = Session.getUserId();

            // Replace ApiClient with your actual API class.
            apis.updateUser(userId, data);

            // Update local session only after the request succeeds.
            Session.updateProfile(
                newName,
                newEmail,
                newStudentId,
                newYearLevel,
                newContactNo
            );

            loadSessionData();
            showViewMode();

            JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException | RuntimeException e) {

            JOptionPane.showMessageDialog(
                this,
                "Unable to update profile: " + e.getMessage(),
                "Update Error",
                JOptionPane.ERROR_MESSAGE
            );

        } finally {
            btnEdit.setEnabled(true);
            btnCancel.setEnabled(true);
        }
    }

    private void setFormEnabled(boolean enabled) {
        btnEdit.setEnabled(enabled);
        btnCancel.setEnabled(enabled);
    }

    private void showSessionError() {

        JOptionPane.showMessageDialog(
            this,
            "Your session has expired. Please log in again.",
            "Session Error",
            JOptionPane.ERROR_MESSAGE
        );
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
