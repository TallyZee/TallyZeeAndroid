package com.aiminfocom.tallyfy.ui.DashBoardSpace;

public enum ProfitNLossEnum {
    Sales_Accounts("Sales Accounts"),

    DIRECT_INCOME("DIRECT INCOME"),

    CLOSING_STOCK("CLOSING STOCK"),

    OPENING_STOCK("OPENING STOCK"),
    PURCHASE("PURCHASE"),

    DIRECT_EXPENSE("DIRECT EXPENSE"),

    Indirect_Incomes("Indirect Incomes"),

    Indirect_Expenses("Indirect Expenses");

    public String value;
    ProfitNLossEnum(String value)
    {
        this.value=value;
    }
    public String toString() {
        return value;
    }
}
