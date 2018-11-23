package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class JournalItem implements Serializable {
    String billref;
    String billAmount;
    String billName;

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

    public JournalItem(String billref, String billAmount, String billName) {
        this.billref = billref;
        this.billAmount = billAmount;
        this.billName = billName;
    }
}
