package org.view.dialog;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.controller.*;
import org.model.Employee;
import org.resources.MetroUI;

public class EmployeeDialog extends javax.swing.JFrame {

    private String URL = null;
    private boolean add = true; // true-Dialog để thêm, false-Dialog để sửa
    private String oldCMND = null; // Để lưu CMND/CCCD cũ để xóa tài khoản

    private void showEmployee(Employee e) {
        // Lưu lại để xóa tài khoản cữ
        oldCMND = e.getIDCard();

        txtSurname.setText(e.getSurname());
        txtName.setText(e.getName());
        txtAddress.setText(e.getAddress());
        cbSex.setSelectedIndex(e.getSexInt());
        dcBirthday.setDate(e.getBirthday());
        txtPhone.setText(e.getPhoneNumber());
        txtEmail.setText(e.getEmail());
        txtIDCard.setText(e.getIDCard());
        txtNationality.setText(e.getNationality());
        txtNation.setText(e.getNation());
        txtBHYT.setText(e.getBHYT());
        txtPersonalName.setText(e.getPersonalName());
        txtPersonalPhone.setText(e.getPhoneNumber());
        txtPersonalAddress.setText(e.getAddressPersonal());

        txtIDEmployee.setText(e.getIDEmployee());
        cbIDDepartment.getModel().setSelectedItem(e.getIDDepartment());
        dcWorkingDate.setDate(e.getWorkingDate());
        txtStatus.setText(Integer.toString(e.getStatus()));

        Image img = EmployeeController.showFullInfo(txtIDEmployee.getText()).getAvatar();
        if (img != null) {
            btnAvatar.setIcon(new ImageIcon(ImageProcess.resize(img, 64, 64)));
            btnAvatar.setText("");
        }
    }

