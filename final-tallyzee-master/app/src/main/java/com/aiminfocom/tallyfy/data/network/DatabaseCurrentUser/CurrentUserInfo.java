package com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser;

import android.support.annotation.NonNull;

import com.aiminfocom.tallyfy.data.BeanModels.UserFeedback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentUserInfo {


    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static int email = user.getEmail().hashCode();
    public static void setFeedbackDetails(UserFeedback userFeedback)
    {
        ArrayList<UserFeedback> arrayList = new ArrayList<>();
        arrayList.add(userFeedback);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/");


        databaseReference.child("Feedback").setValue(arrayList);
    }



    public static void setConactDetails(UserFeedback userFeedback)
    {
        ArrayList<UserFeedback> arrayList = new ArrayList<>();
        arrayList.add(userFeedback);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/Issues/");


        databaseReference.child(userFeedback.getName()).setValue(arrayList);
    }



    public static void setLastSycn(String date)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/Last Sync");
        databaseReference.setValue(date);

    }


}