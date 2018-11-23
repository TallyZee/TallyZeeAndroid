package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class SalesOrderItem implements Serializable {
    String billref;
    String billAmount;
    String billName;
    String billCreditPeriod;

    public String getBillref() {
        return billref;
    }

    public void setBillref(String billref) {
        this.billref = billref;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCreditPeriod() {
        return billCreditPeriod;
    }

    public void setBillCreditPeriod(String billCreditPeriod) {
        this.billCreditPeriod = billCreditPeriod;
    }

    public SalesOrderItem(String billref, String billAmount, String billName, String billCreditPeriod) {
        this.billref = billref;
        this.billAmount = billAmount;
        this.billName = billName;
        this.billCreditPeriod = billCreditPeriod;
    }
}