    private void configComponent() {
        //Chỉ cho phép trạng thái nhập là số
        txtStatus.addKeyListener(new KeyAdapter() {
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
        // Chỉ cho phép CMND/CCCD là số
        txtIDCard.addKeyListener(new KeyAdapter() {
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
        // CHỉ cho phép nhập số ở SDT
        txtPhone.addKeyListener(new KeyAdapter() {
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
        txtPersonalPhone.addKeyListener(new KeyAdapter() {
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
        // Không cho sửa ID Nhân viên
        txtIDEmployee.setEditable(false);
        // Thêm dữ liệu cho ComboBox giới tính
        cbSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
        // Lấy dữ liệu tên Phòng ban vào ComboBox
        ArrayList<String> arr = DepartmentController.getListDepartment();
        for (String string : arr) {
            cbIDDepartment.addItem(string);
        }
    }

    public EmployeeDialog(Employee e) {
        initComponents();
        configComponent();
        MetroUI.applySpecilized(btnSave);
        MetroUI.apply(btnClear);

        if (e != null) {
            add = false;
            showEmployee(e);

        } else {
            lbIDEmployee.setVisible(false);
            txtIDEmployee.setVisible(false);
            cleanInformation();
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

    private String checkSyntax() {
        if ("".equals(txtSurname.getText())) {
            return ("Vui lòng nhập họ, tên đệm\n");
        }
        if ("".equals(txtName.getText())) {
            return ("Vui lòng nhập tên\n");
        }
        if ("".equals(txtIDCard.getText())) {
            return ("Vui lòng nhập CMND/CCCD\n");
        }
        if ("".equals(cbSex.getSelectedItem().toString().trim())) {
            return ("Vui lòng nhập giới tính\n");
        }
        if ("".equals(txtStatus.getText())) {
            return ("Vui lòng nhập trạng thái\n");
        }
        if (dcBirthday.getDate() == null) {
            return ("Ngày sinh bị sai\n");
        }
        if (dcWorkingDate.getDate() == null) {
            return ("Ngày vào làm bị sai\n");
        }
        if (cbIDDepartment.getSelectedItem() == null) {
            return ("Vui lòng chọn số phòng");
        }
        return ("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        txtRoom3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbIDEmployee = new javax.swing.JLabel();
        txtIDEmployee = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        dcBirthday = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtIDCard = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        cbSex = new javax.swing.JComboBox<>();
        dcWorkingDate = new com.toedter.calendar.JDateChooser();
        txtEmail = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtBHYT = new javax.swing.JTextField();
        panelWE = new javax.swing.JPanel();
        btnAvatar = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtNationality = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtNation = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbIDDepartment = new javax.swing.JComboBox<>();
        txtStatus = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtPersonalName = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtPersonalPhone = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtPersonalAddress = new javax.swing.JTextField();

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
        jLabel1.setText("Thông tin Nhân viên");

        lbIDEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbIDEmployee.setText("Mã số sinh viên");

        txtIDEmployee.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtIDEmployee.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Họ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày sinh");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Ngày vào làm");

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

        dcBirthday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcBirthday.setDate(new Date());
        dcBirthday.setDateFormatString("dd/MM/yy");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Địa chỉ ");

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtAddress.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("CMND / CCCD");

        txtPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhone.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("SÐT");

        txtIDCard.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtIDCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        txtSurname.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtSurname.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        txtName.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        cbSex.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbSex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dcWorkingDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dcWorkingDate.setDate(new Date());
        dcWorkingDate.setDateFormatString("dd/MM/yy");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Email");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Số BHYT");

        txtBHYT.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtBHYT.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel23)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIDEmployee)
                                    .addComponent(txtIDEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDCard, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(dcBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSex, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(dcWorkingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))))
                        .addGap(30, 30, 30))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dcWorkingDate, txtPhone});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lbIDEmployee)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(7, 7, 7)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCard))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSex, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcWorkingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(8, 8, 8)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dcBirthday, txtIDEmployee, txtSurname});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtBHYT, txtPhone});

        panelWE.setBackground(new java.awt.Color(255, 255, 255));

        btnAvatar.setText("Ảnh chụp");
        btnAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvatarActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Quốc tịch");

        txtNationality.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtNationality.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Dân tộc");

        txtNation.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtNation.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Phòng ban");

        cbIDDepartment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbIDDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbIDDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDDepartmentActionPerformed(evt);
            }
        });

        txtStatus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtStatus.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Trạng thái");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setText("Thông tin nhân thân");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Tên nhân thân");

        txtPersonalName.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtPersonalName.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("SÐT nhân thân");

        txtPersonalPhone.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtPersonalPhone.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Địa chỉ nhân thân");

        txtPersonalAddress.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        txtPersonalAddress.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10)));

        javax.swing.GroupLayout panelWELayout = new javax.swing.GroupLayout(panelWE);
        panelWE.setLayout(panelWELayout);
        panelWELayout.setHorizontalGroup(
            panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWELayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel33)
                    .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelWELayout.createSequentialGroup()
                            .addComponent(btnAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNation, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cbIDDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtStatus)
                    .addComponent(txtPersonalName)
                    .addComponent(txtPersonalPhone)
                    .addComponent(txtPersonalAddress))
                .addGap(30, 30, 30))
        );
        panelWELayout.setVerticalGroup(
            panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWELayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelWELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelWELayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbIDDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel33)
                .addGap(8, 8, 8)
                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPersonalName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPersonalPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPersonalAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        panelWELayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtPersonalAddress, txtPersonalName, txtPersonalPhone, txtStatus});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelWE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        // Kiểm tra Các thoogn tin cần nhập và nhập đúng
        sb.append(checkSyntax());
        if (sb.length() > 0) {
            JOptionPane.showConfirmDialog(rootPane, sb, "Lỗi", JOptionPane.DEFAULT_OPTION);
            return;
        }

        // Tiến hành tạo một đối tượng để thêm
        Employee e = new Employee();
        e.setSurname(txtSurname.getText());
        e.setName(txtName.getText());
        e.setAddress(txtAddress.getText());
        e.setSex(cbSex.getSelectedItem().toString().trim());
        e.setBirthday(dcBirthday.getDate());
        e.setPhoneNumber(txtPhone.getText());
        e.setEmail(txtEmail.getText());
        e.setIDCard(txtIDCard.getText());
        e.setNationality(txtNationality.getText());
        e.setNation(txtNation.getText());
        e.setBHYT(txtBHYT.getText());
        e.setPersonalName(txtPersonalName.getText());
        e.setPhoneNumber(txtPersonalPhone.getText());
        e.setAddressPersonal(txtPersonalAddress.getText());
        System.out.println(txtIDEmployee.getText());

        // Xử lí ảnh
        if (URL != null) {
            e.setAvatar(ImageProcess.readImage(URL));
        } else if (!"".equals(txtIDEmployee.getText().trim())) {
            e.setAvatar(EmployeeController.showFullInfo(txtIDEmployee.getText()).getAvatar());
        }

        e.setIDEmployee(txtIDEmployee.getText());
        e.setIDDepartment(cbIDDepartment.getSelectedItem().toString().trim());
        e.setWorkingDate(dcWorkingDate.getDate());
        e.setStatus(txtStatus.getText());
        if (add) {
            if (EmployeeController.insData(e)) {
                JOptionPane.showConfirmDialog(rootPane, "Thêm thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);
                // Thêm tài khoản mới
                EmployeeController.createAccount(e.getIDCard());
            } else {
                JOptionPane.showConfirmDialog(rootPane, "Thêm thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
            }
        } else {
            if (EmployeeController.updateData(e)) {
                // Xóa tài khoản cũ
                EmployeeController.delAccount(oldCMND);
                // Thêm tài khoản mới 
                EmployeeController.createAccount(e.getIDCard());
                JOptionPane.showConfirmDialog(rootPane, "Sửa thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);

            } else {
                JOptionPane.showConfirmDialog(rootPane, "Sửa thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
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

    private void btnAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvatarActionPerformed
        try {
            // Lấy ảnh từ máy
            JFileChooser f = new JFileChooser("D:\\Image");
            f.showOpenDialog(null);
            f.setDialogTitle("Chọn ảnh");
            File fileImg = f.getSelectedFile();

            URL = fileImg.getAbsolutePath();
            //Xử lí ảnh
            if (URL != null) {
                Image img = ImageProcess.readImage(URL);
                img = ImageProcess.resize(img, 100, 100);
                btnAvatar.setIcon(new ImageIcon(img));
                btnAvatar.setText("");
            }
        } catch (Exception e) {
            MetroUI.notificate(
                "Lỗi",
                "Không tìm được hình ảnh",
                TrayIcon.MessageType.ERROR
            );
        }
    }//GEN-LAST:event_btnAvatarActionPerformed

    private void cbIDDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIDDepartmentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvatar;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbIDDepartment;
    private javax.swing.JComboBox<String> cbSex;
    private com.toedter.calendar.JDateChooser dcBirthday;
    private com.toedter.calendar.JDateChooser dcWorkingDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIDEmployee;
    private javax.swing.JPanel panelWE;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBHYT;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIDCard;
    private javax.swing.JTextField txtIDEmployee;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNation;
    private javax.swing.JTextField txtNationality;
    private javax.swing.JTextField txtPersonalAddress;
    private javax.swing.JTextField txtPersonalName;
    private javax.swing.JTextField txtPersonalPhone;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtRoom3;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables

    private void clear() {
        Container con = this.getContentPane();
        for (Component c : con.getComponents()) {
            if (c instanceof JTextField) {
                JTextField j = (JTextField) c;
                j.setText("");
            }
        }
    }

}
