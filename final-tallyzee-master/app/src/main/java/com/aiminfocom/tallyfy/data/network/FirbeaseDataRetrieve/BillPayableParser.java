package com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import rx.schedulers.Schedulers;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import java.util.ArrayList;



public class BillPayableParser {

    private Context context;
    private static final String TAG = "BillPayableParse";





    public  void getBillData()
    {
        ArrayList<BillsPayable> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
        .getInstance()
        .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Bill_Payables/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(Schedulers.io())
                .subscribe(dataSnapshot ->
                        {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                BillsPayable billsPayable = data.getValue(BillsPayable.class);
                                arrayList.add(billsPayable);

                            }
                            Log.d(TAG, "onDataChange: "+arrayList);
                        });
    }
}





//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot data:dataSnapshot.getChildren())
//                {
//                    BillsPayable billsPayable = data.getValue(BillsPayable.class);
//                    arrayList.add(billsPayable);
//
//                }
//                Log.d(TAG, "onDataChange: "+arrayList);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

