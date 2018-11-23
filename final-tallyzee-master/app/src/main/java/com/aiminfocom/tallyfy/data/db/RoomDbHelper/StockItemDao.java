package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.StockItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface StockItemDao {

    @Insert
    void insertStockItem(ArrayList<StockItem> arrayList);


    @Query("select * from StockItem")
    Single<List<StockItem>> retrieveStockList();



    @Query("select * from StockItem where type=:type")
    Single<List<StockItem>> itemQuery(String type);

    @Query("select * from StockItem where type=:type")
    Single<List<StockItem>> groupQuery(String type);

    @Query("select * from StockItem where type=:type")
    Single<List<StockItem>> categoryQuery(String type);


}
