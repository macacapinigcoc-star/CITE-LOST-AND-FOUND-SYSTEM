/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lostandfoundapp.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class HomePagePanel extends javax.swing.JPanel {

 
public HomePagePanel() {
   initComponents();

    helloGreet.setText(
        "Hello, " + lostandfoundapp.Session.getName()+ "!"
    );

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

        // GET /api/items
        String response = lostandfoundapp.apis.apis.getItems();

        org.json.JSONArray items;

        // Support Laravel responses that return either:
        // {"data": [...]} or a direct JSON array [...]
        if (response.trim().startsWith("[")) {
            items = new org.json.JSONArray(response);
        } else {
            org.json.JSONObject result =
                new org.json.JSONObject(response);

            if (result.has("data")
                    && result.get("data") instanceof org.json.JSONArray) {
                items = result.getJSONArray("data");
            } else if (result.has("items")
                    && result.get("items") instanceof org.json.JSONArray) {
                items = result.getJSONArray("items");
            } else {
                throw new org.json.JSONException(
                    "Could not find an items array in the API response."
                );
            }
        }
        
        int lostCount = 0;
        int foundCount = 0;

        for (int i = 0; i < items.length(); i++) {
            org.json.JSONObject item = items.getJSONObject(i);

            String type = item.optString("type", "");

            if ("lost".equalsIgnoreCase(type)) {
                lostCount++;
            } else if ("found".equalsIgnoreCase(type)) {
                foundCount++;
            }
        }

        lblLostCount.setText(String.valueOf(lostCount));
        lblFoundCount.setText(String.valueOf(foundCount));

        // Sort newest first when dates use YYYY-MM-DD format.
        java.util.List<org.json.JSONObject> reportList =
            new java.util.ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            reportList.add(items.getJSONObject(i));
        }

        reportList.sort((a, b) ->
            b.optString("incident_date", "")
                .compareTo(a.optString("incident_date", ""))
        );

        for (org.json.JSONObject item : reportList) {

            String itemName = item.optString("name", "Unnamed Item");
            String description = item.optString("description", "");
            String reportType = item.optString("type", "UNKNOWN")
                                      .toUpperCase();
            String reportDate = item.optString("incident_date", "N/A");

            // Get image path or URL if supplied by the API.
            String imagePath = item.optString("image", "");

            // Create report card
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
                new java.awt.Dimension(300, 110)
            );

            card.setMaximumSize(
                new java.awt.Dimension(
                    Integer.MAX_VALUE, 110
                )
            );

            card.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            // Image preview
            javax.swing.JLabel lblImage =
                new javax.swing.JLabel();

            lblImage.setPreferredSize(
                new java.awt.Dimension(100, 90)
            );

            lblImage.setHorizontalAlignment(
                javax.swing.JLabel.CENTER
            );

            if (!imagePath.isEmpty() && !imagePath.equals("null")) {

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
                        java.awt.Image scaled =
                            image.getScaledInstance(
                                100, 90,
                                java.awt.Image.SCALE_SMOOTH
                            );

                        lblImage.setIcon(
                            new javax.swing.ImageIcon(scaled)
                        );
                    }

                } catch (Exception imageError) {
                    System.out.println(
                        "Could not load image: " + imagePath
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
                    "Arial",
                    java.awt.Font.BOLD,
                    16
                )
            );

            javax.swing.JLabel lblDescription =
                new javax.swing.JLabel(description);

            lblDescription.setFont(
                new java.awt.Font(
                    "Arial",
                    java.awt.Font.PLAIN,
                    12
                )
            );

            javax.swing.JLabel lblDate =
                new javax.swing.JLabel("Date: " + reportDate);

            lblDate.setFont(
                new java.awt.Font(
                    "Arial",
                    java.awt.Font.PLAIN,
                    11
                )
            );

            javax.swing.JLabel lblType =
                new javax.swing.JLabel(reportType);

            if ("LOST".equals(reportType)) {
                lblType.setForeground(
                    new java.awt.Color(200, 80, 60)
                );
            } else if ("FOUND".equals(reportType)) {
                lblType.setForeground(
                    new java.awt.Color(30, 140, 80)
                );
            } else {
                lblType.setForeground(java.awt.Color.GRAY);
            }

            textPanel.add(lblName);
            textPanel.add(lblDescription);
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

    } catch (java.io.IOException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Unable to load reports from the API:\n"
                + e.getMessage(),
            "API Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );

    } catch (org.json.JSONException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Unable to read the items API response:\n"
                + e.getMessage(),
            "Response Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    // Refresh the panel display
    panelRecentReports.revalidate();
    panelRecentReports.repaint();
}


   

        

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel121 = new javax.swing.JPanel();
        parentRecentPanel = new javax.swing.JPanel();
        scrollPaneRecentReports = new javax.swing.JScrollPane();
        panelRecentReports = new javax.swing.JPanel();
        lblItemImage = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        lblItemStatus = new javax.swing.JLabel();
        lblItemLocation = new javax.swing.JLabel();
        lblItemDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        loadDashboardCounts = new javax.swing.JPanel();
        jpanel = new javax.swing.JPanel();
        lblLostCount = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Jpanel12 = new javax.swing.JPanel();
        lblFoundCount = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        helloGreet = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jpanel121.setBackground(new java.awt.Color(245, 245, 219));
        jpanel121.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        parentRecentPanel.setBackground(new java.awt.Color(255, 255, 236));
        parentRecentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneRecentReports.setBackground(new java.awt.Color(0, 102, 51));
        scrollPaneRecentReports.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRecentReports.setPreferredSize(new java.awt.Dimension(700, 450));
        scrollPaneRecentReports.setViewportView(panelRecentReports);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(panelRecentReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblItemName)
                    .addComponent(lblItemLocation)
                    .addComponent(lblItemDate)
                    .addComponent(lblItemStatus))
                .addContainerGap(895, Short.MAX_VALUE))
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
                .addContainerGap(284, Short.MAX_VALUE))
        );

        scrollPaneRecentReports.setViewportView(panelRecentReports);

        parentRecentPanel.add(scrollPaneRecentReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 1210, 380));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel4.setText("Recently Reported Items");
        parentRecentPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 350, 50));

        jpanel121.add(parentRecentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 1280, 480));

        loadDashboardCounts.setBackground(new java.awt.Color(242, 255, 242));
        loadDashboardCounts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel.setBackground(new java.awt.Color(255, 255, 255));
        jpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));

        lblLostCount.setBackground(new java.awt.Color(255, 255, 255));
        lblLostCount.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 48)); // NOI18N
        lblLostCount.setForeground(new java.awt.Color(0, 102, 51));
        lblLostCount.setText("1");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setText("Total lost Items");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(39, 39, 39))
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lblLostCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblLostCount)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        loadDashboardCounts.add(jpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 140));

        Jpanel12.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));

        lblFoundCount.setBackground(new java.awt.Color(0, 0, 0));
        lblFoundCount.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 48)); // NOI18N
        lblFoundCount.setForeground(new java.awt.Color(0, 102, 51));
        lblFoundCount.setText("5");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("Total Found Items");

        javax.swing.GroupLayout Jpanel12Layout = new javax.swing.GroupLayout(Jpanel12);
        Jpanel12.setLayout(Jpanel12Layout);
        Jpanel12Layout.setHorizontalGroup(
            Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel12Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel12Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel12Layout.createSequentialGroup()
                        .addComponent(lblFoundCount)
                        .addGap(87, 87, 87))))
        );
        Jpanel12Layout.setVerticalGroup(
            Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel12Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblFoundCount)
                .addGap(19, 19, 19))
        );

        loadDashboardCounts.add(Jpanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, 140));

        jpanel121.add(loadDashboardCounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 50, 400, -1));

        helloGreet.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 80)); // NOI18N
        helloGreet.setForeground(new java.awt.Color(255, 255, 255));
        helloGreet.setText("Hello, ");
        jpanel121.add(helloGreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1230, 110));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/BGGG.png"))); // NOI18N
        jpanel121.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 960));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanel121, javax.swing.GroupLayout.PREFERRED_SIZE, 1504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanel121, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel12;
    private javax.swing.JLabel helloGreet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel121;
    private javax.swing.JLabel lblFoundCount;
    private javax.swing.JLabel lblItemDate;
    private javax.swing.JLabel lblItemImage;
    private javax.swing.JLabel lblItemLocation;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemStatus;
    private javax.swing.JLabel lblLostCount;
    private javax.swing.JPanel loadDashboardCounts;
    private javax.swing.JPanel panelRecentReports;
    private javax.swing.JPanel parentRecentPanel;
    private javax.swing.JScrollPane scrollPaneRecentReports;
    // End of variables declaration//GEN-END:variables

   
}
