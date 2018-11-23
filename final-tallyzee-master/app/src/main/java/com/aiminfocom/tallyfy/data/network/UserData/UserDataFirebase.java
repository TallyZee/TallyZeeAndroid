package com.aiminfocom.tallyfy.data.network.UserData;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;


import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


import static com.aiminfocom.tallyfy.ui.about.AboutFragment.TAG;

public class UserDataFirebase {

     static onDataInterface onDataInterface;
    public UserDataFirebase(onDataInterface onDataInterface)
    {
        UserDataFirebase.onDataInterface =onDataInterface;
    }
    public  void getCurrentUserInfo(String email)
    {



        String getEmail =   String.valueOf(email.hashCode());
        Log.d(TAG, "getCurrentUserInfo: "+getEmail);
        System.out.println(getEmail+"IS THE EMAIL");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+getEmail);

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    String userName = dataSnapshot.child("username").getValue(String.class);
                    String email1 = dataSnapshot.child("email").getValue(String.class);
                    onDataInterface.retrieveUserName(userName,email1);
                    Log.d(TAG, "onDataChange: "+userName);
                });

    }
}
