package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.BeanModels.SalesOrder;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderLedgers;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface SalesOrderDao {
//    @Update
//    void update(SalesOrderVoucher salesOrderVoucher);

    @Insert
    void insertSalesOrder(ArrayList<SalesOrderVoucher> arrayList);

    @Query("select * from SalesOrderVoucher")
    Single<List<SalesOrderVoucher>> retrieveSalesOrderVoucher();


    @Query("select * from SalesOrderVoucher where voucherPartyName =:partyname")
    Single<List<SalesOrderVoucher>> getVouchersByPartyName(String partyname);

    @Query("select * from SalesOrderVoucher where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<SalesOrderVoucher> getSingleVoucher(String voucherNumber, String date);

    @Query("delete from SalesOrderVoucher")
    void deleteAllData();




}
