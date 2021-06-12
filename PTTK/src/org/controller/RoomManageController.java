/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

/**
 *
 * @author kunbo
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class RoomManageController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm

    public static DefaultTableModel getModel() {
        return model;
    }

    // Cơ sở dữ liệu sẽ lưu mã phòng dạng <Số phòng> + <Mã tòa>. Ví dụ: 123A3
    // Hàm numRoom có nhiệm vụ tách lấy phần số phòng
    private static String numRoom(String IDRoom, String IDBuilding) {
        char[] ch = new char[IDRoom.length()];
        // Lấy số phòng (kiểu dữ liệu char[])
        IDRoom.getChars(0, IDRoom.indexOf(IDBuilding), ch, 0);

        // Chuyển char[] thành String
        String numRoom = "";
        for (char c : ch) {
            numRoom += c;
        }
        return numRoom;
    }

    public static void initialModel() throws SQLException {

        model = new DefaultTableModel(new Object[]{
            " ",
            "Mã phòng",
            "Tòa",
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
                if (column == 0) {
                    return true;
                }
                return false;
            }
        };

        Statement stmt = User.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select * From HQTCSDL.PHONGO");

        while (rs.next()) {
            model.addRow(new Object[]{
                false,
                numRoom(rs.getString("IDPHONGO").trim(), rs.getString("IDTOA").trim()),
                rs.getString("IDTOA").trim(),
                rs.getString("COSOVATCHAT").trim(),
                rs.getString("LOAIPHONG").trim(),
                rs.getInt("SONGUOIO"),
                rs.getInt("CONTRONG")
            });
        }
    }

    private static void addRowToModel(
            String txtNumRoom,
            String txtIDBuilding,
            String txtValue,
            String txtKind,
            String txtStatus) {
        model.addRow(new Object[]{
            false,
            txtNumRoom,
            txtIDBuilding,
            txtValue,
            txtKind,
            0, // Số người ở. Vì phòng mới tạo nên mặc dịnh chưa có người ở
            txtStatus
        });

        // Nếu đang trạng thái tìm kiếm thì cũng thêm vào để người dùng còn nhân ra là đã thêm
        if (filterModel != null) {
            filterModel.addRow(new Object[]{
                txtNumRoom,
                txtIDBuilding,
                txtValue,
                txtKind,
                0, // Số người ở. Vì phòng mới tạo nên mặc dịnh chưa có người ở
                txtStatus,});
        }
    }

    private static void removeRowFromModel(String numRoom, String IDBuilding) {
        // Chuỗi truyền vào 99,99% là đúng nên có thể dùng hàm só sánh gần đúng
        for (int i = 0; i < model.getRowCount(); i++) {
            if (compareCloseTo(model.getValueAt(i, 1).toString(), numRoom)
                    && compareCloseTo(model.getValueAt(i, 2).toString(), IDBuilding)) {
                model.removeRow(i);

                // Nếu đang trạng thái tìm kiếm thì cũng xóa ở cả kết quả tìm kiếm luôn
                if (filterModel == null) {
                    return;
                } else {
                    for (int j = 0; j < filterModel.getRowCount(); j++) {
                        if (compareCloseTo(filterModel.getValueAt(j, 1).toString(), numRoom)
                                && compareCloseTo(filterModel.getValueAt(j, 2).toString(), IDBuilding)) {
                            filterModel.removeRow(j);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static boolean insData(
            String txtNumRoom,
            String txtIDBuilding,
            String txtValue,
            String txtKind,
            String txtStatus) {
        try {
            String sql = "Insert into hqtcsdl.PhongO values(?,?,?,?,0,?)";
            txtIDBuilding = txtIDBuilding.toUpperCase();
            PreparedStatement ps = User.getConnection().prepareStatement(sql);

            ps.setString(1, txtNumRoom.concat(txtIDBuilding));
            ps.setString(2, txtIDBuilding);
            ps.setString(3, txtValue);
            ps.setString(4, txtKind);
            ps.setString(5, txtStatus);

            if (ps.executeUpdate() == 1) {
                addRowToModel(txtNumRoom, txtIDBuilding, txtValue, txtKind, txtStatus);
                return true;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public static boolean delData(String numRoom, String IDBuilding) {
        try {
            String sql = "Delete From hqtcsdl.PhongO "
                    + "Where (IDPhongO = '"
                    + numRoom + IDBuilding
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            if (ps.executeUpdate() == 1) {
                removeRowFromModel(numRoom, IDBuilding);
                return true;
            }

        } catch (SQLException e) {
            return false;
        }
        // Return chỗ này cho nó khỏi báo lỗi thôi
        return true;
    }

    public static boolean updateData(
            String txtNumRoom,
            String txtIDBuilding,
            String txtValue,
            String txtKind,
            String txtStatus) {
        try {
            txtIDBuilding = txtIDBuilding.toUpperCase();
            String sql = "Update hqtcsdl.PhongO set"
                    + " IDToa = '" + txtIDBuilding
                    + "', CoSoVatChat = '" + txtValue
                    + "', LoaiPhong = '" + txtKind
                    + "', TrangThai = " + txtStatus
                    + " Where (IDPhongO = '"
                    + txtNumRoom + txtIDBuilding
                    + "')";

            PreparedStatement ps = User.getConnection().prepareStatement(sql);

            if (ps.executeUpdate() == 1) {
                // Xóa cột cũ trong model
                removeRowFromModel(txtNumRoom, txtIDBuilding);
                // Thêm cột mới vào model
                addRowToModel(txtNumRoom, txtIDBuilding, txtValue, txtKind, txtStatus);
                return true;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
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
            String txtValue,
            String txtKind,
            String txtNumber,
            String txtStatus) {
        // Tạo mới model để lưu kết quả tìm được
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã phòng",
            "Tòa",
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
                if (column == 0) {
                    return true;
                }
                return false;
            }
        };

        for (int i = 0; i < model.getRowCount(); i++) {
            if (compareCloseTo(txtNumRoom, model.getValueAt(i, 1).toString())
                    && compareCloseTo(txtIDBuilding, model.getValueAt(i, 2).toString())
                    && compareCloseTo(txtValue, model.getValueAt(i, 3).toString())
                    && compareCloseTo(txtKind, model.getValueAt(i, 4).toString())
                    && compareCloseTo(txtNumber, model.getValueAt(i, 5).toString())
                    && compareCloseTo(txtStatus, model.getValueAt(i, 6).toString())) {
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
