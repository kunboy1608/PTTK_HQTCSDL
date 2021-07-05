package org.resources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Searcher {
    
    private DefaultTableModel tableModel;
    
    public Searcher(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public static boolean compare(String str, String substr) {
        if (str.equals("") | substr.equals("")) return false;
        return str.toLowerCase().trim().contains(substr.toLowerCase().trim());
    }
    
    public static boolean compareList(ArrayList<String> comparator, ArrayList<String> obj) {
        // start at 1 because of ignoring the boolean tickbox 
        for (int i = 1; i < comparator.size(); i++) {
            if (Searcher.compare(comparator.get(i), obj.get(i)))
                return true;
        }
        return false;
    }
    
    //public abstract Vector<String> toVector(T obj);
    
    public static ArrayList<String> toList(ArrayList v) {
        ArrayList<String> resultVector = new ArrayList<>();
        for (Object obj : v) {
            if (obj instanceof String) {
                resultVector.add((String)obj);
            } else if (obj instanceof Integer) {
                resultVector.add(Integer.toString((Integer)obj));
            } else if (obj instanceof Double) {
                resultVector.add(Double.toString((Double)obj));
            } else if (obj instanceof LocalDate) {
                resultVector.add(Utilities.toString((LocalDate)obj));
            }
        }
        return resultVector;
    }
    
    private DefaultTableModel initResultSet() {
        DefaultTableModel resultSet = new DefaultTableModel();
        Vector<String> identifiers = new Vector<>();
        
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            identifiers.add(tableModel.getColumnName(i));
        }
        
        resultSet.setColumnIdentifiers(identifiers);
        
        return resultSet;
    }
        
    public DefaultTableModel getResultList(ArrayList<String> v) {
        DefaultTableModel resultSet = this.initResultSet();
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (Searcher.compareList(
                            Searcher.toList(new ArrayList(tableModel.getDataVector().get(i))), 
                            v
                        )) {
                resultSet.addRow(tableModel.getDataVector().get(i));
            }
        }
        return resultSet;
    }
    
}
