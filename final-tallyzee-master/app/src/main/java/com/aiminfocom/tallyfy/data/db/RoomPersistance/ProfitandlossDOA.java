package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 17/07/2018.
 */
@Dao
public interface ProfitandlossDOA {


    @Query("SELECT * FROM profitnloss")
    Maybe<List<profitnloss>> getAll();



//    @Query("SELECT * FROM BillsReceivable where BILLREF = :id")
//    Maybe<profitnloss> findById(int id);


    @Insert
    void insertAll(profitnloss... users);

    @Delete
    void delete(profitnloss user);

    @Update
    public void updateUsers(profitnloss... users);
}
