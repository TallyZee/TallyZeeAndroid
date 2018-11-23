//package com.aiminfocom.tallyfy.data.Model;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.io.Serializable;
//
//public class ItemsModel implements Parcelable,Serializable{
//
//
//    private String itemName;
//    private String itemRate;
//    private String avgPerRate;
//    private String standardCost;
//    private String standardPrice;
//    private String reorder;
//    private String minOrder;
//    private String quantityNo;
//
//
//    public ItemsModel(Parcel in) {
//        itemName = in.readString();
//        itemRate = in.readString();
//        avgPerRate = in.readString();
//        standardCost = in.readString();
//        standardPrice = in.readString();
//        reorder = in.readString();
//        minOrder = in.readString();
//    }
//
//    public ItemsModel(String itemName, String itemRate, String avgPerRate, String standardCost, String standardPrice, String reorder, String minOrder, String quantityNo) {
//        this.itemName = itemName;
//        this.itemRate = itemRate;
//        this.avgPerRate = avgPerRate;
//        this.standardCost = standardCost;
//        this.standardPrice = standardPrice;
//        this.reorder = reorder;
//        this.minOrder = minOrder;
//        this.quantityNo = quantityNo;
//    }
//
//    public String getQuantityNo() {
//        return quantityNo;
//    }
//
//    public void setQuantityNo(String quantityNo) {
//        this.quantityNo = quantityNo;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public String getItemRate() {
//        return itemRate;
//    }
//
//    public void setItemRate(String itemRate) {
//        this.itemRate = itemRate;
//    }
//
//    public String getAvgPerRate() {
//        return avgPerRate;
//    }
//
//    public void setAvgPerRate(String avgPerRate) {
//        this.avgPerRate = avgPerRate;
//    }
//
//    public String getStandardCost() {
//        return standardCost;
//    }
//
//    public void setStandardCost(String standardCost) {
//        this.standardCost = standardCost;
//    }
//
//    public String getStandardPrice() {
//        return standardPrice;
//    }
//
//    public void setStandardPrice(String standardPrice) {
//        this.standardPrice = standardPrice;
//    }
//
//    public String getReorder() {
//        return reorder;
//    }
//
//    public void setReorder(String reorder) {
//        this.reorder = reorder;
//    }
//
//    public String getMinOrder() {
//        return minOrder;
//    }
//
//    public void setMinOrder(String minOrder) {
//        this.minOrder = minOrder;
//    }
//
//    public static final Creator<ItemsModel> CREATOR = new Creator<ItemsModel>() {
//        @Override
//        public ItemsModel createFromParcel(Parcel in) {
//            return new ItemsModel(in);
//        }
//
//        @Override
//        public ItemsModel[] newArray(int size) {
//            return new ItemsModel[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(itemName);
//        dest.writeString(itemRate);
//        dest.writeString(avgPerRate);
//        dest.writeString(standardCost);
//        dest.writeString(standardPrice);
//        dest.writeString(reorder);
//        dest.writeString(minOrder);
//    }
//}
