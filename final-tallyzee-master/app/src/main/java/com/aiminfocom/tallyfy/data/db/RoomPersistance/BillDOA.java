package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by alahammad on 10/2/17.
 */

@Dao
public interface BillDOA {
    @Query("SELECT * FROM BillsPaybale")
    Maybe<List<BillsPaybale>> getAll();

    @Query("SELECT * FROM BillsPaybale WHERE BILLREF IN (:userIds)")
    Flowable<List<BillsPaybale>> loadAllByIds(int[] userIds);


    @Query("SELECT * FROM BillsPaybale where BILLREF = :id")
    Maybe<BillsPaybale> findById(int id);


    @Insert
    void insertAll(BillsPaybale... users);

    @Delete
    void delete(BillsPaybale user);

    @Update
    public void updateUsers(BillsPaybale... users);
}
