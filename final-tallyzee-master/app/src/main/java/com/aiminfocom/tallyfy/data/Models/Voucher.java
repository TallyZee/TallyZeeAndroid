package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class Voucher implements Serializable{
    String voucherNumber;
    String voucherDate;
    String voucherPartyName;
    String voucherType;
    List<Item> voucherItems;


    public String getVoucherNumber() {
        return voucherNumber;
    }
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
    public String getVoucherDate() {
        return voucherDate;
    }
    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }
    public String getVoucherPartyName() {
        return voucherPartyName;
    }
    public void setVoucherPartyName(String voucherPartyName) {
        this.voucherPartyName = voucherPartyName;
    }
    public Voucher(String voucherNumber, String voucherDate, String voucherPartyName, String voucherType,
                   List<Item> voucherItems, List<BankItem> voucherBankItem, String voucherLedger, String voucherAmount) {
        super();
        this.voucherNumber = voucherNumber;
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherType = voucherType;
        this.voucherItems = voucherItems;

        this.voucherLedger = voucherLedger;
        this.voucherAmount = voucherAmount;
    }
    public List<Item> getVoucherItems() {
        return voucherItems;
    }
    public void setVoucherItems(List<Item> voucherItems) {
        this.voucherItems = voucherItems;
    }
   public String getVoucherType() {
        return voucherType;
    }
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }
    public String getVoucherLedger() {
        return voucherLedger;
    }
    public void setVoucherLedger(String voucherLedger) {
        this.voucherLedger = voucherLedger;
    }
    public String getVoucherAmount() {
        return voucherAmount;
    }
    public void setVoucherAmount(String voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    String voucherLedger;
    String voucherAmount;

}
