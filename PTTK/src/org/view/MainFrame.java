package org.view;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.Icon;
import jiconfont.IconFontSwing;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import org.resources.MetroUI;

public class MainFrame extends javax.swing.JFrame {
    
    private BillPanel billPanel = BillPanel.getInstance();
    private RoomPanel roomPanel = RoomPanel.getInstance();
//    private BillPanel studentPanel = StudentPanel.getInstance();
//    private BillPanel staffPanel = StaffPanel.getInstance(
    
    private Icon styleIcon = MetroUI.getGoogleIcon(MetroUI.icons.STYLE, 24, Color.WHITE);
    private Icon otherIcon = MetroUI.getGoogleIcon(MetroUI.icons.REORDER, 24, Color.WHITE);
    private Icon studentIcon = MetroUI.getGoogleIcon(MetroUI.icons.PEOPLE, 24, Color.WHITE);
    private Icon staffIcon = MetroUI.getGoogleIcon(MetroUI.icons.VPN_KEY, 24, Color.WHITE);
    private Icon roomIcon = MetroUI.getGoogleIcon(MetroUI.icons.LAYERS, 24, Color.WHITE);
            
            
    public MainFrame() {        
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        initComponents();
        drawUI();   
    }
    
    private void drawUI() {
        menuItemGroup.add(menuBill);
        menuItemGroup.add(menuFeature);
        menuItemGroup.add(menuRoom);
        menuItemGroup.add(menuStaff);
        menuItemGroup.add(menuStudent);
        menuItemGroup.setSelected(menuRoom.getModel(), true);
        
        menuBill.setIcon(styleIcon);
        menuFeature.setIcon(otherIcon);
        menuRoom.setIcon(roomIcon);
        menuStaff.setIcon(staffIcon);
        menuStudent.setIcon(studentIcon);
        
        MetroUI.apply(menuBill);
        MetroUI.apply(menuFeature);
        MetroUI.apply(menuRoom);
        MetroUI.apply(menuStaff);
        MetroUI.apply(menuStudent);
        
        displayPanel.setLayout(new CardLayout());
        displayPanel.add(billPanel);
        displayPanel.add(roomPanel);
//        displayPanel.add(studentPanel);
//        displayPanel.add(staffPanel);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuItemGroup = new javax.swing.ButtonGroup();
        menuSideBar = new javax.swing.JPanel();
        menuBill = new javax.swing.JToggleButton();
        menuFeature = new javax.swing.JToggleButton();
        menuRoom = new javax.swing.JToggleButton();
        menuStudent = new javax.swing.JToggleButton();
        menuStaff = new javax.swing.JToggleButton();
        displayPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Kí túc xá");
        setBackground(new java.awt.Color(255, 255, 255));

        menuSideBar.setBackground(new java.awt.Color(51, 51, 51));

        menuBill.setBackground(new java.awt.Color(51, 51, 51));
        menuBill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBill.setForeground(new java.awt.Color(255, 255, 255));
        menuBill.setSelected(true);
        menuBill.setText("Quản lý Hóa Đơn");
        menuBill.setBorder(null);
        menuBill.setContentAreaFilled(false);
        menuBill.setFocusCycleRoot(true);
        menuBill.setFocusPainted(false);
        menuBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBillActionPerformed(evt);
            }
        });

        menuFeature.setBackground(new java.awt.Color(51, 51, 51));
        menuFeature.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuFeature.setForeground(new java.awt.Color(255, 255, 255));
        menuFeature.setText("Tính năng khác");
        menuFeature.setBorder(null);
        menuFeature.setContentAreaFilled(false);
        menuFeature.setFocusCycleRoot(true);
        menuFeature.setFocusPainted(false);
        menuFeature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFeatureActionPerformed(evt);
            }
        });

        menuRoom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuRoom.setForeground(new java.awt.Color(255, 255, 255));
        menuRoom.setText("Quản lý Phòng Ở");
        menuRoom.setBorder(null);
        menuRoom.setContentAreaFilled(false);
        menuRoom.setFocusPainted(false);
        menuRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRoomActionPerformed(evt);
            }
        });

        menuStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuStudent.setForeground(new java.awt.Color(255, 255, 255));
        menuStudent.setText("Quản lý Sinh Viên");
        menuStudent.setBorder(null);
        menuStudent.setContentAreaFilled(false);
        menuStudent.setFocusPainted(false);
        menuStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStudentActionPerformed(evt);
            }
        });

        menuStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuStaff.setForeground(new java.awt.Color(255, 255, 255));
        menuStaff.setText("Quản lý Nhân Viên");
        menuStaff.setBorder(null);
        menuStaff.setContentAreaFilled(false);
        menuStaff.setFocusPainted(false);
        menuStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuSideBarLayout = new javax.swing.GroupLayout(menuSideBar);
        menuSideBar.setLayout(menuSideBarLayout);
        menuSideBarLayout.setHorizontalGroup(
            menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuFeature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuSideBarLayout.createSequentialGroup()
                .addGroup(menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuBill, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(menuStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menuSideBarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {menuBill, menuRoom});

        menuSideBarLayout.setVerticalGroup(
            menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSideBarLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(menuRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuStaff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuBill, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuFeature, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );

        menuSideBarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {menuBill, menuRoom, menuStaff, menuStudent});

        displayPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBillActionPerformed
        billPanel.setVisible(true);
        roomPanel.setVisible(false);
//        studentPanel.setVisible(false);
//        staffPanel.setVisible(false);
    }//GEN-LAST:event_menuBillActionPerformed

    private void menuFeatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFeatureActionPerformed
        billPanel.setVisible(false);
        roomPanel.setVisible(false);
//        studentPanel.setVisible(false);
//        staffPanel.setVisible(false);
    }//GEN-LAST:event_menuFeatureActionPerformed

    private void menuRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRoomActionPerformed
        billPanel.setVisible(false);
        roomPanel.setVisible(true);
//        studentPanel.setVisible(false);
//        staffPanel.setVisible(false);
    }//GEN-LAST:event_menuRoomActionPerformed

    private void menuStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStudentActionPerformed
        billPanel.setVisible(false);
        roomPanel.setVisible(false);
//        studentPanel.setVisible(true);
//        staffPanel.setVisible(false);
    }//GEN-LAST:event_menuStudentActionPerformed

    private void menuStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStaffActionPerformed
        billPanel.setVisible(false);
        roomPanel.setVisible(false);
//        studentPanel.setVisible(false);
//        staffPanel.setVisible(true);
    }//GEN-LAST:event_menuStaffActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel displayPanel;
    private javax.swing.JToggleButton menuBill;
    private javax.swing.JToggleButton menuFeature;
    private javax.swing.ButtonGroup menuItemGroup;
    private javax.swing.JToggleButton menuRoom;
    private javax.swing.JPanel menuSideBar;
    private javax.swing.JToggleButton menuStaff;
    private javax.swing.JToggleButton menuStudent;
    // End of variables declaration//GEN-END:variables
}
