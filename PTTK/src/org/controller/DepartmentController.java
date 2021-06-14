package org.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.Department;

public class DepartmentController implements Controller<Department>, DatabaseOperators<Department> {

    private ArrayList<Department> list = new ArrayList<>();
    
    private static final DepartmentController instance = new DepartmentController();
    
    private DepartmentController() { }

    public static Department parse(Vector<Object> v) {
        Department t = new Department();
        
        t.setId((String)v.get(0));
        t.setLocation((String)v.get(1));
        t.setManagerId((String)v.get(2));
        t.setName((String)v.get(3));
        return t;
    }
    
    public static DepartmentController getInstance() {
        return instance;
    }
    
    @Override
    public String[] getHeader() {
         return new String[] {
            "Mã PVN", "Vị trí", "Mã Nhân viên", "Tên Phòng"
        };
    }

    @Override
    public ArrayList<Department> getList() {
        return this.list;
    }

    @Override
    public Department get(String id) {
        for (Department t : list) {
            if (t.getId().equals(id)) {
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
        tableModel.setColumnIdentifiers(this.getHeader());
        for (Department b : list) {
            tableModel.addRow(toVector(b));
        }
        return tableModel;
    }

    @Override
    public Vector<Object> toVector(Department obj) {
        Vector<Object> v = new Vector<>();
        v.add(obj.getId());
        v.add(obj.getLocation());
        v.add(obj.getManagerId());
        v.add(obj.getName());
        return v;
    }
    
    /* DATABASE HANDLING METHODS
    *  =========================================================================
    */

    @Override
    public void databaseLoad() throws SQLException {
        Statement stmt = DatabaseController.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * from hqtcsdl.PhongNhanVien");
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getString("IDPHONGNHANVIEN"));
                d.setManagerId(rs.getString("IDQUANLI"));
                d.setName(rs.getString("TENPHONG"));
                d.setLocation(rs.getString("VITRI"));
                list.add(d);
            }
    }

    @Override
    public void databaseInsert(Department obj) throws SQLException {
        try {
             String sql = "Insert into htqcsdl.Toa values('"
                    + obj.getId()+ "','"
                    + obj.getManagerId()+ "','"
                    + obj.getName() + "','"
                    + obj.getLocation()
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
            DatabaseController.getConnection().setAutoCommit(false);
            
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
    public void databaseUpdate(Department obj) throws SQLException {
        try {
            String sql = "Update hqtcsdl.PhongNhanVien set "
                    + "IDQuanLi = '" + obj.getManagerId()
                    + "', TenPhong  = '" + obj.getName()
                    + "', ViTri = '" + obj.getLocation()
                    + "' Where (IDToa = '" + obj.getId()
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
            DatabaseController.getConnection().setAutoCommit(false);
            
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
    public void databaseDelete(Department obj) throws SQLException {
        String sql = "Delete hqtcsdl.PhongNhanVien "
                + "where (IDPhongNhanVien='"
                + obj.getId()
                + "')";
        DatabaseController.getConnection().prepareStatement(sql).execute();
    }
    
}
