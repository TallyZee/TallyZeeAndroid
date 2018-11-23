package com.aiminfocom.tallyfy.data.Model;

public class univBatch {
    private String gowdownName;

    private String orderNumber;

    private String batchName;

    private  String amount;

    private  String actualQty;

    private String billQty;

    public String getGowdownName() {
        return gowdownName;
    }

    public void setGowdownName(String gowdownName) {
        this.gowdownName = gowdownName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActualQty() {
        return actualQty;
    }

    public void setActualQty(String actualQty) {
        this.actualQty = actualQty;
    }

    public String getBillQty() {
        return billQty;
    }

    public void setBillQty(String billQty) {
        this.billQty = billQty;
    }

    public String getOrderDueDate() {
        return orderDueDate;
    }

    public void setOrderDueDate(String orderDueDate) {
        this.orderDueDate = orderDueDate;
    }

    public univBatch(String gowdownName, String orderNumber, String batchName, String amount, String actualQty, String billQty, String orderDueDate) {

        this.gowdownName = gowdownName;
        this.orderNumber = orderNumber;
        this.batchName = batchName;
        this.amount = amount;
        this.actualQty = actualQty;
        this.billQty = billQty;
        this.orderDueDate = orderDueDate;
    }

    private  String orderDueDate;
}
