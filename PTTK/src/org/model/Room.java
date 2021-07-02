/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates Hoang Dinh Phu 19520838
 * and open the template in the editor.
 */
package org.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kunbo
 */
public class Room {

    String IDRoom;
    String numRoom;
    String IDBuilding;
    String facilities;
    String kind;
    int capacity;
    int remain;

    public Room() {
        this.IDRoom = "";
        this.IDBuilding = "";
        this.numRoom = "";
        this.facilities = "";
        this.kind = "";
        this.capacity = 0;
        this.remain = 0;
    }

    public Object[] toObject() {
        return new Object[]{
            false,
            IDBuilding,
            numRoom,
            facilities,
            kind,
            capacity,
            remain
        };
    }

    public String getIDRoom() {
        return IDRoom;
    }

    public void setIDRoom(String IDRoom) {
        if (IDRoom == null) {
            return;
        }
        this.IDRoom = IDRoom.trim();
        Matcher matcher = Pattern.compile("(?:[A-Z]+)").matcher(this.IDRoom);
        if (!matcher.find()) {
            throw new NullPointerException("Wrong Format");
        }
        this.numRoom = this.IDRoom.substring(0, matcher.start());
    }

    public void setIDRoom(String numRoom, String IDBuilding) {
        if (numRoom == null || IDBuilding == null) {
            return;
        }
        this.IDRoom = numRoom.trim() + IDBuilding.trim().toUpperCase();
    }

    public String getNumRoom() {
        return numRoom;
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

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        if (facilities == null) {
            return;
        }
        this.facilities = facilities.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        if (kind == null) {
            return;
        }
        this.kind = kind.trim();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(String capacity) {
        if (capacity == null) {
            return;
        }
        this.capacity = Integer.parseInt(capacity.trim());
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public void setRemain(String remain) {
        if (remain == null) {
            return;
        }
        this.remain = Integer.parseInt(remain.trim());
    }
}
