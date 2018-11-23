package com.aiminfocom.tallyfy.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class SalesVoucers implements Parcelable {
    String voucherDate;
    String voucherNumber;
    String voucherPartyName;
    List<Ledger> ledger;



    protected SalesVoucers(Parcel in) {
        voucherDate = in.readString();
        voucherNumber = in.readString();
        voucherPartyName = in.readString();
        ledger = in.createTypedArrayList(Ledger.CREATOR);
    }

    public static final Creator<SalesVoucers> CREATOR = new Creator<SalesVoucers>() {
        @Override
        public SalesVoucers createFromParcel(Parcel in) {
            return new SalesVoucers(in);
        }

        @Override
        public SalesVoucers[] newArray(int size) {
            return new SalesVoucers[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(voucherDate);
        parcel.writeString(voucherNumber);
        parcel.writeString(voucherPartyName);
        parcel.writeTypedList(ledger);
    }
}
