package com.aiminfocom.tallyfy.data.Model;

import java.io.Serializable;

public class SalesOrderAccountingAllocation implements Serializable {
    String ledgerName;

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getLedgerAmount() {
        return ledgerAmount;
    }

    public void setLedgerAmount(String ledgerAmount) {
        this.ledgerAmount = ledgerAmount;
    }

    public SalesOrderAccountingAllocation(String ledgerName, String ledgerAmount) {
        this.ledgerName = ledgerName;
        this.ledgerAmount = ledgerAmount;
    }

    String ledgerAmount;
}
