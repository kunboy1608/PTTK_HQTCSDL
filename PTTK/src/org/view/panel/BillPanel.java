package org.view.panel;

import java.awt.TrayIcon;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import jiconfont.IconFontSwing;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import org.controller.BillController;
import org.resources.IconUtilities;
import org.resources.MetroUI;
import org.view.dialog.BillDialog;
import org.view.dialog.search.BillSearchDialog;

public class BillPanel extends JPanel implements Panel {
 
    private static final BillPanel instace = new BillPanel("Quản Lý Hóa Đơn");
    private static final String PLACEHOLDER = "Nhập mã hóa đơn";
    
    public static BillPanel getInstance() {
        return instace;
    }

    private BillPanel(String title) {
        initComponents();
        lbTitle.setText(title);
        this.reloadTable();
        drawUI();
    }
    
    private void drawUI() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        btnAdd.setIcon(IconUtilities.ICON_ADD);
        btnEdit.setIcon(IconUtilities.ICON_EDIT);
        btnDelete.setIcon(IconUtilities.ICON_REMOVE);
        btnSearch.setIcon(IconUtilities.ICON_FIND);
        btnReload.setIcon(IconUtilities.ICON_LOAD);
        
        MetroUI.apply(btnAdd);
        MetroUI.apply(btnEdit);
        MetroUI.apply(btnDelete);
        MetroUI.apply(btnSearch);
        MetroUI.apply(btnReload);
        MetroUI.apply(txtSearch, PLACEHOLDER);
    }
    
    
    @Override
    public void setTableModel(TableModel model) {
        this.viewTable.setModel(model);
    }
    
    @Override
    public TableModel getTableModel() {
        return this.viewTable.getModel();
    }
    
    @Override
    public void reloadTable() {
        BillController.initialData();
        BillController.initialModel();
        viewTable.setModel(BillController.getModel());
    }
    
    @Override
    public void actionAdd() {
        new BillDialog(null).setVisible(true);
    }

    @Override
    public void actionEdit() {
        if (viewTable.getSelectedRowCount() == 0) {
            this.requireSelection();
            return;
        }
        String billId = (String)viewTable.getModel().getValueAt(viewTable.getSelectedRow(), 1);
        new BillDialog(BillController.showFullInfo(billId)).setVisible(true);
    }

    @Override
    public void actionSearch() {
        // kiểm tra xem cái textfield có trống hay không
        if (txtSearch.getText().equals(PLACEHOLDER)) {
            this.requireFulfill();
            return;
        }
        //tìm dòng được chọn
        for (int i = 0; i < viewTable.getRowCount(); i++) {
            //tìm theo mã
            if (((String)viewTable.getValueAt(i, 1)).equals(txtSearch.getText())) {
                //tô đậm nó lên
                viewTable.setRowSelectionInterval(i, i);
                return;
            }   
        }
        this.notificateUnavailable();
    }

    @Override
    public void actionDelete() {
        int count = 0;
        ArrayList<String> arr = new ArrayList();
        // Đếm và đưa những hóa đơn cần được xóa
        for (int i = 0; i < viewTable.getRowCount(); i++) {
            if ("true".equals(viewTable.getValueAt(i, 0).toString().trim())) {
                ++count;
                arr.add(viewTable.getValueAt(i, 1).toString());
            }
        }
        // Nếu không được chọn thì thông báo
        if (count == 0) {
            this.requireSelection();
        }

        // Nếu người dùng hủy thì thôi
        if (this.requireConfirm() != 0) {
            return;
        }
        // Tiến hành xóa
        count = 0;
        for (String s : arr) {
            if (BillController.delData(s)) {
                ++count;
            }
        }
        // Cập nhật lại bảng từ CSDL
        BillController.initialData();
        BillController.initialModel();
        viewTable.setModel(BillController.getModel());
        
        // Thông báo đã xóa được bao nhiêu hóa đơn
        MetroUI.notificate(
                "Thông báo" ,
                "Đã xóa " + count + " hóa đơn",
                TrayIcon.MessageType.INFO
        );
    }

    @Override
    public void actionPrint() {
        
    }

    @Override
    public void actionAdvancedSearch() {
        new BillSearchDialog().setVisible(true);
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
        lbAdvancedSearch = new javax.swing.JLabel();
        btnPrintReport = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        lbTitle.setText("Quản Lý Hóa Đơn");

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
        viewTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        viewTable.setShowGrid(true);
        viewTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(viewTable);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAdd.setToolTipText("Thêm dữ liệu");
        btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnAdd.setContentAreaFilled(false);
        btnAdd.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEdit.setToolTipText("Chỉnh sửa dữ liệu đã chọn");
        btnEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEdit.setContentAreaFilled(false);
        btnEdit.setPreferredSize(new java.awt.Dimension(50, 50));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setToolTipText("Xóa dữ liệu đã chọn");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnDelete.setContentAreaFilled(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(50, 50));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
        txtSearch.setText("Nhập mã hóa đơn");
        txtSearch.setMargin(new java.awt.Insets(0, 10, 0, 10));

        btnReload.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnReload.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnReload.setContentAreaFilled(false);
        btnReload.setPreferredSize(new java.awt.Dimension(50, 50));
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        lbAdvancedSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbAdvancedSearch.setForeground(new java.awt.Color(0, 102, 204));
        lbAdvancedSearch.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAdvancedSearch.setText("Tìm kiếm nâng cao");
        lbAdvancedSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbAdvancedSearchMousePressed(evt);
            }
        });

        btnPrintReport.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnPrintReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnPrintReport.setContentAreaFilled(false);
        btnPrintReport.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbAdvancedSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbTitle)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAdvancedSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addGap(31, 31, 31))
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.actionAdd();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.actionEdit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        this.actionSearch();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.actionDelete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void lbAdvancedSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAdvancedSearchMousePressed
        this.actionAdvancedSearch();
    }//GEN-LAST:event_lbAdvancedSearchMousePressed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        this.reloadTable();
    }//GEN-LAST:event_btnReloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnPrintReport;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAdvancedSearch;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables

    
    
}
