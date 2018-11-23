package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Company;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrder;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
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
    public static int salesOrderCount,companyC,profitLoss;
    private static LocalCacheManager _instance;
    private AppDatabase db;

    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public LocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void getUsers(final DatabaseCallback databaseCallback, String name, String password) {
        db.userProfileDao().findByNamePass(name, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull User users) throws Exception {
               System.out.println("the data"+users.getEmail()+" "+users.getMobile());
                databaseCallback.onUsersLoaded(users);
            }
        });


}
    public User getUser( String name, String password) {
        final User[] user = new User[1];
        db.userProfileDao().findByNamePass(name, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull User users) throws Exception {
                System.out.println("the data"+users.getEmail()+" "+users.getMobile());
                user[0] =users;

            }
        });
return user[0];

    }


    public void addUser(final DatabaseCallback databaseCallback, final User user) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userProfileDao().insertUser(user);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onUserAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    public void deleteUser(final DatabaseCallback databaseCallback, final BillsReceivable user) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
               // db.userDao().delete(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onUserDeleted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }


    public void updateUser(final DatabaseCallback databaseCallback, final BillsReceivable user) {
        user.setBillCl("first name first name");
        user.setBillDate("last name last name");
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
               // db.userDao().updateUsers(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onUserUpdated();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }
    public void getListBillsReceivable(final DatabaseCallback databaseCallback)
    {
        List<BillsReceivable> list=new ArrayList<BillsReceivable>();

        db.billDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BillsPaybale>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<BillsPaybale> users) throws Exception {

            databaseCallback.onListBillRecable(users);
            }
        });
    }
    public void setListBillsReceivable(final DatabaseCallback databaseCallback)
    {
        List<BillsReceivable> list=new ArrayList<BillsReceivable>();

        db.billDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BillsPaybale>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<BillsPaybale> users) throws Exception {

                databaseCallback.onListBillRecableSave();
            }
        });
    }
    public void addBill(final DatabaseCallback databaseCallback, final BillsPaybale billsPaybale) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if(i>0)
                {
                    System.out.println("the bill added"+billsPaybale);
                    db.billDao().insertAll(billsPaybale);
                    i++;

                }
                else {
                    db.billDao().delete(billsPaybale);
                    db.billDao().insertAll(billsPaybale);
                    System.out.println("the bill added on ++/"+billsPaybale.getBillCl()+" "+billsPaybale.getBillParty()+" "+billsPaybale.getBillref()+" ");
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onListBillRecableSave();
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
                databaseCallback.onDataNotAvailable();
            }
        });
    }
   public  void addProfitandloss(final DatabaseCallback databaseCallback,final profitnloss profitnloss)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if(i==0)
                {
                    db.profitandlossDOA().insertAll(profitnloss);
                    i++;

                }
                else {
                    db.profitandlossDOA().delete(profitnloss);
                    db.profitandlossDOA().insertAll(profitnloss);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onListBillRecableSave();
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
                databaseCallback.onDataNotAvailable();
            }
        });
    }
    public void getSalesOrders(final DatabaseCallback databaseCallback)
    {
        List<SalesOrders> list=new ArrayList<SalesOrders>();

        db.salesOrdersDOA().getAllSalesOrdersList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<SalesOrders>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<SalesOrders> users) throws Exception {

                databaseCallback.onSalesOrdersCallBack(users);
            }
        });
    }
    public  void addSalesOrders(final DatabaseCallback databaseCallback,final SalesOrders salesOrder)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if(salesOrderCount>0)
                {
                    db.salesOrdersDOA().insertSalesOrders(salesOrder);
                    salesOrderCount++;

                }
                else {
                    db.salesOrdersDOA().delete(salesOrder);
                    db.salesOrdersDOA().insertSalesOrders(salesOrder);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

                System.out.println("the salesOrderCount inserteed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
                databaseCallback.onDataNotAvailable();
            }
        });
    }
    public void getStockList(final StockCallBack stockCallBack)
    {
        List<Stock> list=new ArrayList<Stock>();

        db.stockDOA().getAllStockList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Stock>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<Stock> list) throws Exception {
                stockCallBack.dataBaseStockList(list);
            }
        });
    }
    public void addStock(final DatabaseCallback databaseCallback, final Stock stock)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if(salesOrderCount>0)
                {
                    db.stockDOA().insertStock(stock);
                    salesOrderCount++;

                }
                else {
                    db.stockDOA().delete(stock);
                    db.stockDOA().insertStock(stock);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

                System.out.println("the salesOrderCount inserteed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
                databaseCallback.onDataNotAvailable();
            }
        });
    }
    public void getCompanylist(final CompanyCallBack companyCallBack)
    {
       // List<Stock> list=new ArrayList<Stock>();

        db.companyDOA().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Company>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<Company> list) throws Exception {
                companyCallBack.dataBaseCompanyList(list);
            }
        });
    }
    public void setCompany(final CompanyCallBack databaseCallback,final Company company)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if(companyC==0)
                {
                    db.companyDOA().insertAll(company);
                    System.out.println("the company added on setCompany"+company);
                    companyC++;

                }
                else {
                    db.companyDOA().delete(company);
                    db.companyDOA().insertAll(company);
                    System.out.println("the company added on ++/"+company);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {

                System.out.println("the salesOrderCount inserteed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
                databaseCallback.onDataNotAvailable();
            }
        });
    }
}
