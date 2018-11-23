package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails;

import java.io.Serializable;

public class ItemVoucherModel implements Serializable {
String name;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount;

    public ItemVoucherModel(String name,String amount, String apr, String sCost, String sPrice, String reorder, String mOrder, String nos) {
        this.name = name;
        this.amount=amount;
        this.apr = apr;
        this.sCost = sCost;
        this.sPrice = sPrice;
        this.reorder = reorder;
        this.mOrder = mOrder;
        this.nos = nos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getsCost() {
        return sCost;
    }

    public void setsCost(String sCost) {
        this.sCost = sCost;
    }

    public String getsPrice() {
        return sPrice;
    }

    public void setsPrice(String sPrice) {
        this.sPrice = sPrice;
    }

    public String getReorder() {
        return reorder;
    }

    public void setReorder(String reorder) {
        this.reorder = reorder;
    }

    public String getmOrder() {
        return mOrder;
    }

    public void setmOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public String getNos() {
        return nos;
    }

    public void setNos(String nos) {
        this.nos = nos;
    }

    String apr;
String sCost;
String sPrice;
String reorder;
String mOrder;
String nos;

}
