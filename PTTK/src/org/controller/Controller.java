package org.controller;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public interface Controller<T> {
    public String[] getHeader();
    public ArrayList<T> getList();
    public T get(String id);
    public void delete(String id);
    public DefaultTableModel toTable();
    public Vector<Object> toVector(T obj);
}
