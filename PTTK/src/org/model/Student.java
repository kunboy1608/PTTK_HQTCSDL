package org.model;

import java.awt.Image;
import java.time.LocalDate;

public class Student {

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
    private Image Avatar;

    private String studentId;
    private String university;
    private String room;
    private LocalDate comeDate;
    private LocalDate expDate;
    private int status;
    
    private String personalName;
    private String phonePersonal;
    private String addressPersonal;
    
    public Object[] toProperties() {
        return new Object[]{
            studentId,
            surname,
            name,
            sex,
            room,
            cardId,
            university,
            expDate,
            address
        };
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return Avatar;
    }

    public void setAvatar(Image Avatar) {
        this.Avatar = Avatar;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getComeDate() {
        return comeDate;
    }

    public void setComeDate(LocalDate comeDate) {
        this.comeDate = comeDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    
    
    
}
