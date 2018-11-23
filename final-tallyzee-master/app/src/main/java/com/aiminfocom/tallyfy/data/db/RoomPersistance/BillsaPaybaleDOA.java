package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 19/07/2018.
 */

@Dao
public interface BillsaPaybaleDOA {
    @Query("SELECT * FROM BillsPaybale")
    Maybe<List<BillsPaybale>> getAll();



//    @Query("SELECT * FROM BillsReceivable where BILLREF = :id")
//    Maybe<profitnloss> findById(int id);


    @Insert
    void insertAll(BillsPaybale... billsPaybales);

    @Delete
    void delete(BillsPaybale billsPaybale);

    @Update
    public void updateUsers(BillsPaybale... billsPaybales);
}
