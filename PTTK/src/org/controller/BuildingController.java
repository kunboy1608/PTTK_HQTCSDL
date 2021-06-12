package org.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.Building;

public class BuildingController implements Controller<Building>, DatabaseOperators<Building> {

    private ArrayList<Building> list = new ArrayList<>();
    
    private static final BuildingController instance = new BuildingController();
    
    private BuildingController() { }

    public static Building parse(Vector<Object> v) {
        Building t = new Building();
        
        t.setBuildingId((String)v.get(0));
        t.setName((String)v.get(1));
        t.setEmployeeId((String)v.get(2));
        t.setKind((String)v.get(3));
        return t;
    }
    
    public static BuildingController getInstance() {
        return instance;
    }
        
    @Override
    public String[] getHeader() {
        return new String[] {
            
        };
    }

    @Override
    public ArrayList<Building> getList() {
        return this.list;
    }

    @Override
    public Building get(String id) {
        for (Building t : list) {
            if (t.getBuildingId().equals(id)) {
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
        for (Building b : list) {
            tableModel.addRow(toVector(b));
        }
        return tableModel;
    }

    @Override
    public Vector<Object> toVector(Building obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* DATABASE HANDLING METHODS
    *  =========================================================================
    */
    
    @Override
    public void databaseLoad() throws SQLException {
        try {
            Statement stmt = DatabaseController.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * from hqtcsdl.Toa");
            while (rs.next()) {
                Building b = new Building();
                b.setBuildingId(rs.getString("IDTOA"));
                b.setEmployeeId(rs.getString("IDNHANVIEN"));
                b.setName(rs.getString("TENTOA"));
                b.setKind(rs.getInt("LOAITOA"));
                list.add(b);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void databaseInsert(Building obj) throws SQLException {
        try {
            String sql = "Insert into htqcsb) dl.Toa values('"
                    + obj.getBuildingId()+ "','"
                    + obj.getEmployeeId()+ "','"
                    + obj.getName() + "','"
                    + obj.getKind()
                    + "')";
            PreparedStatement ps = DatabaseController.getConnection().prepareStatement(sql);
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
    public void databaseUpdate(Building obj) throws SQLException {
        try {
            String sql = "Update hqtcsdl.Toa set "
                    + "IDNhanVien = '" + obj.getEmployeeId()
                    + "', TenToa  = '" + obj.getName()
                    + "', LoaiToa = '" + obj.getKind()
                    + "' Where (IDToa = '" + obj.getBuildingId()
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
    public void databaseDelete(Building obj) throws SQLException {
        String sql = "Delete hqtcsdl.Toa "
                + "where (IDToa='"
                + obj.getBuildingId()
                + "')";
        PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
        ps.execute();
    }

}
