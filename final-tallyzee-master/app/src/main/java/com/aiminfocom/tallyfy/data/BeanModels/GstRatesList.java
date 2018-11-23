package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "GstRatesList")
public class GstRatesList implements Serializable,Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "gstrat")
    private String gstrat;
    @ColumnInfo(name = "gstratedutyhead")
    private String gstratedutyhead;
    @ColumnInfo(name = "gstrateperunit")
    private String gstrateperunit;
    @ColumnInfo(name = "gstratevaluetiontype")
    private String gstratevaluationtype;

    public GstRatesList()
    {

    }

    @Override
    public String toString() {
        return "GstRatesList{" +
                "id=" + id +
                ", gstrat='" + gstrat + '\'' +
                ", gstratedutyhead='" + gstratedutyhead + '\'' +
                ", gstrateperunit='" + gstrateperunit + '\'' +
                ", gstratevaluationtype='" + gstratevaluationtype + '\'' +
                '}';
    }

    protected GstRatesList(Parcel in) {
        gstrat = in.readString();
        gstratedutyhead = in.readString();
        gstrateperunit = in.readString();
        gstratevaluationtype = in.readString();
    }

    public static final Creator<GstRatesList> CREATOR = new Creator<GstRatesList>() {
        @Override
        public GstRatesList createFromParcel(Parcel in) {
            return new GstRatesList(in);
        }

        @Override
        public GstRatesList[] newArray(int size) {
            return new GstRatesList[size];
        }
    };

    public String getGstrat() {
        return gstrat;
    }

    public void setGstrat(String gstrat) {
        this.gstrat = gstrat;
    }

    public String getGstratedutyhead() {
        return gstratedutyhead;
    }

    public void setGstratedutyhead(String gstratedutyhead) {
        this.gstratedutyhead = gstratedutyhead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGstrateperunit() {
        return gstrateperunit;
    }

    public void setGstrateperunit(String gstrateperunit) {
        this.gstrateperunit = gstrateperunit;
    }

    public String getGstratevaluationtype() {
        return gstratevaluationtype;
    }

    public void setGstratevaluationtype(String gstratevaluationtype) {
        this.gstratevaluationtype = gstratevaluationtype;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gstrat);
        dest.writeString(gstratedutyhead);
        dest.writeString(gstrateperunit);
        dest.writeString(gstratevaluationtype);
    }
}
