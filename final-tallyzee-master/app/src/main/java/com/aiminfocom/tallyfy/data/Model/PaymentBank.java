package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "Payment Bank")
public class PaymentBank implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "bankName")
    private  String bankName;
    @ColumnInfo(name = "bankamount")
    private String bankamount;
    @ColumnInfo(name = "bankTypeTransction")
    private String bankTypeTransction;
    @ColumnInfo(name = "bankDate")
    private String bankDate;
    @ColumnInfo(name = "bankAccNumber")
    private String bankAccNumber;

    protected PaymentBank(Parcel in) {
        bankName = in.readString();
        bankamount = in.readString();
        bankTypeTransction = in.readString();
        bankDate = in.readString();
        bankAccNumber = in.readString();
    }

    public PaymentBank()
    {

    }

    @Override
    public String toString() {
        return "PaymentBank{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", bankamount='" + bankamount + '\'' +
                ", bankTypeTransction='" + bankTypeTransction + '\'' +
                ", bankDate='" + bankDate + '\'' +
                ", bankAccNumber='" + bankAccNumber + '\'' +
                '}';
    }

    public static final Creator<PaymentBank> CREATOR = new Creator<PaymentBank>() {
        @Override
        public PaymentBank createFromParcel(Parcel in) {
            return new PaymentBank(in);
        }

        @Override
        public PaymentBank[] newArray(int size) {
            return new PaymentBank[size];
        }
    };

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

    public String getBankAccNumber() {
        return bankAccNumber;
    }

    public void setBankAccNumber(String bankAccNumber) {
        this.bankAccNumber = bankAccNumber;
    }

    public PaymentBank(String bankName, String bankamount, String bankTypeTransction, String bankDate, String bankAccNumber) {
        this.bankName = bankName;
        this.bankamount = bankamount;
        this.bankTypeTransction = bankTypeTransction;
        this.bankDate = bankDate;
        this.bankAccNumber = bankAccNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeString(bankAccNumber);
    }
}
