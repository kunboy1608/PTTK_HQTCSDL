package org.view.dialog;

import java.awt.Component;
import java.awt.Container;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controller.*;
import org.model.Bill;
import org.resources.MetroUI;

public class BillDialog extends javax.swing.JFrame {

    private boolean add = true;

    private void showMoreInfo(boolean b) {
        panelWE.setVisible(b);
    }

    private void configComponents() {
        txtESum.setEnabled(false);
        txtWSum.setEnabled(false);
        txtBillId.setEnabled(false);
        txtEId.setEnabled(false);
        txtWId.setEnabled(false);
        txtEStart.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9'))
                        || (c == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        txtEEnd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9'))
                        || (c == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        txtWStart.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9'))
                        || (c == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        txtWEnd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9'))
                        || (c == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        txtBillSum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9'))
                        || (c == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Hóa đơn điện nước",
            "Hóa đơn phòng",
            "Khác"
        }));
        ArrayList<String> arr = BuildingController.getListBuilding();
        for (String string : arr) {
            cbBuilding.addItem(string);
        }
        arr = StudentController.getComboboxListItem();
        for (String string : arr) {
            cbStudent.addItem(string);
        }
        arr = EmployeeController.getListIDEmployee();
        for (String string : arr) {
            cbEmployee.addItem(string);
        }
    }

    private void cleanInformation() {
        Container con = this.getContentPane();
        for (Component c : con.getComponents()) {
            if (c instanceof JTextField) {
                JTextField j = (JTextField) c;
                j.setText("");
            }
        }
    }

    private void showBill(Bill b) {
        txtBillId.setText(b.getIDBill());
        cbEmployee.getModel().setSelectedItem(b.getIDEmployee());
        cbBuilding.getModel().setSelectedItem(b.getIDBuilding());

        ArrayList<String> arr = RoomManageController.getListNumRoom(cbBuilding.getSelectedItem().toString().trim());
        for (String string : arr) {
            cbRoom.addItem(string);
        }
        cbRoom.getModel().setSelectedItem(b.getNumRoom());
        cbStudent.getModel().setSelectedItem(b.getIDStudent());
        dcCreatedDate.setDate(b.getInvoiceDate());
        dcSubmittedDate.setDate(b.getCollectionDate());
        cbType.getModel().setSelectedItem(b.getKind());
        cbType.setEnabled(false);
        txtBillSum.setText(String.valueOf(b.getValue()));
        txtNote.setText(b.getNote());
        if (cbType.getSelectedIndex() == 0) {
            showMoreInfo(true);
            txtEId.setText(b.getIDElectric());
            dcECreatedDate.setDate(b.getStartDateE());
            dcESubmittedDate.setDate(b.getEndDateE());
            txtEStart.setText(String.valueOf(b.getHeadNumE()));
            txtEEnd.setText(String.valueOf(b.getBotNumE()));
            txtESum.setText(String.valueOf(b.getSumE()));

            txtWId.setText(b.getIDWater());
            dcWCreatedDate.setDate(b.getStartDateW());
            dcWSubmittedDate.setDate(b.getEndDateW());
            txtWStart.setText(String.valueOf(b.getHeadNumW()));
            txtWEnd.setText(String.valueOf(b.getBotNumW()));
            txtWSum.setText(String.valueOf(b.getSumW()));
            txtBillSum.setEnabled(false);
        } else {
            showMoreInfo(false);
        }

    }

    private void drawUI() {
        MetroUI.applySpecilized(btnSave);
        MetroUI.apply(btnClear);
        MetroUI.apply(btnPrint);
    }

    public BillDialog(Bill b) {
        initComponents();
        configComponents();
        cleanInformation();
        drawUI();

        if (b != null) {
            showBill(b);
            add = false;
        } else {
            btnPrint.setVisible(false);
            cbType.getModel().setSelectedItem("Khác");
            showMoreInfo(false);
        }
    }

    private String checkSyntax() {
        if ("".equals(cbEmployee.getSelectedItem().toString().trim())) {
            return ("Vui lòng nhập mã nhân viên");
        }
        if ("".equals(cbBuilding.getSelectedItem().toString().trim())) {
            return ("Vui lòng nhập mã tòa");
        }
        if ("".equals(cbRoom.getSelectedItem().toString().trim())) {
            return ("Vui lòng nhập số phòng");
        }
        if (dcCreatedDate.getDate() == null) {
            return ("Vui lòng nhập ngày lập");
        }
        if (dcSubmittedDate.getDate() == null) {
            return ("Vui lòng nhập ngày thu");
        }
        if (dcCreatedDate.getDate().after(dcSubmittedDate.getDate())) {
            return ("Ngày thu phải sau hoặc bằng ngày lập");
        }
        if (cbType.getSelectedIndex() == 0) {
            if (dcECreatedDate.getDate() == null) {
                return ("Vui lòng nhập ngày bắt đầu (điện)");
            }
            if (dcESubmittedDate.getDate() == null) {
                return ("Vui lòng nhập ngày kết thúc (điện)");
            }
            if (dcWCreatedDate.getDate() == null) {
                return ("Vui lòng nhập ngày bắt đầu (nước)");
            }
            if (dcWSubmittedDate.getDate() == null) {
                return ("Vui lòng nhập ngày kết thúc (nước)");
            }
            if (dcECreatedDate.getDate().after(dcESubmittedDate.getDate())
                    || dcWCreatedDate.getDate().after(dcWSubmittedDate.getDate())) {
                return ("Ngày bắt đầu phả trước ngày kết thúc");
            }
            if ("".equals(txtEStart.getText())) {
                return ("Vui lòng nhập số đầu");
            }
            if ("".equals(txtEEnd.getText())) {
                return ("Vui lòng nhập số cuối");
            }
            if ("".equals(txtWStart.getText())) {
                return ("Vui lòng nhập số đầu");
            }
            if ("".equals(txtWEnd.getText())) {
                return ("Vui lòng nhập số cuối");
            }
            if (Integer.parseInt(txtEStart.getText().trim())
                    > Integer.parseInt(txtEEnd.getText().trim())) {
                return ("Số đầu phải nhỏ hơn số cuối (điện)");
            }
            if (Integer.parseInt(txtWStart.getText().trim())
                    > Integer.parseInt(txtWEnd.getText().trim())) {
                return ("Số đầu phải nhỏ hơn số cuối (nước)");
            }
        } else {
            if ("".equals(txtBillSum.getText())) {
                return ("Vui lòng nhập trị giá");
            }
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        txtRoom3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBillId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        dcCreatedDate = new com.toedter.calendar.JDateChooser();
        dcSubmittedDate = new com.toedter.calendar.JDateChooser();
        cbBuilding = new javax.swing.JComboBox<>();
        cbType = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbStudent = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtNote = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        cbEmployee = new javax.swing.JComboBox<>();
        txtBillSum = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbRoom = new javax.swing.JComboBox<>();
        btnPrint = new javax.swing.JButton();
        panelWE = new javax.swing.JPanel();
        txtEStart = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEEnd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtWStart = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtWEnd = new javax.swing.JTextField();
        txtEId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtESum = new javax.swing.JTextField();
        txtWId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dcECreatedDate = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dcESubmittedDate = new com.toedter.calendar.JDateChooser();
        txtWSum = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        dcWCreatedDate = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        dcWSubmittedDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Số điện đầu");

        txtRoom3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRoom3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chỉnh sửa hóa đơn");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel1.setText("Thông tin Hóa đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Hóa Đơn");

        txtBillId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtBillId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tòa");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày lập");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày thu");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Loại Hóa Đơn");

        btnSave.setBackground(new java.awt.Color(102, 102, 102));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu thay đổi");
        btnSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setText("Xóa");
        btnClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnClear.setContentAreaFilled(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        dcCreatedDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcCreatedDate.setDate(new Date());
        dcCreatedDate.setDateFormatString("dd/MM/yy");
        dcCreatedDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dcSubmittedDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcSubmittedDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbBuilding.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbBuilding.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuildingActionPerformed(evt);
            }
        });

