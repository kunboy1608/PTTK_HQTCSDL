package org.model;

public class Building {
    
    private String buildingId;
    private String employeeId;
    private String name;
    private String kind;

    public Building() {
    }

    public Building(String buildingId, String employeeId, String name, String kind) {
        this.buildingId = buildingId;
        this.employeeId = employeeId;
        this.name = name;
        this.kind = kind;
    }
    
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKind(int k) {
        this.kind = (k == 0) ? "Nam"
                             : (k == 1) ? "Nữ"
                                        : "Khác";
    }
    
    public void setKind(String k) {
        this.kind = k;
    }
    
    public int getKind() {
        return ("Nam".equals(this.kind)) 
                    ? 0
                    : ("Nữ".equals(this.kind)) 
                        ? 1
                        : 2;
    }
    
    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            buildingId,
            employeeId,
            name,
            kind
        };
    }
    
}
