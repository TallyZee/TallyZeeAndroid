package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.Model.BillsReceables;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface DaoHelper {


    @Insert
     void insertData(ArrayList<BillsReceables> arrayList);

    @Query("select * from BillsReceivable")
    Single<List<BillsReceables>> retriveData();

    @Query("select * from BillsReceivable where billParty = :voucherParty")
    Single<List<BillsReceables>> billsReceivableQuery(String voucherParty);
}
