/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import org.model.Bill;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kunbo
 */
public class BillController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<Bill> arr = null;
    private static int[] priceE;
    private static int[] priceW;

    // head là số điện đầu
    // end là số điện cuối
    // nem là số người ở trong phòng
    public static int calPriceE(int head, int end, int nem) {
        int a = end - head;
        double point = (double) nem * 12.5;
        if (a <= point) {
            return (int) (a * priceE[0]);
        }
        if (a <= point * 2) {
            return (int) (point * priceE[0] + (a - point) * priceE[1]);
        }
        if (a <= point * 4) {
            return (int) (point * priceE[0] + point * priceE[1] + (a - point * 2) * priceE[2]);
        }
        if (a <= point * 6) {
            return (int) (point * priceE[0] + point * priceE[1] + point * priceE[2] + (a - point * 4) * priceE[3]);
        }
        return (int) (point * priceE[0] + point * priceE[1] + point * priceE[2] + point * priceE[3] + (a - point * 6) * priceE[4]);

    }

    public static int calPriceW(int head, int end, int num) {
        int a = end - head;
        int point = num * 4;
        if (a <= point) {
            return a * priceW[0];
        }
        if (a <= num * 6) {
            return point * priceW[0] + (a - point) * priceW[1];
        }
        return point + priceW[0] + (num * 6 - point) * priceW[1] + (a - num * 6) * priceW[2];
    }

    public static void initialPrice() {
        try {
            String sql = "Select * from hqtcsdl.BangThamSo";
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            priceE = new int[5];
            priceW = new int[3];
            for (int i = 0; i < 5; i++) {
                if (rs.next()) {
                    priceE[i] = rs.getInt("GIADIEN");
                }
                if (i >= 3) {
                    continue;
                }
                priceW[i] = rs.getInt("GIANUOC");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Bill showFullInfo(String IDBill) {
        IDBill = IDBill.trim();
        for (Bill b : arr) {
            if (b.getIDBill().trim().equals(IDBill)) {
                return b;
            }
        }
        return null;
    }

    public static boolean updateData(Bill b) {
        try {
            String sql = "Update hqtcsdl.HoaDon set "
                    + "IDNhanVien = '" + b.getIDEmployee()
                    + "', IDPhongO = '" + b.getIDRoom()
                    + "', IDSinhVien = '" + b.getIDStudent()
                    + "', NgayLap = ?"
                    + ", NgayThu = ?"
                    + ", TriGia = ?"
                    + ", GhiChu = ?"
                    + " Where (IDHoaDon = '" + b.getIDBill()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            System.out.println(sql);
            User.getConnection().setAutoCommit(false);
            ps.setDate(1, new java.sql.Date(b.getInvoiceDate().getTime()));
            ps.setDate(2, new java.sql.Date(b.getCollectionDate().getTime()));
            ps.setInt(3, b.getValue());
            ps.setString(4, b.getNote());
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            if (!"Hóa đơn điện nước".equals(b.getKind())) {
                User.getConnection().commit();
                User.getConnection().setAutoCommit(true);
                return true;
            }
            sql = "Update hqtcsdl.ChiTietDien set "
                    + "NgayBatDau = ? "
                    + ", NgayKetThuc = ?"
                    + ", SoDau = ?"
                    + ", SoCuoi = ?"
                    + ", Tong = ? "
                    + "Where (IDChiTietDien ='"
                    + b.getIDElectric() + "')";
            ps = User.getConnection().prepareCall(sql);
            ps.setDate(1, new java.sql.Date(b.getStartDateE().getTime()));
            ps.setDate(2, new java.sql.Date(b.getEndDateE().getTime()));
            ps.setInt(3, b.getHeadNumE());
            ps.setInt(4, b.getBotNumE());
            ps.setInt(5, b.getSumE());
            System.out.println(sql);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            sql = "Update hqtcsdl.ChiTietNuoc set "
                    + "NgayBatDau = ? "
                    + ", NgayKetThuc = ?"
                    + ", SoDau = ?"
                    + ", SoCuoi = ?"
                    + ", Tong = ? "
                    + "Where (IDChiTietNuoc ='"
                    + b.getIDWater() + "')";
            ps = User.getConnection().prepareCall(sql);
            ps.setDate(1, new java.sql.Date(b.getStartDateW().getTime()));
            ps.setDate(2, new java.sql.Date(b.getEndDateW().getTime()));
            ps.setInt(3, b.getHeadNumW());
            ps.setInt(4, b.getBotNumW());
            ps.setInt(5, b.getSumW());
            System.out.println(sql);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở hóa đơn");
            e.printStackTrace();
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static boolean delData(String IDBill) {
        try {
            String sql = "Delete hqtcsdl.ChiTietDien "
                    + "where (IDHoaDon='"
                    + IDBill
                    + "')";
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            User.getConnection().setAutoCommit(false);
            ps.executeUpdate();
            sql = "Delete hqtcsdl.ChiTietNuoc "
                    + "where (IDHoaDon='"
                    + IDBill
                    + "')";
            ps = User.getConnection().prepareCall(sql);
            ps.executeUpdate();
            sql = "Delete hqtcsdl.HoaDon "
                    + "where (IDHoaDon='"
                    + IDBill
                    + "')";
            ps = User.getConnection().prepareCall(sql);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String handleID(String head, int end, int length) {
        String ID = head;
        for (int i = head.length(); i < length - String.valueOf(end).length(); i++) {
            ID += "0";
        }
        return ID + String.valueOf(end);
    }

    public static boolean insData(Bill b) {
        try {
            String sql;
            switch (b.getKind()) {
                case "Hóa đơn điện nước":
                    sql = "Select hqtcsdl.s_ID_HoaDonDienNuoc.nextval from dual";
                    break;
                case "Hóa đơn phòng":
                    sql = "Select hqtcsdl.s_ID_HoaDonPhong.nextval from dual";
                    break;
                default:
                    sql = "Select hqtcsdl.s_ID_HoaDon.nextval from dual";
                    break;
            }
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int tempNumID = 0;
            while (rs.next()) {
                tempNumID = rs.getInt(1);
            }
            String tempID;
            switch (b.getKind()) {
                case "Hóa đơn điện nước":
                    tempID = handleID("HDDN", tempNumID, 12);
                    break;
                case "Hóa đơn phòng":
                    tempID = handleID("HDP", tempNumID, 12);
                    break;
                default:
                    tempID = handleID("HD", tempNumID, 12);
                    break;
            }
            sql = "Insert into hqtcsdl.HoaDon values('"
                    + tempID + "','"
                    + b.getIDEmployee() + "','"
                    + b.getIDRoom() + "','"
                    + b.getIDStudent() + "',"
                    + "?," // Thêm ngày lập
                    + "?,'" // Thêm ngày thu
                    + b.getKind() + "',"
                    + "?,'" // Thêm trị giá
                    + b.getNote()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            User.getConnection().setAutoCommit(false);
            ps.setDate(1, new java.sql.Date(b.getInvoiceDate().getTime()));
            if (b.getCollectionDate() != null) {
                ps.setDate(2, new java.sql.Date(b.getCollectionDate().getTime()));
            } else {
                ps.setString(2, "");
            }
            ps.setInt(3, b.getValue());

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            if (!"Hóa đơn điện nước".equals(b.getKind())) {

                User.getConnection().commit();
                User.getConnection().setAutoCommit(true);
                return true;
            }
            sql = "Select hqtcsdl.s_ID_ChiTietDienNuoc.nextval from dual";
            stmt = User.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tempNumID = rs.getInt(1);
            }
            String tempIDElectric = handleID("D", tempNumID, 10);

            sql = "Insert into hqtcsdl.ChiTietDien values('"
                    + tempIDElectric + "','"
                    + tempID + "',"
                    + "?," // Ngày bắt đầu
                    + "?," // Ngày kết thúc
                    + "?," // Số đầu
                    + "?," // Số cưới
                    + "?)"; // Tổng
            ps = User.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(b.getStartDateE().getTime()));
            ps.setDate(2, new java.sql.Date(b.getEndDateE().getTime()));
            ps.setInt(3, b.getHeadNumE());
            ps.setInt(4, b.getBotNumE());
            ps.setInt(5, b.getSumE());

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }

            String tempIDWater = handleID("N", tempNumID, 10);
            sql = "Insert into hqtcsdl.ChiTietNuoc values('"
                    + tempIDWater + "','"
                    + tempID + "',"
                    + "?," // Ngày bắt đầu
                    + "?," // Ngày kết thúc
                    + "?," // Số đầu
                    + "?," // Số cưới
                    + "?)"; // Tổng
            ps = User.getConnection().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(b.getStartDateW().getTime()));
            ps.setDate(2, new java.sql.Date(b.getEndDateW().getTime()));
            ps.setInt(3, b.getHeadNumW());
            ps.setInt(4, b.getBotNumW());
            ps.setInt(5, b.getSumW());

            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở hóa đơn");
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void unSearch() {
        filterModel = null;
    }

    public static void searchData(
            String IDBill,
            String IDEmployee,
            String IDBuilding,
            String numRoom,
            String IDStudent,
            String invoiceDate,
            String collectionDate,
            String kind,
            String value,
            String note) {
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã hóa đơn",
            "Mã nhân viên",
            "Mã tòa",
            "Số phòng",
            "Mã sinh viên",
            "Ngày lập",
            "Ngày thu",
            "Loại",
            "Trị giá",
            "Ghi chú"}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 6:
                        return Date.class;
                    case 7:
                        return Date.class;
                    case 8:
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
            if (Compare.CloseTo(IDBill, model.getValueAt(i, 1).toString())
                    && Compare.CloseTo(IDEmployee, model.getValueAt(i, 2).toString())
                    && Compare.CloseTo(IDBuilding, model.getValueAt(i, 3).toString())
                    && Compare.CloseTo(numRoom, model.getValueAt(i, 4).toString())
                    && Compare.CloseTo(IDStudent, model.getValueAt(i, 5).toString())
                    && Compare.CloseTo(invoiceDate, model.getValueAt(i, 6).toString())
                    && Compare.CloseTo(collectionDate, model.getValueAt(i, 7).toString())
                    && Compare.CloseTo(kind, model.getValueAt(i, 8).toString())
                    && Compare.CloseTo(value, model.getValueAt(i, 9).toString())
                    && Compare.CloseTo(note, model.getValueAt(i, 10).toString())) {
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
                    model.getValueAt(i, 9),
                    model.getValueAt(i, 10)
                });
            }
        }
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
            "Mã hóa đơn",
            "Mã nhân viên",
            "Mã tòa",
            "Số phòng",
            "Mã sinh viên",
            "Ngày lập",
            "Ngày thu",
            "Loại",
            "Trị giá",
            "Ghi chú"}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    case 6:
                        return Date.class;
                    case 7:
                        return Date.class;
                    case 8:
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
        arr.forEach(b -> {
            model.addRow(b.toObject(false));
        });
    }

    @SuppressWarnings("empty-statement")
    public static void initialData() {
        try {
            arr = new ArrayList<>();
            Statement stmt = User.getConnection().createStatement();
            String sql = "Select h.IDHoaDon, h.IDNhanVien, h.IDPhongO, h.IDSinhVien, \n"
                    + "h.NgayLap, h.NgayThu, h.Loai, h.TriGia, h.GhiChu,\n"
                    + "d.IDChiTietDien, d.NgayBatDau, d.NgayKetThuc, d.SoDau, d.SoCuoi, d.Tong,\n"
                    + "n.IDChiTietNuoc, n.NgayBatDau as \"NBDN\", n.NgayKetThuc as \"NKTN\",\n"
                    + "n.SoDau as \"SDN\", n.SoCuoi as \"SCN\", n.Tong  as \"TN\"\n"
                    + "from hqtcsdl.HoaDon h left join hqtcsdl.ChiTietDien d\n"
                    + "on h.IDHoaDon = d.IDHoaDon left join hqtcsdl.ChiTietNuoc n\n"
                    + "on h.IDHoaDon = n.IDHoaDon \n"
                    + "order by h.IDHoaDon";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Bill b = new Bill();
                b.setIDBill(rs.getString("IDHOADON"));
                b.setIDEmployee(rs.getString("IDNHANVIEN"));
                b.setIDRoom(rs.getString("IDPHONGO"));
                b.setIDStudent(rs.getString("IDSINHVIEN"));
                b.setInvoiceDate(rs.getDate("NGAYLAP"));
                b.setCollectionDate(rs.getDate("NGAYTHU"));
                b.setKind(rs.getString("LOai"));
                b.setValue(rs.getInt("TRIGIA"));
                b.setNote(rs.getString("GHICHU"));

                b.setIDElectric(rs.getString("IDCHITIETDIEN"));
                b.setStartDateE(rs.getDate("NGAYBATDAU"));
                b.setEndDateE(rs.getDate("NGAYKETTHUC"));
                b.setHeadNumE(rs.getInt("SODAU"));
                b.setBotNumE(rs.getInt("SOCUOI"));
                b.setSumE(rs.getInt("TONG"));

                b.setIDWater(rs.getString("IDCHITIETNUOC"));
                b.setStartDateW(rs.getDate("NBDN"));
                b.setEndDateW(rs.getDate("NKTN"));
                b.setHeadNumW(rs.getInt("SDN"));
                b.setBotNumW(rs.getInt("SCN"));
                b.setSumW(rs.getInt("TN"));
                if ("Sinh viên".equals(User.getRole())) {
                    if (b.getIDRoom().equals(User.getIDRoom().trim())
                            || b.getIDStudent().equals(User.getID())) {
                        arr.add(b);
                    }
                } else {
                    arr.add(b);
                }
            };
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở hóa đơn (initial)");
            e.printStackTrace();
        }
    }
}
