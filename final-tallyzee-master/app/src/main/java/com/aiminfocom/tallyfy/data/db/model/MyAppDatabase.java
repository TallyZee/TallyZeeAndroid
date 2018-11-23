package com.aiminfocom.tallyfy.data.db.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aiminfocom.tallyfy.data.Model.BillsReceables;

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract BillDao billDao();
}
