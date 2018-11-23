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
@Entity(tableName = "PurchaseOrderVoucher")
public class PurchaseOrderVoucher implements Serializable,Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "voucherDate")
   private String voucherDate;
    @ColumnInfo(name = "voucherPartyName")
    private String voucherPartyName;
    @ColumnInfo(name = "voucherTypeName")
    private  String voucherTypeName;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @ColumnInfo(name = "voucherNumber")
    private String voucherNumber;
    @ColumnInfo(name = "voucherNarration")
    private String voucherNarration;
    @ColumnInfo(name = "purchaseOrderInventoriesLists")
    @TypeConverters(DataConverter.class)
    private  List<PurchaseOrderInventoriesList> purchaseOrderInventoriesLists;
    @ColumnInfo(name = "ledger")
    @TypeConverters(DataConverter.class)
   private List<PurchaseOrderLedger> ledger;

    protected PurchaseOrderVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherPartyName = in.readString();
        voucherTypeName = in.readString();
        voucherTypeParent = in.readString();
        voucherNumber = in.readString();
        voucherNarration = in.readString();
    }


    public PurchaseOrderVoucher()
    {

    }
    public static final Creator<PurchaseOrderVoucher> CREATOR = new Creator<PurchaseOrderVoucher>() {
        @Override
        public PurchaseOrderVoucher createFromParcel(Parcel in) {
            return new PurchaseOrderVoucher(in);
        }

        @Override
        public PurchaseOrderVoucher[] newArray(int size) {
            return new PurchaseOrderVoucher[size];
        }
    };

    public List<PurchaseOrderInventoriesList> getPurchaseOrderInventoriesLists() {
        return purchaseOrderInventoriesLists;
    }

    public void setPurchaseOrderInventoriesLists(List<PurchaseOrderInventoriesList> purchaseOrderInventoriesLists) {
        this.purchaseOrderInventoriesLists = purchaseOrderInventoriesLists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PurchaseOrderVoucher{" +
                "voucherDate='" + voucherDate + '\'' +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", voucherTypeName='" + voucherTypeName + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", purchaseOrderInventoriesLists=" + purchaseOrderInventoriesLists +
                ", ledger=" + ledger +
                '}';
    }

    public List<PurchaseOrderLedger> getLedger() {
        return ledger;
    }

    public void setLedger(List<PurchaseOrderLedger> ledger) {
        this.ledger = ledger;
    }

    public PurchaseOrderVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<PurchaseOrderInventoriesList> purchaseOrderInventoriesLists, List<PurchaseOrderLedger> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.purchaseOrderInventoriesLists = purchaseOrderInventoriesLists;
        this.ledger = ledger;
    }


    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherPartyName() {
        return voucherPartyName;
    }

    public void setVoucherPartyName(String voucherPartyName) {
        this.voucherPartyName = voucherPartyName;
    }

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

    public String getVoucherTypeParent() {
        return voucherTypeParent;
    }

    public void setVoucherTypeParent(String voucherTypeParent) {
        this.voucherTypeParent = voucherTypeParent;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherNarration() {
        return voucherNarration;
    }

    public void setVoucherNarration(String voucherNarration) {
        this.voucherNarration = voucherNarration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(voucherDate);
        dest.writeString(voucherPartyName);
        dest.writeString(voucherTypeName);
        dest.writeString(voucherTypeParent);
        dest.writeString(voucherNumber);
        dest.writeString(voucherNarration);
    }
}
