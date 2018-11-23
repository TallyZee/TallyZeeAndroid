package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 18/07/2018.
 */
@Dao
public interface SalesOrdersDOA {
    @Query("SELECT * FROM SalesOrders")
    Maybe<List<SalesOrders>> getAllSalesOrdersList();

    @Insert
    void insertSalesOrders(SalesOrders salesOrders);

    @Delete
    void delete(SalesOrders salesOrders);

    @Update
    public void updateSalesOrders(SalesOrders... salesOrders);
}
