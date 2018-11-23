

package com.aiminfocom.tallyfy.ui.main;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.data.BeanModels.DashType;
import com.aiminfocom.tallyfy.data.BeanModels.StockItem;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;


import com.aiminfocom.tallyfy.data.db.RoomDbHelper.CompanyNameCallback;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDatabaseHelper;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbCallback;
import com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser.CurrentUserInfo;
import com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser.getCompanyName;
import com.aiminfocom.tallyfy.data.network.InternetStatus.InternetConnection;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.BottomNavigationInflate;
import com.aiminfocom.tallyfy.ui.main.CompanyList.Companiess;
import com.aiminfocom.tallyfy.ui.main.NullFragment.RobotFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import com.aiminfocom.tallyfy.BuildConfig;
import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.DatabaseCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.StockCallBack;
import com.aiminfocom.tallyfy.data.network.ApiEndPoint;
import com.aiminfocom.tallyfy.ui.about.AboutFragment;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.feed.FeedActivity;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.DashBoardFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;
import com.aiminfocom.tallyfy.ui.main.rating.RateUsDialog;
import com.aiminfocom.tallyfy.utils.ConnectionUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ir.hamsaa.RtlMaterialSpinner;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by gulshan on 23/06/18.
 */

public class MainActivity extends BaseActivity implements MainMvpView, DatabaseCallback, StockCallBack,callbackCompany,SalesVouchersCAllBack, RoomDbCallback ,CompanyNameCallback {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;




//    @BindView(R.id.bottom_navigation_ex)
//    BottomNavigationViewEx bottomBar;

    @BindView(R.id.nothing)
    RelativeLayout nothing;
    @BindView(R.id.container)
    FrameLayout gridLayout;
    @BindView(R.id.space)
    BottomNavigationView bottomNavigationView;
    static  int i=0;
    @BindView(R.id.getData)
    ImageView syncData;

