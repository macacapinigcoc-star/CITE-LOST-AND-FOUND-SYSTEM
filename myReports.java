/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lostandfoundapp;

/**
 *
 * @author Jashryl Ramos
 */
public class myReports extends javax.swing.JPanel {

    /**
     * Creates new form myReports
     */
    public myReports() {
        initComponents();
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

        // Get the currently logged-in user's ID
        int userId = lostandfoundapp.Session.getUserId();

        // GET /api/items?user_id={userId}
        String response =
            lostandfoundapp.apis.apis.getItems(userId);

        org.json.JSONArray items;

        // Handle Laravel response formats
        if (response.trim().startsWith("[")) {

            items = new org.json.JSONArray(response);

        } else {

            org.json.JSONObject result =
                new org.json.JSONObject(response);

            if (result.opt("data") instanceof org.json.JSONArray) {

                items = result.getJSONArray("data");

            } else if (result.opt("items") instanceof org.json.JSONArray) {

                items = result.getJSONArray("items");

            } else {

                throw new org.json.JSONException(
                    "Items array not found in API response."
                );
            }
        }

        java.util.List<org.json.JSONObject> reports =
            new java.util.ArrayList<>();

        // Keep only reports belonging to the logged-in user.
        // This is an additional UI filter; the backend must
        // also enforce access control.
        for (int i = 0; i < items.length(); i++) {

            org.json.JSONObject item = items.getJSONObject(i);

            if (item.has("user_id")
                    && !item.isNull("user_id")
                    && item.optInt("user_id", -1) == userId) {

                reports.add(item);
            }
        }

        // Sort newest first; incident_date should be YYYY-MM-DD.
        reports.sort((a, b) ->
            b.optString("incident_date", "")
                .compareTo(a.optString("incident_date", ""))
        );

        // Display each report
        for (org.json.JSONObject item : reports) {

            String itemName =
                item.optString("name", "Unnamed Item");

            String description =
                item.optString("description", "");

            String reportType =
                item.optString("type", "UNKNOWN")
                    .toUpperCase();

            String reportDate =
                item.optString("incident_date", "N/A");

            String status =
                item.optString("status", "submitted");
            
            String location =
                item.optString("location", "");

            // These fields depend on what Laravel returns.
            String imagePath =
                item.optString("image_url",
                    item.optString("image", ""));

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
                new java.awt.Dimension(700, 160)
            );

            card.setMinimumSize(
                new java.awt.Dimension(300, 160)
            );
            
            card.setMaximumSize(
                new java.awt.Dimension(
                    Integer.MAX_VALUE, 160
                )
            );

            card.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            // Image preview
            javax.swing.JLabel lblImage =
                new javax.swing.JLabel();

            lblImage.setPreferredSize(
                new java.awt.Dimension(100, 120)
            );

            lblImage.setHorizontalAlignment(
                javax.swing.JLabel.CENTER
            );
            


            if (imagePath != null
                    && !imagePath.trim().isEmpty()
                    && !"null".equalsIgnoreCase(imagePath)) {

                try {

                    java.awt.Image image = null;

                    if (imagePath.startsWith("http://")
                            || imagePath.startsWith("https://")) {

                        image = javax.imageio.ImageIO.read(
                            new java.net.URL(imagePath)
                        );

                    } else {

                        java.io.File imageFile =
                            new java.io.File(imagePath);

                        if (imageFile.isFile()) {
                            image = javax.imageio.ImageIO.read(
                                imageFile
                            );
                        }
                    }

                    if (image != null) {
                        lblImage.setIcon(
                            new javax.swing.ImageIcon(
                                image.getScaledInstance(
                                    100, 120,
                                    java.awt.Image.SCALE_SMOOTH
                                )
                            )
                        );
                    }

                } catch (Exception ex) {
                    System.out.println(
                        "Could not load report image: " + imagePath
                    );
                }
            }

            card.add(lblImage, java.awt.BorderLayout.WEST);

            // Report details
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
                new javax.swing.JLabel(itemName);

            lblName.setFont(
                new java.awt.Font(
                    "Gloucester MT Extra Condensed",
                    java.awt.Font.BOLD,
                    18
                )
            );

            javax.swing.JLabel lblDescription =
                new javax.swing.JLabel(description);

            javax.swing.JLabel lblDate =
                new javax.swing.JLabel("Date: " + reportDate);

            lblDate.setFont(
                new java.awt.Font(
                    "Arial",
                    java.awt.Font.PLAIN,
                    11
                )
            );

            int itemId = item.optInt("id", -1);

            // Report type
            javax.swing.JLabel lblType =
                    new javax.swing.JLabel(reportType);

            lblType.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            lblType.setForeground(
                    "LOST".equals(reportType)
                            ? new java.awt.Color(200, 80, 60)
                            : "FOUND".equals(reportType)
                                    ? new java.awt.Color(30, 140, 80)
                                    : java.awt.Color.DARK_GRAY
            );

        // Report status
        javax.swing.JLabel lblStatus =
                new javax.swing.JLabel(status);

        lblStatus.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        lblStatus.setForeground(java.awt.Color.DARK_GRAY);

        // Location
        javax.swing.JLabel lblLocation =
                new javax.swing.JLabel("Location: " + location);

        lblLocation.setForeground(java.awt.Color.DARK_GRAY);

        // Type on the LEFT, status on the RIGHT
        javax.swing.JPanel typeStatusPanel =
                new javax.swing.JPanel(
                        new java.awt.FlowLayout(
                                java.awt.FlowLayout.LEFT, 10, 0
                        )
                );

        typeStatusPanel.setOpaque(false);
        typeStatusPanel.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
        );

