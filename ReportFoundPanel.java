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
import lostandfoundapp.Category;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportFoundPanel extends javax.swing.JPanel {
    private String selectedImagePath = "";
    private final Map<String, Integer> categoryIds = new HashMap<>();
    
    public ReportFoundPanel() {
        initComponents();
        loadCategories();
    }
    
        private void loadCategories() {
            try {
                String response = lostandfoundapp.apis.apis.getCategories();

                JSONArray categories;

                if (response.trim().startsWith("[")) {
                    categories = new JSONArray(response);
                } else {
                    JSONObject json = new JSONObject(response);

                    if (json.has("data")) {
                        categories = json.getJSONArray("data");
                    } else {
                        categories = json.getJSONArray("categories");
                    }
                }

                cmbCategory.removeAllItems();
                categoryIds.clear();

                for (int i = 0; i < categories.length(); i++) {
                    JSONObject item = categories.getJSONObject(i);

                    int id = item.getInt("id");
                    String name = item.getString("name");

                    cmbCategory.addItem(name);
                    categoryIds.put(name, id);
                }

                cmbCategory.setSelectedIndex(-1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to load categories: " + e.getMessage()
                );
            }
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();
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
        jLabel19 = new javax.swing.JLabel();
        lblImagePreview = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtIdentifyingFeatures = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(248, 248, 218));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        jLabel4.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("Location");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Description :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        jLabel6.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 51));
        jLabel6.setText("Category ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        txtItemName.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtItemName.setAutoscrolls(false);
        txtItemName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtItemName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });
        jPanel2.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 360, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (2).png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 40, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (1).png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));

        cmbType.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        cmbType.setForeground(new java.awt.Color(0, 102, 51));
        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Found", "Lost" }));
        cmbType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });
        jPanel2.add(cmbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 360, 51));

        txtDescription.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 18)); // NOI18N
        txtDescription.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescription.setAutoscrolls(false);
        txtDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 360, 70));

        txtDate.setAutoscrolls(false);
        txtDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 410, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (3).png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, -1, -1));

        txtLocation.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtLocation.setAutoscrolls(false);
        txtLocation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtLocation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocationActionPerformed(evt);
            }
        });
        jPanel2.add(txtLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 410, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/qwq (4).png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, -1));

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
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 140, 40));

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
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, 270, 60));

        jButton3.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jButton3.setText("CANCEL REPORT");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 590, 270, 60));

        jLabel12.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 51));
        jLabel12.setText("Item Name");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 90, -1));

        jLabel2.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("Report Item ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, 40));

        jLabel9.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 51));
        jLabel9.setText("Date  (yyyy/mm/dd) ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, -1, -1));

        jLabel14.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 51));
        jLabel14.setText("Upload Image :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, -1));

        jLabel19.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 51));
        jLabel19.setText("Upload Image :");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, -1));

        lblImagePreview.setForeground(new java.awt.Color(0, 102, 51));
        lblImagePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagePreview.setText("IMAGE");
        lblImagePreview.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel2.add(lblImagePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, 250, 160));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setText("“Be the reason someone gets their belongings back.”");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 650, -1, 40));

        jLabel15.setBackground(new java.awt.Color(0, 102, 51));
        jLabel15.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 51));
        jLabel15.setText("Identifying Features");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 140, 30));

        txtIdentifyingFeatures.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtIdentifyingFeatures.setAutoscrolls(false);
        txtIdentifyingFeatures.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        txtIdentifyingFeatures.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtIdentifyingFeatures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentifyingFeaturesActionPerformed(evt);
            }
        });
        jPanel2.add(txtIdentifyingFeatures, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 360, 40));

        jLabel16.setBackground(new java.awt.Color(0, 102, 51));
        jLabel16.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 51));
        jLabel16.setText("Report Type");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 80, 30));

        cmbCategory.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        cmbCategory.setForeground(new java.awt.Color(0, 102, 51));
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personal Belongings", "Electronic Devices", " " }));
        cmbCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });
        jPanel2.add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 360, 51));

        contentPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 710));

        jPanel3.setBackground(new java.awt.Color(224, 248, 224));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 3, true));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lostandfoundapp/panels/FF.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 51));
        jLabel13.setText("FOUND AN ITEM ?");

        jLabel17.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 51));
        jLabel17.setText("Help return it into owner ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(77, 77, 77))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );

        contentPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 260, 710));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTypeActionPerformed

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

    // Validate item name
    if (txtItemName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter the item name.");
        txtItemName.requestFocus();
        return;
    }

