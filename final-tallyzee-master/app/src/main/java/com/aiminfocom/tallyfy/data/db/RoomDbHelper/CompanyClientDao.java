package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface CompanyClientDao {


    @Insert
    void insertHashSet(ArrayList<CompanyList> arrayList);

    @Query("select * from CompanyList")
    Single<List<CompanyList>> retrieveList();


    @Query("delete from CompanyList")
    void deleteAllData();

}
