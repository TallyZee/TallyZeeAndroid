package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "Ledger")
public class Ledger implements Serializable,Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(DataConverter.class)
   private List<Item> itemList;

    @TypeConverters(DataConverter.class)
    private List<BankItem> voucherBankItem;
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "ledgerName")
    private String ledgerName;


public Ledger()
{

}

    public Ledger(int id, List<Item> itemList, List<BankItem> voucherBankItem, String amount, String ledgerName) {
        this.id = id;
        this.itemList = itemList;
        this.voucherBankItem = voucherBankItem;
        this.amount = amount;
        this.ledgerName = ledgerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "itemList=" + itemList +
                ", voucherBankItem=" + voucherBankItem +
                ", amount='" + amount + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                '}';
    }

    protected Ledger(Parcel in) {
        itemList = in.createTypedArrayList(Item.CREATOR);
        voucherBankItem = in.createTypedArrayList(BankItem.CREATOR);
        amount = in.readString();
        ledgerName = in.readString();
    }




    public static final Creator<Ledger> CREATOR = new Creator<Ledger>() {
        @Override
        public Ledger createFromParcel(Parcel in) {
            return new Ledger(in);
        }

        @Override
        public Ledger[] newArray(int size) {
            return new Ledger[size];
        }
    };

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




    public List<BankItem> getVoucherBankItem() {
        return voucherBankItem;
    }

    public void setVoucherBankItem(List<BankItem> voucherBankItem) {
        this.voucherBankItem = voucherBankItem;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(itemList);
        dest.writeTypedList(voucherBankItem);
        dest.writeString(amount);
        dest.writeString(ledgerName);
    }
}
