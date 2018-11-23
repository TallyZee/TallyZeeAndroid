package com.aiminfocom.tallyfy.data.Models;

public enum VoucherType {
    //indicate the Sales type
    SALES("SALES"),
    RECEIPT("RECEIPT"),
    //indicate the STOCK type
    STOCK("STOCK"),
    //indicate the CASH type
    CASH("CASH"),
    //indicate the BANK type
    BANK("BANK"),
    //indicate the PURCHASE type
    PURCHASE("PURCHASE"),
    //indicate the PROFIT_LOSS type
    PROFIT_LOSS("PROFIT_LOSS"),
    //indicate the EXPENSES type
    EXPENSES("EXPENSES"),
    //indicate the PO type
    PO("PO"),
    //indicate the SO type
    SO("SO"),
    //indicate the PAYABLE type
    PAYABLE("PAYABLE"),
    //indicate the RECEIVABLE type//Receipt Note
    RECEIVABLE("RECEIVABLE"),
    DELIVERYNOTE("DELIVERYNOTE"),
    //indicate the DELIVERYNOTE type//Receipt Note
    RECEIPTNOTE("RECEIPTNOTE");
    public String value;
    VoucherType(String value)
    {
        this.value=value;
    }
    public String toString()
    {
        return value;
    }
}
