package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface DebitNoteDao {


    @Insert
    void insertDebitNoteData(ArrayList<DebitNoteVoucher> arrayList);

    @Query("select * from DebitNoteVoucher")
    Single<List<DebitNoteVoucher>> retrieveDebitNoteData();

    @Query("select * from 'DebitNoteVoucher' where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<DebitNoteVoucher> getSingleVoucher(String voucherNumber,String date);



    @Query("select * from DebitNoteVoucher where voucherPartyName =:partyname")
    Single<List<DebitNoteVoucher>> getVouchersByPartyName(String partyname);

    @Query("select * from debitnotevoucher WHERE voucherPartyName =:partyName and voucherDate BETWEEN :from AND :to")
    Single<List<DebitNoteVoucher>> fetchDebitNoteVoucher(String partyName, String from, String to);

    @Query("delete from DebitNoteVoucher")
    void deleteAllData();
}
