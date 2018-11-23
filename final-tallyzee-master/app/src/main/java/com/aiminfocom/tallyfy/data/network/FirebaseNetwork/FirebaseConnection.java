package com.aiminfocom.tallyfy.data.network.FirebaseNetwork;

import android.content.Context;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;


public class FirebaseConnection implements FirebaseConnectionInterface{


    @Override
    public  void onCreateUser(UserProfile userProfile,Context context) {

        FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(userProfile,context);
        firebaseAuthentication.getAccountCreated();
    }

    @Override
    public void onLoginUser(String email,String pass ,Context ctx) {
        FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(email,pass,ctx);
        firebaseAuthentication.getAuthResponse();
    }

    @Override
    public void onDeleteUser() {

    }

    @Override
    public void onForgetPassword(String email,Context ctx) {
        FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(email,ctx);
        firebaseAuthentication.onForgetPassword(email);

    }
}
