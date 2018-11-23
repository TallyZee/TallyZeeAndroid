package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.Model.Ledger;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.Model.VouchrerDataUniv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface SalesVoucherDao {

//    @Update
//    void update(SalesVoucher salesVoucher);
    @Insert
    void insertSalesVoucherData(ArrayList<SalesVoucher> arrayList);


    @Query("select * from SalesVoucher where voucherPartyName =:partyname")
    Single<List<SalesVoucher>> getVouchersByPartyName(String partyname);

    @Query("select * from SalesVoucher")
    Single<List<SalesVoucher>> retrieveData();

    @Query("select * from SalesVoucher WHERE voucherPartyName =:partyName and voucherDate BETWEEN :from AND :to")
    Single<List<SalesVoucher>> fetchUserBetweenDate(String partyName,String from, String to);


    @Query("select * from SalesVoucher where voucherPartyName =:voucherPartyName and voucherDate =:date")
    Maybe<SalesVoucher> getSingleVoucher(String voucherPartyName, String date);

    @Query("select * from SalesVoucher where voucherPartyName = :voucherPartyName")
    Single<List<SalesVoucher>> getPartyName(String voucherPartyName);


    @Query("delete from SalesVoucher")
    void deleteAllData();



}
