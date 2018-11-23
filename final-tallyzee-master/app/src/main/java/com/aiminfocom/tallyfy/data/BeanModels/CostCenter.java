package com.aiminfocom.tallyfy.data.BeanModels;

public class CostCenter {
    String costactualQty;
    String costamount;
    String costbillQty;
    String costname;

    public String getCostactualQty() {
        return costactualQty;
    }

    public void setCostactualQty(String costactualQty) {
        this.costactualQty = costactualQty;
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

    public String getCostname() {
        return costname;
    }

    public void setCostname(String costname) {
        this.costname = costname;
    }

    public CostCenter(String costactualQty, String costamount, String costbillQty, String costname) {

        this.costactualQty = costactualQty;
        this.costamount = costamount;
        this.costbillQty = costbillQty;
        this.costname = costname;
    }
}
