package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "Cost Center")
public class CostCenter implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "costname")
    private String costname;
    @ColumnInfo(name = "costamount")
    private String costamount;
    @ColumnInfo(name = "costbillQty")
    private String costbillQty;
    @ColumnInfo(name = "costactualQty")
    private String costactualQty;

    protected CostCenter(Parcel in) {
        costname = in.readString();
        costamount = in.readString();
        costbillQty = in.readString();
        costactualQty = in.readString();
    }


    public CostCenter()
    {

    }
    public static final Creator<CostCenter> CREATOR = new Creator<CostCenter>() {
        @Override
        public CostCenter createFromParcel(Parcel in) {
            return new CostCenter(in);
        }

        @Override
        public CostCenter[] newArray(int size) {
            return new CostCenter[size];
        }
    };

    @Override
    public String toString() {
        return "CostCenter{" +
                "id=" + id +
                ", costname='" + costname + '\'' +
                ", costamount='" + costamount + '\'' +
                ", costbillQty='" + costbillQty + '\'' +
                ", costactualQty='" + costactualQty + '\'' +
                '}';
    }

    public String getCostname() {
        return costname;
    }

    public void setCostname(String costname) {
        this.costname = costname;
    }

    public String getCostamount() {
        return costamount;
    }

    public void setCostamount(String costamount) {
        this.costamount = costamount;
    }

    public String getCostbillQty() {
        return costbillQty;
    }

    public void setCostbillQty(String costbillQty) {
        this.costbillQty = costbillQty;
    }

    public String getCostactualQty() {
        return costactualQty;
    }

    public void setCostactualQty(String costactualQty) {
        this.costactualQty = costactualQty;
    }

    public CostCenter(String costname, String costamount, String costbillQty, String costactualQty) {
        this.costname = costname;
        this.costamount = costamount;
        this.costbillQty = costbillQty;
        this.costactualQty = costactualQty;
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
        dest.writeString(costname);
        dest.writeString(costamount);
        dest.writeString(costbillQty);
        dest.writeString(costactualQty);
    }
}
