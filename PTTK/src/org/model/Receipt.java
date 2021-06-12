package org.model;

import java.time.LocalDate;

public abstract class Receipt {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startNumber;
    private int endNumber;
    private int sum;

    public Receipt() { }

    public Receipt(String id, LocalDate startDate, LocalDate endDate, int startNumber, int endNumber) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(int endNumber) {
        this.endNumber = endNumber;
    } 
    
}
