package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "BankItem")
public class BankItem implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "bankAccNumber")
    private String bankAccNumber;
    @ColumnInfo(name = "bankName")
    private String bankName;
    @ColumnInfo(name = "bankamount")
    private String bankamount;
    @ColumnInfo(name = "bankTypeTransction")
    private String bankTypeTransction;
    @ColumnInfo(name = "bankDate")
    private  String bankDate;

    public BankItem()
    {

    }

    @Override
    public String toString() {
        return "BankItem{" +
                "bankAccNumber='" + bankAccNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankamount='" + bankamount + '\'' +
                ", bankTypeTransction='" + bankTypeTransction + '\'' +
                ", bankDate='" + bankDate + '\'' +
                '}';
    }

    public BankItem(int id, String bankAccNumber, String bankName, String bankamount, String bankTypeTransction, String bankDate) {
        this.id = id;
        this.bankAccNumber = bankAccNumber;
        this.bankName = bankName;
        this.bankamount = bankamount;
        this.bankTypeTransction = bankTypeTransction;
        this.bankDate = bankDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected BankItem(Parcel in) {
        bankAccNumber = in.readString();
        bankName = in.readString();
        bankamount = in.readString();
        bankTypeTransction = in.readString();
        bankDate = in.readString();
    }

    public static final Creator<BankItem> CREATOR = new Creator<BankItem>() {
        @Override
        public BankItem createFromParcel(Parcel in) {
            return new BankItem(in);
        }

        @Override
        public BankItem[] newArray(int size) {
            return new BankItem[size];
        }
    };

    public String getBankAccNumber() {
        return bankAccNumber;
    }
    public void setBankAccNumber(String bankAccNumber) {
        this.bankAccNumber = bankAccNumber;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankamount() {
        return bankamount;
    }
    public void setBankamount(String bankamount) {
        this.bankamount = bankamount;
    }
    public String getBankTypeTransction() {
        return bankTypeTransction;
    }
    public void setBankTypeTransction(String bankTypeTransction) {
        this.bankTypeTransction = bankTypeTransction;
    }
    public String getBankDate() {
        return bankDate;
    }
    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bankName);
        dest.writeString(bankamount);
        dest.writeString(bankTypeTransction);
        dest.writeString(bankDate);
    }
}
