package org.view;

import java.awt.Color;
import java.awt.TrayIcon;
import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jiconfont.IconFontSwing;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import org.controller.RoomController;
import org.resources.MetroUI;

public class RoomPanel extends javax.swing.JPanel {
    
    private final Icon addIcon = MetroUI.getGoogleIcon(MetroUI.icons.ADD_CIRCLE_OUTLINE, 24, Color.BLACK);
    private final Icon removeIcon = MetroUI.getGoogleIcon(MetroUI.icons.REMOVE_CIRCLE_OUTLINE, 24, Color.RED);
    private final Icon editIcon = MetroUI.getGoogleIcon(MetroUI.icons.CREATE, 24, Color.BLACK);
    private final Icon findIcon = MetroUI.getGoogleIcon(MetroUI.icons.SEARCH, 24, Color.BLACK);
    private final Icon loadIcon = MetroUI.getGoogleIcon(MetroUI.icons.REFRESH, 24, Color.BLACK);
    
    private static final RoomPanel instace = new RoomPanel("Quản lý Phòng Ở");
    
    public static RoomPanel getInstance() {
        return instace;
    }

    private RoomPanel(String title) {
        initComponents();
        lbTitle.setText(title);
        drawUI();
    }
    
    private void drawUI() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        btnAdd.setIcon(addIcon);
        btnEdit.setIcon(editIcon);
        btnDelete.setIcon(removeIcon);
        btnSearch.setIcon(findIcon);
        btnReload.setIcon(loadIcon);
        
        MetroUI.apply(btnAdd);
        MetroUI.apply(btnEdit);
        MetroUI.apply(btnDelete);
        MetroUI.apply(btnSearch);
        MetroUI.apply(btnReload);
        MetroUI.apply(txtSearch, "Nhập thông tin tìm kiếm");
    }
    
    public void setTableModel(TableModel model) {
        this.viewTable.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnReload = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        lbTitle.setText("Quản lý Hóa đơn");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        viewTable.setAutoCreateRowSorter(true);
        viewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        viewTable.setToolTipText("");
        viewTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        viewTable.setGridColor(new java.awt.Color(51, 51, 51));
        viewTable.setRowHeight(24);
        viewTable.setSelectionBackground(new java.awt.Color(102, 102, 102));
        viewTable.setShowGrid(true);
        viewTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(viewTable);

        btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnAdd.setContentAreaFilled(false);
        btnAdd.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEdit.setContentAreaFilled(false);
        btnEdit.setPreferredSize(new java.awt.Dimension(50, 50));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnDelete.setContentAreaFilled(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(50, 50));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(204, 204, 204));
        txtSearch.setText("Nhập thông tin tìm kiếm");
        txtSearch.setMargin(new java.awt.Insets(0, 10, 0, 10));

        btnReload.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnReload.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnReload.setContentAreaFilled(false);
        btnReload.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTitle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbTitle)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        new RoomDialog().setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (viewTable.getSelectedRowCount() == 0) {
            MetroUI.notificate("Lỗi: Thiếu đối tượng", "Vui lòng chọn đối tượng trong bảng để thao tác", TrayIcon.MessageType.ERROR);
            return;
        }
        new RoomDialog(viewTable.getSelectedRow()).setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (viewTable.getSelectedRowCount() == 0) {
            MetroUI.notificate("Lỗi: Thiếu đối tượng", "Vui lòng chọn đối tượng trong bảng để thao tác", TrayIcon.MessageType.ERROR);
            return;
        }
        //tìm dòng được chọn
        for (int i = 0; i < viewTable.getRowCount(); i++) {
            if (((String)viewTable.getValueAt(i, 1)).equals(txtSearch.getText())) {
                viewTable.setRowSelectionInterval(i, i);
                return;
            }   
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        System.out.println(viewTable.getSelectedRowCount());
        if (viewTable.getSelectedRowCount() == 0) {
            MetroUI.notificate("Lỗi: Thiếu đối tượng", "Vui lòng chọn đối tượng trong bảng để thao tác", TrayIcon.MessageType.ERROR);
            return;
        }
        
        DefaultTableModel table = (DefaultTableModel)this.viewTable.getModel();
        //tìm dòng được chọn
        for (int i = 0; i < viewTable.getRowCount(); i++) {
            if (viewTable.isRowSelected(i)) {
                RoomController.getInstance().delete((String) table.getValueAt(i, 1));
                //xóa dòng được chọn khỏi bảng và cập nhật bảng
                viewTable.setModel(RoomController.getInstance().toTable());
                return;
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables

    
}
