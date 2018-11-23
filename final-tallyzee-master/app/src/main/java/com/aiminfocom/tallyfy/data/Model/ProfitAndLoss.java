package com.aiminfocom.tallyfy.data.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "ProfitNLoss")
public class ProfitAndLoss implements Serializable,Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ledgerAmount")
    private String ledgerAmount;
    @ColumnInfo(name = "ledgerName")
    private String ledgerName;
    @ColumnInfo(name = "ledgerNameGroup")
    private String ledgerNameGroup;
    @ColumnInfo(name = "ledgerNameParent")
    private String ledgerNameParent;
    @Ignore
    public ProfitAndLoss(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public ProfitAndLoss()
    {

    }




    protected ProfitAndLoss(Parcel in) {
        ledgerAmount = in.readString();
        ledgerName = in.readString();
        ledgerNameGroup = in.readString();
        ledgerNameParent = in.readString();
    }

    public static final Creator<ProfitAndLoss> CREATOR = new Creator<ProfitAndLoss>() {
        @Override
        public ProfitAndLoss createFromParcel(Parcel in) {
            return new ProfitAndLoss(in);
        }

        @Override
        public ProfitAndLoss[] newArray(int size) {
            return new ProfitAndLoss[size];
        }
    };

    @Override
    public String toString() {
        return "ProfitAndLoss{" +
                "ledgerAmount='" + ledgerAmount + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", ledgerNameGroup='" + ledgerNameGroup + '\'' +
                ", ledgerNameParent='" + ledgerNameParent + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLedgerAmount() {
        return ledgerAmount;
    }

    public void setLedgerAmount(String ledgerAmount) {
        this.ledgerAmount = ledgerAmount;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getLedgerNameGroup() {
        return ledgerNameGroup;
    }

    public void setLedgerNameGroup(String ledgerNameGroup) {
        this.ledgerNameGroup = ledgerNameGroup;
    }

    public String getLedgerNameParent() {
        return ledgerNameParent;
    }

    public void setLedgerNameParent(String ledgerNameParent) {
        this.ledgerNameParent = ledgerNameParent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ledgerAmount);
        dest.writeString(ledgerName);
        dest.writeString(ledgerNameGroup);
        dest.writeString(ledgerNameParent);
    }
}

