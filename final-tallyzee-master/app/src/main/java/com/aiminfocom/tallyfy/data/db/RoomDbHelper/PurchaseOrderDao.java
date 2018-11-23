package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface PurchaseOrderDao {

//    @Update
//    void update(PurchaseOrderVoucher purchaseOrderVoucher);

    @Insert
    void insertPurchaseOrder(ArrayList<PurchaseOrderVoucher> arrayList);

    @Query("select * from PurchaseOrderVoucher")
    Single<List<PurchaseOrderVoucher>> retrievePurchaseOrder();
    @Query("select * from PurchaseOrderVoucher where voucherPartyName =:partyname")
    Single<List<PurchaseOrderVoucher>> getVouchersByPartyName(String partyname);
    @Query("select * from PurchaseOrderVoucher where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<PurchaseOrderVoucher> getSingleVoucher(String voucherNumber,String date);


    @Query("delete from PurchaseOrderVoucher")
    void deleteAllData();
}
