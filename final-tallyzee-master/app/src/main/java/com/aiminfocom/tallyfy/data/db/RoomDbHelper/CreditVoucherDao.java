package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface CreditVoucherDao {

    @Insert
    void insertCreditData(ArrayList<CreditNoteVoucher> list);


    @Query("select * from CreditNoteVoucher")
    Single<List<CreditNoteVoucher>> retrieveCreditData();

    @Query("select * from CreditNoteVoucher where voucherPartyName =:partyname")
    Single<List<CreditNoteVoucher>> getVouchersByPartyName(String partyname);

    @Query("select * from CreditNoteVoucher where voucherPartyName =:voucherNumber and voucherDate =:date")
    Single<CreditNoteVoucher> getSingleVoucher(String voucherNumber,String date);

//    @Update
//    void update(CreditNoteVoucher creditNoteVoucher);


    @Query("delete from CreditNoteVoucher")
    void deleteAllData();
}
