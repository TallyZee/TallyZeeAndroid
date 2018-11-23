package com.aiminfocom.tallyfy.ui.Users;

import android.content.Context;
import android.util.Log;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.data.network.UserData.UserDataFirebase;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GulshanPC on 25/06/2018.
 */

public class UserPresenter<V extends UserMvp> extends BasePresenter<V>
        implements UserMvpPresenter<V> {
    @Inject
    public UserPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getUserProfileData() {
        ArrayList<UserProfile> arrayList = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email);

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    String userName = dataSnapshot.child("username").getValue(String.class);
                    String email1 = dataSnapshot.child("email").getValue(String.class);
                    UserProfile userProfile = new UserProfile(userName,email1);
                    arrayList.add(userProfile);
                    getMvpView().getUserProfileResponse(arrayList);
                });
    }
}
