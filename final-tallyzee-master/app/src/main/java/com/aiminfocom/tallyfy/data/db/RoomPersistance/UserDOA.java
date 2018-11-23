package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.User;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by GulshanPC on 12/07/2018.
 */
@Dao
public interface UserDOA {
    @Query("SELECT * FROM User")
    Maybe<User> getAllUser();


    @Query("SELECT * FROM User WHERE email LIKE :email AND "
            + "password LIKE :password LIMIT 1")
   Flowable<User> findByNamePass(String email, String password);



    @Insert
    void insertUser(User users);

    @Delete
    void delete(User user);

    @Update
    public void updateUsers(User... users);
}
