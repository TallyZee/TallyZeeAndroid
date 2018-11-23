package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserProfileDao {


    @Insert
    void insertUserProfileData(ArrayList<UserProfile> arrayList);

    @Query("select * from UserProfile")
    Single<List<UserProfile>> retrieveUserData();

    @Query("Delete From UserProfile")
    void deleteAllData();
}
