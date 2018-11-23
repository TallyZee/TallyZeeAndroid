package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class LedgerItem  implements Serializable{


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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public LedgerItem(String amount, String ledgerName, List<Item> itemList) {

        this.amount = amount;
        this.ledgerName = ledgerName;
        this.itemList = itemList;
    }

    String amount;
    String ledgerName;

    public LedgerItem(String amount, String ledgerName, List<Item> itemList, List<BankItem> voucherBankItem) {
        this.amount = amount;
        this.ledgerName = ledgerName;
        this.itemList = itemList;
        this.voucherBankItem = voucherBankItem;
    }

    public List<BankItem> getVoucherBankItem() {
        return voucherBankItem;
    }

    public void setVoucherBankItem(List<BankItem> voucherBankItem) {
        this.voucherBankItem = voucherBankItem;
    }

    List<Item> itemList;
    List<BankItem> voucherBankItem;


}
