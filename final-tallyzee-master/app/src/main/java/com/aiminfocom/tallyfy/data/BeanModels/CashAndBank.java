package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GulshanPC on 19/07/2018.
 */
@Entity(tableName = "CashAndBank")
public class CashAndBank {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    @ColumnInfo(name = "Id")
    int id;

    @SerializedName("accName")
    @ColumnInfo(name = "accName")
    public String accName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccinfo() {
        return accinfo;
    }

    public void setAccinfo(String accinfo) {
        this.accinfo = accinfo;
    }

    @SerializedName("accinfo")
    @ColumnInfo(name = "accinfo")
    public String accinfo;

}
