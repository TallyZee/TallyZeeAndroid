package com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.ui.main.SalesVouchersCAllBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SalesVoucherParser {

    public static final String TAG = "SalesVoucherParser";
    public void getSalesVoucher()
    {
        List<SalesVoucher> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Sales_Vouchers/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for(DataSnapshot data:dataSnapshot.getChildren())
              {
                  SalesVoucher salesVoucher  = data.getValue(SalesVoucher.class);

                  list.add(salesVoucher);

              }
                Log.d(TAG, "onDataChange: "+list);
              System.out.println(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}
