/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lostandfoundapp;

/**
 *
 * @author Jashryl Ramos
 */
public class FoundItem extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FoundItem.class.getName());

    /**
     * Creates new form FoundItem
     */
    public FoundItem() {
        initComponents();
         scrollPaneRecentReports.setViewportView(panelRecentReports);

    scrollPaneRecentReports.setVerticalScrollBarPolicy(
        javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
    );

    scrollPaneRecentReports.setHorizontalScrollBarPolicy(
        javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    panelRecentReports.setLayout(
        new javax.swing.BoxLayout(
            panelRecentReports,
            javax.swing.BoxLayout.Y_AXIS
        )
    );
    
    loadRecentReports();
    }
    
private void loadRecentReports() {

    panelRecentReports.removeAll();

    panelRecentReports.setLayout(
        new javax.swing.BoxLayout(
            panelRecentReports,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url =
            "jdbc:mysql://localhost:3306/lostandfound_app";

        String sql =
            "SELECT item_name, description, image, date_found " +
            "FROM found_items " +
            "ORDER BY date_found DESC";

        try (
            java.sql.Connection con =
                java.sql.DriverManager.getConnection(
                    url, "root", ""
                );

            java.sql.PreparedStatement pst =
                con.prepareStatement(sql);

            java.sql.ResultSet rs =
                pst.executeQuery()
        ) {

            while (rs.next()) {

                String itemName = rs.getString("item_name");
                String description = rs.getString("description");
                String imagePath = rs.getString("image");
                String reportDate = rs.getString("date_found");

                javax.swing.JPanel card =
                    new javax.swing.JPanel(
                        new java.awt.BorderLayout(15, 10)
                    );

                card.setBackground(java.awt.Color.WHITE);

                card.setBorder(
                    javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(
                            new java.awt.Color(210, 220, 210)
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(
                            8, 8, 8, 8
                        )
                    )
                );

                card.setPreferredSize(
                    new java.awt.Dimension(300, 140)
                );

                card.setMaximumSize(
                    new java.awt.Dimension(
                        Integer.MAX_VALUE, 140
                    )
                );

                card.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                // Item image
                javax.swing.JLabel lblImage =
                    new javax.swing.JLabel();

                lblImage.setPreferredSize(
                    new java.awt.Dimension(120, 120)
                );

                lblImage.setHorizontalAlignment(
                    javax.swing.JLabel.CENTER
                );

                if (imagePath != null &&
                    !imagePath.trim().isEmpty()) {

                    java.io.File imageFile =
                        new java.io.File(imagePath.trim());

                    if (imageFile.isFile()) {

                        javax.swing.ImageIcon icon =
                            new javax.swing.ImageIcon(
                                imageFile.getAbsolutePath()
                            );

                        java.awt.Image scaledImage =
                            icon.getImage().getScaledInstance(
                                120, 120,
                                java.awt.Image.SCALE_SMOOTH
                            );

                        lblImage.setIcon(
                            new javax.swing.ImageIcon(scaledImage)
                        );

                    } else {
                        lblImage.setText("No Image");
                    }

                } else {
                    lblImage.setText("No Image");
                }

                card.add(
                    lblImage,
                    java.awt.BorderLayout.WEST
                );

                // Item information
                javax.swing.JPanel textPanel =
                    new javax.swing.JPanel();

                textPanel.setOpaque(false);

                textPanel.setLayout(
                    new javax.swing.BoxLayout(
                        textPanel,
                        javax.swing.BoxLayout.Y_AXIS
                    )
                );

                javax.swing.JLabel lblName =
                    new javax.swing.JLabel(
                        itemName == null ? "" : itemName
                    );

                lblName.setFont(
                    new java.awt.Font(
                        "Gloucester MT Extra Condensed",
                        java.awt.Font.BOLD,
                        20
                    )
                );

                javax.swing.JLabel lblDescription =
                    new javax.swing.JLabel(
                        "<html><body style='width:180px'>"
                        + (description == null ? "" : description)
                        + "</body></html>"
                    );

                javax.swing.JLabel lblDate =
                    new javax.swing.JLabel(
                        "Date Found: " +
                        (reportDate == null ? "N/A" : reportDate)
                    );

                lblDate.setFont(
                    new java.awt.Font(
                        "Arial",
                        java.awt.Font.PLAIN,
                        11
                    )
                );

                javax.swing.JLabel lblType =
                    new javax.swing.JLabel("FOUND ITEM");

                lblType.setForeground(
                    new java.awt.Color(30, 140, 80)
                );

                textPanel.add(lblName);
                textPanel.add(
                    javax.swing.Box.createVerticalStrut(4)
                );
                textPanel.add(lblDescription);
                textPanel.add(
                    javax.swing.Box.createVerticalStrut(4)
                );
                textPanel.add(lblDate);
                textPanel.add(lblType);

                card.add(
                    textPanel,
                    java.awt.BorderLayout.CENTER
                );

                panelRecentReports.add(card);

                panelRecentReports.add(
                    javax.swing.Box.createVerticalStrut(8)
                );
            }
        }

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Unable to load found items: " + e.getMessage()
        );

        e.printStackTrace();
    }

    panelRecentReports.revalidate();
    panelRecentReports.repaint();

    scrollPaneRecentReports.revalidate();
    scrollPaneRecentReports.repaint();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        parentRecentPanel = new javax.swing.JPanel();
        scrollPaneRecentReports = new javax.swing.JScrollPane();
        panelRecentReports = new javax.swing.JPanel();
        lblItemImage = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        lblItemStatus = new javax.swing.JLabel();
        lblItemLocation = new javax.swing.JLabel();
        lblItemDate = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        parentRecentPanel.setBackground(new java.awt.Color(255, 255, 236));
        parentRecentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneRecentReports.setBackground(new java.awt.Color(0, 102, 51));
        scrollPaneRecentReports.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRecentReports.setPreferredSize(new java.awt.Dimension(700, 450));

        panelRecentReports.setBackground(new java.awt.Color(255, 255, 255));

        lblItemImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblItemImage.setText("Item Photo");
        lblItemImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblItemName.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 36)); // NOI18N
        lblItemName.setText("jLabel6");

        lblItemStatus.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        lblItemStatus.setText("jLabel6");

        lblItemLocation.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        lblItemLocation.setText("jLabel6");

        lblItemDate.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        lblItemDate.setText("jLabel6");

        lblType.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        lblType.setText("jLabel1");

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRecentReportsLayout = new javax.swing.GroupLayout(panelRecentReports);
        panelRecentReports.setLayout(panelRecentReportsLayout);
        panelRecentReportsLayout.setHorizontalGroup(
            panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecentReportsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lblItemName)
                .addGap(58, 58, 58)
                .addComponent(lblType)
                .addGap(69, 69, 69)
                .addComponent(lblItemLocation)
                .addGap(75, 75, 75)
                .addComponent(lblItemDate)
                .addGap(78, 78, 78)
                .addComponent(lblItemStatus)
                .addGap(81, 81, 81)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        panelRecentReportsLayout.setVerticalGroup(
            panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecentReportsLayout.createSequentialGroup()
                .addGroup(panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRecentReportsLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblType)
                            .addComponent(lblItemLocation)
                            .addComponent(lblItemDate)
                            .addComponent(lblItemStatus)))
                    .addGroup(panelRecentReportsLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblItemName)))
                    .addGroup(panelRecentReportsLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(365, Short.MAX_VALUE))
        );

        scrollPaneRecentReports.setViewportView(panelRecentReports);

        parentRecentPanel.add(scrollPaneRecentReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 1350, 560));

        jPanel1.add(parentRecentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1560, 640));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 548, 47));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel4.setText("Lost Items");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 160, 50));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Types" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 120, 220, 47));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Types" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 120, 230, 47));

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("+  ADD LOST ITEM");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 30, 230, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FoundItem().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblItemDate;
    private javax.swing.JLabel lblItemImage;
    private javax.swing.JLabel lblItemLocation;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemStatus;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel panelRecentReports;
    private javax.swing.JPanel parentRecentPanel;
    private javax.swing.JScrollPane scrollPaneRecentReports;
    // End of variables declaration//GEN-END:variables
}
