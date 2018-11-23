package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class DebitNoteVoucher implements Serializable {
    String voucherDate;

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

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

    public String getVoucherTypeParent() {
        return voucherTypeParent;
    }

    public void setVoucherTypeParent(String voucherTypeParent) {
        this.voucherTypeParent = voucherTypeParent;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherNarration() {
        return voucherNarration;
    }

    public void setVoucherNarration(String voucherNarration) {
        this.voucherNarration = voucherNarration;
    }

    public List<CreditNoteLedger> getLedger() {
        return ledger;
    }

    public void setLedger(List<CreditNoteLedger> ledger) {
        this.ledger = ledger;
    }

    public DebitNoteVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<CreditNoteLedger> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.ledger = ledger;
    }

    String voucherPartyName;
    String voucherTypeName;
    String voucherTypeParent;
    String voucherNumber;
    String voucherNarration;
    List<CreditNoteLedger> ledger;

}
