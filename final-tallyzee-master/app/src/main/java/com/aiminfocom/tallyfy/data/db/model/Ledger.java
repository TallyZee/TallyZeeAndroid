package com.aiminfocom.tallyfy.data.db.model;

import android.content.ClipData;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Ledger implements Parcelable {
    String amount;
    String ledgerNaqme;
    List<Item> items;


    protected Ledger(Parcel in) {
        amount = in.readString();
        ledgerNaqme = in.readString();
        items = in.createTypedArrayList(Item.CREATOR);
    }

    public static final Creator<Ledger> CREATOR = new Creator<Ledger>() {
        @Override
        public Ledger createFromParcel(Parcel in) {
            return new Ledger(in);
        }

        @Override
        public Ledger[] newArray(int size) {
            return new Ledger[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(amount);
        parcel.writeString(ledgerNaqme);
        parcel.writeTypedList(items);
    }
}
