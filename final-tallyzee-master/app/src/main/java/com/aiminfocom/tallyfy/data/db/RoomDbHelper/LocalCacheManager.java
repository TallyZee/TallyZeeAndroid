package com.aiminfocom.tallyfy.data.db.RoomDbHelper;



import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Company;
import com.aiminfocom.tallyfy.data.BeanModels.Sales;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrder;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.StockItem;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CreditNoteLedger;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.LedgerOutstandings;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDatabaseHelper;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.AppDatabase;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.CompanyCallBack;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.DatabaseCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.StockCallBack;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContainer;
import com.aiminfocom.tallyfy.ui.main.ClientFrg.RoomClientCallBack;
import com.aiminfocom.tallyfy.ui.main.MainActivity;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alahammad on 10/4/17.
 */


public class LocalCacheManager {
    private static final String DB_NAME = "TallyFy.db";
    private Context context;
    public static int i;
    public static int salesOrderCount, companyC, profitLoss;
    private static LocalCacheManager _instance;
    private RoomDatabaseHelper db;

    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public LocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, RoomDatabaseHelper.class, "user.db").allowMainThreadQueries().build();
    }


    @SuppressLint("CheckResult")
    public void getBillPayable(final RoomDbCallback roomDbCallback) {
        db.billsPayableDao().retrieveData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(billsPayables -> roomDbCallback.getBillsPayableData(billsPayables));
    }


    public void UpdateSales() {

    }

    public void insertData(ArrayList<BillsPayable> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.billsPayableDao().insertData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }


    public void insertBillsReceivable(ArrayList<BillsReceables> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.daoHelper().insertData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void retrieveReceivable(final RoomDbCallback roomDbCallback) {
        db.daoHelper().retriveData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((billsReceables) ->
                {
                    roomDbCallback.getBillsReceivableData(billsReceables);
                });
    }


    public void insertPurchaseVoucher(ArrayList<PurchaseVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.purchaseVoucherDao().insertData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void retrievePurchaseVoucher(final RoomDbCallback callback) {
        db.purchaseVoucherDao().retriveData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((purchaseVoucher) ->
                {
                    callback.getPurchaseVoucherData(purchaseVoucher);
                });
    }

    public void getVouchersByPartyName(String voucherPartyName, final RoomDbCallback callback) {
        db.salesVoucherDao().getVouchersByPartyName(voucherPartyName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((purchaseVoucher) ->
                {
                    callback.getSalesVoucherByPartyName(purchaseVoucher);
                });
    }

    public void insertSalesVoucherData(ArrayList<SalesVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.salesVoucherDao().insertSalesVoucherData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void retrieveSalesVoucherData(final RoomDbCallback roomDbCallback) {
        db.salesVoucherDao().retrieveData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getSalesVoucherData(res);
                });
    }

    public void getPurchasePartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String ParrtyName) {
        db.purchaseVoucherDao().getVouchersByPartyName(ParrtyName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getPurchasevoucherListName(res);
                });
    }

    public void getPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String Party) {
        db.salesVoucherDao().getVouchersByPartyName(Party).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getvoucherListName(res);
                });
    }

    public void getSalesOrderPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String Party) {
        db.salesOrderDao().getVouchersByPartyName(Party).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getSalesOrdervoucherListName(res);
                });
    }

    public void getPaymentPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String partyName) {
        db.paymentVoucherDao().getVouchersByPartyName(partyName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getPaymentvoucherListName(res);
                });
    }

    public void getPurchaseOrderPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String partyName) {
        db.purchaseOrderDao().getVouchersByPartyName(partyName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getPurchaseOrdervoucherListName(res);
                });
    }

    public void getReciepPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String partyName) {
        db.receiptNoteDao().getVouchersByPartyName(partyName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getReciptvoucherListName(res);
                });
    }

    public void getCeditnotePaymentPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String partyName) {
        db.creditVoucherDao().getVouchersByPartyName(partyName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getCreditNotevoucherListName(res);
                });
    }

    public void getDebitNOePaymentPartyNameList(final RoomDbFindPartyNameInterface roomDbCallback, String partyName) {
        db.debitNoteDao().getVouchersByPartyName(partyName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getdebitNotevoucherListName(res);
                });
    }


    public void insertPaymentVoucherData(ArrayList<PaymentVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.paymentVoucherDao().insertPaymentData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void singleValueSales(final SingleCallBack singleCallBack, String partyName, String date, String type) {

        db.salesVoucherDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    System.out.println(res+"isthe callback");
                    singleCallBack.getvSalesVoucher(res);
                });
    }

    public void singleValuePayment(final SingleCallBack singleCallBack, String partyName, String date, String type) {
        db.paymentVoucherDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    singleCallBack.getPaymentvoucher(res);
                });
    }

    public void singleValuePurchase(final SingleCallBack singleCallBack, String partyName, String date, String type) {
        db.purchaseVoucherDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    singleCallBack.getPurchasevoucher(res);
                });
    }

    public void singleValuePurchaseOrder(final SingleCallBack singleCallBack, String partyName, String date, String type) {
        db.purchaseOrderDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    singleCallBack.getPurchaseOrdervoucher(res);
                });
    }

    public void singleValueSalesOrder(final SingleCallBack singleCallBack, String partyName, String date, String type) {
        db.salesOrderDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    singleCallBack.getSalesOrdervoucher(res);
                });
    }

    public void singleValueReciept(final SingleCallBack singleCallBack, String partyName, String date, String type) {
        db.receiptNoteDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    singleCallBack.getReciptvoucher(res);
                });
    }

    public void setFilter(final SingleCallBack singleCallBack, String type, String partyName, String date) {

        System.out.println("the local chase mechine" + type + " " + date + " " + partyName);
        if (type.equalsIgnoreCase("Sales")) {

        }
        switch (type) {
            case "Sales":
                System.out.println(type + " " + date + " " + partyName);
                db.salesVoucherDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((res) ->
                        {
                            singleCallBack.getvSalesVoucher(res);
                        });
                break;
            case "SalesOrder":

                break;
            case "Payment":

                break;
            case "Purchase":

                break;
            case "PurchaseOrder":

                break;
            case "Reciept":

                break;
            case "CreditNote":
                db.creditVoucherDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((res) ->
                        {
                            singleCallBack.getCreditNotevoucher(res);
                        });
                break;
            case "DebitNote":
                db.debitNoteDao().getSingleVoucher(partyName, date).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((res) ->
                        {
                            singleCallBack.getdebitNotevoucher(res);
                        });
                break;
        }
    }

    public void retrievePaymentVoucher(final RoomDbCallback roomDbCallback) {
        db.paymentVoucherDao().retrievePaymentData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getPaymentVoucherData(res);
                });
    }

    public void insertCreditVoucher(ArrayList<CreditNoteVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.creditVoucherDao().insertCreditData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void retrieveCreidtVoucher(final RoomDbCallback roomDbCallback) {
        db.creditVoucherDao().retrieveCreditData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getCreditVoucherData(res);
                });
    }

    public void insertDebitData(ArrayList<DebitNoteVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.debitNoteDao().insertDebitNoteData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void retrieveDebitVoucher(final RoomDbCallback roomDbCallback) {
        db.debitNoteDao().retrieveDebitNoteData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getDebitVOucherData(res);
                });
    }


    public void insertSalesOrderData(ArrayList<SalesOrderVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.salesOrderDao().insertSalesOrder(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void retrieveSalesOrderData(final RoomDbCallback roomDbCallback) {
        db.salesOrderDao().retrieveSalesOrderVoucher().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getSalesOrderVoucherData(res);
                });
    }

    public void insertPurchaseOrder(ArrayList<PurchaseOrderVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.purchaseOrderDao().insertPurchaseOrder(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void retrievePurchaseOrder(final RoomDbCallback roomDbCallback) {
        db.purchaseOrderDao().retrievePurchaseOrder().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getPurchaseOrderVoucherData(res);
                });

    }

    public void insertReceiptNote(ArrayList<ReceiptVoucher> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.receiptNoteDao().insertReceiptNote(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void retrieveReceiptNote(final RoomDbCallback roomDbCallback) {

        db.receiptNoteDao().retrieveReceiptNote().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    roomDbCallback.getReceiptNoteVoucherData(res);
                });

    }


    public void insertProfitAndLossData(ArrayList<ProfitAndLoss> arrayList) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.profitNLossDao().insertProfitNLossData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }


    public void retrieveProfitNLossData(final RoomDbCallback roomDbCallback)
    {
        db.profitNLossDao().retrieveProfit().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                   roomDbCallback.getProfitAndLossData(res);
                });
    }


    public void getCompanyNames(ArrayList<CompanyList> arrayList)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.companyNameDao().addCompanyName(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void retrieveCompanyName(final CompanyNameCallback roomDbCallback)
    {
        db.companyNameDao().getCompanyList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getCompanyList(res);
                });
    }

    public void deleteCompany(String companyName)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.companyNameDao().deleteCompany(companyName);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }



