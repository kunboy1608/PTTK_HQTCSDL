package org.controller;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.Employee;
import org.resources.ImageProcess;

public class EmployeeController implements Controller<Employee>, DatabaseOperators<Employee>{
    
    private ArrayList<Employee> list = new ArrayList<>();
    private static final EmployeeController INSTANCE = new EmployeeController();
    
    private EmployeeController() { }
    
    public static EmployeeController getInstance() {
        return INSTANCE;
    }
    
    public static Employee parse(Vector<Object> v) {
        Employee t = new Employee();
        
        t.setCardId((String)v.get(0));
        t.setSurname((String)v.get(1));
        t.setName((String)v.get(2));
        t.setSex((String)v.get(3));
        t.setRoom((String)v.get(4));
        t.setStartDate((LocalDate)v.get(5));
        t.setAddress((String)v.get(6));
                
        return t;
    }

    @Override
    public String[] getHeader() {
        return new String[] {
            "Mã Nhân Viên", "Họ tên", "Giới tính", "Phòng", "Ngày vào làm", "Địa chỉ"
        };
    }

    @Override
    public ArrayList<Employee> getList() {
        return this.list;
    }

    @Override
    public Employee get(String id) {
       for (Employee t : list) {
            if (t.getCardId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        list.remove(this.get(id));
    }

    @Override
    public DefaultTableModel toTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        for (Employee b : list) {
            tableModel.addRow(toVector(b));
        }
        return tableModel;
    }

    @Override
    public Vector<Object> toVector(Employee obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /* DATABASE HANDLING METHODS
    *  =========================================================================
    */
    
    @Override
    public void databaseLoad() throws SQLException {
        Statement stmt = DatabaseController.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select t.Ho, t.Ten, t.DiaChi, t.GioiTinh, t.NgaySinh, t.SDT,\n"
                + "t.Email, t.CMND, t.QuocTich, t.DanToc, t.BHYT,\n"
                + "t.TenThanNhan, t.SDTThanNhan, t.DiaChiThanNhan, t.HinhAnh,\n"
                + "n.IDNhanVien, n.IDPhongNhanVien, n.QuanLiPhong, n.VaoLam, n.TrangThai\n"
                + "from (select * from hqtcsdl.ThongTinCoBan where (ID > 10000)) t \n"
                + "join (select * from hqtcsdl.NhanVien where TrangThai = 0) n \n"
                + "on t.ID = s.ID\n"
                + "order by t.ID");
        while (rs.next()) {
            Employee e = new Employee();
                e.setSurname(rs.getString("t.HO"));
                e.setName(rs.getString("t.TEN"));
                e.setAddress(rs.getString("t.DIACHI"));
                e.setSex(
                    rs.getInt("t.GIOITINH") == 0 
                        ? "Nam"
                        : rs.getInt("t.GIOITINH") == 1
                            ? "Nữ"
                            : "Khác"
                );
                e.setBirthday(rs.getDate("t.NGAYSINH").toLocalDate());
                e.setPhoneNumber(rs.getString("t.SDT"));
                e.setEmail(rs.getString("t.Email"));
                e.setCardId(rs.getString("t.CMND"));
                e.setNationality(rs.getString("t.QUOCTICH"));
                e.setNation(rs.getString("t.DANTOC"));
                e.setBhyt(rs.getString("t.BHYT"));
                e.setPersonalName(rs.getString("t.TENTHHANNHAN"));
                e.setPhoneNumber(rs.getString("t.SDTTHANNHAN"));
                e.setAddressPersonal(rs.getString("t.DIACHITHANNHAN"));
                e.setAvatar(ImageProcess.createImageFromBlob(rs.getBlob("t.HINHANH")));
                e.setCardId(rs.getString("n.IDNHANVIEN"));
                e.setRoom(rs.getString("n.IDPHONGNHANVIEN"));
    //            e.setIDRoomManage(rs.getString("n.QUANLIPHONG"));
                e.setStartDate(rs.getDate("n.NGAYVAOLAM").toLocalDate());
                e.setStatus(rs.getInt("n.TRANGTHAI"));
            this.list.add(e);
        }
    }

    @Override
    public void databaseInsert(Employee obj) throws SQLException {
        try {
            // Kiểm tra xem Sinh viên này đã từng tồn tại chưa
            String sql = "Select count(*) "
                    + "from hqtcsdl.NhanVien n join ThongTinCoBan t "
                    + "on n.ID = t.ID "
                    + "where (TrangThai != 0 and CMND ='"
                    + obj.getCardId()
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next() & rs.getInt(1) > 0) {
                return;
            }
            sql = "Insert into htqcsdl.ThongTinCoBan values( "
                    + "'"
                    + obj.getSurname() + "','"
                    + obj.getName() + "','"
                    + obj.getAddress() + "','"
                    + obj.getSex() + "','"
                    + "?','" // Thêm ngày sinh
                    + obj.getPhoneNumber() + "','"
                    + obj.getEmail() + "','"
                    + obj.getCardId()+ "','"
                    + obj.getNationality() + "','"
                    + obj.getNation() + "','"
                    + obj.getBhyt()+ "','"
                    + obj.getPersonalName() + "','"
                    + obj.getPhonePersonal() + "','"
                    + obj.getAddressPersonal() + "',"
                    + "?)" // Thêm Avatar
                    ;
            ps = DatabaseController.getConnection().prepareStatement(sql);
            DatabaseController.getConnection().setAutoCommit(false);
            ps.setDate(1, new Date(
                    obj.getBirthday().atStartOfDay(ZoneId.systemDefault())
                                     .toInstant().toEpochMilli()
            ));
            Blob b = null;
            b.setBytes(1, ImageProcess.toByteArray(obj.getAvatar(), "jpg"));
            ps.setBlob(2, b);

            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
                DatabaseController.getConnection().setAutoCommit(true);
                return;
            }
            
            sql = "Insert into hqtcsdl.NhanVien values('"
                    + "IDEmployee" // Cho này thêm IDNhanVien
                    + obj.getRoom()+ "','"
                    + obj.getRoom() + "','"
                    + "ID" + "'," // Chỗ này thêm ID
                    + "?," // Chỗ này thêm ngày vào làm
                    + "?)"; // Chỗ này  thêm trạng thái
            ps = DatabaseController.getConnection().prepareStatement(sql);
            ps.setDate(1, new Date(
                    obj.getStartDate().atStartOfDay(ZoneId.systemDefault())
                                      .toInstant().toEpochMilli()
            ));
            ps.setInt(2, obj.getStatus());

            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
            } else {
                DatabaseController.getConnection().commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseController.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public void databaseUpdate(Employee obj) throws SQLException {
        try {
            // Cập nhật phần bảng SinhVien
            String sql = "Update hqtcsdl.NhanVien set "
                    + "IDPhongNhanVien = '" + obj.getRoom()
                    + "', QuanLiPhong = '" + obj.getRoom()
                    + "', NgayVao = ?"
                    + ", TrangThai = ?"
                    + " Where (IDNhanVien = '" + obj.getCardId()
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
            DatabaseController.getConnection().setAutoCommit(false);
            ps.setDate(1, new Date(
                    obj.getStartDate().atStartOfDay(ZoneId.systemDefault())
                                      .toInstant().toEpochMilli()
            ));
            ps.setInt(2, obj.getStatus());

            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
                DatabaseController.getConnection().setAutoCommit(true);
                return;
            }
            // Cập nhật phần bảng thông tin cơ bản
            sql = "Update hqtcsdl.ThongTinCoBan set "
                    + "Ho = '" + obj.getSurname()
                    + "', Ten = '" + obj.getName()
                    + "', DiaChi = '" + obj.getAddress()
                    + "', GioiTinh = '" + obj.getSex()
                    + "', NgaySinh = ?"
                    + ", SDT = '" + obj.getPhoneNumber()
                    + "', Email = '" + obj.getEmail()
                    + "', CMND = '" + obj.getCardId()
                    + "', QuocTich = '" + obj.getNationality()
                    + "', DanToc = '" + obj.getNation()
                    + "', BHYT = '" + obj.getBhyt()
                    + "', TenThanNhan = '" + obj.getPersonalName()
                    + "', SDTThanNhan = '" + obj.getPhonePersonal()
                    + "', DiaChiThanNhan = '" + obj.getAddressPersonal()
                    + "', HinhAnh = ?"
                    + " Where (ID in ("
                    + "select ID from hqtcsdl.SinhVien"
                    + " where (IDSinhVien='"
                    + obj.getCardId()
                    + "')" //Còn thiếu
                    + "))";
            ps = DatabaseController.getConnection().prepareCall(sql);
            ps.setDate(1, new Date(
                    obj.getBirthday().atStartOfDay(ZoneId.systemDefault())
                                     .toInstant().toEpochMilli()
            ));
            Blob b = null;
            b.setBytes(1, ImageProcess.toByteArray(obj.getAvatar(), "jpg"));
            ps.setBlob(2, b);

            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
            } else {
                DatabaseController.getConnection().commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseController.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public void databaseDelete(Employee obj) throws SQLException {
        String sql = "Update hqtcsdl.NHANVIEN set "
                + "TrangThai = '1' "
                + "Where (IDSinhvien ='"
                + obj
                + "')";
        PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
        ps.execute();
    }
    
    
            
}
