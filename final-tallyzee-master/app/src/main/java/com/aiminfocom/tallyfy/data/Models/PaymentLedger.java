package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class PaymentLedger implements Serializable {
    String amount;
    String ledgerName;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PaymentLedger(String amount, String ledgerName, List<PaymentBill> paymentBillList, List<PaymentBank> paymentBankList, List<CostCenter> costCenterList) {
        this.amount = amount;
        this.ledgerName = ledgerName;
        this.paymentBillList = paymentBillList;
        this.paymentBankList = paymentBankList;
        this.costCenterList = costCenterList;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<PaymentBill> getPaymentBillList() {
        return paymentBillList;
    }

    public void setPaymentBillList(List<PaymentBill> paymentBillList) {
        this.paymentBillList = paymentBillList;
    }

    public List<PaymentBank> getPaymentBankList() {
        return paymentBankList;
    }

    public void setPaymentBankList(List<PaymentBank> paymentBankList) {
        this.paymentBankList = paymentBankList;
    }

    public List<CostCenter> getCostCenterList() {
        return costCenterList;
    }

    public void setCostCenterList(List<CostCenter> costCenterList) {
        this.costCenterList = costCenterList;
    }

    List<PaymentBill> paymentBillList;
    List<PaymentBank> paymentBankList;
    List<CostCenter> costCenterList;
}
