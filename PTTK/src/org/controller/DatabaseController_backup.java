package org.controller;

import java.awt.Image;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.resources.ImageProcess;

/**
 *
 * @author kunbo
 */
public class DatabaseController_backup {

    private static Connection con = null;
    private static String Username = null;
    private static String Password = null;
    private static String Role = null;
    private static String FullName = null;
    private static Image Avatar = null;

    public DatabaseController_backup() { }

    public static Connection getConnection() {
        return con;
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

    public static void setRole() throws SQLException {
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
            System.out.println(sql);
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
        } catch (SQLException e) { }
    }

    /**
     *
     * @return
     */
    public static boolean Login() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Test", "hqtcsdl", "hqtcsdl");
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
