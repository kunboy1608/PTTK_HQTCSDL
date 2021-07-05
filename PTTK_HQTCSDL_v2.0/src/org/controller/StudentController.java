/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import org.model.Student;
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
public class StudentController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<Student> arr = null;

    private StudentController() {
    }

    public static ArrayList<String> getComboboxListItem() {
        ArrayList<String> a = new ArrayList<>();
        initialData();
        for (Student s : arr) {
            a.add(s.getIDStudent());
        }
        return a;
    }

    public static DefaultTableModel getFilterModel() {
        return filterModel;
    }

    public static DefaultTableModel getModel() {
        return model;
    }

    public static Student showFullInfo(String IDStudent) {
        IDStudent = IDStudent.trim();
        for (Student s : arr) {
            if (IDStudent.equals(s.getIDStudent().trim())) {
                return s;
            }
        }
        return null;
    }

    public static void searchData(
            String IDStudent,
            String Surname,
            String Name,
            String Sex,
            String IDRoom,
            String IDCard,
            String IDSchool,
            String expDate,
            String address) {
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã sinh viên",
            "Họ, tên đệm",
            "Tên",
            "Giới tính",
            "Phòng",
            "CMND/CCCD",
            "Mã trường",
            "Ngày hết hạn",
            "Địa chỉ",}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 8:
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
            if (Compare.CloseTo(IDStudent, model.getValueAt(i, 1).toString())
                    && Compare.CloseTo(Surname, model.getValueAt(i, 2).toString())
                    && Compare.CloseTo(Name, model.getValueAt(i, 3).toString())
                    && Compare.CloseTo(Sex, model.getValueAt(i, 4).toString())
                    && Compare.CloseTo(IDRoom, model.getValueAt(i, 5).toString())
                    && Compare.CloseTo(IDCard, model.getValueAt(i, 6).toString())
                    && Compare.CloseTo(IDSchool, model.getValueAt(i, 7).toString())
                    && Compare.CloseTo(expDate, model.getValueAt(i, 8).toString())
                    && Compare.CloseTo(address, model.getValueAt(i, 9).toString())) {
                filterModel.addRow(new Object[]{
                    true,
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3),
                    model.getValueAt(i, 4),
                    model.getValueAt(i, 5),
                    model.getValueAt(i, 6),
                    model.getValueAt(i, 7),
                    model.getValueAt(i, 8),
                    model.getValueAt(i, 9)
                });
            }
        }
    }

    public static void unSearch() {
        filterModel = null;
    }

    public static boolean insData(Student s) {
        try {
            // Kiểm tra xem Sinh viên này đã từng tồn tại chưa
            String sql = "Select count(*) "
                    + "from hqtcsdl.SinhVien s join hqtcsdl.ThongTinCoBan t "
                    + "on s.ID =  t.ID "
                    + "where (CMND ='"
                    + s.getIDCard()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    System.out.println(rs.getInt(1));
                    if (updateData(s)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            sql = "select hqtcsdl.s_ID_SinhVien.nextval from dual";
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
                    + s.getSurname() + "','"
                    + s.getName() + "','"
                    + s.getAddress() + "',"
                    + "?,"
                    + "?,'" // Thêm ngày sinh
                    + s.getPhoneNumber() + "','"
                    + s.getEmail() + "','"
                    + s.getIDCard() + "','"
                    + s.getNationality() + "','"
                    + s.getNation() + "','"
                    + s.getBHYT() + "','"
                    + s.getPersonalName() + "','"
                    + s.getPhonePersonal() + "','"
                    + s.getAddressPersonal() + "',"
                    + "?)" // Thêm Avatar
                    ;
            System.out.println(sql);
            ps = User.getConnection().prepareStatement(sql);
            User.getConnection().setAutoCommit(false);
            ps.setInt(1, s.getSexInt());
            ps.setDate(2, new java.sql.Date(s.getBirthday().getTime()));
            if (s.getAvatar() != null) {
                byte[] b = ImageProcess.toByteArray(s.getAvatar(), "jpg");
                ps.setBinaryStream(3, new ByteArrayInputStream(b), b.length);
            } else {
                ps.setString(3, "");
            }

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }

            sql = "Insert into hqtcsdl.SinhVien values('"
                    + String.valueOf(IDtemp + 10000) + "','" // Cho này thêm IDSinhVien
                    + String.valueOf(IDtemp) + "','"// Chỗ này thêm ID
                    + s.getIDRoom() + "','"
                    + s.getIDSchool() + "',"
                    + "?," // Chỗ này thêm ngày vào
                    + "?," // Chỗ này thêm ngày hết hạn
                    + "?)"; // Chỗ này  thêm trạng thái
            System.out.println(sql);
            ps = User.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(s.getComeDate().getTime()));
            ps.setDate(2, new java.sql.Date(s.getExpDate().getTime()));
            ps.setInt(3, s.getStatus());

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }

            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);

            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở Sinh viên");
            e.printStackTrace();
            try {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);

            } catch (SQLException ex) {
                System.out.println("Lỗi commit ở Sinh viên");
            }
            return false;
        }
    }

    public static boolean delData(String IDStudent, String IDCard) {
        try {
            String sql = "Update hqtcsdl.SINHVIEN set "
                    + "TrangThai = '-1', "
                    + "IDTruong = null, "
                    + "IDPhongO = null "
                    + "Where (IDSinhvien ='"
                    + IDStudent
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            if (ps.executeUpdate() != 1) {
                return false;
            }
            delAccount(IDCard);
            return true;

        } catch (SQLException e) {
            System.out.println("Lỗi xóa Sinh viên");
            return false;
        }
    }

    public static void delAccount(String IDCard) {
        try {
            String sql = "Drop user \""
                    + IDCard + "\" ";
            CallableStatement cs = User.getConnection().prepareCall(sql);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Lỗi xóa tài khoản Sinh viên");
        }
    }

    public static void createAccount(String IDCard) {
        try {
            String sql = "Create user \""
                    + IDCard.trim() + "\" "
                    + "identified by \"111111\"";

            System.out.println(sql);
            CallableStatement cs = User.getConnection().prepareCall(sql);
            cs.execute();

            sql = "Grant r_SinhVien to "
                    + " \""
                    + IDCard.trim() + "\" ";
            System.out.println(sql);
            cs = User.getConnection().prepareCall(sql);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Lỗi tạo tài khoản Student");
            e.printStackTrace();
        }
    }

    public static boolean updateData(Student s) {
        try {
            // Cập nhật phần bảng SinhVien
            String sql = "Update hqtcsdl.SinhVien set "
                    + "IDPhongO = '" + s.getIDRoom()
                    + "', IDTruong = '" + s.getIDSchool()
                    + "', NgayVao = ?"
                    + ", NgayHetHan = ?"
                    + ", TrangThai = ?"
                    + " Where (IDSinhVien = '"
                    + s.getIDStudent()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            User.getConnection().setAutoCommit(false);
            ps.setDate(1, new java.sql.Date(s.getComeDate().getTime()));
            ps.setDate(2, new java.sql.Date(s.getExpDate().getTime()));
            ps.setInt(3, s.getStatus());

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            // Cập nhật phần bảng thông tin cơ bản
            sql = "Update hqtcsdl.ThongTinCoBan set "
                    + "Ho = '" + s.getSurname()
                    + "', Ten = '" + s.getName()
                    + "', DiaChi = '" + s.getAddress()
                    + "', GioiTinh = '" + s.getSexInt()
                    + "', NgaySinh = ?"
                    + ", SDT = '" + s.getPhoneNumber()
                    + "', Email = '" + s.getEmail()
                    + "', CMND = '" + s.getIDCard()
                    + "', QuocTich = '" + s.getNationality()
                    + "', DanToc = '" + s.getNation()
                    + "', BHYT = '" + s.getBHYT()
                    + "', TenThanNhan = '" + s.getPersonalName()
                    + "', SDTThanNhan = '" + s.getPhonePersonal()
                    + "', DiaChiThanNhan = '" + s.getAddressPersonal()
                    + "', HinhAnh = ?"
                    + " Where (ID in ("
                    + "select ID from hqtcsdl.SinhVien"
                    + " where (IDSinhVien='"
                    + s.getIDStudent()
                    + "')" //Còn thiếu
                    + "))";
            System.out.println(sql);
            ps = User.getConnection().prepareCall(sql);
            ps.setDate(1, new java.sql.Date(s.getBirthday().getTime()));
            if (s.getAvatar() != null) {
                byte[] b = ImageProcess.toByteArray(s.getAvatar(), "jpg");
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

        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở Sinh viên");
            e.printStackTrace();
            try {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
            }
            return false;
        }
    }

    public static void initialModel() {
        model = new DefaultTableModel(new Object[]{
            " ",
            "Mã sinh viên",
            "Họ, tên đệm",
            "Tên",
            "Giới tính",
            "Phòng",
            "CMND/CCCD",
            "Mã trường",
            "Ngày hết hạn",
            "Địa chỉ",}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 8:
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
        arr.forEach(s -> {
            model.addRow(s.toObject(false));
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
                    + "s.IDSinhVien, s.IDPhongO, s.IDTruong, s.NgayVao, s.NgayHetHan, s.TrangThai \n"
                    + "from (select * from HQTCSDL.ThongTinCoBan where (ID >= 10000)) t \n"
                    + "join (select * from HQTCSDL.SinhVien Where TrangThai = 0 ) s\n"
                    + "on s.ID = t.ID \n"
                    + "order by t.ID");

            while (rs.next()) {
                Student s = new Student();
                s.setSurname(rs.getString("HO"));
                s.setName(rs.getString("TEN"));
                s.setAddress(rs.getString("DIACHI"));
                s.setSex(rs.getInt("GIOITINH"));
                s.setBirthday(rs.getDate("NGAYSINH"));
                s.setPhoneNumber(rs.getString("SDT"));
                s.setEmail(rs.getString("Email"));
                s.setIDCard(rs.getString("CMND"));
                s.setNationality(rs.getString("QUOCTICH"));
                s.setNation(rs.getString("DANTOC"));
                s.setBHYT(rs.getString("BHYT"));
                s.setPersonalName(rs.getString("TENTHANNHAN"));
                s.setPhoneNumber(rs.getString("SDTTHANNHAN"));
                s.setAddressPersonal(rs.getString("DIACHITHANNHAN"));
                s.setAvatar(ImageProcess.createImageFromBlob(rs.getBlob("HINHANH")));
                s.setIDStudent(rs.getString("IDSINHVIEN"));
                s.setIDRoom(rs.getString("IDPHONGO"));
                s.setIDSchool(rs.getString("IDTRUONG"));
                s.setComeDate(rs.getDate("NGAYVAO"));
                s.setExpDate(rs.getDate("NGAYHETHAN"));
                s.setStatus(rs.getInt("TRANGTHAI"));
                arr.add(s);
            };
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Sinh Viên");
        }
    }
}
