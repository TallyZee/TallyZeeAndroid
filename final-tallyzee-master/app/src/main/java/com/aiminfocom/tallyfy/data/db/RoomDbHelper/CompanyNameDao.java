package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface CompanyNameDao {

    @Insert
    void addCompanyName(ArrayList<CompanyList> arrayList);

    @Query("select * from CompanyList")
    Single<List<CompanyList>> getCompanyList();

    @Query("select * from CompanyList")
    List<CompanyList> getListCompany();

//    @Query("select * from CompanyList where companyName LIKE :companyName")
//    void deleteCompanyName(String companyName);

    @Query("delete  from CompanyList where companyName =:name")
    void deleteCompany(String name);

    @Delete
    void deleteCompany(CompanyList model);


    @Query("delete from CompanyList")
    void deleteAllData();


}
