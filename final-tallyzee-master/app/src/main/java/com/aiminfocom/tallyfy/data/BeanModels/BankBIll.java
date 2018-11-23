package com.aiminfocom.tallyfy.data.BeanModels;

public class BankBIll {
    private String bankAccNumber;

    private String bankName;

    private String bankamount;

    private String bankTypeTransction;

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

    public BankBIll(String bankAccNumber, String bankName, String bankamount, String bankTypeTransction, String bankDate) {

        this.bankAccNumber = bankAccNumber;
        this.bankName = bankName;
        this.bankamount = bankamount;
        this.bankTypeTransction = bankTypeTransction;
        this.bankDate = bankDate;
    }

    private  String bankDate;
}
