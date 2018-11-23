package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class CostCenter implements Serializable {
    String costname;
    String costamount;
    String costbillQty;
    String costactualQty;

    public String getCostname() {
        return costname;
    }

    public void setCostname(String costname) {
        this.costname = costname;
    }

    public String getCostamount() {
        return costamount;
    }

    public void setCostamount(String costamount) {
        this.costamount = costamount;
    }

    public String getCostbillQty() {
        return costbillQty;
    }

    public void setCostbillQty(String costbillQty) {
        this.costbillQty = costbillQty;
    }

    public String getCostactualQty() {
        return costactualQty;
    }

    public void setCostactualQty(String costactualQty) {
        this.costactualQty = costactualQty;
    }

    public CostCenter(String costname, String costamount, String costbillQty, String costactualQty) {
        this.costname = costname;
        this.costamount = costamount;
        this.costbillQty = costbillQty;
        this.costactualQty = costactualQty;
    }
}
