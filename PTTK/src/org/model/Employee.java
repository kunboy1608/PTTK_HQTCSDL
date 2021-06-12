package org.model;

import java.awt.Image;
import java.time.LocalDate;

public class Employee {

    private String surname;
    private String name;
    private String address;
    private String sex;
    private LocalDate birthday;
    private String phoneNumber;
    private String email;
    private String cardId;
    private String nationality;
    private String nation;
    private String bhyt;
    private Image avatar;
    private boolean isRoomManager;
    
    private String personalName;
    private String phonePersonal;
    private String addressPersonal;
    private String room;
    
    private Building building;
    private LocalDate startDate;
    private int status;
    
    
    public Object[] toProperties() {
        return new Object[]{
            cardId,
            surname,
            name,
            sex,
            building,
            startDate,
            address
        };
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getFullName() {
        return this.surname + " " + this.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }
    
    public int getSexCode() {
        return this.sex.equals("Nam")
                    ? 0
                    : this.sex.equals("Nữ")
                            ? 1
                            : 2;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public void setSex(int sexCode) {
        this.sex = (sexCode == 0) ? "Nam"
                                  : (sexCode == 1) ? "Nữ"
                                                   : "Khác";
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isRoomManager() {
        return isRoomManager;
    }

    public void setRoomManager(boolean isRoomManager) {
        this.isRoomManager = isRoomManager;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getPhonePersonal() {
        return phonePersonal;
    }

    public void setPhonePersonal(String phonePersonal) {
        this.phonePersonal = phonePersonal;
    }

    public String getAddressPersonal() {
        return addressPersonal;
    }

    public void setAddressPersonal(String addressPersonal) {
        this.addressPersonal = addressPersonal;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    

}