        typeStatusPanel.add(lblType);
        typeStatusPanel.add(lblStatus);

        // Buttons for this specific report
        javax.swing.JButton btnEditReport =
                new javax.swing.JButton("Edit Report");

        javax.swing.JButton btnCancelReport =
                new javax.swing.JButton("Cancel Report");

        // Store the ID belonging to each card
        btnEditReport.putClientProperty("itemId", itemId);
        btnCancelReport.putClientProperty("itemId", itemId);

        // Keep buttons on one row
        javax.swing.JPanel buttonPanel =
                new javax.swing.JPanel(
                        new java.awt.FlowLayout(
                                java.awt.FlowLayout.LEFT, 8, 2
                        )
                );

        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        buttonPanel.add(btnEditReport);
        buttonPanel.add(btnCancelReport);

        // Attach listeners and retrieve the correct ID when clicked
        btnEditReport.addActionListener(e -> {
            int selectedItemId = (Integer)
                    btnEditReport.getClientProperty("itemId");

            editReport(selectedItemId);
        });

        btnCancelReport.addActionListener(e -> {
            int selectedItemId = (Integer)
                    btnCancelReport.getClientProperty("itemId");

            cancelReport(selectedItemId);
        });

        // Explicitly set readable colors for the text labels
        lblName.setForeground(java.awt.Color.BLACK);
        lblDescription.setForeground(java.awt.Color.DARK_GRAY);
        lblDate.setForeground(java.awt.Color.DARK_GRAY);

        // Preserve the existing vertical layout
        textPanel.add(lblName);
        textPanel.add(lblDescription);
        textPanel.add(lblDate);
        textPanel.add(typeStatusPanel);
        textPanel.add(lblLocation);
        textPanel.add(buttonPanel);

        card.add(textPanel, java.awt.BorderLayout.CENTER);

            panelRecentReports.add(card);

            panelRecentReports.add(
                javax.swing.Box.createVerticalStrut(8)
            );
        }

        if (reports.isEmpty()) {

            javax.swing.JLabel lblEmpty =
                new javax.swing.JLabel(
                    "You have not submitted any reports yet."
                );

            lblEmpty.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            panelRecentReports.add(lblEmpty);
        }

    } catch (java.io.IOException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Unable to load your reports:\n" + e.getMessage(),
            "API Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );

    } catch (org.json.JSONException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Invalid items API response:\n" + e.getMessage(),
            "Response Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    panelRecentReports.revalidate();
    panelRecentReports.repaint();

    scrollPaneRecentReports.revalidate();
    scrollPaneRecentReports.repaint();
}
    
    private void editReport(int itemId) {
    System.out.println("Edit Report ID: " + itemId);

    // TODO: Open your edit form using this ID.
}
    
    private void cancelReport(int itemId) {
    System.out.println("Cancel Report ID: " + itemId);

        // TODO: Show a confirmation dialog, then call your API.
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentRecentPanel = new javax.swing.JPanel();
        scrollPaneRecentReports = new javax.swing.JScrollPane();
        panelRecentReports = new javax.swing.JPanel();
        lblItemImage = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        lblItemStatus = new javax.swing.JLabel();
        lblItemLocation = new javax.swing.JLabel();
        lblItemDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        parentRecentPanel.setBackground(new java.awt.Color(255, 255, 236));
        parentRecentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneRecentReports.setBackground(new java.awt.Color(0, 102, 51));
        scrollPaneRecentReports.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRecentReports.setPreferredSize(new java.awt.Dimension(700, 450));

        panelRecentReports.setBackground(new java.awt.Color(0, 102, 51));

        lblItemImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblItemImage.setText("Item Photo");
        lblItemImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblItemName.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 36)); // NOI18N
        lblItemName.setText("jLabel6");

        lblItemStatus.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        lblItemStatus.setText("jLabel6");

        lblItemLocation.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 18)); // NOI18N
        lblItemLocation.setText("jLabel6");

        lblItemDate.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 18)); // NOI18N
        lblItemDate.setText("jLabel6");

        javax.swing.GroupLayout panelRecentReportsLayout = new javax.swing.GroupLayout(panelRecentReports);
        panelRecentReports.setLayout(panelRecentReportsLayout);
        panelRecentReportsLayout.setHorizontalGroup(
            panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecentReportsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblItemName)
                    .addComponent(lblItemLocation)
                    .addComponent(lblItemDate)
                    .addComponent(lblItemStatus))
                .addContainerGap(925, Short.MAX_VALUE))
        );
        panelRecentReportsLayout.setVerticalGroup(
            panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecentReportsLayout.createSequentialGroup()
                .addGroup(panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRecentReportsLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblItemName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItemLocation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItemDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItemStatus))
                    .addGroup(panelRecentReportsLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(415, Short.MAX_VALUE))
        );

        scrollPaneRecentReports.setViewportView(panelRecentReports);

        parentRecentPanel.add(scrollPaneRecentReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1300, 610));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel4.setText("My Reports");
        parentRecentPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 350, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parentRecentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1422, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parentRecentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblItemDate;
    private javax.swing.JLabel lblItemImage;
    private javax.swing.JLabel lblItemLocation;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemStatus;
    private javax.swing.JPanel panelRecentReports;
    private javax.swing.JPanel parentRecentPanel;
    private javax.swing.JScrollPane scrollPaneRecentReports;
    // End of variables declaration//GEN-END:variables
}
