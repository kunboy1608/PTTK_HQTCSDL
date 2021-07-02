package org.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.model.*;

public class BuildingController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<Building> arr = null;

    public static ArrayList<String> getListBuilding() {
        initialData();
        ArrayList<String> a = new ArrayList<>();
        arr.forEach(b -> {
            a.add(b.getIDBuilding());
        });
        return a;
    }

    public static Building showFullInfo(String IDBuilding) {
        IDBuilding = IDBuilding.trim();
        for (Building b : arr) {
            if (IDBuilding.equals(b.getIDBuilding().trim())) {
                return b;
            }
        }
        return null;
    }

    public static boolean updateData(Building b) {
        try {
            String sql = "Update hqtcsdl.Toa set "
                    + "IDNhanVien = '" + b.getIDEmployee()
                    + "', TenToa  = '" + b.getName()
                    + "', LoaiToa = '" + b.getKindInt()
                    + "' Where (IDToa = '" + b.getIDBuilding()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            User.getConnection().setAutoCommit(false);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Tòa");
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BuildingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delData(String IDBuilding) {
        try {
            String sql = "Delete hqtcsdl.Toa "
                    + "where (IDToa='"
                    + IDBuilding.trim()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Lỗi xóa tòa");
            return false;
        }
    }

    public static boolean insData(Building b) {
        try {
            String sql = "Insert into hqtcsdl.Toa values('"
                    + b.getIDBuilding() + "','"
                    + b.getIDEmployee() + "','"
                    + b.getName() + "','"
                    + b.getKindInt()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            User.getConnection().setAutoCommit(false);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở tòa");
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BuildingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void unSearch() {
        filterModel = null;
    }

    public static void searchData(
            String IDBuilding,
            String IDEmployee,
            String Name,
            String kind
    ) {
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã tòa",
            "Mã trưởng tòa",
            "Tên tòa",
            "Loại tòa",}, 0) {
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
            if (compareCloseTo(IDBuilding, model.getValueAt(i, 1).toString())
                    && compareCloseTo(IDEmployee, model.getValueAt(i, 2).toString())
                    && compareCloseTo(Name, model.getValueAt(i, 3).toString())
                    && compareCloseTo(kind, model.getValueAt(i, 4).toString())) {
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

    public static void initialModel() {
        model = new DefaultTableModel(new Object[]{
            " ",
            "Mã tòa",
            "Mã trưởng tòa",
            "Tên tòa",
            "Loại tòa",}, 0) {
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
        arr.forEach(b -> {
            model.addRow(b.toObject(false));
        });
    }

    @SuppressWarnings("empty-statement")
    public static void initialData() {
        try {
            arr = new ArrayList<>();
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * from hqtcsdl.Toa");
            while (rs.next()) {
                Building b = new Building();
                b.setIDBuilding(rs.getString("IDTOA"));
                b.setIDEmployee(rs.getString("IDNHANVIEN"));
                b.setName(rs.getString("TENTOA"));
                b.setKind(rs.getInt("LOAITOA"));
                arr.add(b);
            };
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở tòa");
            e.printStackTrace();
        }
    }
}
