package com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.aiminfocom.tallyfy.data.BeanModels.DashType;

import java.io.Serializable;

/**
 * Created by GulshanPC on 27/06/2018.
 */

public class ItemsModel  implements Serializable{

    private int image;
    private String currency;
    private String moduleName;
    private double amount;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String color;

    public String getPecrent() {
        return pecrent;
    }

    public void setPecrent(String pecrent) {
        this.pecrent = pecrent;
    }

    private String pecrent;

    public int getPandl() {
        return pandl;
    }

    public void setPandl(int pandl) {
        this.pandl = pandl;
    }

    private int pandl;

    public DashType getDashType() {
        return dashType;
    }

    public void setDashType(DashType dashType) {
        this.dashType = dashType;
    }

    private DashType dashType;

    @Override
    public String toString() {
        return "ItemsModel{" +
                "image=" + image +
                ", currency='" + currency + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", amount=" + amount +
                ", color='" + color + '\'' +
                ", pecrent='" + pecrent + '\'' +
                ", pandl=" + pandl +
                ", dashType=" + dashType +
                '}';
    }

    public ItemsModel(int image, String currency, String moduleName, double amount, String color, String percent, int pandl, DashType dashType)
    {
        this.dashType=dashType;
        this.image=image;
        this.currency=currency;
        this.moduleName=moduleName;
        this.amount=amount;
        this.color=color;
        this.pecrent=percent;
        this.pandl=pandl;
    }




    public int  getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}
