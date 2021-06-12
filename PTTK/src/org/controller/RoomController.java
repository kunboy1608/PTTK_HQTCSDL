package org.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.Room;

public class RoomController implements Controller<Room>, DatabaseOperators<Room>{
    
    private ArrayList<Room> list = new ArrayList<>();
    
    private static final RoomController instance = new RoomController();
    
    private RoomController() { }

    public static Room parse(Vector<Object> v) {
        Room t = new Room();
        
        t.setRoomId((String)v.get(0));
        t.setBuilding((String)v.get(1));
        t.setRoomType((Integer)v.get(2));
        t.setRoomRatio((String)v.get(3));
        t.setFacility((String)v.get(4));
        t.setStatus((Integer)v.get(5));    
        return t;
    }
    
    public static RoomController getInstance() {
        return instance;
    }
    
    @Override
    public ArrayList<Room> getList() {
        return this.list;
    } 
    
    @Override
    public String[] getHeader() {
        return new String[] {
            "Mã Phòng", "Tòa", "Loại phòng", "Số người", "Cơ sở vật chất", "Trạng thái"
          //"Mã phòng", "Tòa", "Cơ sở vật chất", "Loại phòng", "Số ngưởi ở", "Còn trống"
        };
    }
    
    @Override
    public Vector<Object> toVector(Room obj) {
        Vector<Object> v = new Vector<>();
        v.add(obj.getRoomId());
        v.add(obj.getBuilding());
        v.add(obj.getRoomType());
        v.add(obj.getRoomRatio());
        v.add(obj.getFacility());
        v.add(obj.getStatus());
        return v;
    }
    
    @Override
    public DefaultTableModel toTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        for (Room b : list) {
            tableModel.addRow(toVector(b));
        }
        return tableModel;
    }
    
    
    @Override
    public Room get(String id) {
        for (Room t : list) {
            if (t.getRoomId().equals(id)) {
                return t;
            }
        }
        return null;
    }
    
    @Override
    public void delete(String id) {
        list.remove(this.get(id));
    }
    
    /* DATABASE HANDLING METHODS
    *  =========================================================================
    */
    
    public void databaseLoad() throws SQLException {

        Statement stmt = DatabaseController.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select * From HQTCSDL.PHONGO");

        while (rs.next()) {
            Room r = new Room();
            r.setRoomId(rs.getString("IDPHONGO").trim());
            r.setRoomAndBuilding(rs.getString("IDTOA").trim());
            r.setFacility(rs.getString("COSOVATCHAT").trim());
            r.setRoomType(Integer.valueOf(rs.getString("LOAIPHONG").trim()));
            r.setRoomCapacity(rs.getInt("SONGUOIO"));
            r.setStatus(rs.getInt("TRANGTHAI"));
            
            this.list.add(r);
        }
    }
    
    public void databaseInsert(Room r) throws SQLException {
        String sql = "Insert into hqtcsdl.PhongO values(?,?,?,?,0,?)";
        PreparedStatement ps = DatabaseController.getConnection().prepareStatement(sql);

        ps.setString(1, r.getRoomAndBuilding());
        ps.setString(2, r.getBuilding());
        ps.setString(3, r.getFacility());
        ps.setString(4, r.getRoomType().toString());
        ps.setString(5, r.getStatus().toString());

        ps.executeUpdate();
    }
    
    public void databaseDelete(Room r) throws SQLException {
        String sql = "Delete From hqtcsdl.PhongO "
                + "Where (IDPhongO = '"
                + r.getRoomAndBuilding()
                + "')";
        DatabaseController.getConnection().prepareStatement(sql).executeUpdate();
    }
    
    public void databaseUpdate(Room r) throws SQLException {
        String sql = "Update hqtcsdl.PhongO set"
                + " IDToa = '" + r.getBuilding().toUpperCase()
                + "', CoSoVatChat = '" + r.getFacility()
                + "', LoaiPhong = '" + r.getRoomType()
                + "', TrangThai = " + r.getStatus()
                + " Where (IDPhongO = '" + r.getRoomAndBuilding() + "')";

        DatabaseController.getConnection().prepareStatement(sql).executeUpdate();
    }
    
}
