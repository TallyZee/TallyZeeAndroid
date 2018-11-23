package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface ReceiptNoteDao {
//    @Update
//    void update(ReceiptVoucher receiptVoucher);

    @Insert
    void insertReceiptNote(ArrayList<ReceiptVoucher> arrayList);

    @Query("select * from ReceiptVoucher")
    Single<List<ReceiptVoucher>> retrieveReceiptNote();

    @Query("select * from ReceiptVoucher where voucherPartyName =:partyname")
    Single<List<ReceiptVoucher>> getVouchersByPartyName(String partyname);

    @Query("select * from ReceiptVoucher where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<ReceiptVoucher> getSingleVoucher(String voucherNumber,String date);

    @Query("delete from ReceiptVoucher")
    void deleteAllData();


    @Query("select * from ReceiptVoucher WHERE voucherPartyName =:partyName and voucherDate BETWEEN :from AND :to")
    Single<List<ReceiptVoucher>> fetchReceiptData(String partyName, String from, String to);
}