    @BindView(R.id.syncProgress)
     ProgressBar syncProgress;
    HashSet<String> finalSet = new HashSet<>();
    @BindView(R.id.lastSync)
    TextView lastSync;
    private OkHttpClient client = new OkHttpClient();
    int cn=0,cn1=0,cn2=0,cn3=0,cn4=0,cn5=0,cn6=0,cn7=0,cn8=0,cn9=0,cn10=0;
    private TextView mNameTextView;
    private TextView mEmailTextView;
   public static android.support.v7.widget.SearchView searchView;
    ArrayList<Client> companyList = new ArrayList<>();
    double amount = 0,amount1=0,amount2=0,amount3=0,amount4=0,amount5=0,amount6=0,amount7=0,amount8=0;
    int salesAmount = 0,purchaseAmount=0,debitAmount=0,paymentAmount=0,salesOrderAmount=0,purchaseOrderAmount=0,receiptAmount=0,receivableAmount=0,payableAmount=0;
    String url = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>outreportmenu</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
    String urlforProfit = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>profit and loss</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
    String stock = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>Stock Summary</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
    String salesOrdersUrls = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>sales orders</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
    private RotateAnimation rotateAnimation;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private static final  String TAG = "MAinActivity";
    private ArrayList<String> adapterList = new ArrayList<>();
    public static SearchableSpinner materialSpinner;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public static RoomDatabaseHelper roomDatabaseHelper;
    @OnClick(R.id.getData)
    public void getData(View view)
    {

        if(InternetConnection.isNetworkAvailable(getApplicationContext())) {

//        mPresenter.getSalesList();
            Date date = new Date();
            UserProfile userProfile = new UserProfile();
            userProfile.setLastSync(date.toString().substring(0,20));
            CurrentUserInfo.setLastSycn(date.toString().substring(0,20));
            syncData.startAnimation(rotateAnimation);
            Toast.makeText(getApplicationContext(),"Syncing In Progress",Toast.LENGTH_LONG).show();
            mPresenter.getSalesVoucherData();
            syncProgress.setVisibility(View.VISIBLE);
//            mPresenter.getBillsPayableData();
            mPresenter.getBillReceivableData();
            mPresenter.getBillsPayableData();
            mPresenter.getPurhcaseVoucherData();
            mPresenter.getPaymentVoucherData();
            mPresenter.getCreditVoucherData();
            mPresenter.getDebitVoucherData();
            mPresenter.getSalesOrderVoucherData();
            mPresenter.getPurchaseOrderVoucherData();
            mPresenter.getReceiptNoteVoucherData();
            mPresenter.getProfitAndLossData();
            mPresenter.getStockItemData();


//            mPresenter.getContraVoucherData();
//            mPresenter.getPaymentVoucherData();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Seems Like Your Internet Connection is Not working",Toast.LENGTH_LONG).show();
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        int email = user.getEmail().hashCode();

        getActivityComponent().inject(this);
        roomDatabaseHelper = Room.databaseBuilder(getApplicationContext(),RoomDatabaseHelper.class,"user.db").allowMainThreadQueries().build();
        materialSpinner = findViewById(R.id.materialSpinner);
        setUnBinder(ButterKnife.bind(this));
        bottomNavigationView =  findViewById(R.id.space);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://tallyfy-364a0.firebaseio.com/Users/"+email+"/Last Sync");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String last_sync = dataSnapshot.getValue(String.class);
                try {
                    if (!last_sync.isEmpty() && !(last_sync == null)) {
                        System.out.println(last_sync + "is LASY SYNC");
                        lastSync.setText("Last Sync :" + last_sync);
                    }
                } catch (Exception e)
                {
                    lastSync.setText("Last Sync :" );
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Menu a = bottomNavigationView.getMenu();
//        MenuItem b = a.findItem(R.id.Logo);
//        b.setIcon(R.drawable.ic_tallyzeelogo);

        searchView = findViewById(R.id.ic_search2);
         rotateAnimation = new RotateAnimation(0,360f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(20000000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        searchView.setMaxWidth(Integer.MAX_VALUE);


        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCompanyName(this);

//        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
//        spaceNavigationView.addSpaceItem(new SpaceItem("DashBoard", R.drawable.ic_dashboard));
//        spaceNavigationView.addSpaceItem(new SpaceItem("Client", R.drawable.ic_client));
//        spaceNavigationView.addSpaceItem(new SpaceItem("Items", R.drawable.ic_items));
//        spaceNavigationView.addSpaceItem(new SpaceItem("Sync", R.drawable.ic_sync));
//        spaceNavigationView.showIconOnly();

            ButterKnife.bind(this);

//        request();
//        ConnectionUtil connectionUtil=new ConnectionUtil();
//         connectionUtil.connectTally("List of Companies");
//        System.out.println(AppController.getInstance().convertToXml(url));
        getAllData();
        setUpNull();
        // mPresenter.getBillsReceivable(url);
//        LocalCacheManager.getInstance(this).addUser(this,new BillsReceivable("1","2","2","2","2","2"));
//        mPresenter.setBillsReceivable(new BillsReceivable("1","test","test","test","test","test"));
//        LocalCacheManager.getInstance(this).getUsers(this);
        //DataGenerator.with(AppDataBase.getAppDatabase(getApplicationContext())).generateBillsReceivable(new ArrayList<>());
        mPresenter.onAttach(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,adapterList);
        materialSpinner.setAdapter(arrayAdapter);
       materialSpinner.setStatusListener(new IStatusListener() {
           @Override
           public void spinnerIsOpening() {
               materialSpinner.hideEdit();
           }

           @Override
           public void spinnerIsClosing() {

           }
       });
        materialSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                System.out.println(position+"is the position"+"and id is"+id);
                setUp();
            }

            @Override
            public void onNothingSelected() {
                setUpNull();


            }
        });

    }

    @Override
    protected void onRestart() {

        super.onRestart();

    }




    void syncData() {

        Observable.fromCallable(() -> {

            try {
                RequestBody body = RequestBody.create(ApiEndPoint.XML, url);
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(ApiEndPoint.TALLY_ENDPOINT)
                        .addHeader("Content-Type",
                                "text/xml;charset=utf-8")
                        .post(body)
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                // Toast.makeText(this,"make sure youDr ip and port must be validate",Toast.LENGTH_LONG).show();
                Log.e("Network request", "Failure", e);
            }

            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    if (response.toString().equalsIgnoreCase("false")) {
                        System.out.println("the request got error");
                        return;
                    } else {
                        System.out.println("row result is:"+response.toString());
                        System.out.println("result is:" + ConnectionUtil.xmltoJson(response.toString()));
                        String cleanString = response.toString().replaceAll("\r", "").replaceAll("\n", "");
                        System.out.println("result is:" + ConnectionUtil.xmltoJson(cleanString));
                        ArrayList<BillsPaybale> list = (ArrayList<BillsPaybale>) ConnectionUtil.getInistance().getBillsPaybale(ConnectionUtil.xmltoJson(cleanString));
                        System.out.println("result is:" + response + " " + response.toString() + "list:" + list);

                        if (list.size() >= 1) {
                            BillsPaybale bill;
                            for (int i = 0; i < list.size(); i++) {
                                bill = list.get(i);
                                LocalCacheManager.getInstance(this).addBill(this, bill);
                            }

                        }

                    }
                });


       // SyncDataFor(urlforProfit);


    }

    void SyncDataFor(String url) {
        Observable.fromCallable(() -> {

            try {
                RequestBody body = RequestBody.create(ApiEndPoint.XML, url);
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
                .subscribe((response) -> {
                    if (response.toString().equalsIgnoreCase("false")) {
                        System.out.println("the request got error");
                    } else {
                        String cleanString = response.toString().replaceAll("\r", "").replaceAll("\n", "");
                        System.out.println("result is:" + ConnectionUtil.xmltoJson(cleanString));
                        profitnloss list = (profitnloss) ConnectionUtil.getInistance().getProfitAndLoss(this, ConnectionUtil.xmltoJson(cleanString));
                        System.out.println("result is:" + response + " " + response.toString() + "list:" + list);
                        if (list != null)
                            LocalCacheManager.getInstance(this).addProfitandloss(this, list);


                    }
                });
        syncDataForSalesOrders();
    }

    void syncDataForSalesOrders() {
        Observable.fromCallable(() -> {

            try {
                RequestBody body = RequestBody.create(ApiEndPoint.XML, salesOrdersUrls);
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
                .subscribe((response) -> {
                    if (response.toString().equalsIgnoreCase("false")) {
                        System.out.println("the request got error");
                    } else {
                        String cleanString = response.toString().replaceAll("\r", "").replaceAll("\n", "");
                        System.out.println("result is:" + ConnectionUtil.xmltoJson(cleanString));
                        List<SalesOrders> list = (ArrayList<SalesOrders>) ConnectionUtil.getInistance().getListSalesOrder(ConnectionUtil.xmltoJson(cleanString));
                        System.out.println("result is:" + response + " " + response.toString() + "list:" + list);
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                LocalCacheManager.getInstance(this).addSalesOrders(this, list.get(i));
                            }

                        }

                    }
                });
        syncDataForStock();
    }

    void syncDataForStock() {
        Observable.fromCallable(() -> {

            try {
                RequestBody body = RequestBody.create(ApiEndPoint.XML, stock);
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
                .subscribe((response) -> {
                    if (response.toString().equalsIgnoreCase("false")) {
                        System.out.println("the request got error");
                    } else {
                        String cleanString = response.toString().replaceAll("\r", "").replaceAll("\n", "");
                        System.out.println("result is:" + ConnectionUtil.xmltoJson(cleanString));
                        List<Stock> list = (ArrayList<Stock>) ConnectionUtil.getInistance().getStockList(this, ConnectionUtil.xmltoJson(cleanString));
                        System.out.println("result is:" + response + " " + response.toString() + "list:" + list);
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                LocalCacheManager.getInstance(this).addStock(this, list.get(i));
                            }

                        }

                    }
                });
    }
void setUPButtom()
{

}
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        bottomNavigationView.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }


    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {
        //load profile pic url into ANImageView
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }


    @Override
    public void showAboutFragment() {
        lockDrawer();

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void lockDrawer() {

    }

    @Override
    public void unlockDrawer() {

    }

    @Override
    public void onGetStock(List<Stock> list) {
        System.out.println("the stock data is :" + list);

    }



    @Override
    public void showBillResponse(ArrayList<BillsReceivable> response) {
        // JSON to JsonElement, convert to String later.
        System.out.println("the result  from db is:" + response);
        Gson gson = new Gson();
//        JsonElement json = gson.fromJson(response, JsonElement.class);
//        String result = gson.toJson(json);
        //BillFixed billFixed=new Gson().fromJson(AppController.getInstance().convertToXml(response),BillFixed.class);
        // System.out.println("the result is:"+result);

    }

    @Override
    public void showBillReceivableResponse(BillsReceivable list) {
        System.out.println("the bill response" + list.getBillCl() + "" + list.getBillParty());
    }

    @Override
    public void getResponse(List<SalesVoucher> res) {
        ArrayList<SalesVoucher> listtr= (ArrayList<SalesVoucher>) res;
       for(int i=0;i<listtr.size();i++)
           System.out.print(listtr.get(i).getVoucherNumber());
    }

    @Override
    public void getSalesVoucherResponse(ArrayList<SalesVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertSalesVoucherData(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);

        cn=count;
        getCountInitiialize();
        Log.d(TAG, "getSalesVoucherResponse: Data Inserted Of SAlesVoucher"+arrayList);
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getAmount().length());
                        amount = amount + Double.valueOf(arrayList.get(i).getVoucherAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        String new_amount = String.valueOf(amount);
        int question = String.valueOf(amount).indexOf('E');
        salesAmount = (int) Math.round(amount);
        int num= (int) ((amount* 100D) / 100D);
        System.out.println("numbber:"+num);
        System.out.println("Amount for sales voucher is"+amount);
        System.out.println("Amount for sales voucher is"+salesAmount);


    }

    @Override
    public void getBillPayableResponse(ArrayList<BillsPayable> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertData(arrayList);
        Log.d(TAG, "getBillPayableResponse: Data Inserted Of Payable"+arrayList);
        cn1=count;
        getCountInitiialize();

    }


    @Override
    public void getBillReceivableResponse(ArrayList<BillsReceables> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertBillsReceivable(arrayList);
        Log.d(TAG, "getBillReceivableResponse: Data Inserted Of Receivable"+arrayList);

        cn2=count;
        getCountInitiialize();

    }

    @Override
    public void getPurchaseVoucherResponse(ArrayList<PurchaseVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertPurchaseVoucher(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getAmount().length());
                        amount5 = amount5 + Double.valueOf(arrayList.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        purchaseAmount = (int) Math.round(amount5);
        System.out.println("Amount for sales voucher is"+amount5);
        System.out.println("Amount for purchase voucher is"+purchaseAmount);

        cn3=count;
        getCountInitiialize();
        Log.d(TAG, "getPurchaseVoucherResponse: Data Inserted Of Purchase Voucher"+arrayList);



    }

    @Override
    public void getPaymentVoucherResponse(ArrayList<PaymentVoucher> arrayList,int count) {

        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertPaymentVoucherData(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        cn4=count;
        getCountInitiialize();
        Log.d(TAG, "getPaymentVoucherResponse: Data Inserted Of Payment Voucher"+arrayList);
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getAmount().length());
                        amount1 = amount1 + Double.valueOf(arrayList.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        paymentAmount = (int) Math.round(amount);
        System.out.println("Amount for sales voucher is"+amount);
        System.out.println("Amount for payment voucher is"+paymentAmount);

    }

    @Override
    public void getCreditVoucherResponse(ArrayList<CreditNoteVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertCreditVoucher(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        cn5=count;
        getCountInitiialize();
        Log.d(TAG, "getCreditVoucherResponse: Data Inserted Of Credit Note Voucher"+arrayList);



    }

    @Override
    public void getDebitVoucherResponse(ArrayList<DebitNoteVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertDebitData(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        cn6=count;
        getCountInitiialize();
        Log.d(TAG, "getDebitVoucherResponse: Data Inserted Of Debit Note Voucher"+arrayList);
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getAmount().length());
                        amount = amount + Double.valueOf(arrayList.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        debitAmount = (int) Math.round(amount);
        System.out.println("Amount for sales voucher is"+amount);
        System.out.println("Amount for debitAmount voucher is"+debitAmount);

    }

    @Override
    public void getSalesOrderVoucherResponse(ArrayList<SalesOrderVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertSalesOrderData(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        cn7=count;
        getCountInitiialize();
        Log.d(TAG, "getSalesOrderVoucherResponse: Data Inserted Of Sales Order Voucher"+arrayList);
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getLedgerAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getLedgerAmount().length());
                        amount2 = amount2 + Double.valueOf(arrayList.get(i).getLedger().get(0).getLedgerAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        salesOrderAmount = (int) Math.round(amount2);
        System.out.println("Amount for sales voucher is"+amount2);
        System.out.println("Amount for salesOrderAmount voucher is"+salesOrderAmount);



    }

    @Override
    public void getPurchaseOrderVoucherResponse(ArrayList<PurchaseOrderVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertPurchaseOrder(arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        cn8=count;
        getCountInitiialize();
        Log.d(TAG, "getPurchaseOrderVoucherResponse: Data Inserted Of Purchase Order Voucher"+arrayList);
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getAmount().length());
                        amount3 = amount3 + Double.valueOf(arrayList.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        purchaseOrderAmount = (int) Math.round(amount3);
        System.out.println("Amount for sales voucher is"+amount3);
        System.out.println("Amount for purchaseOrderAmount voucher is"+purchaseOrderAmount);

    }

    @Override
    public void getReceiptVoucherResponse(ArrayList<ReceiptVoucher> arrayList,int count) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertReceiptNote(arrayList);
         for(int i=0;i<arrayList.size();i++)
        {
            String party = arrayList.get(i).getVoucherPartyName();
            finalSet.add(party);
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        if(count==1)
        {


        }
        cn9=count;
        getCountInitiialize();
        for(int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i) != null) {
                if(arrayList.get(i).getLedger()!=null) {
                    if (arrayList.get(i).getLedger().get(0) != null) {
                        int question = arrayList.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (arrayList.get(i).getLedger().get(0).getAmount().length());
                        amount4 = amount4 + Double.valueOf(arrayList.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        receiptAmount = (int) Math.round(amount4);
        System.out.println("Amount for sales voucher is"+amount4);
        System.out.println("Amount for receiptAmount voucher is"+receiptAmount);

    }

    @Override
    public void getProfitAndLossResponse(ArrayList<ProfitAndLoss> arrayList) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertProfitAndLossData(arrayList);
        Log.d(TAG, "getProfitAndLossResponse: Inserted Data Of PNL"+arrayList);
    }

    @Override
    public void getComapnyListResponse(ArrayList<CompanyList> arrayList) {

        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getCompanyNames(arrayList);
        Log.d(TAG, "getComapnyListResponse: Inserted Data of Company List" + arrayList);
        for(int i=0;i<arrayList.size();i++)
        {
            adapterList.add(arrayList.get(i).getCompanyName());
        }
        Log.d(TAG, "getCompanyList: "+adapterList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,adapterList);
        materialSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void getUserProfileResponse(ArrayList<UserProfile> arrayList) {

        Log.d(TAG, "getUserProfileResponse: "+arrayList);

    }

    @Override
    public void getStockItemResponse(ArrayList<StockItem> arrayList) {
        Log.d(TAG, "getStockItemResponse: "+arrayList);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertStockItem(arrayList);
    }

//    @Override
//    public void getStockItemResponse(ArrayList<StockItem> arrayList) {
//        Log.d(TAG, "getStockItemResponse: "+arrayList);
//        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertStockItem(arrayList);
//        Log.d(TAG, "getStockItemResponse: Successfully Inserted StockItem Data");
//    }

    void getCountInitiialize()
    {
        int total_count = cn+cn1+cn2+cn3+cn4+cn5+cn6+cn7+cn8+cn9;
        if(total_count==10)
        {
            getAllData();
            syncData.clearAnimation();
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Sync Completed ",Toast.LENGTH_LONG).show();
                    syncProgress.setVisibility(View.GONE);
                }
            });

        }
    }
    public void getAllData()
    {

        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveReceiptNote(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrievePurchaseOrder(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveSalesOrderData(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveDebitVoucher(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrievePaymentVoucher(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrievePurchaseVoucher(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveReceivable(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getBillPayable(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveSalesVoucherData(this);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCreidtVoucher(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
//
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0),"home",finalSet), DashBoardFragment.TAG)
                .commit();



        setUpNavBar();
        mPresenter.onNavMenuCreated();
//        if( mPresenter.getCompanyName().isEmpty())
//        {
//            mainTakeMeThere.setVisibility(View.VISIBLE);
//        }
//        else {
//            mainTakeMeThere.setVisibility(View.GONE);
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .disallowAddToBackStack()
//                    .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0)), DashBoardFragment.TAG)
//                    .commit();
//        }
        //setupCardContainerView();
    }


    private void setUpNull()
    {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.container, new RobotFragment())
                .commit();
        setUpNavBar();

    }

    private void setUpNavBar() {
        BottomNavigationInflate.setUpNavigation(bottomNavigationView,getpreaperList(0),fragmentManager,finalSet);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home:
//                        bottomNavigationView.setVisibility(View.VISIBLE);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0), "home"), DashBoardFragment.TAG)
//                                .commit();
//                        break;
//
//                    case R.id.client:
//                        bottomNavigationView.setVisibility(View.VISIBLE);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0), "client"), DashBoardFragment.TAG)
//                                .commit();
//                        break;
//
//
//                    case R.id.Logo:
//                        getSupportFragmentManager()
//                        .beginTransaction()
//                        .disallowAddToBackStack()
//                        .replace(R.id.container, UserFragment.newInstance(), UserFragment.TAG)
//                        .commit();
//                        break;
//
//                    case R.id.item:
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .replace(R.id.container, ItemContainer.newInstance(), ItemContainer.TAG)
//                                .commit();
//                            break;
//
//                    case R.id.report:
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .add(R.id.container, GroupFragment.newInstance(), GroupFragment.TAG)
//                                .commit();
//                        break;
//                }
//                return false;
//            }
//        });
            }
//        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
////                switch (item.getItemId()) {
//                    case R.id.home:
//                        item.setCheckable(true);
//                        nothing.setVisibility(View.GONE);
//                        gridLayout.setVisibility(View.VISIBLE);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0),"home"), DashBoardFragment.TAG)
//                                .commit();
//                        break;
//                    case R.id.client:
//                        mainTakeMeThere.setVisibility(View.GONE);
//                        gridLayout.setVisibility(View.VISIBLE);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0),"client"), DashBoardFragment.TAG)
//                                .commit();
//                        nothing.setVisibility(View.GONE);
//
//                        item.setCheckable(true);
//                        System.out.println("R.id.client");
//                        break;
//                    case R.id.item:
//                        mainTakeMeThere.setVisibility(View.GONE);
//                        gridLayout.setVisibility(View.GONE);
//                       nothing.setVisibility(View.VISIBLE);
//                        item.setCheckable(true);
//                        System.out.println("R.id.item");
//                        break;
//                    case R.id.report:
//                        mainTakeMeThere.setVisibility(View.GONE);
//                        gridLayout.setVisibility(View.GONE);
//                        nothing.setVisibility(View.VISIBLE);
//                        item.setCheckable(true);
//                        System.out.println("R.id.report");
//                        break;
//                }
//                return false;
//            }
//        });

    private void temp() {
        ConnectionUtil.getData(this);
        ConnectionUtil.connectTally(url);
        syncData();
    }

    public ArrayList<ItemsModel> getpreaperList(int i) {

        ArrayList<ItemsModel> list = new ArrayList<ItemsModel>();
        list.add(new ItemsModel(R.drawable.ic_sales,"12.5","Sales",125432,"#E53935","12.5%",R.drawable.ic_up,DashType.SALES));
        list.add(new ItemsModel(R.drawable.ic_receivable,"12.5","Receivable",12642,"#F4511E","12.5%",R.drawable.ic_down,DashType.RECEIVABLE));
        list.add(new ItemsModel(R.drawable.ic_payable,"12.5","Payable",225432,"FF9800","12.5%",R.drawable.ic_down,DashType.PAYABLE));
        list.add(new ItemsModel(R.drawable.ic_bank,"12.5","Bank",425432,"#7CB342","12.5%",R.drawable.ic_up,DashType.BANK));
        list.add(new ItemsModel(R.drawable.ic_cash,"12.5","Cash",725432,"#009688","12.5%",R.drawable.ic_down,DashType.CASH));
        list.add(new ItemsModel(R.drawable.ic_profit_n_loss,"12.5","Profit And Loss",432,"#03A9F4","12.5%",R.drawable.ic_down,DashType.PROFIT_LOSS));
        list.add(new ItemsModel(R.drawable.ic_expenses,"12.5","Expenses",25432,"#5C6BC0","12.5%",R.drawable.ic_up,DashType.EXPENSES));
        list.add(new ItemsModel(R.drawable.ic_po,"12.5","PO",90432,"#CDDC39","12.5%",R.drawable.ic_down,DashType.PO));
        list.add(new ItemsModel(R.drawable.ic_so,"12.5","SO",245432,"#FFC107","12.5%",R.drawable.ic_up,DashType.SO));
        list.add(new ItemsModel(R.drawable.ic_purchase,"12.5","Purchase",565432,"#9E9D24","12.5%",R.drawable.ic_up,DashType.PURCHASE));
        list.add(new ItemsModel(R.drawable.ic_stock,"12.5","Stock",895432,"#F4511E","12.5%",R.drawable.ic_down,DashType.STOCK));
        list.add(new ItemsModel(R.drawable.ic_delivery_note,"12.5","Delivery Note",565432,"#CDDC39","12.5%",R.drawable.ic_up,DashType.DELIVERYNOTE));
        list.add(new ItemsModel(R.drawable.ic_receipt_note,"12.5","Receipt Note",895432,"#FFC107","12.5%",R.drawable.ic_down,DashType.RECEIPTNOTE));



        if (i != 0) {
            ArrayList<ItemsModel> itemslist = new ArrayList<ItemsModel>();
            for (int j = 0; j < i; j++) {
                itemslist.add(list.get(i));
            }
            return itemslist;
        }
        return list;
    }
//    private void setupCardContainerView() {
//
//        int screenWidth = ScreenUtils.getScreenWidth(this);
//        int screenHeight = ScreenUtils.getScreenHeight(this);
//
//        mCardsContainerView.getBuilder()
//                .setDisplayViewCount(3)
//                .setHeightSwipeDistFactor(10)
//                .setWidthSwipeDistFactor(5)
//                .setSwipeDecor(new SwipeDecor()
//                        .setViewWidth((int) (0.90 * screenWidth))
//                        .setViewHeight((int) (0.75 * screenHeight))
//                        .setPaddingTop(20)
//                        .setSwipeRotationAngle(10)
//                        .setRelativeScale(0.01f));
//
//        mCardsContainerView.addItemRemoveListener(new ItemRemovedListener() {
//            @Override
//            public void onItemRemoved(int count) {
//                if (count == 0) {
//                    // reload the contents again after 1 sec delay
//                    new Handler(getMainLooper()).postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mPresenter.onCardExhausted();
//                        }
//                    }, 800);
//                }
//            }
//        });
//    }



//    void request()
//    {
//        String urls="http://192.168.1.7:9000"+url;
//        StringRequest req = new StringRequest(Request.Method.POST, urls,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println("refe"+AppController.getInstance().convertToXml(response));
//                        System.out.println("the result is:"+response);
//                       // processData(response);
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // handle error response
//                    }
//                }
//        );
//    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void showRateUsDialog() {
        RateUsDialog.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void openMyFeedActivity() {
        startActivity(FeedActivity.getStartIntent(this));
    }

    @Override
    public void closeNavigationDrawer() {

    }


    @Override
    public void onUsersLoaded(User users) {

    }

    @Override
    public void onUserDeleted() {
        System.out.println("the on UserUpdate() :");
    }

    @Override
    public void onUserAdded() {
        System.out.println("the on UserUpdate() :");
    }

    @Override
    public void onDataNotAvailable() {
        System.out.println("the onDeatcha  :");
    }

    @Override
    public void onUserUpdated() {
        System.out.println("the on UserUpdate() :");
    }

    @Override
    public void onListBillRecable(List<BillsPaybale> list) {

    }



    @Override
    public void onListBillRecableSave() {
        System.out.println("the on get BillSaved :");
    }

    @Override
    public void onProfitandLoss(List<profitnloss> list) {
        System.out.println("Profit and loss:" + list);
    }

    @Override
    public void onSalesOrdersCallBack(List<SalesOrders> list) {
        System.out.println("the list is OncallBack" + list);
    }


    @Override
    public void dataBaseStockList(List<Stock> list) {
        System.out.println("Stock list is:" + list);
    }

    @Override
    public void getCallBack() {

//        getSupportFragmentManager()
//                .beginTransaction()
//                .disallowAddToBackStack()
//                .add(R.id.container, DashBoardFragment.newInstance(getpreaperList(0),"home"), DashBoardFragment.TAG)
//                .commit();

    }


    @Override
    public List<SalesVoucher> getLIst(List<SalesVoucher> salesVouchers) {
        System.out.println(salesVouchers.size());
        return null;
    }


    @Override
    public void getBillsPayableData(List<BillsPayable> list) {

    }

    @Override
    public void getBillsReceivableData(List<BillsReceables> list) {

    }

    @Override
    public void getPurchaseVoucherData(List<PurchaseVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "1: Executed");
    }

    @Override
    public void getSalesVoucherByPartyName(List<SalesVoucher> list) {

    }

    @Override
    public void getSalesVoucherData(List<SalesVoucher> list) {
        double amount = 0;
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));

        }

        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetgetSalesVoucherData: "+companyList);
//        Log.d(TAG, "3: Executed");
    }

    @Override
    public void getPaymentVoucherData(List<PaymentVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetPaymentVoucherData: "+companyList);
//        Log.d(TAG, "4: Executed");
    }

    @Override
    public void getCreditVoucherData(List<CreditNoteVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetgetCreditVoucherData: "+companyList);
//        Log.d(TAG, "5: Executed");
    }

    @Override
    public void getDebitVOucherData(List<DebitNoteVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetgetDebitVOucherData: "+companyList);
//        Log.d(TAG, "6: Executed");
    }

    @Override
    public void getSalesOrderVoucherData(List<SalesOrderVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetgetSalesOrderVoucherData: "+companyList);
//        Log.d(TAG, "7: Executed");
    }

    @Override
    public void getPurchaseOrderVoucherData(List<PurchaseOrderVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetgetPurchaseOrderVoucherData: "+companyList);
//        Log.d(TAG, "8: Executed");
    }

    @Override
    public void getReceiptNoteVoucherData(List<ReceiptVoucher> list) {
        for(int i=0;i<list.size();i++)
        {
            String party = list.get(i).getVoucherPartyName();
            companyList.add(new Client(party));
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).insertComapnyClient(companyList);
        Log.d(TAG, "InsertedgetgetReceiptNoteVoucherData: "+companyList);
        Log.d(TAG, "9: Executed");
    }

    @Override
    public void getProfitAndLossData(List<ProfitAndLoss> list) {

    }

    @Override
    public void getCompanyList(List<CompanyList> list) {
        if(list.size()!=0) {
            for (int i = 0; i < list.size(); i++) {
                adapterList.add(list.get(i).getCompanyName());
            }
            Log.d(TAG, "getCompanyList: " + adapterList);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, adapterList);
            materialSpinner.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        }
        else {
            mPresenter.getCompanyList();
        }
    }

    @Override
    public void getClientAfterDelete(List<CompanyList> list) {
        if(list.size()!=0) {
            for (int i = 0; i < list.size(); i++) {
                adapterList.add(list.get(i).getCompanyName());
            }
            Log.d(TAG, "getCompanyList: " + adapterList);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, adapterList);
            materialSpinner.setAdapter(arrayAdapter);
        }
    }


    @Override
    public void getUserData(List<UserProfile> list) {

    }

    @Override
    public void getSalesQueryData(List<SalesVoucher> list) {

    }

    @Override
    public void getBillsPayableQueryData(List<BillsPayable> list) {

    }

    @Override
    public void getBillsReceivableQueryData(List<BillsReceables> list) {

    }

    @Override
    public void getReceiptQueryData(List<ReceiptVoucher> list) {

    }

    @Override
    public void getDebitNoteQueryData(List<DebitNoteVoucher> list) {

    }

    @Override
    public void getCreditNoteQueryData(List<CreditNoteVoucher> list) {

    }

    @Override
    public void getPaymentQueryData(List<PaymentVoucher> list) {

    }

    @Override
    public void getPurchaseQueryData(List<PurchaseVoucher> list) {

    }

    @Override
    public void getPurchaseOrderQueryData(List<PurchaseOrderVoucher> list) {

    }

    @Override
    public void getSalesOrderQueryData(List<SalesOrderVoucher> list) {

    }

    @Override
    public void getgetClientCompanyData(List<CompanyList> list) {

    }

    @Override
    public void getSalesDateBetween(List<SalesVoucher> list) {

    }



}
