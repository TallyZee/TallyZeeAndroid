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
@Entity(tableName = "SalesVoucher")
public class SalesVoucher  implements Serializable,Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "voucherDate")
    private String voucherDate;
    @ColumnInfo(name = "voucherPartyName")
    private String voucherPartyName;
    @ColumnInfo(name = "voucherAmount")
    private String voucherAmount;

    public String getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(String voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    @ColumnInfo(name = "voucherTypeName")
    private String voucherTypeName;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @ColumnInfo(name = "voucherNumber")
    private String voucherNumber;
    @ColumnInfo(name = "voucherNarration")
    private String voucherNarration;
    @ColumnInfo(name = "voucherInventories")
    @TypeConverters(DataConverter.class)
    private List<Inventories> voucherInventories;
    @ColumnInfo(name = "invoiceOrderList")
    @TypeConverters(DataConverter.class)
    private List<InvoiceOrderList> invoiceOrderList;
    @ColumnInfo(name = "ledger")
    @TypeConverters(DataConverter.class)
    private List<Ledger> ledger;

    public SalesVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<Inventories> voucherInventories, List<InvoiceOrderList> invoiceOrderList, List<Ledger> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.voucherInventories = voucherInventories;
        this.invoiceOrderList = invoiceOrderList;
        this.ledger = ledger;
    }

    public SalesVoucher()
    {

    }

    public SalesVoucher(String voucherDate,String voucherAmount, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration) {
        this.voucherDate = voucherDate;
        this.voucherAmount=voucherAmount;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected SalesVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherPartyName = in.readString();
        voucherTypeName = in.readString();
        voucherTypeParent = in.readString();
        voucherNumber = in.readString();
        voucherNarration = in.readString();
    }

    @Override
    public String toString() {
        return "SalesVoucher{" +
                "voucherDate='" + voucherDate + '\'' +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", voucherTypeName='" + voucherTypeName + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", voucherInventories=" + voucherInventories +
                ", invoiceOrderList=" + invoiceOrderList +
                ", ledger=" + ledger +
                '}';
    }

    public static final Creator<SalesVoucher> CREATOR = new Creator<SalesVoucher>() {
        @Override
        public SalesVoucher createFromParcel(Parcel in) {
            return new SalesVoucher(in);
        }

        @Override
        public SalesVoucher[] newArray(int size) {
            return new SalesVoucher[size];
        }
    };

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

    public List<Inventories> getVoucherInventories() {
        return voucherInventories;
    }

    public void setVoucherInventories(List<Inventories> voucherInventories) {
        this.voucherInventories = voucherInventories;
    }

    public List<InvoiceOrderList> getInvoiceOrderList() {
        return invoiceOrderList;
    }

    public void setInvoiceOrderList(List<InvoiceOrderList> invoiceOrderList) {
        this.invoiceOrderList = invoiceOrderList;
    }

    public List<Ledger> getLedger() {
        return ledger;
    }

    public void setLedger(List<Ledger> ledger) {
        this.ledger = ledger;
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
        dest.writeTypedList(voucherInventories);
        dest.writeTypedList(invoiceOrderList);
        dest.writeTypedList(ledger);
    }
}
