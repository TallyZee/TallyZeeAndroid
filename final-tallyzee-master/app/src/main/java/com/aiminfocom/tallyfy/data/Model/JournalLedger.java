package com.aiminfocom.tallyfy.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class JournalLedger implements Serializable,Parcelable {
    private String ledgerAmount;
    private String ledgerName;
    private List<JournalItem> ledgerItem;

    protected JournalLedger(Parcel in) {
        ledgerAmount = in.readString();
        ledgerName = in.readString();
        ledgerItem = in.createTypedArrayList(JournalItem.CREATOR);
    }

    public static final Creator<JournalLedger> CREATOR = new Creator<JournalLedger>() {
        @Override
        public JournalLedger createFromParcel(Parcel in) {
            return new JournalLedger(in);
        }

        @Override
        public JournalLedger[] newArray(int size) {
            return new JournalLedger[size];
        }
    };

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

    public List<JournalItem> getLedgerItem() {
        return ledgerItem;
    }

    public void setLedgerItem(List<JournalItem> ledgerItem) {
        this.ledgerItem = ledgerItem;
    }

    public JournalLedger(String ledgerAmount, String ledgerName, List<JournalItem> ledgerItem) {
        this.ledgerAmount = ledgerAmount;
        this.ledgerName = ledgerName;
        this.ledgerItem = ledgerItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ledgerAmount);
        dest.writeString(ledgerName);
        dest.writeTypedList(ledgerItem);
    }
}
