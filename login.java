/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lostandfoundapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.json.JSONObjectl;

/**]\
 *
 * @author Jashryl Ramos
 */
public class login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(login.class.getName());

    
    public login() {
        initComponents();
        getRootPane().setDefaultButton(loginBtn);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        signupbtn = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        signupBtn = new javax.swing.JButton();
        chkShowPassword = new javax.swing.JCheckBox();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 720));

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        signupbtn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        signupbtn.setForeground(new java.awt.Color(0, 102, 51));
        signupbtn.setText("LOGIN");
        jPanel3.add(signupbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jLabel3.setText("Username:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        username.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 14)); // NOI18N
        username.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel3.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 229, 30));

        loginBtn.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(8, 158, 8));
        loginBtn.setText("LOGIN");
        loginBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel3.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 120, -1));

        signupBtn.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 24)); // NOI18N
        signupBtn.setForeground(new java.awt.Color(8, 158, 8));
        signupBtn.setText("SIGNUP");
        signupBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        signupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupBtnActionPerformed(evt);
            }
        });
        jPanel3.add(signupBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 138, 32));

        chkShowPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkShowPassword.setForeground(new java.awt.Color(0, 102, 51));
        chkShowPassword.setText("Show password");
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });
        jPanel3.add(chkShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, 40));

        txtPassword.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 14)); // NOI18N
        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel3.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 229, 30));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Don't have an account yet?");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, 25));
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 44, 37, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 51));
        jLabel11.setText("Password:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, 560, 610));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/adsd.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 1560, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        
        String studentID = username.getText().trim();
       String password = new String(txtPassword.getPassword());

       if (studentID.isEmpty()) {
           JOptionPane.showMessageDialog(
                   this,
                   "Student ID is required!",
                   "Login",
                   JOptionPane.ERROR_MESSAGE
           );
           return;
       }

       if (password.isEmpty()) {
           JOptionPane.showMessageDialog(
                   this,
                   "Password is required!",
                   "Login",
                   JOptionPane.ERROR_MESSAGE
           );
           return;
       }

       try {
           // Send login request through apis.java
           org.json.JSONObject response =
                   lostandfoundapp.apis.apis.login(
                           studentID,
                           password
                   );

//           // Read user information from API response           
           JSONObject user = response.getJSONObject("user");

                  // Save logged-in user
                lostandfoundapp.Session.setUser(
                user.getInt("id"),
                user.optString("name", ""),
                user.optString("email", ""),
                user.optString("student_id", ""),
                user.optString("year_level", ""),
                user.optString("contact_no", "")
            );

 
 

           // Open main application
           MainFraMe main = new MainFraMe(user.optString("name", ""));
           main.setLocationRelativeTo(null);
           main.setVisible(true);

           // Close login window
           this.dispose();

       } catch (java.io.IOException e) {

           JOptionPane.showMessageDialog(
                   this,
                   "Login failed or API connection error:\n"
                           + e.getMessage(),
                   "Login Error",
                   JOptionPane.ERROR_MESSAGE
           );

       } catch (org.json.JSONException e) {

           JOptionPane.showMessageDialog(
                   this,
                   "Unexpected API response. Check the login "
                           + "response format.\n" + e.getMessage(),
                   "Response Error",
                   JOptionPane.ERROR_MESSAGE
           );
       }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void signupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupBtnActionPerformed
signupp signuppform = new signupp();
        signuppform.setLocationRelativeTo(null);
        signuppform.setVisible(true);
        this.dispose();
       
    }//GEN-LAST:event_signupBtnActionPerformed

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed
        if (chkShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_chkShowPasswordActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        loginBtn.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton signupBtn;
    private javax.swing.JLabel signupbtn;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
