package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class JournalLedger implements Serializable {
    String ledgerAmount;
    String ledgerName;
    List<JournalItem> ledgerItem;

    public String getLedgerAmount() {
        return ledgerAmount;
    }

    public void setLedgerAmount(String ledgerAmount) {
        this.ledgerAmount = ledgerAmount;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<JournalItem> getLedgerItem() {
        return ledgerItem;
    }

    public void setLedgerItem(List<JournalItem> ledgerItem) {
        this.ledgerItem = ledgerItem;
    }

    public JournalLedger(String ledgerAmount, String ledgerName, List<JournalItem> ledgerItem) {
        this.ledgerAmount = ledgerAmount;
        this.ledgerName = ledgerName;
        this.ledgerItem = ledgerItem;
    }
}