        cbType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTypeActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Mã Sinh Viên");

        cbStudent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Ghi chú");

        txtNote.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNote.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Mã nhân viên");

        cbEmployee.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtBillSum.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtBillSum.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Tổng gía trị hóa đơn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Phòng");

        cbRoom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnPrint.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPrint.setText("In");
        btnPrint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnPrint.setContentAreaFilled(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(210, 210, 210))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(dcCreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcSubmittedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(cbStudent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtBillId, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel23)
                            .addComponent(txtNote)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBillSum))
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBillId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcSubmittedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcCreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(8, 8, 8)
                .addComponent(txtBillSum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbBuilding, cbType, dcCreatedDate, dcSubmittedDate, txtBillId});

        panelWE.setBackground(new java.awt.Color(255, 255, 255));

        txtEStart.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEStart.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Số điện cuối");

        txtEEnd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEEnd.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Số nước đầu");

        txtWStart.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtWStart.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Số nước cuối");

        txtWEnd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtWEnd.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        txtEId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tổng trị giá điện");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Mã CT Điện");

        txtESum.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtESum.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        txtWId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtWId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Mã CT Nước");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Ngày bắt đầu");

        dcECreatedDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcECreatedDate.setDate(new Date());
        dcECreatedDate.setDateFormatString("dd/MM/yy");
        dcECreatedDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Ngày kết thúc");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tổng trị giá nước");

        dcESubmittedDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcESubmittedDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtWSum.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtWSum.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Ngày bắt đầu");

        dcWCreatedDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcWCreatedDate.setDate(new Date());
        dcWCreatedDate.setDateFormatString("dd/MM/yy");
        dcWCreatedDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Ngày kết thúc");

        dcWSubmittedDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcWSubmittedDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Số điện đầu");

        javax.swing.GroupLayout panelWELayout = new javax.swing.GroupLayout(panelWE);
        panelWE.setLayout(panelWELayout);
        panelWELayout.setHorizontalGroup(
            panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWELayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtWSum, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtWId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(txtEId, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtESum, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel12)))
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dcWCreatedDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtWStart, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dcECreatedDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel13)))
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14)
                        .addComponent(jLabel10)
                        .addComponent(txtEEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addComponent(dcESubmittedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtWEnd))
                    .addComponent(dcWSubmittedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        panelWELayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dcWSubmittedDate, txtEEnd, txtEStart});

        panelWELayout.setVerticalGroup(
            panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWELayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcECreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcESubmittedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtESum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtWSum)
                        .addComponent(dcWCreatedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dcWSubmittedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelWELayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dcECreatedDate, dcESubmittedDate, dcWSubmittedDate, txtEStart});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelWE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelWE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        StringBuilder sb = new StringBuilder();
        sb.append(checkSyntax());
        if (sb.length() > 0) {
            MetroUI.notificate("Lỗi", sb.toString(), TrayIcon.MessageType.ERROR);
            return;
        }

        Bill b = new Bill();
        b.setIDBill(txtBillId.getText());
        b.setIDEmployee(cbEmployee.getSelectedItem().toString());
        b.setIDRoom(cbRoom.getSelectedItem().toString(),
                cbBuilding.getSelectedItem().toString());

        b.setIDStudent(cbStudent.getSelectedItem().toString());
        b.setInvoiceDate(dcCreatedDate.getDate());
        b.setCollectionDate(dcSubmittedDate.getDate());
        b.setKind(cbType.getSelectedItem().toString());
        b.setNote(txtNote.getText());

        if (cbType.getSelectedIndex() == 0) {
            b.setIDElectric(txtEId.getText());
            b.setStartDateE(dcECreatedDate.getDate());
            b.setEndDateE(dcESubmittedDate.getDate());
            b.setHeadNumE(txtEStart.getText());
            b.setBotNumE(txtEEnd.getText());

            b.setIDWater(txtWId.getText());
            b.setStartDateW(dcWCreatedDate.getDate());
            b.setEndDateW(dcWSubmittedDate.getDate());
            b.setHeadNumW(txtWStart.getText());
            b.setBotNumW(txtWEnd.getText());

            BillController.initialPrice();
            int capacity = RoomManageController.getCapacity(
                    cbRoom.getSelectedItem().toString(),
                    cbBuilding.getSelectedItem().toString());
            txtESum.setText(String.valueOf(BillController.calPriceE(
                    Integer.parseInt(txtEStart.getText()),
                    Integer.parseInt(txtEEnd.getText()),
                    capacity
            )));
            txtWSum.setText(String.valueOf(BillController.calPriceW(
                    Integer.parseInt(txtWStart.getText()),
                    Integer.parseInt(txtWEnd.getText()),
                    capacity
            )));

            b.setSumE(txtESum.getText());
            b.setSumW(txtWSum.getText());
            txtBillSum.setText(String.valueOf(
                    Integer.parseInt(txtWSum.getText()) + Integer.parseInt(txtESum.getText())
            ));
            b.setValue(txtBillSum.getText());
        }
        b.setValue(txtBillSum.getText());
        if (add) {
            if (BillController.insData(b)) {
                MetroUI.notificate("Thông báo", "Thêm thành công", TrayIcon.MessageType.INFO);
            } else {
                MetroUI.notificate("Thông báo", "Thêm thất bại", TrayIcon.MessageType.ERROR);
            }
        } else {
            if (BillController.updateData(b)) {
                MetroUI.notificate("Thông báo", "Thêm thành công", TrayIcon.MessageType.INFO);
            } else {
                MetroUI.notificate("Thông báo", "Thêm thất bại", TrayIcon.MessageType.ERROR);
            }
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        Container con = this.getContentPane();
        for (Component c : con.getComponents()) {
            if (c instanceof JTextField) {
                JTextField j = (JTextField) c;
                j.setText("");
            }
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void cbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTypeActionPerformed
        switch (cbType.getSelectedIndex()) {
            case -1:
                break;
            case 0:
                txtBillSum.setEnabled(false);
                showMoreInfo(true);
                break;
            default:
                txtBillSum.setEnabled(true);
                showMoreInfo(false);
                break;
        }
    }//GEN-LAST:event_cbTypeActionPerformed

    private void cbBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuildingActionPerformed
        // TODO add your handling code here:
        if (cbBuilding.getSelectedIndex() != -1) {
            ArrayList<String> arr = RoomManageController.getListNumRoom(cbBuilding.getItemAt(cbBuilding.getSelectedIndex()));
            cbRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));
            arr.forEach(string -> {
                cbRoom.addItem(string);
            });
        }
    }//GEN-LAST:event_cbBuildingActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd;
            HashMap hm = new HashMap();
            if (cbType.getSelectedIndex() != 0) {
                jd = JRXmlLoader.load("src/org/report/Para_ChiTietHoaDon.jrxml");
                hm.put("MaHD", txtBillId.getText().trim());
            } else {
                jd = JRXmlLoader.load("src/org/report/Para_ChiTietHoaDonDN.jrxml");
                hm.put("idhoadon", txtBillId.getText().trim());
            }
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbBuilding;
    private javax.swing.JComboBox<String> cbEmployee;
    private javax.swing.JComboBox<String> cbRoom;
    private javax.swing.JComboBox<String> cbStudent;
    private javax.swing.JComboBox<String> cbType;
    private com.toedter.calendar.JDateChooser dcCreatedDate;
    private com.toedter.calendar.JDateChooser dcECreatedDate;
    private com.toedter.calendar.JDateChooser dcESubmittedDate;
    private com.toedter.calendar.JDateChooser dcSubmittedDate;
    private com.toedter.calendar.JDateChooser dcWCreatedDate;
    private com.toedter.calendar.JDateChooser dcWSubmittedDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelWE;
    private javax.swing.JTextField txtBillId;
    private javax.swing.JTextField txtBillSum;
    private javax.swing.JTextField txtEEnd;
    private javax.swing.JTextField txtEId;
    private javax.swing.JTextField txtEStart;
    private javax.swing.JTextField txtESum;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtRoom3;
    private javax.swing.JTextField txtWEnd;
    private javax.swing.JTextField txtWId;
    private javax.swing.JTextField txtWStart;
    private javax.swing.JTextField txtWSum;
    // End of variables declaration//GEN-END:variables

}
