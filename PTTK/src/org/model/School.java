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
public class School {

    String IDSchool;
    String Name;

    public School() {
        this.IDSchool = "";
        this.Name = "";
    }

    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            IDSchool,
            Name
        };
    }

    public String getIDSchool() {
        return IDSchool;
    }

    public void setIDSchool(String IDSchool) {
        if (IDSchool == null) {
            return;
        }
        this.IDSchool = IDSchool.trim();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        if (Name == null) {
            return;
        }
        this.Name = Name.trim();
    }
}
