package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.Model.BillsPayable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface BillsPayableDao {

//    @Update
//    void update(BillsPayable billsPayable);
    @Insert
    void insertData(ArrayList<BillsPayable> arrayList);

    @Query("select * from BillsPayable")
    Single<List<BillsPayable>> retrieveData();


    @Query("select * from BillsPayable where billParty  = :billsParty")
    Single<List<BillsPayable>> getBillsPayableParty(String billsParty);

    @Query("delete from BillsPayable")
    void deleteAllData();




}
