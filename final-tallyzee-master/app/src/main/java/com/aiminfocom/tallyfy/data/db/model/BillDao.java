package com.aiminfocom.tallyfy.data.db.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.aiminfocom.tallyfy.data.Model.BillsReceables;

import java.util.ArrayList;

@Dao
public interface BillDao {




    @Insert
    void insertData(ArrayList<BillsReceables> arrayList);
}
