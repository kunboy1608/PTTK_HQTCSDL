package org.model;

public class University {
    
    String universityId;
    String name;

    public University() {
    }

    public University(String id, String name) {
        this.universityId = id;
        this.name = name;
    }
    
    public Object[] toObject(boolean b){
        return new Object[]{
            b,
            universityId,
            name
        };
    }

    public String getId() {
        return universityId;
    }

    public void setId(String id) {
        this.universityId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
}
