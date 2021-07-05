/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates Hoang Dinh Phu 19520838
 * and open the template in the editor.
 */
package org.controller;

import org.model.Employee;
import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kunbo
 */
public class EmployeeController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<Employee> arr = null;

    // Lấy danh sách nhân viên
    public static ArrayList<String> getListIDEmployee() {
        ArrayList<String> a = new ArrayList<>();
        initialData();
        arr.forEach(e -> {
            a.add(e.getIDEmployee());
        });
        return a;
    }

    public static ArrayList<String> getListIDEmployee(String IDDepartment) {
        IDDepartment = IDDepartment.trim();
        ArrayList<String> a = new ArrayList<>();
        initialData();
        for (Employee e : arr) {
            if (e.getIDDepartment().equals(IDDepartment)) {
                a.add(e.getIDEmployee());
            }
        }
        return a;
    }

    public static DefaultTableModel getFilterModel() {
        return filterModel;
    }

    public static DefaultTableModel getModel() {
        return model;
    }

    public static Employee showFullInfo(String IDEmployee) {
        IDEmployee = IDEmployee.trim();
        for (Employee e : arr) {
            if (IDEmployee.equals(e.getIDEmployee().trim())) {
                return e;
            }
        }
        return null;
    }

    public static void searchData(
            String IDEmployee,
            String Surname,
            String Name,
            String Sex,
            String IDCard,
            String IDDepartment,
            String workingDate,
            String address) {
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã nhân viên",
            "Họ, tên đệm",
            "Tên",
            "Giới tính",
            "CMND/CCCD",
            "Mã phòng ban",
            "Ngày vào làm",
            "Địa chỉ",}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 7:
                        return Date.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        for (int i = 0; i < model.getRowCount(); i++) {
            if (Compare.CloseTo(IDEmployee, model.getValueAt(i, 1).toString())
                    && Compare.CloseTo(Surname, model.getValueAt(i, 2).toString())
                    && Compare.CloseTo(Name, model.getValueAt(i, 3).toString())
                    && Compare.CloseTo(Sex, model.getValueAt(i, 4).toString())
                    && Compare.CloseTo(IDCard, model.getValueAt(i, 5).toString())
                    && Compare.CloseTo(IDDepartment, model.getValueAt(i, 6).toString())
                    && Compare.CloseTo(workingDate, model.getValueAt(i, 7).toString())
                    && Compare.CloseTo(address, model.getValueAt(i, 8).toString())) {
                filterModel.addRow(new Object[]{
                    true,
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3),
                    model.getValueAt(i, 4),
                    model.getValueAt(i, 5),
                    model.getValueAt(i, 6),
                    model.getValueAt(i, 7),
                    model.getValueAt(i, 8)
                });
            }
        }
    }

    public static void unSearch() {
        filterModel = null;
    }

    public static boolean insData(Employee e) {
        try {
            // Kiểm tra xem Nhân viên này đã từng tồn tại chưa
            String sql = "Select count(*) "
                    + "from hqtcsdl.NhanVien n join hqtcsdl.ThongTinCoBan t "
                    + "on n.ID =  t.ID "
                    + "where (CMND ='"
                    + e.getIDCard()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    System.out.println(rs.getInt(1));
                    if (updateData(e)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            sql = "select hqtcsdl.s_ID_NhanVien.nextval from dual";
            Statement stmt = User.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            int IDtemp = -1;
            while (rs.next()) {
                IDtemp = rs.getInt(1);
            }
            if (IDtemp == -1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }

            sql = "Insert into hqtcsdl.ThongTinCoBan values('"
                    + String.valueOf(IDtemp) + "','"
                    + e.getSurname() + "','"
                    + e.getName() + "','"
                    + e.getAddress() + "',"
                    + "?,"
                    + "?,'" // Thêm ngày sinh
                    + e.getPhoneNumber() + "','"
                    + e.getEmail() + "','"
                    + e.getIDCard() + "','"
                    + e.getNationality() + "','"
                    + e.getNation() + "','"
                    + e.getBHYT() + "','"
                    + e.getPersonalName() + "','"
                    + e.getPhonePersonal() + "','"
                    + e.getAddressPersonal() + "',"
                    + "?)" // Thêm Avatar
                    ;
            System.out.println(sql);
            ps = User.getConnection().prepareStatement(sql);
            User.getConnection().setAutoCommit(false);
            ps.setInt(1, e.getSexInt());
            ps.setDate(2, new java.sql.Date(e.getBirthday().getTime()));
            if (e.getAvatar() != null) {
                byte[] b = ImageProcess.toByteArray(e.getAvatar(), "jpg");
                ps.setBinaryStream(3, new ByteArrayInputStream(b), b.length);
            } else {
                ps.setString(3, "");
            }

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }

            sql = "Insert into hqtcsdl.NhanVien values('"
                    + String.valueOf(IDtemp + 10000) + "','" // Cho này thêm IDNhanVien
                    + e.getIDDepartment() + "',"
                    + "null,'"
                    // Chỗ này là Phòng nào mà nhân viên này quản lí. Sẽ được thêm bên quản lí phòng
                    + String.valueOf(IDtemp) + "','"// Chỗ này thêm ID
                    + "?," // Chỗ này thêm ngày vào làm
                    + "?)"; // Chỗ này  thêm trạng thái
            System.out.println(sql);
            ps = User.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(e.getWorkingDate().getTime()));
            ps.setInt(2, e.getStatus());

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }

            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);

            return true;
        } catch (SQLException ex) {
            System.out.println("Lỗi câu truy vấn ở Nhân viên");
            ex.printStackTrace();
            try {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);

            } catch (SQLException exc) {
                System.out.println("Lỗi commit ở Nhân viên");
            }
            return false;
        }
    }

    public static boolean delData(String IDEmployee, String IDCard) {
        try {
            String sql = "Update hqtcsdl.NhanVien set "
                    + "TrangThai = '-1', "
                    + "IDPhongNhanVien == null "
                    + "Where (IDNhanVien ='"
                    + IDEmployee
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Lỗi xóa Nhân viên");
            return false;
        }
    }

    public static boolean updateData(Employee e) {
        try {
            // Cập nhật phần bảng Nhân viên
            String sql = "Update hqtcsdl.NhanVien set "
                    + "IDPhongNhanVien = '" + e.getIDDepartment()
                    + "', NgayVaoLam = ?"
                    + ", TrangThai = ?"
                    + " Where (IDNhanVien = '"
                    + e.getIDEmployee()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            User.getConnection().setAutoCommit(false);
            ps.setDate(1, new java.sql.Date(e.getWorkingDate().getTime()));
            ps.setInt(2, e.getStatus());
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            // Cập nhật phần bảng thông tin cơ bản
            sql = "Update hqtcsdl.ThongTinCoBan set "
                    + "Ho = '" + e.getSurname()
                    + "', Ten = '" + e.getName()
                    + "', DiaChi = '" + e.getAddress()
                    + "', GioiTinh = '" + e.getSexInt()
                    + "', NgaySinh = ?"
                    + ", SDT = '" + e.getPhoneNumber()
                    + "', Email = '" + e.getEmail()
                    + "', CMND = '" + e.getIDCard()
                    + "', QuocTich = '" + e.getNationality()
                    + "', DanToc = '" + e.getNation()
                    + "', BHYT = '" + e.getBHYT()
                    + "', TenThanNhan = '" + e.getPersonalName()
                    + "', SDTThanNhan = '" + e.getPhonePersonal()
                    + "', DiaChiThanNhan = '" + e.getAddressPersonal()
                    + "', HinhAnh = ?"
                    + " Where (ID in ("
                    + "select ID from hqtcsdl.NhanVien"
                    + " where (IDNhanVien='"
                    + e.getIDEmployee()
                    + "')" //Còn thiếu
                    + "))";
            System.out.println(sql);
            ps = User.getConnection().prepareCall(sql);
            ps.setDate(1, new java.sql.Date(e.getBirthday().getTime()));
            if (e.getAvatar() != null) {
                byte[] b = ImageProcess.toByteArray(e.getAvatar(), "jpg");
                ps.setBinaryStream(2, new ByteArrayInputStream(b), b.length);
            } else {
                ps.setString(2, "");
            }

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;

        } catch (SQLException | java.lang.NullPointerException ex) {
            System.out.println("Lỗi câu truy vấn ở Nhân viên");
            ex.printStackTrace();
            try {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
            } catch (SQLException exc) {
                System.out.println("Lỗi commit ở Nhân viên");
            }
            return false;
        }
    }

    public static void initialModel() {
        model = new DefaultTableModel(new Object[]{
            " ",
            "Mã nhân viên",
            "Họ, tên đệm",
            "Tên",
            "Giới tính",
            "CMND/CCCD",
            "Mã phòng ban",
            "Ngày vào làm",
            "Địa chỉ",}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 7:
                        return Date.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        arr.forEach(e -> {
            model.addRow(e.toObject(false));
        });
    }

    @SuppressWarnings("empty-statement")
    public static void initialData() {
        try {
            arr = new ArrayList<>();
            Statement stmt = User.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery("Select t.Ho, t.Ten, t.DiaChi, t.GioiTinh, t.NgaySinh, t.SDT, \n"
                    + "t.Email, t.CMND, t.QuocTich, t.DanToc, t.BHYT,\n"
                    + "t.TenThanNhan, t.SDTThanNhan, t.DiaChiThanNhan, t.HinhAnh,\n"
                    + "n.IDNhanVien, n.IDPhongNhanVien, n.NgayVaoLam, n.TrangThai \n"
                    + "from (select * from HQTCSDL.ThongTinCoBan where (ID < 10000)) t \n"
                    + "join (select * from HQTCSDL.NhanVien Where TrangThai = 0 ) n\n"
                    + "on n.ID = t.ID \n"
                    + "order by t.ID");

            while (rs.next()) {
                Employee e = new Employee();
                e.setSurname(rs.getString("HO"));
                e.setName(rs.getString("TEN"));
                e.setAddress(rs.getString("DIACHI"));
                e.setSex(rs.getInt("GIOITINH"));
                e.setBirthday(rs.getDate("NGAYSINH"));
                e.setPhoneNumber(rs.getString("SDT"));
                e.setEmail(rs.getString("Email"));
                e.setIDCard(rs.getString("CMND"));
                e.setNationality(rs.getString("QUOCTICH"));
                e.setNation(rs.getString("DANTOC"));
                e.setBHYT(rs.getString("BHYT"));
                e.setPersonalName(rs.getString("TENTHANNHAN"));
                e.setPhoneNumber(rs.getString("SDTTHANNHAN"));
                e.setAddressPersonal(rs.getString("DIACHITHANNHAN"));
                e.setAvatar(ImageProcess.createImageFromBlob(rs.getBlob("HINHANH")));
                e.setIDEmployee(rs.getString("IDNHANVIEN"));
                e.setIDDepartment(rs.getString("IDPHONGNHANVIEN"));
                e.setWorkingDate(rs.getDate("NGAYVAOLAM"));
                e.setStatus(rs.getInt("TRANGTHAI"));
//                if (User.getRole().contains("Quản lí phòng")) {
//                    if (e.getIDDepartment().equals(User.getIDRoom().trim())) {
//                        arr.add(e);
//                    }
//                } else {
//                    arr.add(e);
//                }
                arr.add(e);
            };
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Nhân viên");
        }
    }
}
