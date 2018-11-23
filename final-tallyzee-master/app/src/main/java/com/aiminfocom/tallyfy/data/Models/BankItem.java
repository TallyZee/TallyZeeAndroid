package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class BankItem implements Serializable {
    String bankAccNumber;
    public BankItem(String bankAccNumber, String bankName, String bankamount, String bankTypeTransction, String bankDate) {
        super();
        this.bankAccNumber = bankAccNumber;
        this.bankName = bankName;
        this.bankamount = bankamount;
        this.bankTypeTransction = bankTypeTransction;
        this.bankDate = bankDate;
    }
    public String getBankAccNumber() {
        return bankAccNumber;
    }
    public void setBankAccNumber(String bankAccNumber) {
        this.bankAccNumber = bankAccNumber;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankamount() {
        return bankamount;
    }
    public void setBankamount(String bankamount) {
        this.bankamount = bankamount;
    }
    public String getBankTypeTransction() {
        return bankTypeTransction;
    }
    public void setBankTypeTransction(String bankTypeTransction) {
        this.bankTypeTransction = bankTypeTransction;
    }
    public String getBankDate() {
        return bankDate;
    }
    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }
    String bankName;
    String bankamount;
    String bankTypeTransction;
    String bankDate;
}
