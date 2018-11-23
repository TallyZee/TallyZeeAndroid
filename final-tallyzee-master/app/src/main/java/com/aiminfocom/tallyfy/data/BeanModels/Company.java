package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GulshanPC on 29/06/2018.
 */
@Entity(tableName = "Company")
public class Company implements Parcelable {
    protected Company(Parcel in) {
        companyName = in.readString();
        companyId = in.readInt();
    }
    public Company(int companyId,String companyName)
    {
        this.companyId=companyId;
        this.companyName=companyName;
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    @SerializedName("companyName")
    @ColumnInfo(name = "companyName")
    private String companyName;
    @PrimaryKey()
    @SerializedName("companyId")
    @ColumnInfo(name = "companyId")
    private int companyId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyName);
        dest.writeInt(companyId);
    }
}
