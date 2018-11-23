package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class PurchaseOrderItem implements Serializable {
    String billName;
    String billAmount;
    String billType;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getOrderDueDate() {
        return orderDueDate;
    }

    public void setOrderDueDate(String orderDueDate) {
        this.orderDueDate = orderDueDate;
    }

    public PurchaseOrderItem(String billName, String billAmount, String billType, String orderDueDate) {
        this.billName = billName;
        this.billAmount = billAmount;
        this.billType = billType;
        this.orderDueDate = orderDueDate;
    }

    String orderDueDate;
}
