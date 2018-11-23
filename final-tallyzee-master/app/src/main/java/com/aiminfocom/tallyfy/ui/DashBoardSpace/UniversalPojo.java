package com.aiminfocom.tallyfy.ui.DashBoardSpace;

import android.os.Parcel;
import android.os.Parcelable;

public class UniversalPojo implements Parcelable {
    private String stockItemName;

    private String basicUserDesc;

    private String rate;

    private String edqty;

    public UniversalPojo(String stockItemName, String basicUserDesc, String rate, String edqty, String actualQty, String partyName, String amount) {
        this.stockItemName = stockItemName;
        this.basicUserDesc = basicUserDesc;
        this.rate = rate;
        this.edqty = edqty;
        this.actualQty = actualQty;
        this.partyName = partyName;
        this.amount = amount;
    }

    private String actualQty;

    private String partyName;

    public UniversalPojo(String partyName, String amount, String getBillRef, String billOverdue) {
        this.partyName = partyName;
        this.amount = amount;
        this.getBillRef = getBillRef;
        this.billOverdue = billOverdue;
    }

    private String amount;
    private String getBillRef;
    private String billOverdue;

    public UniversalPojo(String partyName, String amount, String getBillRef) {
        this.partyName = partyName;
        this.amount = amount;
        this.getBillRef = getBillRef;
    }


    public UniversalPojo(String billOverdue) {
        this.billOverdue = billOverdue;
    }

    public UniversalPojo(String partyName, String amount) {
        this.partyName = partyName;
        this.amount = amount;
    }


    protected UniversalPojo(Parcel in) {
        partyName = in.readString();
        amount = in.readString();
        getBillRef = in.readString();
    }

    @Override
    public String toString() {
        return "UniversalPojo{" +
                "partyName='" + partyName + '\'' +
                ", amount='" + amount + '\'' +
                ", getBillRef='" + getBillRef + '\'' +
                ", billOverdue='" + billOverdue + '\'' +
                '}';
    }

    public static final Creator<UniversalPojo> CREATOR = new Creator<UniversalPojo>() {
        @Override
        public UniversalPojo createFromParcel(Parcel in) {
            return new UniversalPojo(in);
        }

        @Override
        public UniversalPojo[] newArray(int size) {
            return new UniversalPojo[size];
        }
    };

    public String getBillOverdue() {
        return billOverdue;
    }

    public void setBillOverdue(String billOverdue) {
        this.billOverdue = billOverdue;
    }

    public String getGetBillRef() {
        return getBillRef;
    }

    public void setGetBillRef(String getBillRef) {
        this.getBillRef = getBillRef;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(partyName);
        dest.writeString(amount);
        dest.writeString(getBillRef);
    }
}
