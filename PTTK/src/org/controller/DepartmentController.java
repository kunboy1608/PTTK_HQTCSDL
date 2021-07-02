package org.controller;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.model.*;

public class DepartmentController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<Department> arr = null;
    
    public static ArrayList<String> getListDepartment() {
        initialData();
        ArrayList<String> a = new ArrayList<>();
        arr.forEach(d -> {
            a.add(d.getIDDepartment());
        });
        return a;
    }

    public static void createAccount(String IDManager) {
        try {
            String sql = "Select CMND from hqtcsdl.ThongTinCoBan where (ID ='"
                    + String.valueOf(Integer.parseInt(IDManager) - 10000)
                    + "')";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tempIDCard = "";
            while (rs.next()) {
                tempIDCard = rs.getString(1);
            }
            sql = "Create user \""
                    + tempIDCard.trim() + "\" "
                    + "identified by \"111111\"";

            System.out.println(sql);
            CallableStatement cs = User.getConnection().prepareCall(sql);
            cs.execute();

            sql = "Grant r_NhanVien to "
                    + " \""
                    + tempIDCard.trim() + "\" ";
            System.out.println(sql);
            cs = User.getConnection().prepareCall(sql);
            cs.execute();

        } catch (SQLException e) {
            System.out.println("Lỗi tạo tài khoản Nhân viên");
        }
    }

    public static void delAccount(String IDManager) {
        try {
            String sql = "Select CMND from hqtcsdl.ThongTinCoBan where (ID ='"
                    + String.valueOf(Integer.parseInt(IDManager) - 10000)
                    + "')";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tempIDCard = "";
            while (rs.next()) {
                tempIDCard = rs.getString(1);
            }

            sql = "Drop user \""
                    + tempIDCard.trim() + "\" ";
            CallableStatement cs = User.getConnection().prepareCall(sql);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Lỗi xóa tài khoản Nhân viên");
        }
    }

    private static void granRole(String IDManager) {
        try {
            String sql = "Select CMND from hqtcsdl.ThongTinCoBan where (ID ='"
                    + String.valueOf(Integer.parseInt(IDManager) - 10000)
                    + "')";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tempIDCard = "";
            while (rs.next()) {
                tempIDCard = rs.getString(1);
            }
            sql = "Grant r_QuanLiPhongNhanVien to "
                    + " \""
                    + tempIDCard.trim() + "\" ";
            System.out.println(sql);
            CallableStatement cs = User.getConnection().prepareCall(sql);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Lỗi cấp quyền cho nhân viên");
        }
    }

    public static boolean updateData(Department d, String oldID) {
        try {
            String sql = "Update hqtcsdl.PhongNhanVien set "
                    + "IDPhongNhanVien = '" + d.getIDDepartment()
                    + "', IDQuanLi  = '" + d.getIDManager()
                    + "', TenPhong  = '" + d.getName()
                    + "', ViTri = '" + d.getLocation()
                    + "' Where (IDPhongNhanVien = '" + d.getIDDepartment()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            User.getConnection().setAutoCommit(false);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            // Thu hồi quyền của quản lí cũ
            delAccount(oldID);
            createAccount(oldID);
            // Cấp quyền cho quản lí mới
            granRole(d.getIDManager());

            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Phòng nhân viên");
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static boolean delData(String IDDepartment) {
        try {
            String sql = "Delete hqtcsdl.PhongNhanVien "
                    + "where (IDPhongNhanVien='"
                    + IDDepartment
                    + "')";
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            if (ps.executeUpdate() != 1) {
                return false;
            }

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean insData(Department d) {
        try {
            String sql = "Insert into hqtcsdl.PhongNhanVien values('"
                    + d.getIDDepartment() + "','"
                    + d.getIDManager() + "','"
                    + d.getName() + "','"
                    + d.getLocation()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            User.getConnection().setAutoCommit(false);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            granRole(d.getIDManager());

            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở PhongNhanVien");
            e.printStackTrace();
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static void unSearch() {
        filterModel = null;
    }

    public static void searchData(
            String IDDepartment,
            String IDManager,
            String Name,
            String location
    ) {
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã phòng",
            "Mã quản lí",
            "Tên tòa",
            "Vị trí",}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
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
            if (compareCloseTo(IDDepartment, model.getValueAt(i, 1).toString())
                    && compareCloseTo(IDManager, model.getValueAt(i, 2).toString())
                    && compareCloseTo(Name, model.getValueAt(i, 3).toString())
                    && compareCloseTo(location, model.getValueAt(i, 4).toString())) {
                filterModel.addRow(new Object[]{
                    true,
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3),
                    model.getValueAt(i, 4)
                });
            }
        }
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

    public static DefaultTableModel getFilterModel() {
        return filterModel;
    }

    public static DefaultTableModel getModel() {
        return model;
    }

    public static Department showFullInfo(String IDDepartment) {
        IDDepartment = IDDepartment.trim();
        for (Department d : arr) {
            if (d.getIDDepartment().trim().equals(IDDepartment)) {
                return d;
            }
        }
        return null;
    }

    public static void initialModel() {
        model = new DefaultTableModel(new Object[]{
            " ",
            "Mã phòng",
            "Mã quản lí",
            "Tên tòa",
            "Vị trí",}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        arr.forEach(d -> {
            model.addRow(d.toObject(false));
        });
    }

    @SuppressWarnings("empty-statement")
    public static void initialData() {
        try {
            arr = new ArrayList<>();
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * from hqtcsdl.PhongNhanVien");
            while (rs.next()) {
                Department d = new Department();
                d.setIDDepartment(rs.getString("IDPHONGNHANVIEN"));
                d.setIDManager(rs.getString("IDQUANLI"));
                d.setName(rs.getString("TENPHONG"));
                d.setLocation(rs.getString("VITRI"));
                arr.add(d);
            };
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở Phòng Nhân Viên");
        }
    }
}
