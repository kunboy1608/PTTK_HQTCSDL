package org.controller;

import java.sql.SQLException;

public interface DatabaseOperators<T> {
    
    /**
     * Load data from the database and parse them to objects and save to list of
     * implemented class.
     * 
     * @throws SQLException
     */
    public void databaseLoad() throws SQLException;
    /**
     * Load the objects from the list in implemented class and parse them to 
     * saveable patterns for putting into the database.
     * 
     * @throws SQLException
     * 
     */
    //public void databaseFlush() throws SQLException;
    
    public void databaseInsert(T obj) throws SQLException;
    public void databaseUpdate(T obj) throws SQLException;
    public void databaseDelete(T obj) throws SQLException;
    
}