//    public void deleteCompany(String name)
//    {
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                db.companyNameDao().deleteCompanyName(name);
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//    }


    public void insertUserProfile(ArrayList<UserProfile> arrayList)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userProfileDao().insertUserProfileData(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void retrieveUserProfile(final RoomDbCallback roomDbCallback)
    {
        db.userProfileDao().retrieveUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                   roomDbCallback.getUserData(res);
                });
    }

    public void insertComapnyClient(ArrayList<Client> arrayList)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.clientDoa().insertHashSet(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void retrieveClientList(final RoomClientCallBack roomDbCallback)
    {
        db.clientDoa().retrieveList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getListClient(res);
                });

    }





//    public void retrieveUserData(final RoomDbCallback roomDbCallback)
//    {
//        db.userProfileDao().retrieveUserData().subscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe((res)->
//                {
//                   roomDbCallback.getUserData(res);
//                });
//    }



    public void salesQueryExecute(String voucherPartyName, final RoomDbCallback roomDbCallback)
    {
        db.salesVoucherDao().getPartyName(voucherPartyName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getSalesQueryData(res);
                });

    }



    public void billsPayableQueryExecute(String voucherPartyName,final RoomDbCallback roomDbCallback)
    {
        db.billsPayableDao().getBillsPayableParty(voucherPartyName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getBillsPayableQueryData(res);
                });
    }


    public void billsReceivableQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.daoHelper().billsReceivableQuery(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                   roomDbCallback.getBillsReceivableQueryData(res);
                });
    }

    public void receiptNoteQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.receiptNoteDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                   roomDbCallback.getReceiptQueryData(res);
                });
    }

    public void debitNoteQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.debitNoteDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getDebitNoteQueryData(res);
                });
    }

    public void creditNoteQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.creditVoucherDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getCreditNoteQueryData(res);
                });
    }

    public void paymentQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.paymentVoucherDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getPaymentQueryData(res);
                });
    }

    public void purachaseQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.purchaseVoucherDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getPurchaseQueryData(res);
                });
    }
    public void purchaseOrderQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.purchaseOrderDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getPurchaseOrderQueryData(res);
                });
    }

    public void salesOrderQueryExecute(String voucherParty,final RoomDbCallback roomDbCallback)
    {
        db.salesOrderDao().getVouchersByPartyName(voucherParty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getSalesOrderQueryData(res);
                });
    }


    public void fetchSalesDate(String voucherParty,String from ,String to,final DateRetrival roomDbCallback)
    {
        db.salesVoucherDao().fetchUserBetweenDate(voucherParty,from,to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getSalesDateBetween(res);
                });
    }

    public void fetchReceiptDate(String voucherParty,String from ,String to,final DateRetrival roomDbCallback)
    {
        db.receiptNoteDao().fetchReceiptData(voucherParty,from,to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getReceiptDateBetween(res);
                });
    }

    public void fetchPaymentDate(String voucherParty,String from ,String to,final DateRetrival roomDbCallback)
    {
        db.paymentVoucherDao().fetchPaymentDate(voucherParty,from,to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getPaymentDateBetween(res);
                });
    }

    public void fetchDebitNote(String voucherParty,String from ,String to,final DateRetrival roomDbCallback)
    {
        db.debitNoteDao().fetchDebitNoteVoucher(voucherParty,from,to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    roomDbCallback.getDebitDateBetween(res);
                });
    }


    public void deleteBillsPayable()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.billsPayableDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
            System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteClientDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.clientDoa().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteCompanyClientDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.companyClientDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteComapnyNameDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.companyNameDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteCreditVoucherdao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.creditVoucherDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteDebitVoucherDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.debitNoteDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deletePaymentVoucher()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.paymentVoucherDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteProfitNLossDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.profitNLossDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deletepurchaseOrderDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.purchaseOrderDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deletePurchaseVoucherDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.purchaseVoucherDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void deleteReceiptDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.receiptNoteDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void deleteSalesOrderDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.salesOrderDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteSalesVoucherDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.salesVoucherDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteUserProfileDao()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userProfileDao().deleteAllData();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                System.out.println("DELETED ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void insertStockItem(ArrayList<StockItem> arrayList)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.stockItemDao().insertStockItem(arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void itemQueryData(String type,final com.aiminfocom.tallyfy.data.db.RoomDbHelper.ItemContainer itemContainer)
    {
        db.stockItemDao().itemQuery(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                   itemContainer.getItemQuery(res);
                });

    }

    public void groupQueryData(String type,final com.aiminfocom.tallyfy.data.db.RoomDbHelper.ItemContainer itemContainer)
    {
        db.stockItemDao().groupQuery(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    itemContainer.getGroupQuery(res);
                });

    }

    public void categoryQueryData(String type,final com.aiminfocom.tallyfy.data.db.RoomDbHelper.ItemContainer itemContainer)
    {
        db.stockItemDao().categoryQuery(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    itemContainer.getCategoryQuery(res);
                });

    }

}
