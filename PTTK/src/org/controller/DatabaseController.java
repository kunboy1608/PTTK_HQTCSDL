package org.controller;

import java.awt.Image;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.resources.ImageProcess;

public class DatabaseController {

    private static Connection con = null;
    private static String Username = null;
    private static String Password = null;

    // Phần thông tin hiển thị khi đăng nhập thành công
    private static String ID;
    private static String FullName;
    private static String address;
    private static String Sex;
    private static Date birthday;
    private static String IDRoom;
    private static Image Avatar = null;
    private static String Role = null;
    private static final DatabaseController instance = new DatabaseController();

    public DatabaseController() {

    }

    public static boolean changePass(String curPass, String newPass, String conPass) {
        curPass = curPass.trim();
        newPass = newPass.trim();
        conPass = conPass.trim();
        if (!curPass.equals(Password)) {
            return false;
        }
        if (!newPass.equals(conPass)) {
            return false;
        }
        String sql = "Alter user \""
                + Username
                + "\" identified by \""
                + newPass + "\"";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            return !ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("4");
            return false;
        }

    }

    public static String getFullName() {
        return FullName;
    }
    
    public static void setFullName() throws SQLException {
        String sql = "Select Ho, Ten "
                + "From hqtcsdl.ThongTinCoBan "
                + "Where (CMND ="
                + Username
                + ")";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            FullName = rs.getString("Ho") + " " + rs.getString("Ten");
        }
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

    public static DatabaseController getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return con;
    }

    private static boolean compareCloseTo(String subString, String containString) {
        // Nếu chuỗi con null --> Tìm với mọi kết quả
        if (subString == null) {
            return true;
        }
        // Chuỗi mẹ null --> thoát
        if (containString == null) {
            return false;
        }
        // Xử lí UpCase với trim để được chuỗi phù hợp nhất
        containString = containString.trim();
        containString = containString.toUpperCase();
        subString = subString.trim();
        subString = subString.toUpperCase();

        // Trả về kết quả chuỗi con nằm ? thuộc chuỗi mẹ 
        return containString.contains(subString);
    }
    
    public static void initialAvatar() throws SQLException, IOException {
        String sql = "Select HinhAnh "
                + "From hqtcsdl.ThongTinCoBan "
                + "Where (CMND= '"
                + Username
                + "' and HinhAnh is not null)";
       
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            Blob b = rs.getBlob(1);
            byte[] arr = ImageProcess.BlobToByteArray(b);
            Avatar = ImageProcess.createImageFromByteArray(arr, "jpg");
        }
    }

    public static void initialInfor() throws SQLException {
        if (Username == "hqtcsdl") {
            FullName = "admin";
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
        if (compareCloseTo("Sinh viên", Role)) {
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
    }

    public static void setRole() throws SQLException {
        // Cấp quyền cho admin
        if (compareCloseTo(Username, "hqtcsdl")) {
            Role = "admin";
            System.out.println("ngulz 2");
            return;
        }
        String sql = "Select ID "
                + "From hqtcsdl.ThongTinCoBan "
                + "Where (CMND= "
                + Username
                + ")";
        int tempID = -1;
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        if (rs.next()) {
            tempID = rs.getInt("ID");
        }
        if (tempID >= 0 && tempID < 10000) {
            sql = "Select * "
                    + "From hqtcsdl.NhanVien n join hqtcsdl.PhongNhanVien p "
                    + "on n.IDPhongNhanVien = p.IDPhongNhanVien "
                    + "where (ID = "
                    + tempID
                    + ")";
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                if (rs.getInt("IDNhanVien") == rs.getInt("IDQuanli")) {
                    Role = "Quản lí phòng " + rs.getString("TenPhong");
                } else {
                    Role = rs.getString("TenPhong");
                }
            }
        } else {
            Role = "Sinh viên";
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
            System.out.println("Kết nối không thành công.");
            return false;
        }
    }
}
