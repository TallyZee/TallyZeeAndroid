package com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class BillReceivableParser {

    public static final String TAG = "BillReceivableParser";

    public void onDataRetreive() {
        ArrayList<BillsReceables> receivableList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/bill_Receivables/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    BillsReceables bill = data.getValue(BillsReceables.class);
                    receivableList.add(bill);
                }
                Log.d(TAG, "onDataChange: "+receivableList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
