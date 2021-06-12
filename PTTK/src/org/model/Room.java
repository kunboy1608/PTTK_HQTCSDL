
package org.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Room {

    private String roomId;
    private String building;
    private String facility;
    private Integer roomType;
    private Integer status;
    private Integer roomCapacity;
    private Integer roomCurrent;

    public Room() {}
    
    public Room(String roomId, String building, String facility, Integer roomType,
                Integer status, Integer roomCapacity) {
        this.roomId = roomId;
        this.building = building;
        this.facility = facility;
        this.roomType = roomType;
        this.status = status;
        this.roomCapacity = roomCapacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
    
    public String getRoomRatio() {
        return (roomCurrent + "/" + roomCurrent);
    }
    
    public Integer getRoomCurrent() {
        return this.roomCurrent;
    }
    
    public void setRoomCurrent(Integer current) {
        this.roomCurrent = current;
    }
    
    public void setRoomRatio(String ratio) {
        if (ratio.contains("/")) {
            this.roomCurrent = Integer.valueOf(ratio.split("/")[0]);
            this.roomCapacity = Integer.valueOf(ratio.split("/")[1]);
        } else {
            this.roomCapacity = Integer.valueOf(ratio);
            this.roomCurrent = 0;
        }
    }
    
    public void setRoomAndBuilding(String storagePattern) {
        Matcher matcher = Pattern.compile("(?:[A-Z]+)").matcher(storagePattern);
        if (!matcher.find()) {
            throw new NullPointerException("Wrong Format");
        }
        int location = matcher.start();
        this.setRoomId(storagePattern.substring(0, location));
        this.setBuilding(storagePattern.substring(location));
    }
    
    public String getRoomAndBuilding() {
        return this.getRoomId() + this.getBuilding();
    }
    
}
