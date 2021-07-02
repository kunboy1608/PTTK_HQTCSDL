/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

/**
 *
 * @author kunbo
 */
public class Department {

    String IDDepartment;
    String IDManager;
    String Name;
    String location;

    public Department() {
        this.IDDepartment = "";
        this.IDManager = "";
        this.Name = "";
        this.location = "";
    }

    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            IDDepartment,
            IDManager,
            Name,
            location
        };
    }

    public String getIDDepartment() {
        return IDDepartment;
    }

    public void setIDDepartment(String IDDepartment) {
        if (null == IDDepartment) {
            return;
        }
        this.IDDepartment = IDDepartment.trim();
    }

    public String getIDManager() {
        return IDManager;
    }

    public void setIDManager(String IDManager) {
        if (null == IDManager) {
            return;
        }
        this.IDManager = IDManager.trim();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        if (null == Name) {
            return;
        }
        this.Name = Name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (null == location) {
            return;
        }
        this.location = location.trim();
    }

}
