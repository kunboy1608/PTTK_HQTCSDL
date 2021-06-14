package org.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.model.University;

public class UniversityController implements Controller<University>, DatabaseOperators<University>{

    private ArrayList<University> list = new ArrayList<>();
    
    private static final UniversityController instance = new UniversityController();
    
    private UniversityController() { }

    public static University parse(Vector<Object> v) {
        University t = new University();
        
        t.setId((String)v.get(0));
        t.setName((String)v.get(1));
        
        return t;
    }
    
    public static UniversityController getInstance() {
        return instance;
    }
    
    @Override
    public String[] getHeader() {
        return new String[] {
            
        };
    }

    @Override
    public ArrayList<University> getList() {
        return this.list;
    }

    @Override
    public University get(String id) {
         for (University t : list) {
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
        for (University b : list) {
            tableModel.addRow(toVector(b));
        }
        return tableModel;
    }

    @Override
    public Vector<Object> toVector(University obj) {
        Vector<Object> v = new Vector<>();
            v.add(obj.getId());
            v.add(obj.getName());
        return v;
    }

    /* DATABASE HANDLING METHODS
    *  =========================================================================
    */
    
    @Override
    public void databaseLoad() throws SQLException {
        Statement stmt = DatabaseController.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select * from hqtcsdl.Tuong");
        while (rs.next()) {
            University s = new University();
            s.setId(rs.getString("IDTRUONG"));
            s.setName(rs.getString("TENTRUONG"));
            list.add(s);
        };
    }

    @Override
    public void databaseInsert(University obj) throws SQLException {
        try {
             String sql = "Insert into htqcsdl.Toa values('"
                    + obj.getId()+ "','"
                    + obj.getName()
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
    public void databaseUpdate(University obj) throws SQLException {
         try {

            String sql = "Update hqtcsdl.Truong set "
                    + "TenTruong = '" + obj.getName()
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
    public void databaseDelete(University obj) throws SQLException {
        String sql = "Delete hqtcsdl.Toa "
                + "where (IDToa='"
                + obj.getId()
                + "')";
        PreparedStatement ps = DatabaseController.getConnection().prepareCall(sql);
        ps.execute();
    }
    
}
