package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface PurchaseVoucherDao {



    @Insert
    void insertData(ArrayList<PurchaseVoucher> arrayList);


    @Query("select * from PurchaseVoucher")
    Single<List<PurchaseVoucher>> retriveData();

    @Query("select * from PurchaseVoucher where voucherPartyName =:partyname")
    Single<List<PurchaseVoucher>> getVouchersByPartyName(String partyname);

    @Query("select * from PurchaseVoucher where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<PurchaseVoucher> getSingleVoucher(String voucherNumber,String date);

    @Query("select * from PurchaseVoucher where voucherPartyName = :voucherPartyName")
    Single<List<SalesVoucher>> getPartyName(String voucherPartyName);



    @Query("delete from PurchaseVoucher")
    void deleteAllData();
}
