/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.awt.Image;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kunbo
 */
public class Student {
    String ID;
    String Surname;
    String Name;
    String address;
    String Sex;
    Date birthday;
    String phoneNumber;
    String Email;
    String IDCard;
    String nationality;
    String nation;
    String BHYT;
    String personalName;
    String phonePersonal;
    String addressPersonal;
    Image Avatar;

    String IDStudent;
    String IDRoom;
    String numRoom;
    String IDBuilding;
    String IDSchool;
    Date comeDate;
    Date expDate;
    int status;

    public Student() {
        this.ID = "";
        this.Surname = "";
        this.Name = "";
        this.address = "";
        this.Sex = "";
        this.phoneNumber = "";
        this.Email = "";
        this.IDCard = "";
        this.nationality = "";
        this.nation = "";
        this.BHYT = "";
        this.personalName = "";
        this.phonePersonal = "";
        this.addressPersonal = "";
        this.Avatar = null;
        this.IDStudent = "";
        this.IDRoom = "";
        this.numRoom = "";
        this.IDBuilding = "";
        this.IDSchool = "";
        int status = 0;
    }

    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            IDStudent,
            Surname,
            Name,
            Sex,
            IDRoom,
            IDCard,
            IDSchool,
            expDate,
            address
        };
    }

    public String getID() {
        return ID;
    }

    public String getNumRoom() {
        return numRoom;
    }

    public String getIDBuilding() {
        return IDBuilding;
    }

    public void setID(String ID) {
        if (ID == null) {
            return;
        }
        this.ID = ID.trim();
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        if (Surname == null) {
            return;
        }
        this.Surname = Surname.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            return;
        }
        this.address = address.trim();
    }

    public String getSex() {
        return Sex;
    }

    public int getSexInt() {
        if (this.Sex == "Nam") {
            return 0;
        } else {
            return 1;
        }
    }

    public void setSex(int s) {
        if (s == 0) {
            this.Sex = "Nam";
        } else if (s == 1) {
            this.Sex = "Nữ";
        } else {
            this.Sex = "Khác";
        }
    }

    public void setSex(String sex) {
        if (sex == null) {
            return;
        }
        this.Sex = sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return;
        }
        this.phoneNumber = phoneNumber.trim();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        if (Email == null) {
            return;
        }
        this.Email = Email.trim();
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        if (IDCard == null) {
            return;
        }
        this.IDCard = IDCard.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (nationality == null) {
            return;
        }
        this.nationality = nationality.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        if (nation == null) {
            return;
        }
        this.nation = nation.trim();
    }

    public String getBHYT() {
        return BHYT;
    }

    public void setBHYT(String BHYT) {
        if (BHYT == null) {
            return;
        }
        this.BHYT = BHYT.trim();
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        if (personalName == null) {
            return;
        }
        this.personalName = personalName.trim();
    }

    public String getPhonePersonal() {
        return phonePersonal;
    }

    public void setPhonePersonal(String phonePersonal) {
        if (phonePersonal == null) {
            return;
        }
        this.phonePersonal = phonePersonal.trim();
    }

    public String getAddressPersonal() {
        return addressPersonal;
    }

    public void setAddressPersonal(String addressPersonal) {
        if (addressPersonal == null) {
            return;
        }
        this.addressPersonal = addressPersonal.trim();
    }

    public Image getAvatar() {
        return Avatar;
    }

    public void setAvatar(Image Avatar) {
        this.Avatar = Avatar;
    }

    public String getIDStudent() {
        return IDStudent.trim();
    }

    public void setIDStudent(String IDStudent) {
        if (IDStudent == null) {
            return;
        }
        this.IDStudent = IDStudent.trim();
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
        this.IDBuilding = this.IDRoom.substring(matcher.start());
    }

    public void setIDRoom(String numRoom, String IDBuilding) {
        numRoom = numRoom.trim();
        IDBuilding = IDBuilding.trim();
        this.IDRoom = numRoom + IDBuilding;
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

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(String sta) {
        if (sta == null) {
            return;
        }
        sta = sta.trim();
        this.status = Integer.parseInt(sta);
    }
}
