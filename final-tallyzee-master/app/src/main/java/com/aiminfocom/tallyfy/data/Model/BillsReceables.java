package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "BillsReceivable")
public class BillsReceables implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "billDate")
    private  String billDate;
    @ColumnInfo(name = "billRef")
    private String billRef;
    @ColumnInfo(name = "billParty")
    private String billParty;
    @ColumnInfo(name = "billCl")
    private String billCl;
    @ColumnInfo(name = "billDue")
    private String billDue;
    @ColumnInfo(name = "billOverDue")
    private String billOverDue;

    protected BillsReceables(Parcel in) {
        billDate = in.readString();
        billRef = in.readString();
        billParty = in.readString();
        billCl = in.readString();
        billDue = in.readString();
        billOverDue = in.readString();
    }

    public BillsReceables()
    {

    }

    @Override
    public String toString() {
        return "BillsPayable{" +
                "billDate='" + billDate + '\'' +
                ", billRef='" + billRef + '\'' +
                ", billParty='" + billParty + '\'' +
                ", billCl='" + billCl + '\'' +
                ", billDue='" + billDue + '\'' +
                ", billOverDue='" + billOverDue + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Creator<BillsPayable> CREATOR = new Creator<BillsPayable>() {
        @Override
        public BillsPayable createFromParcel(Parcel in) {
            return new BillsPayable(in);
        }

        @Override
        public BillsPayable[] newArray(int size) {
            return new BillsPayable[size];
        }
    };

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillRef() {
        return billRef;
    }

    public void setBillRef(String billRef) {
        this.billRef = billRef;
    }

    public String getBillParty() {
        return billParty;
    }

    public void setBillParty(String billParty) {
        this.billParty = billParty;
    }

    public String getBillCl() {
        return billCl;
    }

    public void setBillCl(String billCl) {
        this.billCl = billCl;
    }

    public String getBillDue() {
        return billDue;
    }

    public void setBillDue(String billDue) {
        this.billDue = billDue;
    }

    public String getBillOverDue() {
        return billOverDue;
    }

    public void setBillOverDue(String billOverDue) {
        this.billOverDue = billOverDue;
    }

    public BillsReceables(String billDate, String billRef, String billParty, String billCl, String billDue, String billOverDue) {
        this.billDate = billDate;
        this.billRef = billRef;
        this.billParty = billParty;
        this.billCl = billCl;
        this.billDue = billDue;
        this.billOverDue = billOverDue;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billDate);
        dest.writeString(billRef);
        dest.writeString(billParty);
        dest.writeString(billCl);
        dest.writeString(billDue);
        dest.writeString(billOverDue);
    }
}
