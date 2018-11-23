package com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aiminfocom.tallyfy.data.Model.ContraVoucher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContraVouchersParser  {

    public static final String TAG = "ContraVouchersParser";
    public  void getContraVouchers()
    {
        ArrayList<ContraVoucher> contraList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Contra_Vouchers/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    ContraVoucher contraVoucher = data.getValue(ContraVoucher.class);
                    contraList.add(contraVoucher);
                }
                Log.d(TAG, "onDataChange: "+contraList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
