/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lostandfoundapp;
import javax.swing.*;



/**
 *
 * @author Jashryl Ramos
 */
public class myMatches extends javax.swing.JPanel {

    /**
     * Creates new form myMatches
     */
    public myMatches() {
        initComponents();

        jScrollPane1.setViewportView(jpanel);

        jScrollPane1.setVerticalScrollBarPolicy(
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane1.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        loadMyMatches();

        // Keep this if contentPanel is intended
        // for showing a separate response screen.
        // Do not add contentPanel inside jpanel.
    }

    private void showPanel(javax.swing.JPanel panel) {
        contentPanel.removeAll();

        contentPanel.setLayout(
            new java.awt.BorderLayout()
        );

        contentPanel.add(
            panel,
            java.awt.BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }
       
       private void loadMyMatches() {

          try {
        int userId = lostandfoundapp.Session.getUserId();

        if (userId <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "No logged-in user found.",
                "My Matches",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String response =
            lostandfoundapp.apis.apis.getItemMatches(userId);

        org.json.JSONArray matches;
        String trimmed = response.trim();

        if (trimmed.startsWith("[")) {
            matches = new org.json.JSONArray(response);
        } else {
            org.json.JSONObject result =
                new org.json.JSONObject(response);

            if (result.opt("data") instanceof org.json.JSONArray) {
                matches = result.getJSONArray("data");
            } else if (result.opt("item_matches")
                    instanceof org.json.JSONArray) {
                matches = result.getJSONArray("item_matches");
            } else {
                matches = new org.json.JSONArray();
            }
        }

        // Configure the list panel
        jpanel.removeAll();

        jpanel.setLayout(
            new javax.swing.BoxLayout(
                jpanel,
                javax.swing.BoxLayout.Y_AXIS
            )
        );

        jpanel.setBackground(
            new java.awt.Color(248, 248, 218)
        );

        // Keep the list at the top
        jpanel.setAlignmentX(
            java.awt.Component.LEFT_ALIGNMENT
        );

        if (matches.length() == 0) {

            javax.swing.JLabel emptyLabel =
                new javax.swing.JLabel("No matches found yet.");

            emptyLabel.setFont(
                new java.awt.Font(
                    "Arial",
                    java.awt.Font.PLAIN,
                    18
                )
            );

            emptyLabel.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(
                    20, 20, 20, 20
                )
            );

            emptyLabel.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            jpanel.add(emptyLabel);

        } else {

            // Create one card for every match
            for (int i = 0; i < matches.length(); i++) {

                org.json.JSONObject match =
                    matches.getJSONObject(i);

//                org.json.JSONObject lostItem =
//                    match.optJSONObject("lost_item");
//
//                org.json.JSONObject foundItem =
//                    match.optJSONObject("found_item");

                org.json.JSONObject item =
                    match.optJSONObject("item");
                
                int matchId = match.optInt("id", 0);

                respondPanel responsePanel = new respondPanel(matchId);

                responsePanel.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                jpanel.add(responsePanel);

                if (item == null) {
                        item = match;
                    }

                String itemName = item.optString(
                    "name",
                    item.optString("item_name", "Matched Item")
                );

                String location = item.optString(
                    "location",
                    item.optString(
                        "where_lost",
                        item.optString(
                            "where_found",
                            "Location unavailable"
                        )
                    )
                );

                String date = item.optString(
                    "incident_date",
                    item.optString(
                        "created_at",
                        "Date unavailable"
                    )
                );

                String status = match.optString(
                    "status",
                    "Match found"
                );

                // Card panel
                javax.swing.JPanel card =
                    new javax.swing.JPanel(
                        new java.awt.BorderLayout(15, 8)
                    );

                card.setBackground(java.awt.Color.WHITE);

                card.setBorder(
                    javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(
                            new java.awt.Color(190, 210, 190)
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(
                            15, 15, 15, 15
                        )
                    )
                );

                card.setPreferredSize(
                    new java.awt.Dimension(900, 130)
                );

                card.setMaximumSize(
                    new java.awt.Dimension(
                        Integer.MAX_VALUE,
                        130
                    )
                );

                card.setMinimumSize(
                    new java.awt.Dimension(0, 130)
                );

                card.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                // Photo placeholder
                javax.swing.JLabel photo =
                    new javax.swing.JLabel("PHOTO");

                photo.setHorizontalAlignment(
                    javax.swing.SwingConstants.CENTER
                );

                photo.setPreferredSize(
                    new java.awt.Dimension(100, 100)
                );

                photo.setBorder(
                    javax.swing.BorderFactory.createLineBorder(
                        java.awt.Color.GRAY
                    )
                );

                card.add(photo, java.awt.BorderLayout.WEST);

                // Item details
                javax.swing.JPanel details =
                    new javax.swing.JPanel();

                details.setOpaque(false);

                details.setLayout(
                    new javax.swing.BoxLayout(
                        details,
                        javax.swing.BoxLayout.Y_AXIS
                    )
                );

                javax.swing.JLabel nameLabel =
                    new javax.swing.JLabel(itemName);

                nameLabel.setFont(
                    new java.awt.Font(
                        "Arial",
                        java.awt.Font.BOLD,
                        20
                    )
                );

                javax.swing.JLabel locationLabel =
                    new javax.swing.JLabel(
                        "Location: " + location
                    );

                javax.swing.JLabel dateLabel =
                    new javax.swing.JLabel(
                        "Date: " + date
                    );

                javax.swing.JLabel statusLabel =
                    new javax.swing.JLabel(
                        "Status: " + status
                    );

                statusLabel.setForeground(
                    new java.awt.Color(0, 102, 51)
                );

                details.add(nameLabel);
                details.add(
                    javax.swing.Box.createVerticalStrut(8)
                );
                details.add(locationLabel);
                details.add(
                    javax.swing.Box.createVerticalStrut(5)
                );
                details.add(dateLabel);
                details.add(
                    javax.swing.Box.createVerticalStrut(5)
                );
                details.add(statusLabel);

                card.add(
                    details,
                    java.awt.BorderLayout.CENTER
                );

                // Add card to the vertical list
               jpanel.add(card);

                // Response panel below this match card
//                respondPanel responsePanel = new respondPanel();

                responsePanel.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                jpanel.add(responsePanel);

                jpanel.add(
                    javax.swing.Box.createVerticalStrut(15)
                );
            }
        }

        // Configure scrolling
        jScrollPane1.setViewportView(jpanel);

        jScrollPane1.setVerticalScrollBarPolicy(
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        jScrollPane1.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        jpanel.revalidate();
        jpanel.repaint();

        jScrollPane1.revalidate();
        jScrollPane1.repaint();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Failed to load matches:\n" + e.getMessage(),
                "My Matches",
                JOptionPane.ERROR_MESSAGE
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

        jpaanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpanel = new javax.swing.JPanel();
        lblPhoto = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        lblItemLocation = new javax.swing.JLabel();
        lblItemDate = new javax.swing.JLabel();
        lblItemStatus = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();

        jpaanel.setBackground(new java.awt.Color(248, 248, 218));
        jpaanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(0, 102, 51));

        jpanel.setBackground(new java.awt.Color(255, 255, 255));
        jpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoto.setText("PHOTO");
        lblPhoto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jpanel.add(lblPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 190, 190));

        lblItemName.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 36)); // NOI18N
        lblItemName.setText("ITEM NAME");
        jpanel.add(lblItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 130, 40));

        lblItemLocation.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 18)); // NOI18N
        lblItemLocation.setText("jLabel6");
        jpanel.add(lblItemLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));

        lblItemDate.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 18)); // NOI18N
        lblItemDate.setText("jLabel6");
        jpanel.add(lblItemDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        lblItemStatus.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        lblItemStatus.setText("jLabel6");
        jpanel.add(lblItemStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpanel.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 640, 180));

        jScrollPane1.setViewportView(jpanel);

        jpaanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 1210, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jpaanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1329, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpaanel, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpaanel;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblItemDate;
    private javax.swing.JLabel lblItemLocation;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemStatus;
    private javax.swing.JLabel lblPhoto;
    // End of variables declaration//GEN-END:variables
}
