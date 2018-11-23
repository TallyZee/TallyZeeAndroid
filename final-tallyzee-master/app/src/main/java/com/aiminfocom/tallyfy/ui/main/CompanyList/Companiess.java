package com.aiminfocom.tallyfy.ui.main.CompanyList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser.getCompanyName;
import com.aiminfocom.tallyfy.ui.Companies.CompaniesFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Companiess {

    getCompanyName companyName;
    public Companiess(CompaniesFragment fragment)
    {
        companyName = fragment;
    }


    private  final String TAG = "COMPANIES";
    private  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private  int email = user.getEmail().hashCode();

    public  void getCompanyName()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/companyList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String s = ds.getValue(String.class);
                    arrayList.add(s);

                }
                Log.d(TAG, "onDataChange: "+ arrayList);
                companyName.getName(arrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
