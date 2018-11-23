/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.aiminfocom.tallyfy.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.StockItem;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.ContraVoucher;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve.SalesVoucherParser;
import com.aiminfocom.tallyfy.data.network.IndependentThread.BillPayableThread;
import com.aiminfocom.tallyfy.data.network.IndependentThread.BillReceivableThread;
import com.aiminfocom.tallyfy.data.network.IndependentThread.ContraVoucherThread;
import com.aiminfocom.tallyfy.data.network.IndependentThread.CreditNoteThread;
import com.aiminfocom.tallyfy.data.network.IndependentThread.SalesVoucherThread;
import com.androidnetworking.error.ANError;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.data.network.ApiEndPoint;
import com.aiminfocom.tallyfy.data.network.model.LogoutResponse;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.ConnectionUtil;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static com.aiminfocom.tallyfy.ui.about.AboutFragment.TAG;


/**
 * Created by gulshan on 23/06/18.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";
    private OkHttpClient client = new OkHttpClient();
    DataManager dataManager;
    String urlforProfit = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>profit and loss</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        this.dataManager=dataManager;
    }

    @Override
    public void onDrawerOptionAboutClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showAboutFragment();
    }

    @Override
    public void onDrawerOptionLogoutClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().doLogoutApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LogoutResponse>() {
                    @Override
                    public void accept(LogoutResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getDataManager().setUserAsLoggedOut();
                        getMvpView().hideLoading();
                        getMvpView().openLoginActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));

    }
    public void getStock(Context context)
    {

    }



    @Override
    public void getBillsReceivable(String bodyParam) {
//        getCompositeDisposable().add(getDataManager()
//                .getBillsReceivableList()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(list-> {
//                            Log.i(TAG, "PostAREquestToBillReceable: " + list.toString());
//                            getMvpView().hideLoading();
//                            getMvpView().showBillResponse((ArrayList<BillsReceivable>)list);
//                        },
//                        throwable -> {
//                            Log.e(TAG, "PostAREquestToBillReceable:: " + throwable.toString());
//
//                        }));

    }


    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().updateAppVersion();

        final String currentUserName = getDataManager().getCurrentUserName();
        if (currentUserName != null && !currentUserName.isEmpty()) {
            getMvpView().updateUserName(currentUserName);
        }

        final String currentUserEmail = getDataManager().getCurrentUserEmail();
        if (currentUserEmail != null && !currentUserEmail.isEmpty()) {
            getMvpView().updateUserEmail(currentUserEmail);
        }

        final String profilePicUrl = getDataManager().getCurrentUserProfilePicUrl();
        if (profilePicUrl != null && !profilePicUrl.isEmpty()) {
            getMvpView().updateUserProfilePic(profilePicUrl);
        }
    }

    @Override
    public void getSalesVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<SalesVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/Sales_Vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        SalesVoucher salesVoucher = data.getValue(SalesVoucher.class);
                        arrayList.add(salesVoucher);

                    }
                    getMvpView().getSalesVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getBillsPayableData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<BillsPayable> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/Bill_Payables/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        BillsPayable billsPayable = data.getValue(BillsPayable.class);
                        arrayList.add(billsPayable);

                    }
                    getMvpView().getBillPayableResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

//    @Override
//    public void getSalesVoucherData() {
//        ArrayList<SalesVoucher> arrayList = new ArrayList<>();
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Sales_Vouchers/");
//        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
//                .subscribeOn(rx.schedulers.Schedulers.computation())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .subscribe(dataSnapshot ->
//                {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        SalesVoucher salesVoucher = data.getValue(SalesVoucher.class);
//                        arrayList.add(salesVoucher);
//
//                    }
//                    getMvpView().getSalesVoucherResponse(arrayList);
//                    Log.d(TAG, "onDataChange: "+arrayList);
//                });
//    }


//    public void getBillsPayableData()
//    {
//        ArrayList<BillsPayable> arrayList = new ArrayList<>();
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Bill_Payables/");
//        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
//                .subscribeOn(rx.schedulers.Schedulers.computation())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .subscribe(dataSnapshot ->
//                {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        BillsPayable billsPayable = data.getValue(BillsPayable.class);
//                        arrayList.add(billsPayable);
//
//                    }
//                    getMvpView().getBillPayableResponse(arrayList);
//                    Log.d(TAG, "onDataChange: "+arrayList);
//                });
//    }

    @Override
    public void getBillReceivableData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<BillsReceables> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/bill_Receivables/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        BillsReceables billsReceables = data.getValue(BillsReceables.class);
                        arrayList.add(billsReceables);

                    }
                    getMvpView().getBillReceivableResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getPurhcaseVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<PurchaseVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/Purchase_Vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        PurchaseVoucher purchaseVoucher = data.getValue(PurchaseVoucher.class);
                        arrayList.add(purchaseVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getPurchaseVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getPaymentVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<PaymentVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/Payment_Vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        PaymentVoucher paymentVoucher = data.getValue(PaymentVoucher.class);
                        arrayList.add(paymentVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getPaymentVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getCreditVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<CreditNoteVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/CreditNote_Vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        CreditNoteVoucher creditNoteVoucher = data.getValue(CreditNoteVoucher.class);
                        arrayList.add(creditNoteVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getCreditVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getDebitVoucherData() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        int email =  user.getEmail().hashCode();
        ArrayList<DebitNoteVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/DebitNote_vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        DebitNoteVoucher debitNoteVoucher = data.getValue(DebitNoteVoucher.class);
                        arrayList.add(debitNoteVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getDebitVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getSalesOrderVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<SalesOrderVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/SalesOrderVouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        SalesOrderVoucher salesOrderVoucher = data.getValue(SalesOrderVoucher.class);
                        arrayList.add(salesOrderVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getSalesOrderVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getPurchaseOrderVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<PurchaseOrderVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/PurchaseOrder_vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        PurchaseOrderVoucher purchaseOrderVoucher = data.getValue(PurchaseOrderVoucher.class);
                        arrayList.add(purchaseOrderVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getPurchaseOrderVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getReceiptNoteVoucherData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<ReceiptVoucher> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/Receipt_Vouchers/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        ReceiptVoucher receiptVoucher = data.getValue(ReceiptVoucher.class);
                        arrayList.add(receiptVoucher);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getReceiptVoucherResponse(arrayList,1);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getProfitAndLossData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<ProfitAndLoss> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/-1106778441/AIM INFOCOM SERVICES PVTLTD (1516)/ProfitandLoss/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        ProfitAndLoss profitAndLoss = data.getValue(ProfitAndLoss.class);
                        arrayList.add(profitAndLoss);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getProfitAndLossResponse(arrayList);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

    @Override
    public void getCompanyList() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<CompanyList> arrayList = new ArrayList<>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/companyList");
                  RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                          .subscribeOn(rx.schedulers.Schedulers.computation())
                          .observeOn(rx.schedulers.Schedulers.io())
                          .subscribe(dataSnapshot ->
                          {
                              for (DataSnapshot data : dataSnapshot.getChildren()) {
                                  String s = data.getValue(String.class);
                                  CompanyList companyList = new CompanyList(s);
                                  arrayList.add(companyList);


                              }
                              getMvpView().getComapnyListResponse(arrayList);
//                    String s = res.child("0").getValue(String.class);
//                  String s1 = res.child("0").getValue(String.class);
//                  String s2 = res.child("0").getValue(String.class);
//                  String s3 = res.child("0").getValue(String.class);
//                  String s4 = res.child("0").getValue(String.class);
//                    CompanyList companyList = new CompanyList(s);
//
//                      arrayList.add(companyList);
//                      getMvpView().getComapnyListResponse(arrayList);

              });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren())
//                {
//
//                    String s = ds.getValue(String.class);
//                    CompanyList companyList = new CompanyList(s);
//                    Log.d(TAG, "onDataChange: "+s);
//                    arrayList.add(companyList);
//
//                }
//                Log.d(TAG, "onDataChange: "+ arrayList);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
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
                    Log.d(TAG, "onDataChange: "+userName);
                    UserProfile userProfile = new UserProfile(userName,email1);
                    arrayList.add(userProfile);
                    getMvpView().getUserProfileResponse(arrayList);
                });

    }

    @Override
    public void getStockItemData() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        int email =  mAuth.getCurrentUser().getEmail().hashCode();
        ArrayList<StockItem> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/AIM INFOCOM SERVICES PVTLTD (1516)/StockItem/");
        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.schedulers.Schedulers.io())
                .subscribe(dataSnapshot ->
                {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        StockItem stockItem = data.getValue(StockItem.class);
                        arrayList.add(stockItem);

                    }
                    System.out.println("Purchase Voucher Data"+arrayList);
                    getMvpView().getStockItemResponse(arrayList);
                    Log.d(TAG, "onDataChange: "+arrayList);
                });
    }

//    @Override
//    public void getPurhcaseVoucherData() {
//        ArrayList<PurchaseVoucher> arrayList = new ArrayList<>();
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/-649514226/Aiminfocom/Purchase_Vouchers/");
//        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
//                .subscribeOn(rx.schedulers.Schedulers.computation())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .subscribe(dataSnapshot ->
//                {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        PurchaseVoucher purchaseVoucher = data.getValue(PurchaseVoucher.class);
//                        arrayList.add(purchaseVoucher);
//
//                    }
//                    getMvpView().getPurchaseVoucherResponse(arrayList);
//                    Log.d(TAG, "onDataChange: "+arrayList);
//                });
//    }


//    @Override
//    public void getContraVoucherData() {
//        ArrayList<ContraVoucher> arrayList = new ArrayList<>();
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Contra_Vouchers/");
//        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
//                .subscribeOn(rx.schedulers.Schedulers.computation())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .subscribe(dataSnapshot ->
//                {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        ContraVoucher contraVoucher = data.getValue(ContraVoucher.class);
//                        arrayList.add(contraVoucher);
//
//                    }
//                    getMvpView().getContraVoucherResponse(arrayList);
//                    Log.d(TAG, "onDataChange: "+arrayList);
//                });
//    }

//    @Override
//    public void getReceiptVoucherData() {
//        ArrayList<ReceiptVoucher> arrayList = new ArrayList<>();
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Receipt_Vouchers/");
//        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
//                .subscribeOn(rx.schedulers.Schedulers.computation())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .subscribe(dataSnapshot ->
//                {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        ReceiptVoucher receiptVoucher = data.getValue(ReceiptVoucher.class);
//                        arrayList.add(receiptVoucher);
//
//                    }
//                    getMvpView().getReceiptVoucherResponse(arrayList);
//                    Log.d(TAG, "onDataChange: "+arrayList);
//                });
//    }

//    @Override
//    public void getPaymentVoucherData() {
//        ArrayList<PaymentVoucher> arrayList = new ArrayList<>();
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/TallFy/Users/5/gulshanpadhan/Payment_Vouchers/");
//        RxFirebaseDatabase.observeSingleValueEvent(databaseReference)
//                .subscribeOn(rx.schedulers.Schedulers.computation())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .subscribe(dataSnapshot ->
//                {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        PaymentVoucher paymentVoucher = data.getValue(PaymentVoucher.class);
//                        arrayList.add(paymentVoucher);
//
//                    }
//                    getMvpView().getPaymentVoucherResponse(arrayList);
//                    Log.d(TAG, "onDataChange: "+arrayList);
//                });
//    }


    @Override
    public void onDrawerRateUsClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showRateUsDialog();
    }

    @Override
    public void onDrawerMyFeedClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().openMyFeedActivity();
    }

    @Override
    public void getSalesList() {
//        SalesVoucherParser salesVoucherParser=new SalesVoucherParser();
//      salesVoucherParser.getSalesList().subscribeOn(Schedulers.computation())
//             .subscribe((res)->{getMvpView().getResponse(res);});
    }

    @Override
    public String getCompanyName() {
        return getDataManager().getCompanyName();
    }

    @Override
    public void getStockCall(Context context) {
        Observable.fromCallable(() -> {

            try {
                RequestBody body = RequestBody.create(ApiEndPoint.XML,urlforProfit);
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(ApiEndPoint.TALLY_ENDPOINT)
                        .addHeader("Content-Type",
                                "text/xml;charset=utf-8")
                        .post(body)
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                // Toast.makeText(this,"make sure your ip and port must be validate",Toast.LENGTH_LONG).show();
                Log.e("Network request", "Failure", e);
            }

            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Serializable response) -> {
                    if(response.toString().equalsIgnoreCase("false"))
                    {
                        System.out.println("the request got error");
                    }else {
                        String cleanString = response.toString().replaceAll("\r", "").replaceAll("\n", "");
                        System.out.println("result is:"+ ConnectionUtil.xmltoJson(cleanString));
                        ArrayList<Stock> list=(ArrayList<Stock>) ConnectionUtil.getInistance().getStockList(context, ConnectionUtil.xmltoJson(cleanString));
                        System.out.println("result is:"+response+" "+response.toString()+"list:"+list);
                        getMvpView().onGetStock(list);


                    }
                });
    }

    @Override
    public void setBillsReceivable(BillsReceivable billsReceivable) {
//        getCompositeDisposable().add(getDataManager()
//                .insertBillsReceivable(billsReceivable)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(list-> {
//                            Log.i(TAG, "PostAREquestToBillReceable: " + list.toString());
//                            getMvpView().hideLoading();
//                            getMvpView().showBillReceivableResponse(list);
//                        },
//                        throwable -> {
//                            Log.e(TAG, "PostAREquestToBillReceable:: " + throwable.toString());
//
//                        }));

    }





}
