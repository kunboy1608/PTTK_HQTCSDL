/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import org.model.School;
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
public class SchoolController {

    private static DefaultTableModel model = null;
    private static DefaultTableModel filterModel = null; // Để lưu kết quả tìm kiếm
    private static ArrayList<School> arr = null;

//    private static final SchoolController instance = new SchoolController();
    public static ArrayList<String> getComboboxListItem() {
        initialData();
        ArrayList<String> a = new ArrayList<>();
        for (School s : arr) {
            a.add(s.getIDSchool());
        }
        return a;
    }

    public static School showFullInfo(String IDSchool) {
        IDSchool = IDSchool.trim();
        for (School s : arr) {
            if (IDSchool.equals(s.getIDSchool().trim())) {
                return s;
            }
        }
        return null;
    }

    public static boolean updateData(School s) {
        try {

            String sql = "Update hqtcsdl.Truong set "
                    + "TenTruong = '" + s.getName()
                    + "' where (IDTruong = '"
                    + s.getIDSchool() + "')";
            System.out.println(sql);
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            User.getConnection().setAutoCommit(false);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn ở Trường");
            e.printStackTrace();
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SchoolController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static boolean delData(String IDSchool) {
        try {
            String sql = "Delete hqtcsdl.Truong "
                    + "where (IDTruong='"
                    + IDSchool
                    + "')";
            PreparedStatement ps = User.getConnection().prepareCall(sql);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean insData(School b) {
        try {

            String sql = "Insert into hqtcsdl.Truong values('"
                    + b.getIDSchool() + "','"
                    + b.getName()
                    + "')";
            PreparedStatement ps = User.getConnection().prepareStatement(sql);
            User.getConnection().setAutoCommit(false);
            if (ps.executeUpdate() != 1) {
                User.getConnection().rollback();
                User.getConnection().setAutoCommit(true);
                return false;
            }
            User.getConnection().commit();
            User.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở Truong");
            try {
                User.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SchoolController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static void unSearch() {
        filterModel = null;
    }

    public static void searchData(
            String IDSchool,
            String Name
    ) {
        filterModel = new DefaultTableModel(new Object[]{
            " ",
            "Mã trường",
            "Tên trường"
        }, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
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
            if (Compare.CloseTo(IDSchool, model.getValueAt(i, 1).toString())
                    && Compare.CloseTo(Name, model.getValueAt(i, 2).toString())) {
                filterModel.addRow(new Object[]{
                    true,
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),});
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
            "Mã trường",
            "Tên trường"
        }, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        arr.forEach(s -> {
            model.addRow(s.toObject(false));
        });
    }

    @SuppressWarnings("empty-statement")
    public static void initialData() {
        try {
            arr = new ArrayList<>();
            Statement stmt = User.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * from hqtcsdl.Truong");
            arr = new ArrayList<>();
            while (rs.next()) {
                School s = new School();
                s.setIDSchool(rs.getString("IDTRUONG"));
                s.setName(rs.getString("TENTRUONG"));
                arr.add(s);
            };
        } catch (SQLException e) {
            System.out.println("Lỗi câu truy vấn ở Truong");
            e.printStackTrace();
        }
    }
}
