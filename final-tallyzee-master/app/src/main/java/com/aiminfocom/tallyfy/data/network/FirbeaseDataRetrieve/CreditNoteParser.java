package com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreditNoteParser {

public static final String TAG = "CreditNoteParser";
    public void getCreditVouchers()
    {
        ArrayList<CreditNoteVoucher> creditNoteList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/CreditNote_Vouchers/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    CreditNoteVoucher creditNoteVoucher = data.getValue(CreditNoteVoucher.class);
                    creditNoteList.add(creditNoteVoucher);
                }
                Log.d(TAG, "onDataChange: "+creditNoteList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
