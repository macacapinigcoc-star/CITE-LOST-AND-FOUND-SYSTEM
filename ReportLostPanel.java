/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lostandfoundapp.panels;

import java.awt.Image;
import java.io.File;
import javax.swing.JOptionPane;
import lostandfoundapp.MainFraMe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
/**
 *
 * @author Jashryl Ramos
 */
public class ReportLostPanel extends javax.swing.JPanel {
        private String selectedImagePath = "";

//           private String loggedInName;static {
//}
    public ReportLostPanel() {
        initComponents();
        

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        reportType = new javax.swing.JComboBox<>();
        txtDescription = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblImagePreview = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtItemName1 = new javax.swing.JTextField();
        txtIName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmbCategory1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 51));
        contentPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 231));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jLabel3.setText("(optional)");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, -1, -1));

        jLabel4.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("Last Seen at:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Description :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        jLabel6.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 51));
        jLabel6.setText("Category ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (2).png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (1).png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        reportType.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        reportType.setForeground(new java.awt.Color(0, 102, 51));
        reportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Found", "Lost" }));
        reportType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        reportType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                reportTypeItemStateChanged(evt);
            }
        });
        reportType.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                reportTypeInputMethodTextChanged(evt);
            }
        });
        reportType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportTypeActionPerformed(evt);
            }
        });
        jPanel2.add(reportType, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 360, 51));

        txtDescription.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtDescription.setAutoscrolls(false);
        txtDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 360, 70));

        txtDate.setAutoscrolls(false);
        txtDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 410, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (3).png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        txtLocation.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtLocation.setAutoscrolls(false);
        txtLocation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtLocation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocationActionPerformed(evt);
            }
        });
        jPanel2.add(txtLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 410, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (4).png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, -1));

        jButton1.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (5).png"))); // NOI18N
        jButton1.setText("Choose Image");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, 130, 40));

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("SUBMIT REPORT");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 610, 270, 60));

        jButton3.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jButton3.setText("CANCEL REPORT");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 610, 270, 60));

        jLabel12.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 51));
        jLabel12.setText("Item Name");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 70, -1));

        jLabel2.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("My Reports ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 40));

        jLabel9.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 51));
        jLabel9.setText("Date Lost ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        jLabel14.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 51));
        jLabel14.setText("Upload Image :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, -1));

        lblImagePreview.setForeground(new java.awt.Color(0, 102, 51));
        lblImagePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagePreview.setText("IMAGE");
        lblImagePreview.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel2.add(lblImagePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, 250, 140));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setText("Helping lost belongings find their way home.");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 670, -1, 40));

        jLabel15.setBackground(new java.awt.Color(0, 102, 51));
        jLabel15.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 51));
        jLabel15.setText("Identifying Features");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 140, 30));

        txtItemName1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtItemName1.setAutoscrolls(false);
        txtItemName1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtItemName1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtItemName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemName1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtItemName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 360, 50));

        txtIName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtIName.setAutoscrolls(false);
        txtIName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtIName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtIName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtINameActionPerformed(evt);
            }
        });
        jPanel2.add(txtIName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 360, 40));

        jLabel16.setBackground(new java.awt.Color(0, 102, 51));
        jLabel16.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 51));
        jLabel16.setText("Report Type");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 80, 30));

        cmbCategory1.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        cmbCategory1.setForeground(new java.awt.Color(0, 102, 51));
        cmbCategory1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category", "ID or Cards", "Phone or Gadgets", "Bags or Wallet", "Wallet", "Books or Notes", "ID/Documents", "Accessories", "School Supplies", "Keys", "Clothing", "Others" }));
        cmbCategory1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        cmbCategory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategory1ActionPerformed(evt);
            }
        });
        jPanel2.add(cmbCategory1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 360, 51));

        contentPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 710));

        jPanel1.setBackground(new java.awt.Color(223, 250, 223));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/BB.png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 140, 170));

        jLabel13.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 51));
        jLabel13.setText("A STORY");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        jLabel19.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 51));
        jLabel19.setText("EVERY ITEM HAS ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 250, -1));

        contentPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 260, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportTypeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter =
        new FileNameExtensionFilter(
            "Image Files", "jpg", "jpeg", "png", "gif"
        );

        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();

            selectedImagePath = selectedFile.getAbsolutePath();

            ImageIcon icon = new ImageIcon(selectedImagePath);

            Image image = icon.getImage().getScaledInstance(
                lblImagePreview.getWidth(),
                lblImagePreview.getHeight(),
                Image.SCALE_SMOOTH
            );

            lblImagePreview.setIcon(new ImageIcon(image));

            lblImagePreview.setText("");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

//        if (txtStudentId.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(
//                this,
//                "Please enter the studentid."
//            );
//            txtStudentId.requestFocus();
//            return;
//        }

        if (reportType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a category."
            );
            reportType.requestFocus();
            return;
        }

        if (txtDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter the description."
            );
            txtDescription.requestFocus();
            return;
        }

        if (txtLocation.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter where the item was lost."
            );
            txtLocation.requestFocus();
            return;
        }

        if (txtDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter the date lost."
            );
            txtDate.requestFocus();
            return;
        }

        int bntconfirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to report this lost item?",
            "Confirm Report",
            JOptionPane.YES_NO_OPTION
        );

        if (bntconfirm == JOptionPane.YES_OPTION) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/lostandfound_app";
                String user = "root";
                String pass = "";

                try (Connection con = DriverManager.getConnection(url, user, pass)) {
                    String sql = "INSERT INTO lost_items "
                    + "(user_id, category_id, item_name, description, where_lost, date_lost, image, name, student_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,)";

                    try (PreparedStatement pst = con.prepareStatement(sql)) {

                        int userId = 9;
                        int categoryId = reportType.getSelectedIndex();

                        pst.setInt(1, userId);
                        pst.setInt(2, categoryId);
                       // pst.setString(3, txtStudentId.getText().trim());
                        pst.setString(4, txtDescription.getText().trim());
                        pst.setString(5, txtLocation.getText().trim());
                        pst.setString(6, txtDate.getText().trim());
                        pst.setString(7, selectedImagePath);

                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(
                            this,
                            "Lost item reported successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }

            } catch (ClassNotFoundException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "MySQL Driver not found!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Database Error: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtItemName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemName1ActionPerformed

    private void txtINameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtINameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtINameActionPerformed

    private void cmbCategory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategory1ActionPerformed

    private void reportTypeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_reportTypeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_reportTypeInputMethodTextChanged

    private void reportTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_reportTypeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_reportTypeItemStateChanged

    private void txtLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbCategory1;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImagePreview;
    private javax.swing.JComboBox<String> reportType;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtIName;
    private javax.swing.JTextField txtItemName1;
    private javax.swing.JTextField txtLocation;
    // End of variables declaration//GEN-END:variables
}
