package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class CreditNoteLedger implements Serializable {
    String amount;
    String ledgerName;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<CreditNoteItem> getCreditNoteItems() {
        return creditNoteItems;
    }

    public void setCreditNoteItems(List<CreditNoteItem> creditNoteItems) {
        this.creditNoteItems = creditNoteItems;
    }

    public CreditNoteLedger(String amount, String ledgerName, List<CreditNoteItem> creditNoteItems) {
        this.amount = amount;
        this.ledgerName = ledgerName;
        this.creditNoteItems = creditNoteItems;
    }

    List<CreditNoteItem> creditNoteItems;
}
