package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface PaymentVoucherDao {

//    @Update
//    void update(PaymentVoucher paymentVoucher);

    @Insert
    void insertPaymentData(ArrayList<PaymentVoucher> arrayList);

    @Query("select * from `Payment Voucher`")
    Single<List<PaymentVoucher>> retrievePaymentData();

    @Query("select * from 'Payment Voucher' where voucherPartyName =:partyname")
    Single<List<PaymentVoucher>> getVouchersByPartyName(String partyname);


    @Query("select * from 'Payment Voucher' where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<PaymentVoucher> getSingleVoucher(String voucherNumber,String date);

    @Query("delete from 'Payment Voucher'")
    void deleteAllData();



    @Query("select * from 'Payment Voucher' WHERE voucherPartyName =:partyName and voucherDate BETWEEN :from AND :to")
    Single<List<PaymentVoucher>> fetchPaymentDate(String partyName, String from, String to);

}
