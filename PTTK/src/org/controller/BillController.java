package org.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.Bill;
import org.model.ElectricityReceipt;
import org.model.WaterReceipt;

public class BillController implements Controller<Bill>, DatabaseOperators<Bill>{
    
    public static final String TITLE_NUMBER = "STT";
    public static final String TITLE_ID = "Mã Hóa Đơn";
    public static final String TITLE_ROOM = "Phòng";
    public static final String TITLE_BUILDING = "Tòa";
    public static final String TITLE_CREATED_DATE = "Ngày Lập";
    public static final String TITLE_SUBMIT_DATE = "Ngày Thu";
    public static final String TITLE_TYPE = "Loại";
    public static final String TITLE_SUM = "Trị giá";
    
    private ArrayList<Bill> list = new ArrayList<>();
    private static BillController instance = new BillController();
    
    private BillController() { 
    }

    public static Bill parse(Vector<Object> v) {
        Bill b = new Bill();
        
        b.setBillId((String)v.get(1));
        b.setRoom((String)v.get(2), (String)v.get(3));
        b.setCreatedDate(LocalDate.parse((String)v.get(4)));
        b.setSubmittedDate(LocalDate.parse((String)v.get(5)));
        b.setType((String)v.get(6));
        b.setSum((Integer)v.get(7));
            
        return b;
    }
    
    public static BillController getInstance() {
        return instance;
    }
    
    @Override
    public ArrayList<Bill> getList() {
        return this.list;
    } 
    
