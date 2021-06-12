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

    String departmentId;
    String managerId;
    String name;
    String location;

    public Department() {
    }

    public Department(String departmentId, String managerId, String name, String location) {
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.name = name;
        this.location = location;
    }
    
    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            departmentId,
            managerId,
            name,
            location
        };
    }

    public String getId() {
        return departmentId;
    }

    public void setId(String IDDepartment) {
        this.departmentId = IDDepartment;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String IDManager) {
        this.managerId = IDManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