//    // Validate category
//    if (cmbCategory.getSelectedIndex() == 0) {
//        JOptionPane.showMessageDialog(this, "Please select a category.");
//        cmbCategory.requestFocus();
//        return;
//    }

    // Validate description
    if (txtDescription.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter the description.");
        txtDescription.requestFocus();
        return;
    }

    // Validate location
    if (txtLocation.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(
                this, "Please enter where the item was found."
        );
        txtLocation.requestFocus();
        return;
    }

    // Validate date
    if (txtDate.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter the date found.");
        txtDate.requestFocus();
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to report this found item?",
            "Confirm Report",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        // Build API payload
        org.json.JSONObject payload = new org.json.JSONObject();

        // Replace getUserId() with your actual Session getter if different
        int userId = lostandfoundapp.Session.getUserId();

        // Assumes the first category is a placeholder
        // and the remaining combo values match database category IDs.
       String selectedCategory =
        (String) cmbCategory.getSelectedItem();

        if (selectedCategory == null) {
            JOptionPane.showMessageDialog(this, "Please select a category.");
            return;
        }
        
        String selectedType = cmbType.getSelectedItem().toString().trim().toLowerCase();


        if (selectedType == null) {
            JOptionPane.showMessageDialog(this, "Please select an item type.");
            return;
        }

        int categoryId = categoryIds.get(selectedCategory);

        payload.put("category_id", categoryId);

        payload.put("user_id", userId);
        payload.put("category_id", categoryId);
        payload.put("type", selectedType);
        payload.put("name", txtItemName.getText().trim());
        payload.put("color", "white");
        payload.put("description", txtDescription.getText().trim());
        payload.put("identifying_features", txtIdentifyingFeatures.getText().trim());
        payload.put("incident_date", txtDate.getText().trim());
        payload.put("location", txtLocation.getText().trim());
        payload.put("status", "submitted");

        // POST /api/items
        String response = lostandfoundapp.apis.apis.createItem(payload);

        // Read the newly created item's ID.
        JSONObject itemResponse = new JSONObject(response);

        // Supports either {"id": 1} or {"data": {"id": 1}}.
        JSONObject createdItem = itemResponse.has("data")
                ? itemResponse.getJSONObject("data")
                : itemResponse;

        int itemId = createdItem.getInt("id");

        // Register the photo if the user selected one.
        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {

            File imageFile = new File(selectedImagePath);

            JSONObject photoPayload = new JSONObject();
            photoPayload.put("item_id", itemId);
            photoPayload.put(
                    "path",
                    "items/" + itemId + "/" + imageFile.getName()
            );
            photoPayload.put("is_primary", true);
            photoPayload.put("sort_order", 0);

            String photoResponse =
                    lostandfoundapp.apis.apis.createItemPhoto(photoPayload);

            System.out.println("Photo response: " + photoResponse);
        }

        JOptionPane.showMessageDialog(
                this,
                "Found item reported successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Clear the form after a successful report
        txtItemName.setText("");
        txtDescription.setText("");
        txtLocation.setText("");
        txtDate.setText("");
        cmbType.setSelectedIndex(0);
        cmbCategory.setSelectedIndex(-1);
        lblImagePreview.setIcon(null);
        lblImagePreview.setText("");
        selectedImagePath = null;

        System.out.println("API response: " + response);


    } catch (java.io.IOException e) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to submit found item:\n" + e.getMessage(),
                "API Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (org.json.JSONException e) {
        JOptionPane.showMessageDialog(
                this,
                "Invalid API response:\n" + e.getMessage(),
                "Response Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

           
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameActionPerformed

    private void txtIdentifyingFeaturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentifyingFeaturesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentifyingFeaturesActionPerformed

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbType;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblImagePreview;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtIdentifyingFeatures;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtLocation;
    // End of variables declaration//GEN-END:variables

    
}
