package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface ProfitNLossDao  {


    @Insert
    void insertProfitNLossData(ArrayList<ProfitAndLoss> arrayList);

    @Query("select * from ProfitNLoss")
    Single<List<ProfitAndLoss>> retrieveProfit();

    @Query("delete from ProfitNLoss")
    void deleteAllData();
}
