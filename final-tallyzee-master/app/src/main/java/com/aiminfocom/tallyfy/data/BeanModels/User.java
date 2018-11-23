package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

/**
 * Created by GulshanPC on 12/07/2018.
 */
@Entity(tableName = "User")
public class User {
    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    @PrimaryKey(autoGenerate = true)
    int userId;
    public User(String email,String name,String mobile,String password)
    {

      this.email=email;
      this.mobile=mobile;
      this.name=name;
      this.password=password;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    @ColumnInfo(name = "name")
    String name;
    @SerializedName("email")
    @ColumnInfo(name = "email")
    String email;
    @SerializedName("mobile")
    @ColumnInfo(name = "mobile")
    String mobile;

    @SerializedName("password")
    @ColumnInfo(name = "password")
    String password;
}
