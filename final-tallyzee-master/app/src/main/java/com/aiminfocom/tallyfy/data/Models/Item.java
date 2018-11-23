package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class Item  implements Serializable {
    String billName;
    String billAmount;
    String billType;

    public Item(String billName, String billAmount, String billType, String billCreditPeriod) {
        this.billName = billName;
        this.billAmount = billAmount;
        this.billType = billType;
        this.billCreditPeriod = billCreditPeriod;
    }

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

    public String getBillCreditPeriod() {
        return billCreditPeriod;
    }

    public void setBillCreditPeriod(String billCreditPeriod) {
        this.billCreditPeriod = billCreditPeriod;
    }

    String billCreditPeriod;
}
