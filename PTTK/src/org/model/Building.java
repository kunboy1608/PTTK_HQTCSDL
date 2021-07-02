/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates Hoang Dinh Phu 19520838
 * and open the template in the editor.
 */
package org.model;

/**
 *
 * @author kunbo
 */
public class Building {

    String IDBuilding;
    String IDEmployee;
    String Name;
    String kind;

    public Building() {
        this.IDBuilding = "";
        this.IDEmployee = "";
        this.Name = "";
        this.kind = "";
    }

    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            IDBuilding,
            IDEmployee,
            Name,
            kind
        };
    }

    public String getIDBuilding() {
        return IDBuilding;
    }

    public void setIDBuilding(String IDBuilding) {
        if (IDBuilding == null) {
            return;
        }
        this.IDBuilding = IDBuilding.trim();
    }

    public String getIDEmployee() {
        return IDEmployee;
    }

    public void setIDEmployee(String IDEmployee) {
        if (IDEmployee == null) {
            return;
        }
        this.IDEmployee = IDEmployee.trim();
    }

    public String getKind() {
        return kind;
    }

    public int getKindInt() {
        if ("Nam".equals(this.kind)) {
            return 0;
        }
        return 1;
    }

    public void setKind(int k) {
        if (k == 0) {
            this.kind = "Nam";
        } else {
            this.kind = "Nữ";
        }
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
