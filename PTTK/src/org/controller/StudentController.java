package org.controller;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.Student;
import org.resources.ImageProcess;

public class StudentController implements Controller<Student>, DatabaseOperators<Student> {

    private ArrayList<Student> list = new ArrayList<>();
    private static StudentController instance = new StudentController();
    
    private StudentController() { 
    }

    public static Student parse(Vector<Object> v) {
        Student b = new Student();
        
        b.setCardId((String)v.get(1));
            
        return b;
    }
    
    public static StudentController getInstance() {
        return instance;
    }
    
    @Override
    public String[] getHeader() {
        return new String[]{
            "Mã sinh viên", "Họ, tên đệm", "Tên", "Giới tính", "Phòng", "CMND/CCCD",
            "Mã trường", "Ngày hết hạn", "Địa chỉ"
        };
    }

    @Override
    public ArrayList<Student> getList() {
        return this.list;
    }

    @Override
    public Student get(String id) {
        for (Student b : list) {
            if (b.getCardId().equals(id)) {
                return b;
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
        for (Student b : list) {
            tableModel.addRow(toVector(b));
        }
        return tableModel;
    }

    @Override
    public Vector<Object> toVector(Student obj) {
        return null;
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
                + "s.IDSinhVien, s.IDPhongO, s.IDTruong, s.NgayVao, s.NgayHetHan, s.TrangThai\n"
                + "from (select * from HQTCSDL.ThongTinCoBan where (ID < 10000)) t \n"
                + "join (select * from HQTCSDL.NhanVien Where TrangThai = 0 ) n\n"
                + "on n.ID = t.ID\n"
                + "order by t.ID");
        while (rs.next()) {
            Student s = new Student();
                s.setStatus(rs.getInt("n.TRANGTHAI"));
                s.setSurname(rs.getString("t.HO"));
                s.setName(rs.getString("t.TEN"));
                s.setAddress(rs.getString("t.DIACHI"));
                s.setSex(
                    rs.getInt("t.GIOITINH") == 0 
                        ? "Nam"
                        : rs.getInt("t.GIOITINH") == 1
                            ? "Nữ"
                            : "Khác"
                );
                s.setBirthday(rs.getDate("t.NGAYSINH").toLocalDate());
                s.setPhoneNumber(rs.getString("t.SDT"));
                s.setEmail(rs.getString("t.Email"));
                s.setCardId(rs.getString("t.CMND"));
                s.setNationality(rs.getString("t.QUOCTICH"));
                s.setNation(rs.getString("t.DANTOC"));
                s.setBhyt(rs.getString("t.BHYT"));
                s.setPersonalName(rs.getString("t.TENTHHANNHAN"));
                s.setPhoneNumber(rs.getString("t.SDTTHANNHAN"));
                s.setAddressPersonal(rs.getString("t.DIACHITHANNHAN"));
                s.setAvatar(ImageProcess.createImageFromBlob(rs.getBlob("t.HINHANH")));

                s.setStudentId(rs.getString("s.IDSINHVIEN"));
                s.setRoom(rs.getString("n.IDPHONGO"));
                s.setUniversity(rs.getString("s.IDTRUONG"));
                s.setComeDate(rs.getDate("s.NGAYVAO").toLocalDate());
                s.setExpDate(rs.getDate("s.NGAYHETHAN").toLocalDate());
                s.setStatus(rs.getInt("s.TRANGTHAI"));
            list.add(s);
        }
    }

    @Override
    public void databaseInsert(Student obj) throws SQLException {
        try {
            // Kiểm tra xem Sinh viên này đã từng tồn tại chưa
            String sql = "Select count(*) "
                    + "from hqtcsdl.SinhVien s join ThongTinCoBan t"
                    + "on s.ID =  t.ID "
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
                    + obj.getBhyt() + "','"
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
            sql = "Insert into hqtcsdl.SinhVien values('"
                    + "IDSInhvien" // Cho này thêm IDSinhVien
                    + "ID" + "','" // Chỗ này thêm ID
                    + obj.getRoom()+ "','"
                    + obj.getUniversity()+ "','"
                    + "?," // Chỗ này thêm ngày vào
                    + "?," // Chỗ này thêm ngày hết hạn
                    + "?)"; // Chỗ này  thêm trạng thái
            ps = DatabaseController.getConnection().prepareStatement(sql);
            ps.setDate(1, new Date(
                    obj.getComeDate().atStartOfDay(ZoneId.systemDefault())
                                     .toInstant().toEpochMilli()
            ));
            ps.setDate(2, new Date(
                    obj.getExpDate().atStartOfDay(ZoneId.systemDefault())
                                    .toInstant().toEpochMilli()
            ));
            ps.setInt(3, obj.getStatus());

            if (!ps.execute()) {
                DatabaseController.getConnection().rollback();
            } else {
                DatabaseController.getConnection().commit();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở Sinh viên");
            e.printStackTrace();
        } finally {
            DatabaseController.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public void databaseUpdate(Student obj) throws SQLException {
        try {
            // Cập nhật phần bảng SinhVien
            String sql = "Update hqtcsdl.SinhVien set "
                    + "IDPhong = '" + obj.getRoom()
                    + "', IDTruong = '" + obj.getUniversity()
                    + "', NgayVao = ?"
                    + ", NgayHetHan = ?"
                    + ", TrangThai = 1"
                    + " Where (IDSinhVien = '"
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
            DatabaseController.getConnection().setAutoCommit(false);
             ps.setDate(1, new Date(
                    obj.getComeDate().atStartOfDay(ZoneId.systemDefault())
                                     .toInstant().toEpochMilli()
            ));
            ps.setDate(2, new Date(
                    obj.getExpDate().atStartOfDay(ZoneId.systemDefault())
                                    .toInstant().toEpochMilli()
            ));

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
                    + obj.getStudentId()
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
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Sinh viên");
            e.printStackTrace();
        } finally {
            DatabaseController.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public void databaseDelete(Student obj) throws SQLException {
        String sql = "Update hqtcsdl.SINHVIEN set "
                + "TrangThai = '1' "
                + "Where (IDSinhvien ='"
                + obj.getCardId()
                + "')";
        PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
        ps.execute();
    }
    
}
