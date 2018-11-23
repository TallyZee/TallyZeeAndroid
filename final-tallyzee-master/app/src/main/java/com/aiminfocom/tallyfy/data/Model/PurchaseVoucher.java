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
@Entity(tableName = "PurchaseVoucher")
public class PurchaseVoucher implements Serializable,Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "voucherDate")
    private String voucherDate;
    @ColumnInfo(name = "voucherNarration")
    private String voucherNarration;
    @ColumnInfo(name = "voucherNumber")
    private String voucherNumber;
    @ColumnInfo(name = "voucherPartyName")
    private String voucherPartyName;
    @ColumnInfo(name = "voucherTypeName")
    private String voucherTypeName;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "voucherInventories")
    private List<VoucherInventories> voucherInventories;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "Ledger")
    private List<Ledger> ledger;





    public PurchaseVoucher()
    {

    }
    protected PurchaseVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherNarration = in.readString();
        voucherNumber = in.readString();
        voucherPartyName = in.readString();
        voucherTypeName = in.readString();
        voucherTypeParent = in.readString();
        ledger = in.createTypedArrayList(Ledger.CREATOR);
    }

    @Override
    public String toString() {
        return "PurchaseVoucher{" +
                "id=" + id +
                ", voucherDate='" + voucherDate + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", voucherTypeName='" + voucherTypeName + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", voucherInventories=" + voucherInventories +
                ", ledger=" + ledger +
                '}';
    }

    public PurchaseVoucher(int id, String voucherDate, String voucherNarration, String voucherNumber, String voucherPartyName, String voucherTypeName, String voucherTypeParent, List<Ledger> ledger) {
        this.id = id;
        this.voucherDate = voucherDate;
        this.voucherNarration = voucherNarration;
        this.voucherNumber = voucherNumber;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.ledger = ledger;
    }


    public static final Creator<PurchaseVoucher> CREATOR = new Creator<PurchaseVoucher>() {
        @Override
        public PurchaseVoucher createFromParcel(Parcel in) {
            return new PurchaseVoucher(in);
        }

        @Override
        public PurchaseVoucher[] newArray(int size) {
            return new PurchaseVoucher[size];
        }
    };

    public static Creator<PurchaseVoucher> getCREATOR() {
        return CREATOR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherNarration() {
        return voucherNarration;
    }

    public void setVoucherNarration(String voucherNarration) {
        this.voucherNarration = voucherNarration;
    }

    public List<VoucherInventories> getVoucherInventories() {
        return voucherInventories;
    }

    public void setVoucherInventories(List<VoucherInventories> voucherInventories) {
        this.voucherInventories = voucherInventories;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherPartyName() {
        return voucherPartyName;
    }

    public void setVoucherPartyName(String voucherPartyName) {
        this.voucherPartyName = voucherPartyName;
    }

    public List<Ledger> getLedger() {
        return ledger;
    }

    public void setLedger(List<Ledger> ledger) {
        this.ledger = ledger;
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(voucherDate);
        dest.writeString(voucherNarration);
        dest.writeString(voucherNumber);
        dest.writeString(voucherPartyName);
        dest.writeString(voucherTypeName);
        dest.writeString(voucherTypeParent);
        dest.writeTypedList(voucherInventories);
        dest.writeTypedList(ledger);
    }
}
