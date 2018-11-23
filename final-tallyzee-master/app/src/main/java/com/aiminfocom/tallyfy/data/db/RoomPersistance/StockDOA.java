package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 18/07/2018.
 */
@Dao
public interface StockDOA {

    @Query("SELECT * FROM Stock")
    Maybe<List<Stock>> getAllStockList();

    @Insert
    void insertStock(Stock stock);

    @Delete
    void delete(Stock stock);

    @Update
    public void updateStock(Stock... stocks);
}