    @Override
    public DefaultTableModel toTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(this.getHeader());
        int counter = 1;
        for (Bill b : list) {
            Vector<Object> v = new Vector();
            v.add(counter);
            v.addAll(Arrays.asList(b.getProperties()));
            System.out.println(v.toString());
            tableModel.addRow(v);
        }
        return tableModel;
    }
   
    @Override
    public Bill get(String billId) {
        for (Bill b : list) {
            if (b.getBillId().equals(billId)) {
                return b;
            }
        }
        return null;
    }
    
    @Override
    public void delete(String billId) {
        for (Bill b : list) {
            if (b.getBillId().equals(billId)) {
                list.remove(b);
                return;
            }
        }
    }

    @Override
    public String[] getHeader() {
        return new String[]{
            TITLE_NUMBER, TITLE_ID, TITLE_ROOM, TITLE_BUILDING,
            TITLE_CREATED_DATE, TITLE_SUBMIT_DATE, TITLE_TYPE, TITLE_SUM
        };
    }

 
    @Override
    public Vector<Object> toVector(Bill obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /* DATABASE HANDLING METHODS
    *  =========================================================================
    */
    
    @Override
    public void databaseUpdate(Bill b) throws SQLException {
        try {
            String sql = "Update hqtcsdl.HoaDon set "
                    + "IDNhanVien = '" + b.getEmployee()
                    + "', IDPhongO = '" + b.getRoom()
                    + "', IDSinhVien = ?" + b.getStudent()
                    + ", NgayLap = ?"
                    + ", NgayThu = ?"
                    + " Where (IDHoaDon = '" + b.getBillId()
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
            DatabaseController.getConnection().setAutoCommit(false);
            ps.setDate(1, new Date(
                    b.getCreatedDate().atStartOfDay(ZoneId.systemDefault())
                                      .toInstant().toEpochMilli()
            ));
            ps.setDate(2, new Date(
                    b.getSubmittedDate().atStartOfDay(ZoneId.systemDefault())
                                        .toInstant().toEpochMilli()
            ));
            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
            } else {
                DatabaseController.getConnection().commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseController.getConnection().setAutoCommit(true);
        }
        
    }
    
    @Override
    public void databaseDelete(Bill b) throws SQLException {
        String sql = "Delete hqtcsdl.HoaDon "
                + "where (IDHoaDon='"
                + b.getBillId()
                + "')";
        PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
        ps.execute();
    }
    
    @SuppressWarnings("empty-statement")
    @Override
    public void databaseLoad() throws SQLException {

        Statement stmt = DatabaseController.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select h.IDHoaDon, h.IDNhanVien, h.IDPhongO, h.IDSinhVien, "
                + "h.NgayLap, h.NgayThu, h.Loai, h.TriGia, h.GhiChu,\n"
                + "d.IDChiTietDien, d.NgayBatDau, d.NgayKetThuc, d.SoDau, d.SoCuoi, d.Tong,\n"
                + "n.IDChiTietNuoc, n.NgayBatDau, n.NgayKetThuc, n.SoDau, n.SoCuoi, n.Tong\n"
                + "from HQTCSDL.HoaDon h left join HQTCSDL.ChiTietDien d\n"
                + "on h.IDHoaDon= d.IDHoaDon left join HQTCSDL.ChiTietNuoc n\n"
                + "on h.IDHoaDon = n.IDHoaDon "
                + "order by h.IDHoaDon");
        while (rs.next()) {
            Bill b = new Bill();
            b.setBillId(rs.getString("h.HOADON"));
            b.setEmployee(rs.getString("h.IDNHANVIEN"));
            b.setRoom(rs.getString("h.IDPHONGO"));
            b.setStudent(rs.getString("h.IDSINHVIEN"));
            b.setCreatedDate(rs.getDate("h.NGAYLAP").toLocalDate());
            b.setSubmittedDate(rs.getDate("h.NGAYTHU").toLocalDate());
            b.setType(rs.getString("h.Loai"));
            b.setSum(rs.getInt("h.TRIGIA"));
//                b.set(rs.getString("h.GHICHU"));
            ElectricityReceipt er = new ElectricityReceipt();
            er.setId(rs.getString("d.IDCHITIETDIEN"));
            er.setStartDate(rs.getDate("d.NGAYBATDAU").toLocalDate());
            er.setEndDate(rs.getDate("d.NGAYKETTHUC").toLocalDate());
            er.setStartNumber(rs.getInt("d.SODAU"));
            er.setEndNumber(rs.getInt("h.SOCUOI"));
            er.setSum(rs.getInt("d.TONG"));
            b.setElectricDetail(er);

            WaterReceipt wr = new WaterReceipt();
            wr.setId(rs.getString("n.IDCHITIETNUOC"));
            wr.setStartDate(rs.getDate("n.NGAYBATDAU").toLocalDate());
            wr.setEndDate(rs.getDate("n.NGAYKETTHUC").toLocalDate());
            wr.setStartNumber(rs.getInt("n.SODAU"));
            wr.setEndNumber(rs.getInt("n.SOCUOI"));
            wr.setSum(rs.getInt("n.TONG"));
            b.setWaterDetail(wr);

            this.list.add(b);
        };
    }
    
    @Override
    public void databaseInsert(Bill b) throws SQLException {
        try {

            String sql = "Insert into htqcsdl.HoaDon values('"
                    + b.getBillId() + "','"
                    + b.getEmployee() + "','"
                    + b.getRoom() + "','"
                    + b.getStudent() + "',"
                    + "?," // Thêm ngày lập
                    + "?,'" // Thêm ngày thu
                    + b.getType() + "',"
                    + "?,'" // Thêm trị giá
                    + ""
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareStatement(sql);
            DatabaseController.getConnection().setAutoCommit(false);
            ps.setDate(1, new Date(
                    b.getCreatedDate().atStartOfDay(ZoneId.systemDefault())
                            .toInstant().toEpochMilli()
            ));
            ps.setDate(2, new Date(
                    b.getSubmittedDate().atStartOfDay(ZoneId.systemDefault())
                            .toInstant().toEpochMilli()
            ));
            ps.setInt(3, b.getSum());

            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
            } else {
                DatabaseController.getConnection().commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseController.getConnection().setAutoCommit(true);
        }
    }

}
