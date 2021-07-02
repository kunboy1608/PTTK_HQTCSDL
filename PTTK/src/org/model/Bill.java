/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kunbo
 */
public class Bill {

    String IDBill;
    String IDEmployee;
    String IDRoom;
    String numRoom;
    String IDBuilding;
    String IDStudent;
    Date invoiceDate;
    Date collectionDate;
    String kind;
    int value;
    String note;

    String IDElectric;
    Date startDateE;
    Date endDateE;
    int headNumE;
    int botNumE;
    int sumE;

    String IDWater;
    Date startDateW;
    Date endDateW;
    int headNumW;
    int botNumW;
    int sumW;

    public Bill() {
        // Khởi tạo như thế này để đảm bảo không xảy ra trường hợp null
        this.IDBill = "";
        this.IDEmployee = "";
        this.IDRoom = "";
        this.numRoom = "";
        this.IDBuilding = "";
        this.IDStudent = "";
        this.kind = "";
        this.note = "";

        this.IDElectric = "";
        this.headNumE = 0;
        this.botNumE = 0;
        this.sumE = 0;

        this.IDWater = "";
        this.headNumW = 0;
        this.botNumW = 0;
        this.sumW = 0;
    }
    // Cuyển thành kiểu Object
    public Object[] toObject(boolean b) {
        return new Object[]{
            b,
            IDBill,
            IDEmployee,
            IDBuilding,
            numRoom,
            IDStudent,
            invoiceDate,
            collectionDate,
            kind,
            value,
            note
        };
    }

    public String getNumRoom() {
        return numRoom;
    }

    public String getIDBuilding() {
        return IDBuilding;
    }

    public int getSumE() {
        return sumE;
    }

    public void setSumE(int sumE) {
        this.sumE = sumE;
    }
    // Xử lí đầu vào đảm bảo đúng định dạng và trim() nhiều lần
    public void setSumE(String sumE) {
        if (sumE == null) {
            return;
        }
        sumE = sumE.trim();
        this.sumE = Integer.parseInt(sumE);
    }

    public int getSumW() {
        return sumW;
    }

    public void setSumW(int sumW) {
        this.sumW = sumW;
    }

    public void setSumW(String sumW) {
        if (sumW == null) {
            return;
        }
        sumW = sumW.trim();
        this.sumW = Integer.parseInt(sumW);
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValue(String value) {
        if (value == null) {
            return;
        }
        value = value.trim();
        this.value = Integer.parseInt(value);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        if (note == null) {
            return;
        }
        this.note = note.trim();
    }

    public String getIDBill() {
        return IDBill;
    }

    public void setIDBill(String IDBill) {
        if (IDBill == null) {
            return;
        }
        this.IDBill = IDBill.trim();
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

    public String getIDStudent() {
        return IDStudent;
    }

    public void setIDStudent(String IDStudent) {
        if (IDStudent == null) {
            return;
        }
        this.IDStudent = IDStudent.trim();
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getIDElectric() {
        return IDElectric;
    }

    public void setIDElectric(String IDElectric) {
        if (IDElectric == null) {
            return;
        }
        this.IDElectric = IDElectric;
    }

    public Date getStartDateE() {
        return startDateE;
    }

    public void setStartDateE(Date startDateE) {
        this.startDateE = startDateE;
    }

    public Date getEndDateE() {
        return endDateE;
    }

    public void setEndDateE(Date endDateE) {
        this.endDateE = endDateE;
    }

    public int getHeadNumE() {
        return headNumE;
    }

    public void setHeadNumE(int headNumE) {
        this.headNumE = headNumE;
    }

    public void setHeadNumE(String headNumE) {
        if (headNumE == null) {
            return;
        }
        headNumE = headNumE.trim();
        this.headNumE = Integer.parseInt(headNumE);
    }

    public int getBotNumE() {
        return botNumE;
    }

    public void setBotNumE(int botNumE) {
        this.botNumE = botNumE;
    }

    public void setBotNumE(String botNumE) {
        if (botNumE == null) {
            return;
        }
        botNumE = botNumE.trim();
        this.botNumE = Integer.parseInt(botNumE);
    }

    public String getIDWater() {
        return IDWater;
    }

    public void setIDWater(String IDWater) {
        if (IDWater == null) {
            return;
        }
        this.IDWater = IDWater.trim();
    }

    public Date getStartDateW() {
        return startDateW;
    }

    public void setStartDateW(Date startDateW) {
        this.startDateW = startDateW;
    }

    public Date getEndDateW() {
        return endDateW;
    }

    public void setEndDateW(Date endDateW) {
        this.endDateW = endDateW;
    }

    public int getHeadNumW() {
        return headNumW;
    }

    public void setHeadNumW(int headNumW) {
        this.headNumW = headNumW;
    }

    public void setHeadNumW(String headNumW) {
        if (headNumW == null) {
            return;
        }
        headNumW = headNumW.trim();
        this.headNumW = Integer.parseInt(headNumW);
    }

    public int getBotNumW() {
        return botNumW;
    }

    public void setBotNumW(int botNumW) {
        this.botNumW = botNumW;
    }

    public void setBotNumW(String botNumW) {
        if (botNumW == null) {
            return;
        }
        botNumW = botNumW.trim();
        this.botNumW = Integer.parseInt(botNumW);
    }
}
