package org.controller;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kunbo
 */
public class User {

    private static Connection con = null;
    private static String Username = null;
    private static String Password = null;

    // Phần thông tin hiển thị khi đăng nhập thành công
    // Hiện tại chưa biết để chỗ nào cả
    private static String ID;
    private static String FullName;
    private static String address;
    private static String Sex;
    private static Date birthday;
    private static String IDRoom;
    private static Image Avatar = null;
    private static String Role = null;
    private static final User instance = new User();

    public User() {
    }

    public static boolean changePass(char[] curPass, char[] newPass, char[] conPass) {
        if (!Arrays.equals(curPass, Password.toCharArray())) {
            return false;
        }
        if (!Arrays.equals(newPass, conPass)) {
            return false;
        }
        String tempPass = "";
        for (char c : newPass) {
            tempPass += c;
        }
        Password = tempPass;
        String sql = "Alter user \""
                + Username
                + "\" identified by \""
                + tempPass + "\"";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            return !ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static String getFullName() {
        return FullName;
    }

    public static String getID() {
        return ID;
    }

    public static String getAddress() {
        return address;
    }

    public static String getSex() {
        return Sex;
    }

    public static Date getBirthday() {
        return birthday;
    }

    public static String getIDRoom() {
        return IDRoom;
    }

    public static User getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return con;
    }

    public static void initialInfor() {
        try {
            if ("hqtcsdl".equals(Username) || "hdp".equals(Username)) {
                FullName = "Ban quản lí";
                return;
            }
            String sql = "Select ID, Ho, Ten, DiaChi, NgaySinh, GioiTinh, HinhAnh "
                    + "From hqtcsdl.ThongTinCoBan "
                    + "Where (CMND ='"
                    + Username
                    + "')";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ID = rs.getString("ID");
                FullName = rs.getString("HO") + " " + rs.getString("TEN");
                address = rs.getString("DIACHI");
                Sex = rs.getInt("GIOITINH") == 0 ? "Nam" : "Nữ";
                birthday = rs.getDate("NGAYSINH");
                Blob b = rs.getBlob("HINHANH");
                Avatar = ImageProcess.createImageFromBlob(b);
            }
            if (Compare.CloseTo("Sinh viên", Role)) {
                sql = "Select IDSinhVien, IDPhongO "
                        + "from hqtcsdl.SinhVien "
                        + "Where (ID='"
                        + ID
                        + "')";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    ID = rs.getString("IDSINHVIEN");
                    IDRoom = rs.getString("IDPHONGO");
                }
            } else {
                sql = "Select IDNhanVien, IDPhongNhanVien "
                        + "From hqtcsdl.NhanVien "
                        + "Where (ID='"
                        + ID
                        + "')";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    ID = rs.getString("IDNHANVIEN");
                    IDRoom = rs.getString("IDPHONGNHANVIEN");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở khỏi tạo thông tin User");
        }
    }

    public static void initialRole() {
        // Cấp quyền cho admin

        if (Compare.CloseTo(Username, "hqtcsdl") || "hdp".equals(Username)) {
            Role = "Ban quản lí";
            return;
        }
        Role = "";
        try {
            String sql = "Select ID "
                    + "From hqtcsdl.ThongTinCoBan "
                    + "Where (CMND= '"
                    + Username
                    + "')";
            int tempID = -1;
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                tempID = rs.getInt("ID");
            }
            if (tempID >= 0 && tempID < 10000) {
                sql = "Select n.IDNhanVien, p.IDQuanLi, p.TenPhong "
                        + "From hqtcsdl.NhanVien n join hqtcsdl.PhongNhanVien p "
                        + "on n.IDPhongNhanVien = p.IDPhongNhanVien "
                        + "where (ID = '"
                        + tempID
                        + "')";
                ps = con.createStatement();
                rs = ps.executeQuery(sql);
                if (rs.next()) {
                    if (rs.getInt("IDNhanVien") == rs.getInt("IDQUANLI")) {
                        Role = "Quản lí phòng " + rs.getString("TENPHONG");
                    }
                    sql = "Select count(*) "
                            + "From hqtcsdl.Toa t "
                            + "where (IDNhanVien = '"
                            + (tempID + 10000)
                            + "')";

                    System.out.println(sql);
                    ps = con.createStatement();
                    rs = ps.executeQuery(sql);
                    if (rs.next()) {
                        if (rs.getInt(1) > 0) {
                            Role += " Trưởng tòa";
                        }
                    }

                }
            } else {
                Role = "Sinh viên";
            }
        } catch (SQLException e) {
        }
    }
    // Lấy ảnh từ cơ sở dữ liệu và chuyển đổi

    public static Image getAvatar() {
        return (Avatar != null ? ImageProcess.resize(Avatar, 64, 64) : null);
    }

    public static String getRole() {
        return Role;
    }

    public static void setPassword(String p) {
        Password = p;
    }

    public static void setPassword(char[] p) {
        String tempPass = "";
        for (char c : p) {
            tempPass += c;
        }
        Password = tempPass;
    }

    public static void setUsername(String u) {
        Username = u;
    }

    public static void disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
        }
    }

    /**
     *
     * @return
     */
    public static boolean Login() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Test", Username, Password);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kết nối không thành công.");
            return false;
        }
    }
}
