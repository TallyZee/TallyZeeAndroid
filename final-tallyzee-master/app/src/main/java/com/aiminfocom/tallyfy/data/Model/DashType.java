package com.aiminfocom.tallyfy.data.Model;

//enum for DashBoard Type
public enum DashType {
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
    Payment("Payment"),
    //indicate the PAYABLE type
    PAYABLE("PAYABLE"),
    //indicate the RECEIVABLE type//Receipt Note
    RECEIVABLE("RECEIVABLE"),
    DELIVERYNOTE("DELIVERYNOTE"),
    //indicate the DELIVERYNOTE type//Receipt Note
    RECEIPTNOTE("RECEIPTNOTE");
    public String value;
    DashType(String value)
    {
        this.value=value;
    }
    public String toString()
    {
        return value;
    }
}

