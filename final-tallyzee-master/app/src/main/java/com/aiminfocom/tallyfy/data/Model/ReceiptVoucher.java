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
@Entity(tableName = "ReceiptVoucher")
public class ReceiptVoucher implements Parcelable,Serializable {
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
    @ColumnInfo(name = "vocuherTypeName")
    private String vocuherTypeName;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @ColumnInfo(name = "ledgers")
    @TypeConverters(DataConverter.class)
    private List<Ledger> ledger;

    public ReceiptVoucher()
    {

    }

    protected ReceiptVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherNarration = in.readString();
        voucherNumber = in.readString();
        voucherPartyName = in.readString();
        vocuherTypeName = in.readString();
        voucherTypeParent = in.readString();
        ledger = in.createTypedArrayList(Ledger.CREATOR);
    }

    public static final Creator<ReceiptVoucher> CREATOR = new Creator<ReceiptVoucher>() {
        @Override
        public ReceiptVoucher createFromParcel(Parcel in) {
            return new ReceiptVoucher(in);
        }

        @Override
        public ReceiptVoucher[] newArray(int size) {
            return new ReceiptVoucher[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReceiptVoucher{" +
                "voucherDate='" + voucherDate + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", vocuherTypeName='" + vocuherTypeName + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", ledgers=" + ledger +
                '}';
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

    public String getVocuherTypeName() {
        return vocuherTypeName;
    }

    public void setVocuherTypeName(String vocuherTypeName) {
        this.vocuherTypeName = vocuherTypeName;
    }

    public String getVoucherTypeParent() {
        return voucherTypeParent;
    }

    public void setVoucherTypeParent(String voucherTypeParent) {
        this.voucherTypeParent = voucherTypeParent;
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
        dest.writeString(voucherNarration);
        dest.writeString(voucherNumber);
        dest.writeString(voucherPartyName);
        dest.writeString(vocuherTypeName);
        dest.writeString(voucherTypeParent);
        dest.writeTypedList(ledger);
    }
}
