/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author kunbo
 */
public class Employee {

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

    String IDEmployee;
    String IDDepartment;
    Date workingDate;
    int status;

    public Employee() {
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
        this.IDEmployee = "";
        this.IDDepartment = "";
        int status = 0;
    }

    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            IDEmployee,
            Surname,
            Name,
            Sex,
            IDCard,
            IDDepartment,
            workingDate,
            address
        };
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

    public String getIDDepartment() {
        return IDDepartment;
    }

    public void setIDDepartment(String IDDepartment) {
         if (IDDepartment == null) {
            return;
        }
        this.IDDepartment = IDDepartment;
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }

    public String getID() {
        return ID;
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
