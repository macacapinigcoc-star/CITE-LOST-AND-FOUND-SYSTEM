/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lostandfoundapp;

import java.awt.Component;
import javax.swing.JPanel;
import java.awt.HeadlessException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lostandfoundapp.panels.HomePagePanel;
import lostandfoundapp.panels.ReportFoundPanel;
import lostandfoundapp.panels.ReportLostPanel;
import lostandfoundapp.panels.ViewItemsPanel;
import lostandfoundapp.dashboard.components.SummaryCard;


public class MainFraMe extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFraMe.class.getName());

    public MainFraMe() {
        initComponents();
        
    }
    private String loggedInName;

    public MainFraMe(String username) {
        
        initComponents();

     

    setLocationRelativeTo(null);

    showPanel(new HomePagePanel());

    //.setText("WELCOME " + username.toUpperCase());

    }

    private void showPanel(javax.swing.JPanel panel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new java.awt.BorderLayout());
        contentPanel.add(panel, java.awt.BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        homeTab6 = new javax.swing.JButton();
        profileTab6 = new javax.swing.JButton();
        foundTab6 = new javax.swing.JButton();
        lossTab6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        itemTab6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 51));
        setMinimumSize(new java.awt.Dimension(1450, 780));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setForeground(new java.awt.Color(0, 102, 51));

        lblWelcome.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(0, 102, 51));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(lblWelcome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(1509, 1509, 1509)
                .addComponent(lblWelcome))
        );

        jPanel1.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 1370, 750));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(153, 255, 204));

        homeTab6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        homeTab6.setForeground(new java.awt.Color(0, 102, 51));
        homeTab6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/00.png"))); // NOI18N
        homeTab6.setText("HOME");
        homeTab6.setBorder(null);
        homeTab6.setOpaque(true);
        homeTab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeTab6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeTab6MouseExited(evt);
            }
        });
        homeTab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeTab4homeTab1ActionPerformed(evt);
            }
        });

        profileTab6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        profileTab6.setForeground(new java.awt.Color(0, 102, 51));
        profileTab6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/4.png"))); // NOI18N
        profileTab6.setText("PROFILE");
        profileTab6.setBorder(null);
        profileTab6.setOpaque(true);
        profileTab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileTab4profileTab1ActionPerformed(evt);
            }
        });

        foundTab6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        foundTab6.setForeground(new java.awt.Color(0, 102, 51));
        foundTab6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/asa1.png"))); // NOI18N
        foundTab6.setText("REPORT ITEM");
        foundTab6.setBorder(null);
        foundTab6.setOpaque(true);
        foundTab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foundTab4foundTab1ActionPerformed(evt);
            }
        });

        lossTab6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        lossTab6.setForeground(new java.awt.Color(0, 102, 51));
        lossTab6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/3.png"))); // NOI18N
        lossTab6.setText("MY REPORT");
        lossTab6.setBorder(null);
        lossTab6.setOpaque(true);
        lossTab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lossTab4lossTab1ActionPerformed(evt);
            }
        });

        itemTab6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        itemTab6.setForeground(new java.awt.Color(0, 102, 51));
        itemTab6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/ddddddw.png"))); // NOI18N
        itemTab6.setText("MATCHES");
        itemTab6.setBorder(null);
        itemTab6.setOpaque(true);
        itemTab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTab4itemTab1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(homeTab6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(profileTab6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(foundTab6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(lossTab6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(itemTab6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeTab6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileTab6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(foundTab6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lossTab6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemTab6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel6)
                .addGap(0, 354, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 200, 740));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SYSTEM");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 130, 40));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("FIND  .  RETURN  . REUNITE");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CITE LOST & FOUND ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton19.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 102, 51));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/1.png"))); // NOI18N
        jButton19.setText("LOGOUT");
        jButton19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 2, true));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 40, 160, 43));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/BGGG.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1570, 110));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1660, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13jButton8ActionPerformed
{                                                  
    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to log out?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
    );

    if (confirm == JOptionPane.YES_OPTION) {
       
        java.awt.Window window =
                javax.swing.SwingUtilities.getWindowAncestor(this);

        if (window != null) {
            window.dispose();
        }

        login login = new login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
            }  
        }
    }//GEN-LAST:event_jButton13jButton8ActionPerformed
    
    private void itemTab4itemTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTab4itemTab1ActionPerformed
        showPanel(new MyMatchesPage());

       
    }//GEN-LAST:event_itemTab4itemTab1ActionPerformed

    private void lossTab4lossTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lossTab4lossTab1ActionPerformed
    showPanel(new myReports());
    }//GEN-LAST:event_lossTab4lossTab1ActionPerformed

    private void foundTab4foundTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foundTab4foundTab1ActionPerformed
        showPanel(new ReportFoundPanel());
    }//GEN-LAST:event_foundTab4foundTab1ActionPerformed

    private void profileTab4profileTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileTab4profileTab1ActionPerformed
    showPanel(new ProfilePagePanel());
    }//GEN-LAST:event_profileTab4profileTab1ActionPerformed

    private void homeTab4homeTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeTab4homeTab1ActionPerformed

        showPanel(new HomePagePanel());
    }//GEN-LAST:event_homeTab4homeTab1ActionPerformed

    private void homeTab6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTab6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_homeTab6MouseExited

    private void homeTab6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTab6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_homeTab6MouseEntered

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
        java.awt.EventQueue.invokeLater(() -> new MainFraMe().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton foundTab6;
    private javax.swing.JButton homeTab6;
    private javax.swing.JButton itemTab6;
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JButton lossTab6;
    private javax.swing.JButton profileTab6;
    // End of variables declaration//GEN-END:variables
}
