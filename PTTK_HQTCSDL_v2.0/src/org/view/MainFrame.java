package org.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import jiconfont.IconFontSwing;
import jiconfont.icons.GoogleMaterialDesignIcons;
import org.resources.IconUtilities;
import org.resources.MetroUI;
import org.view.dialog.ChangePasswordDialog;
import org.view.panel.BillPanel;
import org.view.panel.BuildingPanel;
import org.view.panel.DepartmentPanel;
import org.view.panel.EmployeePanel;
import org.view.panel.RoomPanel;
import org.view.panel.StatisticPanel;
import org.view.panel.StudentPanel;
import org.view.panel.UniversityPanel;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
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

    public void setUser(String username, String id, Image avt) {
        this.lbIdentifier.setText(id);
        this.lbUser.setText(username);
        if (avt != null) {
            this.avatar.setIcon(new ImageIcon(avt));
        } else {
            this.avatar.setIcon(new ImageIcon(
                    new ImageIcon("src/org/resources/img/bill.png")
                            .getImage()
                            .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        }
        grantRole();
    }

    private void grantRole() {
        menuBuilding.setEnabled(false);
        menuEmployee.setEnabled(false);
        menuBill.setEnabled(false);
        menuDepartment.setEnabled(false);
        menuRoom.setEnabled(false);
        menuStatistic.setEnabled(false);
        menuStudent.setEnabled(false);
        menuUniversity.setEnabled(false);
        
        if ("Sinh viên".equals(this.lbIdentifier.getText())) {
            menuBill.setEnabled(true);
            this.displayPanel.add(BillPanel.getInstance());
            return;
        }
        if (this.lbIdentifier.getText().contains("Trưởng tòa")) {
            menuStudent.setEnabled(true);
            menuBill.setEnabled(true);
            this.displayPanel.add(StudentPanel.getInstance());
        }
        if (this.lbIdentifier.getText().contains("Quản lí phòng")) {
            menuEmployee.setEnabled(true);
            menuDepartment.setEnabled(true);
            menuBill.setEnabled(true);
            menuStatistic.setEnabled(true);
            this.displayPanel.removeAll();
            this.displayPanel.add(EmployeePanel.getInstance());
            return;
        }
        if ("Ban quản lí".equals(this.lbIdentifier.getText())) {
            menuBuilding.setEnabled(true);
            menuEmployee.setEnabled(true);
            menuBill.setEnabled(true);
            menuDepartment.setEnabled(true);
            menuRoom.setEnabled(true);
            menuStatistic.setEnabled(true);
            menuStudent.setEnabled(true);
            menuUniversity.setEnabled(true);
            this.displayPanel.add(RoomPanel.getInstance());
        }
    }

    private void drawUI() {

        for (Component c : this.menuSideBar.getComponents()) {
            if (c instanceof JToggleButton) {
                menuItemGroup.add((JToggleButton) c);
                MetroUI.apply((JToggleButton) c);
            }
        }

        //menuItemGroup.setSelected(menuRoom.getModel(), true);
        menuBill.setIcon(IconUtilities.ICON_BILL);
        menuFeature.setIcon(IconUtilities.ICON_FEATURES);
        menuRoom.setIcon(IconUtilities.ICON_ROOM);
        menuEmployee.setIcon(IconUtilities.ICON_STAFF);
        menuStudent.setIcon(IconUtilities.ICON_STUDENT);
        menuDepartment.setIcon(IconUtilities.ICON_DEPARTMENT);
        menuStatistic.setIcon(IconUtilities.ICON_STATISTIC);
        menuUniversity.setIcon(IconUtilities.ICON_UNIVERSITY);
        menuBuilding.setIcon(IconUtilities.ICON_BUILDING);

        this.repaint();

        displayPanel.setVisible(true);
        displayPanel.setLayout(new CardLayout());
        menuFeature.setVisible(false);
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
        menuBuilding = new javax.swing.JToggleButton();
        menuDepartment = new javax.swing.JToggleButton();
        menuUniversity = new javax.swing.JToggleButton();
        menuStatistic = new javax.swing.JToggleButton();
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

        avatar.setBorderPainted(false);
        avatar.setContentAreaFilled(false);
        avatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avatarActionPerformed(evt);
            }
        });

        lbIdentifier.setBackground(new java.awt.Color(255, 255, 255));
        lbIdentifier.setForeground(new java.awt.Color(255, 255, 255));
        lbIdentifier.setText("Mã số định danh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUser)
                    .addComponent(lbIdentifier))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(21, 21, 21)
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        menuBuilding.setBackground(new java.awt.Color(51, 51, 51));
        menuBuilding.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBuilding.setForeground(new java.awt.Color(255, 255, 255));
        menuBuilding.setText("Quản lý Tòa");
        menuBuilding.setBorder(null);
        menuBuilding.setContentAreaFilled(false);
        menuBuilding.setFocusCycleRoot(true);
        menuBuilding.setFocusPainted(false);
        menuBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBuildingActionPerformed(evt);
            }
        });

        menuDepartment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuDepartment.setForeground(new java.awt.Color(255, 255, 255));
        menuDepartment.setText("Quản lý Phòng Ban");
        menuDepartment.setContentAreaFilled(false);
        menuDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDepartmentActionPerformed(evt);
            }
        });

        menuUniversity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuUniversity.setForeground(new java.awt.Color(255, 255, 255));
        menuUniversity.setText("Quản Lý Trường");
        menuUniversity.setContentAreaFilled(false);
        menuUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUniversityActionPerformed(evt);
            }
        });

        menuStatistic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuStatistic.setForeground(new java.awt.Color(255, 255, 255));
        menuStatistic.setText("Thống Kê");
        menuStatistic.setContentAreaFilled(false);
        menuStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStatisticActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuSideBarLayout = new javax.swing.GroupLayout(menuSideBar);
        menuSideBar.setLayout(menuSideBarLayout);
        menuSideBarLayout.setHorizontalGroup(
            menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuFeature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuSideBarLayout.createSequentialGroup()
                .addGroup(menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuDepartment)
                    .addComponent(menuUniversity)
                    .addComponent(menuStatistic))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(menuRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuSideBarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {menuBuilding, menuDepartment, menuStatistic, menuUniversity});

        menuSideBarLayout.setVerticalGroup(
            menuSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSideBarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(menuRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuBill, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuUniversity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuStatistic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuFeature, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        menuSideBarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {menuBill, menuRoom, menuStudent});

        menuSideBarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {menuBuilding, menuDepartment, menuStatistic, menuUniversity});

        displayPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
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
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBillActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(BillPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuBillActionPerformed

    private void menuFeatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFeatureActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
    }//GEN-LAST:event_menuFeatureActionPerformed

    private void menuRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRoomActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(RoomPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuRoomActionPerformed

    private void menuStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStudentActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(StudentPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuStudentActionPerformed

    private void menuEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmployeeActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(EmployeePanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuEmployeeActionPerformed

    private void menuBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBuildingActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(BuildingPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuBuildingActionPerformed

    private void menuDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDepartmentActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(DepartmentPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuDepartmentActionPerformed

    private void menuUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUniversityActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(UniversityPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuUniversityActionPerformed

    private void menuStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStatisticActionPerformed
        this.displayPanel.removeAll();
        this.displayPanel.setVisible(false);
        this.displayPanel.add(StatisticPanel.getInstance());
        this.displayPanel.setVisible(true);
    }//GEN-LAST:event_menuStatisticActionPerformed

    private void avatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avatarActionPerformed
        new ChangePasswordDialog().setVisible(true);
    }//GEN-LAST:event_avatarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton avatar;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIdentifier;
    private javax.swing.JLabel lbUser;
    private javax.swing.JToggleButton menuBill;
    private javax.swing.JToggleButton menuBuilding;
    private javax.swing.JToggleButton menuDepartment;
    private javax.swing.JToggleButton menuEmployee;
    private javax.swing.JToggleButton menuFeature;
    private javax.swing.ButtonGroup menuItemGroup;
    private javax.swing.JToggleButton menuRoom;
    private javax.swing.JPanel menuSideBar;
    private javax.swing.JToggleButton menuStatistic;
    private javax.swing.JToggleButton menuStudent;
    private javax.swing.JToggleButton menuUniversity;
    // End of variables declaration//GEN-END:variables

}
