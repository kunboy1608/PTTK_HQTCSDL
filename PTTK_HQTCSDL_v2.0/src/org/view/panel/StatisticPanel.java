package org.view.panel;

import java.awt.Color;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jiconfont.IconFontSwing;
import jiconfont.icons.GoogleMaterialDesignIcons;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controller.User;
import org.resources.MetroUI;
import org.view.dialog.StudentDialog;

public class StatisticPanel extends JPanel {

    private static final StatisticPanel instace = new StatisticPanel("Thống kê");
    private static final String PLACEHOLDER = "Nhập mã trường";

    public static StatisticPanel getInstance() {
        return instace;
    }

    private StatisticPanel(String title) {
        initComponents();
        lbTitle.setText(title);
        drawUI();
    }

    private void drawUI() {
        MetroUI.apply(btnListDN);
        MetroUI.apply(btnListEmployee);
        MetroUI.apply(btnListEmptyRoom);
        MetroUI.apply(btnListStudent);
        MetroUI.apply(btnPieChart);
        MetroUI.apply(btnValue);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        btnListStudent = new javax.swing.JButton();
        btnListEmptyRoom = new javax.swing.JButton();
        btnListEmployee = new javax.swing.JButton();
        btnListDN = new javax.swing.JButton();
        btnPieChart = new javax.swing.JButton();
        btnValue = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        lbTitle.setText("Thống Kê");

        btnListStudent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnListStudent.setText("Danh sách sinh viên");
        btnListStudent.setToolTipText("Thêm dữ liệu");
        btnListStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnListStudent.setContentAreaFilled(false);
        btnListStudent.setPreferredSize(new java.awt.Dimension(50, 50));
        btnListStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListStudentActionPerformed(evt);
            }
        });

        btnListEmptyRoom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnListEmptyRoom.setText("Danh sách phòng trống");
        btnListEmptyRoom.setToolTipText("Thêm dữ liệu");
        btnListEmptyRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnListEmptyRoom.setContentAreaFilled(false);
        btnListEmptyRoom.setPreferredSize(new java.awt.Dimension(50, 50));
        btnListEmptyRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListEmptyRoomActionPerformed(evt);
            }
        });

        btnListEmployee.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnListEmployee.setText("Danh sách nhân viên");
        btnListEmployee.setToolTipText("Thêm dữ liệu");
        btnListEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnListEmployee.setContentAreaFilled(false);
        btnListEmployee.setPreferredSize(new java.awt.Dimension(50, 50));
        btnListEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListEmployeeActionPerformed(evt);
            }
        });

        btnListDN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnListDN.setText("Danh sách hóa đơn điện nước >= X VND");
        btnListDN.setToolTipText("Thêm dữ liệu");
        btnListDN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnListDN.setContentAreaFilled(false);
        btnListDN.setPreferredSize(new java.awt.Dimension(50, 50));
        btnListDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListDNActionPerformed(evt);
            }
        });

        btnPieChart.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnPieChart.setText("Biểu đồ sinh viên từng trường");
        btnPieChart.setToolTipText("Thêm dữ liệu");
        btnPieChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnPieChart.setContentAreaFilled(false);
        btnPieChart.setPreferredSize(new java.awt.Dimension(50, 50));
        btnPieChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPieChartActionPerformed(evt);
            }
        });

        btnValue.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnValue.setText("Doanh thu theo tháng của năm X");
        btnValue.setToolTipText("Thêm dữ liệu");
        btnValue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnValue.setContentAreaFilled(false);
        btnValue.setPreferredSize(new java.awt.Dimension(50, 50));
        btnValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTitle)
                    .addComponent(btnListEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(btnListEmptyRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addComponent(btnPieChart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListDN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbTitle)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListEmptyRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
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

    private void btnListStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListStudentActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd = JRXmlLoader.load("src/org/report/DanhSachSinhVien.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap hm = new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListStudentActionPerformed

    private void btnListEmptyRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListEmptyRoomActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd = JRXmlLoader.load("src/org/report/DanhSachPhongTrong.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap hm = new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListEmptyRoomActionPerformed

    private void btnListEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListEmployeeActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd = JRXmlLoader.load("src/org/report/DanhSachNhanVien.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap hm = new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListEmployeeActionPerformed

    private void btnListDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListDNActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd = JRXmlLoader.load("src/org/report/Para_DienNuoc.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap hm = new HashMap();
            String s = JOptionPane.showInputDialog("Nhập X:");
            hm.put("value", Integer.valueOf(s.trim()));
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListDNActionPerformed

    private void btnPieChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPieChartActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd = JRXmlLoader.load("src/org/report/pie_chart_Truong.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap hm = new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPieChartActionPerformed

    private void btnValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValueActionPerformed
        try {
            // TODO add your handling code here:
            JasperDesign jd = JRXmlLoader.load("src/org/report/Para_DoanhThuThangNamX.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap hm = new HashMap();
            String s = JOptionPane.showInputDialog("Nhập X:");
            hm.put("Nam", Integer.valueOf(s));
            JasperPrint jp = JasperFillManager.fillReport(jr, hm, User.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(StudentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnValueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListDN;
    private javax.swing.JButton btnListEmployee;
    private javax.swing.JButton btnListEmptyRoom;
    private javax.swing.JButton btnListStudent;
    private javax.swing.JButton btnPieChart;
    private javax.swing.JButton btnValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbTitle;
    // End of variables declaration//GEN-END:variables

}
