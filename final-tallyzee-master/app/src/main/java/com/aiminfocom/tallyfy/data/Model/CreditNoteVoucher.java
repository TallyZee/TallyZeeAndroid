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
@Entity(tableName = "CreditNoteVoucher")
public class CreditNoteVoucher implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "voucherDate")
    private String voucherDate;
    @ColumnInfo(name = "voucherPartyName")
    private String voucherPartyName;
    @ColumnInfo(name = "voucherTypeName")
    private String voucherTypeName;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @ColumnInfo(name = "voucherNumber")
    private String voucherNumber;
    @ColumnInfo(name = "voucherNarration")
    private String voucherNarration;
    @ColumnInfo(name = "ledger")
    @TypeConverters(DataConverter.class)
    private List<CreditNoteLedger> ledger;

    public CreditNoteVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<CreditNoteLedger> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.ledger = ledger;
    }


    public CreditNoteVoucher()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreditNoteVoucher{" +
                "id=" + id +
                ", voucherDate='" + voucherDate + '\'' +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", voucherTypeName='" + voucherTypeName + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", ledger=" + ledger +
                '}';
    }

    protected CreditNoteVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherPartyName = in.readString();
        voucherTypeName = in.readString();
        voucherTypeParent = in.readString();
        voucherNumber = in.readString();
        voucherNarration = in.readString();
        ledger = in.createTypedArrayList(CreditNoteLedger.CREATOR);
    }

    public static final Creator<CreditNoteVoucher> CREATOR = new Creator<CreditNoteVoucher>() {
        @Override
        public CreditNoteVoucher createFromParcel(Parcel in) {
            return new CreditNoteVoucher(in);
        }

        @Override
        public CreditNoteVoucher[] newArray(int size) {
            return new CreditNoteVoucher[size];
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

    public List<CreditNoteLedger> getLedger() {
        return ledger;
    }

    public void setLedger(List<CreditNoteLedger> ledger) {
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
        dest.writeTypedList(ledger);
    }
}
