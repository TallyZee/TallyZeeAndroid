package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "Payment Ledger")
public class PaymentLedger implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "amount")
   private String amount;
    @ColumnInfo(name = "ledgerName")
    private String ledgerName;

    public List<PaymentBill> getPaymentBillList() {
        return paymentBillList;
    }

    public void setPaymentBillList(List<PaymentBill> paymentBillList) {
        this.paymentBillList = paymentBillList;
    }

    public List<PaymentBank> getPaymentBankList() {
        return paymentBankList;
    }

    public void setPaymentBankList(List<PaymentBank> paymentBankList) {
        this.paymentBankList = paymentBankList;
    }

    public List<CostCenter> getCostCenterList() {
        return costCenterList;
    }

    public void setCostCenterList(List<CostCenter> costCenterList) {
        this.costCenterList = costCenterList;
    }

    @ColumnInfo(name = "paymentBillList")
    @TypeConverters(DataConverter.class)
    private  List<PaymentBill> paymentBillList;
    @ColumnInfo(name = "paymentBankList")
    @TypeConverters(DataConverter.class)
    private  List<PaymentBank> paymentBankList;
    @ColumnInfo(name = "costCenterList")
    @TypeConverters(DataConverter.class)
    private  List<CostCenter> costCenterList;

    protected PaymentLedger(Parcel in) {
        amount = in.readString();
        ledgerName = in.readString();
        paymentBillList = in.createTypedArrayList(PaymentBill.CREATOR);
        paymentBankList = in.createTypedArrayList(PaymentBank.CREATOR);
        costCenterList = in.createTypedArrayList(CostCenter.CREATOR);
    }

    public PaymentLedger()
    {

    }
    public static final Creator<PaymentLedger> CREATOR = new Creator<PaymentLedger>() {
        @Override
        public PaymentLedger createFromParcel(Parcel in) {
            return new PaymentLedger(in);
        }

        @Override
        public PaymentLedger[] newArray(int size) {
            return new PaymentLedger[size];
        }
    };

    @Override
    public String toString() {
        return "PaymentLedger{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", paymentBillList=" + paymentBillList +
                ", paymentBankList=" + paymentBankList +
                ", costCenterList=" + costCenterList +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PaymentLedger(String amount, String ledgerName, List<PaymentBill> paymentBillList, List<PaymentBank> paymentBankList, List<CostCenter> costCenterList) {
        this.amount = amount;
        this.ledgerName = ledgerName;
        this.paymentBillList = paymentBillList;
        this.paymentBankList = paymentBankList;
        this.costCenterList = costCenterList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(amount);
        dest.writeString(ledgerName);
        dest.writeTypedList(paymentBillList);
        dest.writeTypedList(paymentBankList);
        dest.writeTypedList(costCenterList);
    }
}
