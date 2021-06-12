package org.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Bill {
    
    private String billId;
    private String building;
    private String room;
    private String employee;
    private String student;
    private LocalDate submittedDate;
    private Integer sum;
    private String billType;
    private LocalDate createdDate;
    
    private ElectricityReceipt electricDetail;
    private WaterReceipt waterDetail;

    public Bill() { }
    
    public Bill(String billId, String room, String building, LocalDate createdDate, 
                LocalDate submittedDate, String billType, Integer sum) {
        this.billId = billId;
        this.room = room;
        this.building = building;
        this.createdDate = createdDate;
        this.submittedDate = submittedDate;
        this.billType = billType;
        this.sum = sum;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    
    
    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
   
    public void setBillId(String billId) {
        this.billId = billId;
    }
    
    public void setRoom(String room, String building) {
        this.room = room;
        this.building = building;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }
    
    public void setSum(Integer sum) {
        this.sum = sum;
    }
    
    public void setType(String billType) {
        this.billType = billType;
    }
    
    public String getType() {
        return this.billType;
    }

    public String getBillId() {
        return billId;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    private String dateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }
    
    public LocalDate getSubmittedDate() {
        return this.submittedDate;
    }

    public Integer getSum() {
        return sum;
    }

    public String getBillType() {
        return billType;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }
    
    public Object[] getProperties() {
        Object[] r = {getBillId(), getRoom(), getBuilding(), getCreatedDate(),
                      getSubmittedDate(), getBillType(), getSum()};
        return r;
    }
    
    public ElectricityReceipt getElectricDetail() {
        return electricDetail;
    }

    public void setElectricDetail(ElectricityReceipt electricDetail) {
        this.electricDetail = electricDetail;
    }

    public WaterReceipt getWaterDetail() {
        return waterDetail;
    }

    public void setWaterDetail(WaterReceipt waterDetail) {
        this.waterDetail = waterDetail;
    }
    
    
    
}
