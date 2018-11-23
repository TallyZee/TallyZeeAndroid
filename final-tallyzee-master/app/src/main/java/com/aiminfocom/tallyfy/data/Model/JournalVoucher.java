package com.aiminfocom.tallyfy.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class JournalVoucher implements Serializable,Parcelable {
    private String voucherDate;
    private  String voucherPartyName;
    private String voucherTypeName;
    private String voucherTypeParent;
    private String voucherNumber;
    private String voucherNarration;
    private List<JournalLedger> ledger;

    protected JournalVoucher(Parcel in) {
        voucherDate = in.readString();
        voucherPartyName = in.readString();
        voucherTypeName = in.readString();
        voucherTypeParent = in.readString();
        voucherNumber = in.readString();
        voucherNarration = in.readString();
        ledger = in.createTypedArrayList(JournalLedger.CREATOR);
    }

    public static final Creator<JournalVoucher> CREATOR = new Creator<JournalVoucher>() {
        @Override
        public JournalVoucher createFromParcel(Parcel in) {
            return new JournalVoucher(in);
        }

        @Override
        public JournalVoucher[] newArray(int size) {
            return new JournalVoucher[size];
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

    public List<JournalLedger> getLedger() {
        return ledger;
    }

    public void setLedger(List<JournalLedger> ledger) {
        this.ledger = ledger;
    }

    public JournalVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<JournalLedger> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
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
        dest.writeTypedList(ledger);
    }
}
