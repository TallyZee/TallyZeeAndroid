package com.aiminfocom.tallyfy.data.network.FirebaseNetwork;

import android.content.Context;

import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public interface FirebaseConnectionInterface {

    void onCreateUser(UserProfile userProfile,Context context);

   void onLoginUser(String email,String pass,Context ctx);

    void onDeleteUser();

    void onForgetPassword(String email,Context context);
}
