package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.Company;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 20/07/2018.
 */
@Dao
public interface CompanyDOA {
    @Query("SELECT * FROM Company")
    Maybe<List<Company>> getAll();



//    @Query("SELECT * FROM BillsReceivable where BILLREF = :id")
//    Maybe<profitnloss> findById(int id);


    @Insert
    void insertAll(Company... users);

    @Delete
    void delete(Company user);

    @Update
    public void updateUsers(Company... users);

}
