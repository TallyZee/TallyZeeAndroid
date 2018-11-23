package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity(tableName = "CompanyList")
public class CompanyList implements Parcelable,Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "companyName")
    private String companyName;

    public CompanyList() {
    }

    @Ignore
    public CompanyList(String companyName) {
        this.companyName = companyName;
    }

    protected CompanyList(Parcel in) {
        id = in.readInt();
        companyName = in.readString();
    }

    @Override
    public String toString() {
        return "CompanyList{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public static final Creator<CompanyList> CREATOR = new Creator<CompanyList>() {
        @Override
        public CompanyList createFromParcel(Parcel in) {
            return new CompanyList(in);
        }

        @Override
        public CompanyList[] newArray(int size) {
            return new CompanyList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(companyName);
    }
}
