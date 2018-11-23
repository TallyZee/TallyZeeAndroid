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
@Entity(tableName = "DebitNoteVoucher")
public class DebitNoteVoucher implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "voucherDate")
   private String voucherDate;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @ColumnInfo(name = "voucherNumber")
    private String voucherNumber;
    @ColumnInfo(name = "voucherNarration")
    private String voucherNarration;
    @ColumnInfo(name = "ledger")
    @TypeConverters(DataConverter.class)
    private List<CreditNoteLedger> ledger;
    @ColumnInfo(name = "allInventerios")
    @TypeConverters(DataConverter.class)
    private List<AllInventoriesEntry> allInventerios;

    protected DebitNoteVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherTypeParent = in.readString();
        voucherNumber = in.readString();
        voucherNarration = in.readString();
        ledger = in.createTypedArrayList(CreditNoteLedger.CREATOR);
        allInventerios = in.createTypedArrayList(AllInventoriesEntry.CREATOR);
        voucherPartyName = in.readString();
        voucherTypeName = in.readString();
    }

    public DebitNoteVoucher() {
    }

    public static final Creator<DebitNoteVoucher> CREATOR = new Creator<DebitNoteVoucher>() {
        @Override
        public DebitNoteVoucher createFromParcel(Parcel in) {
            return new DebitNoteVoucher(in);
        }

        @Override
        public DebitNoteVoucher[] newArray(int size) {
            return new DebitNoteVoucher[size];
        }
    };

    @Override
    public String toString() {
        return "DebitNoteVoucher{" +
                "id=" + id +
                ", voucherDate='" + voucherDate + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", ledger=" + ledger +
                ", allInventerios=" + allInventerios +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", voucherTypeName='" + voucherTypeName + '\'' +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<CreditNoteLedger> getLedger() {
        return ledger;
    }

    public void setLedger(List<CreditNoteLedger> ledger) {
        this.ledger = ledger;
    }

    public List<AllInventoriesEntry> getAllInventerios() {
        return allInventerios;
    }

    public void setAllInventerios(List<AllInventoriesEntry> allInventerios) {
        this.allInventerios = allInventerios;
    }

    String voucherPartyName;
    String voucherTypeName;

    public DebitNoteVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<CreditNoteLedger> ledger, List<AllInventoriesEntry> allInventerios) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.ledger = ledger;
        this.allInventerios = allInventerios;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(voucherDate);
        dest.writeString(voucherTypeParent);
        dest.writeString(voucherNumber);
        dest.writeString(voucherNarration);
        dest.writeTypedList(ledger);
        dest.writeTypedList(allInventerios);
        dest.writeString(voucherPartyName);
        dest.writeString(voucherTypeName);
    }
}
