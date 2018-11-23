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
@Entity(tableName = "CreidtNoteLedger")
public class CreditNoteLedger implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "ledgerName")
    private String ledgerName;
    @ColumnInfo(name = "creditNoteItems")
    @TypeConverters(DataConverter.class)
    private List<CreditNoteItem> itemList;

    public CreditNoteLedger() {
    }

    protected CreditNoteLedger(Parcel in) {
        amount = in.readString();
        ledgerName = in.readString();
        itemList = in.createTypedArrayList(CreditNoteItem.CREATOR);
    }

    public static final Creator<CreditNoteLedger> CREATOR = new Creator<CreditNoteLedger>() {
        @Override
        public CreditNoteLedger createFromParcel(Parcel in) {
            return new CreditNoteLedger(in);
        }

        @Override
        public CreditNoteLedger[] newArray(int size) {
            return new CreditNoteLedger[size];
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreditNoteLedger{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", itemList=" + itemList +
                '}';
    }

    public List<CreditNoteItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CreditNoteItem> itemList) {
        this.itemList = itemList;
    }

    public CreditNoteLedger(String amount, String ledgerName, List<CreditNoteItem> itemList) {
        this.amount = amount;
        this.ledgerName = ledgerName;
        this.itemList = itemList;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(amount);
        dest.writeString(ledgerName);
        dest.writeTypedList(itemList);
    }
}
