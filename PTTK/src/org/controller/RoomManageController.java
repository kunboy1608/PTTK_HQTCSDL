package org.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import org.model.*;

public class RoomManageController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<Room> arr = null;

    public static DefaultTableModel getModel() {
        return model;
    }

    public static int getCapacity(String numRoom, String IDBuilding) {
        try {
            numRoom = numRoom.trim();
            IDBuilding = IDBuilding.trim();
            Statement stmt = User.getConnection().createStatement();
            String sql = "Select SoNguoiO from hqtcsdl.PhongO where IDPhongO='"
                    + numRoom + IDBuilding + "')";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("SONGUOIO");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy số người ở");
            return 0;
        }
    }

    public static ArrayList<String> getListNumRoomEmpty(String IDBuilding) {
        try {
            ArrayList<String> a = new ArrayList<>();
            String sql = "Select IDPhongO from hqtcsdl.PhongO "
                    + "Where (IDtoa='"
                    + IDBuilding.trim() + "' "
                    + "and ConTrong !=0 )";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String temp;
            while (rs.next()) {
                temp = rs.getString("IDPHONGO");
                temp = temp.trim();
                Matcher matcher = Pattern.compile("(?:[A-Z]+)").matcher(temp);
                if (!matcher.find()) {
                    throw new NullPointerException("Wrong Format");
                }
                a.add(temp.substring(0, matcher.start()));
            }
            return a;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở lấy danh sách phòng");
            return null;
        }
    }

    public static ArrayList<String> getListNumRoom(String IDBuilding) {
        try {
            ArrayList<String> a = new ArrayList<>();
            String sql = "Select IDPhongO from hqtcsdl.PhongO "
                    + "Where (IDtoa='"
                    + IDBuilding.trim() + "')";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String temp;
            while (rs.next()) {
                temp = rs.getString("IDPHONGO");
                temp = temp.trim();
                Matcher matcher = Pattern.compile("(?:[A-Z]+)").matcher(temp);
                if (!matcher.find()) {
                    throw new NullPointerException("Wrong Format");
                }
                a.add(temp.substring(0, matcher.start()));
            }
            return a;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở lấy danh sách phòng");
            return null;
        }
    }

    public static ArrayList<String> getListNumRoom() {
        try {
            ArrayList<String> a = new ArrayList<>();
            String sql = "Select IDPhongO from hqtcsdl.PhongO ";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String temp;
            while (rs.next()) {
                temp = rs.getString("IDPHONGO");
                temp = temp.trim();
                Matcher matcher = Pattern.compile("(?:[A-Z]+)").matcher(temp);
                if (!matcher.find()) {
                    throw new NullPointerException("Wrong Format");
                }
                a.add(temp.substring(0, matcher.start()));
            }
            return a;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở lấy danh sách phòng");
            return null;
        }
    }

    public static void initialData() {
        try {
            arr = new ArrayList<>();
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * From HQTCSDL.PHONGO");

            while (rs.next()) {
                Room r = new Room();
                r.setIDBuilding(rs.getString("IDTOA"));
                r.setIDRoom(rs.getString("IDPHONGO"));
                r.setFacilities(rs.getString("COSOVATCHAT"));
                r.setKind(rs.getString("LOAIPHONG"));
                r.setCapacity(rs.getInt("SONGUOIO"));
                r.setRemain(rs.getInt("CONTRONG"));
                arr.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Phòng ở");
            e.printStackTrace();
        }
    }

    public static void initialModel() {
        model = new DefaultTableModel(new Object[]{
            " ",
            "Mã tòa",
            "Số phòng",
            "Cơ sở vật chất",
            "Loại phòng",
            "Số ngưởi ở",
            "Còn trống"}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 5:
                        return Integer.class;
                    case 6:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        arr.forEach(r -> {
            model.addRow(r.toObject());
        });
    }

    public static Room showFullInfo(String numRoom, String IDBuilding) {
        initialData();
        numRoom = numRoom.trim();
        IDBuilding = IDBuilding.trim();
        for (Room r : arr) {
            if (r.getIDBuilding().equals(IDBuilding)
                    && r.getNumRoom().equals(numRoom)) {
                return r;
            }
        }
        return null;
    }

    public static boolean insData(Room r) {
        try {
            String sql = "Insert into hqtcsdl.PhongO values(?,?,?,?,?,?)";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);

            ps.setString(1, r.getIDRoom());
            ps.setString(2, r.getIDBuilding());
            ps.setString(3, r.getFacilities());
            ps.setString(4, r.getKind());
            ps.setInt(5, r.getCapacity());
            // Lúc đầu mới thêm phòng thì chưa có người ở nên trống toàn bô
            ps.setInt(6, r.getCapacity());

            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean delData(String numRoom, String IDBuilding) {
        try {
            String sql = "Delete From hqtcsdl.PhongO "
                    + "Where (IDPhongO = '"
                    + numRoom.trim() + IDBuilding.trim()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateData(Room r) {
        try {
            String sql = "Update hqtcsdl.PhongO set"
                    + "', CoSoVatChat = '" + r.getFacilities()
                    + "', LoaiPhong = '" + r.getKind()
                    + "', SoNguoiO = " + r.getCapacity()
                    + " Where (IDPhongO = '"
                    + r.getIDRoom()
                    + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareStatement(sql);

            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Do không tìm được hàm nào so sánh phù hợp nên viết lại luôn
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

    public static void searchData(
            String txtNumRoom,
            String txtIDBuilding,
            String txtFacilities,
            String txtKind,
            String txtCapacity,
            String txtRemain) {
        // Tạo mới model để lưu kết quả tìm được
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã tòa",
            "Số phòng",
            "Cơ sở vật chất",
            "Loại phòng",
            "Số ngưởi ở",
            "Còn trống"}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 5:
                        return Integer.class;
                    case 6:
                        return Integer.class;
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
            if (compareCloseTo(txtNumRoom, model.getValueAt(i, 1).toString())
                    && compareCloseTo(txtIDBuilding, model.getValueAt(i, 2).toString())
                    && compareCloseTo(txtFacilities, model.getValueAt(i, 3).toString())
                    && compareCloseTo(txtKind, model.getValueAt(i, 4).toString())
                    && compareCloseTo(txtCapacity, model.getValueAt(i, 5).toString())
                    && compareCloseTo(txtRemain, model.getValueAt(i, 6).toString())) {
                filterModel.addRow(new Object[]{
                    true,
                    model.getValueAt(i, 1).toString(),
                    model.getValueAt(i, 2).toString(),
                    model.getValueAt(i, 3).toString(),
                    model.getValueAt(i, 4).toString(),
                    model.getValueAt(i, 5).toString(),
                    model.getValueAt(i, 6).toString()
                });
            }
        }
    }

    public static DefaultTableModel getFilterModel() {
        return filterModel;
    }

    public static void unSearch() {
        filterModel = null;
    }
}
