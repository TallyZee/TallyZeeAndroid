package com.aiminfocom.tallyfy.data.BeanModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GulshanPC on 30/06/2018.
 */

public class Voucher implements Parcelable {
    String PartyName;
    String refNum;
    String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    String itemName;


    public Voucher(String partyName,String dashType,String refNum,String amount,String sgst,String cgst,String productId,String quantity,String itemName)
    {
        PartyName=partyName;
        this.refNum=refNum;
        this.amount=amount;
        this.sgst=sgst;
        this.cgst=cgst;
        DashType=dashType;
        this.productId=productId;
        this.itemName=itemName;
        this.quantity=quantity;
    }
    public String getPartyName() {
        return PartyName;
    }

    public void setPartyName(String partyName) {
        PartyName = partyName;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getDashType() {
        return DashType;
    }

    public void setDashType(String dashType) {
        DashType = dashType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    String DashType;
    String amount;
    String cgst;
    String sgst;
    String productId;

    protected Voucher(Parcel in) {
        productId=in.readString();
        PartyName=in.readString();
        DashType=in.readString();
        cgst=in.readString();
        sgst=in.readString();
        productId=in.readString();
        quantity=in.readString();
        itemName=in.readString();
    }

    public static final Creator<Voucher> CREATOR = new Creator<Voucher>() {
        @Override
        public Voucher createFromParcel(Parcel in) {
            return new Voucher(in);
        }

        @Override
        public Voucher[] newArray(int size) {
            return new Voucher[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PartyName);
        dest.writeString(refNum);
        dest.writeString(DashType);
        dest.writeString(amount);
        dest.writeString(cgst);
        dest.writeString(sgst);
        dest.writeString(productId);
        dest.writeString(quantity);
        dest.writeString(itemName);
    }
}
