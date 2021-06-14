package org.view;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.Icon;
import jiconfont.IconFontSwing;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import org.resources.MetroUI;

public class MainFrame extends javax.swing.JFrame {
        
    private Icon styleIcon = MetroUI.getGoogleIcon(MetroUI.icons.STYLE, 24, Color.WHITE);
    private Icon otherIcon = MetroUI.getGoogleIcon(MetroUI.icons.REORDER, 24, Color.WHITE);
    private Icon studentIcon = MetroUI.getGoogleIcon(MetroUI.icons.PEOPLE, 24, Color.WHITE);
    private Icon staffIcon = MetroUI.getGoogleIcon(MetroUI.icons.VPN_KEY, 24, Color.WHITE);
    private Icon roomIcon = MetroUI.getGoogleIcon(MetroUI.icons.LAYERS, 24, Color.WHITE);
            
    private static final MainFrame INSTANCE = new MainFrame();
    
    public static MainFrame getInstance() {
        return INSTANCE;
    }
    
    private MainFrame() {        
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
        
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        initComponents();
        drawUI();   
        
        this.setLocationRelativeTo(null);
        
    }
    
    public void setUser(String username, String id) {
        this.lbIdentifier.setText(id);
        this.lbUser.setText(username);
    }
    
    private void drawUI() {
        menuItemGroup.add(menuBill);
        menuItemGroup.add(menuFeature);
        menuItemGroup.add(menuRoom);
        menuItemGroup.add(menuEmployee);
        menuItemGroup.add(menuStudent);
        //menuItemGroup.setSelected(menuRoom.getModel(), true);
        
        menuBill.setIcon(styleIcon);
        menuFeature.setIcon(otherIcon);
        menuRoom.setIcon(roomIcon);
        menuEmployee.setIcon(staffIcon);
        menuStudent.setIcon(studentIcon);
        
        MetroUI.apply(menuBill);
        MetroUI.apply(menuFeature);
        MetroUI.apply(menuRoom);
        MetroUI.apply(menuEmployee);
        MetroUI.apply(menuStudent);
        
        displayPanel.setVisible(false);
        displayPanel.setLayout(new CardLayout());
        displayPanel.add(BillPanel.getInstance());
        displayPanel.add(RoomPanel.getInstance());
        displayPanel.add(StudentPanel.getInstance());
        displayPanel.add(EmployeePanel.getInstance());
        
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
        menuEmployee = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        lbUser = new javax.swing.JLabel();
        avatar = new javax.swing.JButton();
        lbIdentifier = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Kí túc xá");
        setBackground(new java.awt.Color(255, 255, 255));

        menuSideBar.setBackground(new java.awt.Color(51, 51, 51));

        menuBill.setBackground(new java.awt.Color(51, 51, 51));
        menuBill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBill.setForeground(new java.awt.Color(255, 255, 255));
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

        menuEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuEmployee.setForeground(new java.awt.Color(255, 255, 255));
        menuEmployee.setText("Quản lý Nhân Viên");
        menuEmployee.setBorder(null);
        menuEmployee.setContentAreaFilled(false);
        menuEmployee.setFocusPainted(false);
        menuEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmployeeActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        lbUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbUser.setForeground(new java.awt.Color(255, 255, 255));
        lbUser.setText("Họ và Tên");

        avatar.setText("Avt");

        lbIdentifier.setBackground(new java.awt.Color(255, 255, 255));
        lbIdentifier.setForeground(new java.awt.Color(255, 255, 255));
        lbIdentifier.setText("Mã số định danh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUser)
                    .addComponent(lbIdentifier))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIdentifier, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout menuSideBarLayout = new javax.swing.GroupLayout(menuSideBar);
        menuSideBar.setLayout(menuSideBarLayout);
        menuSideBarLayout.setHorizontalGroup(
            menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuFeature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuSideBarLayout.createSequentialGroup()
                .addComponent(menuRoom, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addComponent(menuBill, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addComponent(menuEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuSideBarLayout.setVerticalGroup(
            menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSideBarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(menuRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuEmployee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuBill, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuFeature, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );

        menuSideBarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {menuBill, menuEmployee, menuRoom, menuStudent});

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
        displayPanel.setVisible(true);
            BillPanel.getInstance().setVisible(true);
        RoomPanel.getInstance().setVisible(false);
        EmployeePanel.getInstance().setVisible(false);
        StudentPanel.getInstance().setVisible(false);
    }//GEN-LAST:event_menuBillActionPerformed

    private void menuFeatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFeatureActionPerformed
        displayPanel.setVisible(false);
        BillPanel.getInstance().setVisible(false);
        RoomPanel.getInstance().setVisible(false);
        EmployeePanel.getInstance().setVisible(false);
        StudentPanel.getInstance().setVisible(false);
    }//GEN-LAST:event_menuFeatureActionPerformed

    private void menuRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRoomActionPerformed
        displayPanel.setVisible(true);
        BillPanel.getInstance().setVisible(false);
            RoomPanel.getInstance().setVisible(true);
        EmployeePanel.getInstance().setVisible(false);
        StudentPanel.getInstance().setVisible(false);
    }//GEN-LAST:event_menuRoomActionPerformed

    private void menuStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStudentActionPerformed
        displayPanel.setVisible(true);
        BillPanel.getInstance().setVisible(false);
        RoomPanel.getInstance().setVisible(false);
        EmployeePanel.getInstance().setVisible(false);
            StudentPanel.getInstance().setVisible(true);
    }//GEN-LAST:event_menuStudentActionPerformed

    private void menuEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmployeeActionPerformed
        displayPanel.setVisible(true);
        BillPanel.getInstance().setVisible(false);
        RoomPanel.getInstance().setVisible(false);
            EmployeePanel.getInstance().setVisible(true);
        StudentPanel.getInstance().setVisible(false);
    }//GEN-LAST:event_menuEmployeeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton avatar;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIdentifier;
    private javax.swing.JLabel lbUser;
    private javax.swing.JToggleButton menuBill;
    private javax.swing.JToggleButton menuEmployee;
    private javax.swing.JToggleButton menuFeature;
    private javax.swing.ButtonGroup menuItemGroup;
    private javax.swing.JToggleButton menuRoom;
    private javax.swing.JPanel menuSideBar;
    private javax.swing.JToggleButton menuStudent;
    // End of variables declaration//GEN-END:variables
}
