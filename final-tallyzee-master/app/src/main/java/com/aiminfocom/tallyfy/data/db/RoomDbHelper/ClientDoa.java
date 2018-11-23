package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.Model.Client;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface ClientDoa {
    @Insert
    void insertHashSet(ArrayList<Client> arrayList);

    @Query("select * from Client")
    Single<List<Client>> retrieveList();


    @Query("delete from Client")
    void deleteAllData();

}
