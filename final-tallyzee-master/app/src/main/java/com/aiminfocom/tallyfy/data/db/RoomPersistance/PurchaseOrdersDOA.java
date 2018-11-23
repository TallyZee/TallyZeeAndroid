package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.PurchaseOrder;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 19/07/2018.
 */
@Dao
public interface PurchaseOrdersDOA {
    @Query("SELECT * FROM PurchaseOrder")
    Maybe<List<PurchaseOrder>> getAllSalesOrdersList();

    @Insert
    void insertSalesOrders(PurchaseOrder purchaseOrder);

    @Delete
    void delete(PurchaseOrder purchaseOrder);

    @Update
    public void updateSalesOrders(PurchaseOrder... purchaseOrders);


}
