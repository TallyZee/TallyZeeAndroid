package com.aiminfocom.tallyfy.ui.DashBoardSpace;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.data.BeanModels.CalendarValues;
import com.aiminfocom.tallyfy.data.BeanModels.DashType;
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
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbCallback;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Group.GroupFragment;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContainer;
import com.aiminfocom.tallyfy.ui.ProfitandLoss.ProfitnLossActivity;
import com.aiminfocom.tallyfy.ui.Users.UserFragment;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWise;
import com.aiminfocom.tallyfy.ui.main.CalenderFilter.ShowCalender;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.DashBoardFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;
import com.aiminfocom.tallyfy.utils.CalenderUtilView;
import com.aiminfocom.tallyfy.utils.CalenderUtilsCal;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.utils.AppConstants;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DashBoardSpaceActivity extends BaseActivity implements DashBoardSpaceMvp, PopupMenu.OnMenuItemClickListener, RoomDbCallback,OncallBack
        ,android.support.v7.widget.SearchView.OnQueryTextListener {


    private static final String TAG = "DashBoardSpaceActivity";
    private BarFragment barFragment;
    @BindView(R.id.list_voucher)
    RecyclerView listVoucher;
    @BindView(R.id.amount)
    TextView mAmount;
    @BindView(R.id.netAmount)
    TextView netAmount;
    @BindView(R.id.view4)
    View view;


    @BindView(R.id.grossAmount)
    TextView grossAmount;
    @BindView(R.id.amountSales)
    TextView sales;
    @BindView(R.id.amountValue)
    TextView returnSales;
    @BindView(R.id.bo)
    ImageView back;
    @BindView(R.id.HadderText)
    TextView hadderText;
    @BindView(R.id.calender)
    ImageView calender;
    @BindView(R.id.parent)
    RelativeLayout parent;
    PieChart mPieChart;
    LineChart mLineChart;
    DashType dashType;
    @BindView(R.id.ic_search)
    android.support.v7.widget.SearchView searchView;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.myProgressBar)
    ProgressBar progressBar;
    FragmentManager fragmentManager = getSupportFragmentManager();
    SpringDotsIndicator springDotsIndicator;
    @Inject
    VoucherAdapter voucherAdapter;
    BarChart mChart;
    ArrayList<UniversalPojo> universalPojos = new ArrayList<>();
    @Inject
    DashBoardSpaceMvpPresenter<DashBoardSpaceMvp> mPresenter;
    private String getDate;
    ArrayList<UniversalPojo> newList = new ArrayList<>();

    public static Intent getStartIntent(Context context, DashType ty) {
        Intent intent = new Intent(context, DashBoardSpaceActivity.class);
        Bundle b = new Bundle();
        b.putString(AppConstants.VALUE, ty.toString());
        intent.putExtra(AppConstants.PRODUCT_VALUE, b);

        // intent.putParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST, list);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash_board_space);
        ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        springDotsIndicator = findViewById(R.id.spring_dots_indicator);
        springDotsIndicator.setViewPager(viewPager);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(DashBoardSpaceActivity.this);
        ArrayList<UniversalPojo> list = new ArrayList<>();
        voucherAdapter.setVoucherGroupListAdapter(list, getApplicationContext());
        searchView.setOnQueryTextListener(this);
//        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        listVoucher.setAdapter(voucherAdapter);
        //  list = getIntent().getParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST);
        String dt = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.VALUE);
        dashType = DashType.valueOf(dt);
        // dashTypeDef(dashType);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId())
//                {
//                    case R.id.home:
//                        fragmentManager
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .replace(R.id.conain, DashBoardFragment.newInstance(getpreaperList(0),"home"),DashBoardFragment.TAG)
//                                .commit();
//                        break;
//                    case R.id.client:
//
//                        fragmentManager
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .replace(R.id.conain, DashBoardFragment.newInstance(getpreaperList(0), "client"), DashBoardFragment.TAG)
//                                .commit();
//                        break;
//
//
//                    case R.id.Logo:
//                        fragmentManager
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .replace(R.id.conain, UserFragment.newInstance(), UserFragment.TAG)
//                                .commit();
//                        break;
//
//                    case R.id.item:
//                        fragmentManager
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .replace(R.id.conain, ItemContainer.newInstance(), ItemContainer.TAG)
//                                .commit();
//                        break;
//
//                    case R.id.report:
//                        fragmentManager
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .replace(R.id.conain, GroupFragment.newInstance(), GroupFragment.TAG)
//                                .commit();
//                        break;
//                }
//                return false;
//            }
//        });

        System.out.println("the dash typeb is" + dashType);
        switch (dashType) {
            case RECEIVABLE:
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveReceivable(this);
                break;

            case PAYABLE:
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getBillPayable(this);
                break;

            case PURCHASE:
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrievePurchaseVoucher(this);
                break;

            case SALES:
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                //  com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPartyNameList(this);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCreidtVoucher(this);

                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveSalesVoucherData(this);
                break;

            case EXPENSES:
                parent.setVisibility(View.GONE);

                progressBar.setVisibility(View.VISIBLE);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrievePaymentVoucher(this);
                break;

            case DELIVERYNOTE:
               // parent.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveDebitVoucher(this);
                break;

            case SO:
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveSalesOrderData(this);
                break;

            case PO:
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                LocalCacheManager.getInstance(getApplicationContext()).retrievePurchaseOrder(this);
                break;

            case RECEIPTNOTE:
                parent.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                LocalCacheManager.getInstance(getApplicationContext()).retrieveReceiptNote(this);
                break;

            case PROFIT_LOSS:
                progressBar.setVisibility(View.VISIBLE);
                LocalCacheManager.getInstance(getApplicationContext()).retrieveProfitNLossData(this);
                break;

        }
        hadderText.setText(dashType.toString());
        double amount = 0;
//        for(int i=0;i<list.size();i++)
//        {
//            System.out.println(list.get(i).getBillCl()+"party: "+list.get(i).getBillParty()+"ref: "+list.get(i).getBillref()+"date: "+list.get(i).getBillDate());
//
//       amount=amount+Double.valueOf(list.get(i).getBillDate().replace("\"", ""));
//        }
        mAmount.setText("12000.00");


    }
void hadderVIsible(DashType dashType)
{
    switch (dashType)
    {
        case SALES:
            break;
    }
}

    void getClick() {
        voucherAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                     System.out.println(TAG+"Response: of the String is:"+response);
                     System.out.println("response of partyname is"+response.getPartyName()+"NEXT"+response.getAmount()) ;
                    startActivity(VouchersItemWise.getStartIntent(this, dashType.toString(), response.getPartyName()));

//               Toast.makeText(this,response.billParty,Toast.LENGTH_LONG).show();
                });
    }

    void getClickSales(HashMap<String, ArrayList<SalesVoucher>> list) {
        voucherAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    // System.out.println(TAG+"Response: of the String is:"+
                    //  startActivity(VouchersItemWise.getStartIntent(this,dashType.toString(),response,voucher.getValue()));
//               Toast.makeText(this,response.billParty,Toast.LENGTH_LONG).show();
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.searchBar);
        // Retrieve the SearchView and plug it into SearchManager
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @OnClick({R.id.bo, R.id.calender})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bo:
                finish();
                break;
            case R.id.calender:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ShowCalender userPopUp = new ShowCalender();
                userPopUp.show(fragmentManager, "Show fragment");
                // getSupportFragmentManager().beginTransaction().show(new ShowCalender());
                break;
        }

    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DashBoardSpaceActivity.class);
        return intent;
    }

    void dashTypeDef(DashType dashType) {
        switch (dashType) {
            case SALES:

                break;
            case RECEIVABLE:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case PAYABLE:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case STOCK:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case DELIVERYNOTE:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case PROFIT_LOSS:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case SO:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case PO:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case CASH:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case BANK:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;
            case RECEIPTNOTE:
                //startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                break;

        }

    }

    void componet() {

    }

    public ArrayList<ItemsModel> getpreaperList(int i) {

        ArrayList<ItemsModel> list = new ArrayList<ItemsModel>();
        list.add(new ItemsModel(R.drawable.ic_sales, "12.5", "Sales", 125432, "#E53935", "12.5%", R.drawable.ic_up, DashType.SALES));
        list.add(new ItemsModel(R.drawable.ic_receivable, "12.5", "Receivable", 12642, "#F4511E", "12.5%", R.drawable.ic_down, DashType.RECEIVABLE));
        list.add(new ItemsModel(R.drawable.ic_payable, "12.5", "Payable", 225432, "FF9800", "12.5%", R.drawable.ic_down, DashType.PAYABLE));
        list.add(new ItemsModel(R.drawable.ic_bank, "12.5", "Bank", 425432, "#7CB342", "12.5%", R.drawable.ic_up, DashType.BANK));
        list.add(new ItemsModel(R.drawable.ic_cash, "12.5", "Cash", 725432, "#009688", "12.5%", R.drawable.ic_down, DashType.CASH));
        list.add(new ItemsModel(R.drawable.ic_profit_n_loss, "12.5", "Profit And Loss", 432, "#03A9F4", "12.5%", R.drawable.ic_down, DashType.PROFIT_LOSS));
        list.add(new ItemsModel(R.drawable.ic_expenses, "12.5", "Expenses", 25432, "#5C6BC0", "12.5%", R.drawable.ic_up, DashType.EXPENSES));
        list.add(new ItemsModel(R.drawable.ic_po, "12.5", "PO", 90432, "#CDDC39", "12.5%", R.drawable.ic_down, DashType.PO));
        list.add(new ItemsModel(R.drawable.ic_so, "12.5", "SO", 245432, "#FFC107", "12.5%", R.drawable.ic_up, DashType.SO));
        list.add(new ItemsModel(R.drawable.ic_purchase, "12.5", "Purchase", 565432, "#9E9D24", "12.5%", R.drawable.ic_up, DashType.PURCHASE));
        list.add(new ItemsModel(R.drawable.ic_stock, "12.5", "Stock", 895432, "#F4511E", "12.5%", R.drawable.ic_down, DashType.STOCK));
        list.add(new ItemsModel(R.drawable.ic_delivery_note, "12.5", "Delivery Note", 565432, "#CDDC39", "12.5%", R.drawable.ic_up, DashType.DELIVERYNOTE));
        list.add(new ItemsModel(R.drawable.ic_receipt_note, "12.5", "Receipt Note", 895432, "#FFC107", "12.5%", R.drawable.ic_down, DashType.RECEIPTNOTE));


        if (i != 0) {
            ArrayList<ItemsModel> itemslist = new ArrayList<ItemsModel>();
            for (int j = 0; j < i; j++) {
                itemslist.add(list.get(i));
            }
            return itemslist;
        }
        return list;
    }

    public void initPiceChart() {

        ArrayList<PieEntry> pieList = new ArrayList<>();
        pieList.add(new PieEntry(4f, 0));
        pieList.add(new PieEntry(8f, 1));
        pieList.add(new PieEntry(6f, 2));
        pieList.add(new PieEntry(12f, 3));

        PieDataSet pieDataSet = new PieDataSet(pieList, "HELLO");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");


        PieData pieData = new PieData(pieDataSet);

    }

    void initChart() {
        mChart = findViewById(R.id.barChart);
//        mLineChart=findViewById(R.id.lineChart);
//        mPieChart=findViewById(R.id.pieChart);
        mChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setDrawGridLines(false);

        mChart.getLegend().setEnabled(false);

        mChart.setFitBars(true);
    }

    private void setData(List<BillsReceables> list, int count) {

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < list.size(); i++) {
            //  int value=list.get(i).getBillCl();
            float val = (float) (Math.random() * count) + 15;
            // String amount=list.get(i).getBillDate();Integer.valueOf(list.get(i).getBillDate().replaceAll("^\"|\"$", ""))
            yVals.add(new BarEntry(i, (int) val));
        }
        BarDataSet set = new BarDataSet(yVals, "Data Set");
        set.setColors(AppConstants.Bars_Colors);
        set.setDrawValues(true);

        BarData data = new BarData(set);

        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(800);
    }

//    @OnClick(R.id.popup)
//    void onPopUp(View view) {
//        PopupMenu popup = new PopupMenu(DashBoardSpaceActivity.this, view);
//        popup.setOnMenuItemClickListener(DashBoardSpaceActivity.this);
//        popup.inflate(R.menu.pop_up);
//        popup.show();
//    }

    @Override
    protected void setUp() {


    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.net:
                // type.setText("Net");
                return true;
            case R.id.gross:
                //type.setText("Gross");
                return true;

            default:
                return false;
        }

    }

    @Override
    public void getBillsPayableData(List<BillsPayable> list) {
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, ArrayList<BillsPayable>> hashMap = new HashMap<>();
        ArrayList<BillsPayable> billsPayablesList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            universalPojoArrayList.add(new UniversalPojo(list.get(i).getBillOverDue()));
            newList.add(new UniversalPojo(list.get(i).getBillParty(), list.get(i).getBillDate()));
            billsPayablesList = new ArrayList<>();
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getBillParty().equalsIgnoreCase(list.get(j).getBillParty())) {
                    billsPayablesList.add(list.get(j));
                    list.remove(j);
                }


            }
            if (billsPayablesList.size() == 0) {
                billsPayablesList.add(list.get(i));
            }
            hashMap.put(list.get(i).getBillParty(), billsPayablesList);

        }
        for (Map.Entry entry : hashMap.entrySet()) {
            ArrayList<BillsPayable> arrayList = (ArrayList<BillsPayable>) entry.getValue();
            Double value = 0.0;
            for (int z = 0; z < arrayList.size(); z++) {
                value = value + Double.valueOf(arrayList.get(z).getBillCl());
            }
            universalPojos.add(new UniversalPojo(String.valueOf(entry.getKey()), String.valueOf(value)));
        }
        CalenderUtilView calenderUtilView = new CalenderUtilView();
        calenderUtilView.getData(universalPojoArrayList, "Bills Payable");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();
//        setAdapter(universalPojos);
        progressBar.setVisibility(View.GONE);
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        System.out.println("DASJBOARD LIST" + newList);
        ShowCalender.listGetter(newList);
        ShowCalender showCalender = new ShowCalender();
        getClick();
    }

    @Override
    public void getBillsReceivableData(List<BillsReceables> list) {
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, ArrayList<BillsReceables>> hashMap = new HashMap<>();
        ArrayList<BillsReceables> billsPayablesList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            universalPojoArrayList.add(new UniversalPojo(list.get(i).getBillOverDue()));
            billsPayablesList = new ArrayList<>();
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getBillParty().equalsIgnoreCase(list.get(j).getBillParty())) {
                    billsPayablesList.add(list.get(j));
                    list.remove(j);
                }
            }
            if (billsPayablesList.size() == 0) {
                billsPayablesList.add(list.get(i));
            }
            hashMap.put(list.get(i).getBillParty(), billsPayablesList);

        }
        for (Map.Entry entry : hashMap.entrySet()) {
            ArrayList<BillsReceables> arrayList = (ArrayList<BillsReceables>) entry.getValue();
            Double value = 0.0;
            for (int z = 0; z < arrayList.size(); z++) {
                value = value + Double.valueOf(arrayList.get(z).getBillCl());
            }


            universalPojos.add(new UniversalPojo(String.valueOf(entry.getKey()), String.valueOf(value)));
        }
        CalenderUtilView calenderUtilView = new CalenderUtilView();
        calenderUtilView.getData(universalPojoArrayList, "Bills Receivable");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();

        progressBar.setVisibility(View.GONE);
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        getClick();

    }

    @Override
    public void getPurchaseVoucherData(List<PurchaseVoucher> list) {
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, ArrayList<PurchaseVoucher>> hashMap = new HashMap<>();
        ArrayList<PurchaseVoucher> billsPayablesList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("THIS IS VOUCHER DATE" + list.get(i).getVoucherDate());
            if (list.get(i) != null)
                if (list.get(i).getLedger() != null)
                    if (list.get(i).getLedger().get(0).getAmount() != null && !list.get(i).getLedger().get(0).getAmount().isEmpty())
                        universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), list.get(i).getLedger().get(0).getAmount()));
            newList.add(new UniversalPojo(list.get(i).getVoucherPartyName(), list.get(i).getVoucherDate()));
            newList.add(new UniversalPojo(list.get(i).getVoucherDate()));

            System.out.println("SIZE IS" + newList.size());
            billsPayablesList = new ArrayList<>();
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
                    billsPayablesList.add(list.get(j));
                    list.remove(j);
                }
            }
            if (billsPayablesList.size() == 0) {
                billsPayablesList.add(list.get(i));
            }
            if (!list.get(i).getVoucherPartyName().isEmpty() || !list.get(i).getVoucherPartyName().equalsIgnoreCase("")) {
                hashMap.put(list.get(i).getVoucherPartyName(), billsPayablesList);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            ArrayList<PurchaseVoucher> arrayList = (ArrayList<PurchaseVoucher>) entry.getValue();
            Double value = 0.0;

            for (int z = 0; z < arrayList.size(); z++) {
                if (arrayList.get(z).getLedger() != null || arrayList.get(z).getLedger() != null) {
                    if (!arrayList.get(z).getLedger().get(0).equals("") || !arrayList.get(z).getLedger().get(0).equals(null))
                        value = value + Double.valueOf(arrayList.get(z).getLedger().get(0).getAmount());
                } else {
                    value = 0.0;
                }
            }

            universalPojos.add(new UniversalPojo(String.valueOf(entry.getKey()), String.valueOf(value)));

        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCreidtVoucher(this);

        CalenderUtilsCal.getCount(universalPojoArrayList, getApplicationContext(), "test");

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();

        progressBar.setVisibility(View.GONE);
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        getClick();
//        ShowCalender showCalender = new ShowCalender();
//        Calendar calendar = Calendar.getInstance();
//
//        showCalender.getDate(newList,calendar.getTime());
        ShowCalender showCalender = new ShowCalender();
        ShowCalender.listGetter(newList);
    }

    @Override
    public void getSalesVoucherByPartyName(List<SalesVoucher> list) {

        // System.out.println(list);
    }

    @Override
    public void getSalesVoucherData(List<SalesVoucher> list) {
        // System.out.println(list);
        //  HashSet<VouchrerDataUniv> listFilter= new HashSet<>(list) ;

        int netamnt = 0;
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<UniversalPojo> universalPojos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate()));
            System.out.println(list.get(i).getVoucherPartyName() + " " + list.get(i).getVoucherAmount());
            newList.add(new UniversalPojo(list.get(i).getVoucherPartyName(), list.get(i).getVoucherDate()));
        }

        Double superAmount = 0.0;
        Double amount = 0.0;
        //System.out.println(listFilter);
        for (int i = 0; i < list.size(); i++) {

            try {
                amount = Double.valueOf(list.get(i).getVoucherAmount().replaceAll("-", ""));

            } catch (Exception e) {
                int question = list.get(i).getVoucherAmount().lastIndexOf('?');
                int length = (list.get(i).getVoucherAmount().length());
                amount = Double.valueOf(list.get(i).getVoucherAmount().substring(question + 1, length).replaceAll("-", ""));
            }

            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
                    try {

                        amount = amount + Double.valueOf(list.get(j).getVoucherAmount().replaceAll("-", ""));
                    } catch (Exception e) {
                        int question = list.get(j).getVoucherAmount().lastIndexOf('?');
                        int length = (list.get(j).getVoucherAmount().length());
                        amount = amount + Double.valueOf(list.get(j).getVoucherAmount().substring(question + 1, length).replaceAll("-", ""));
                    }
                    list.remove(j);
                }

            }
            if (list.get(i).getVoucherInventories() != null) {
                if (list.get(i).getVoucherInventories().get(0).getAmount() != null && !list.get(i).getVoucherInventories().get(0).getAmount().isEmpty()) {
                    try {
                        netamnt = netamnt + Double.valueOf(list.get(i).getVoucherInventories().get(0).getAmount().replace("-", "")).intValue();
                    } catch (Exception e) {
                        int question = list.get(i).getVoucherInventories().get(0).getAmount().lastIndexOf('?');
                        int length = list.get(i).getVoucherInventories().get(0).getAmount().length();
                        netamnt = netamnt + Double.valueOf(list.get(i).getVoucherInventories().get(0).getAmount().substring(question + 1, length).replaceAll("-", "")).intValue();
                    }
                } else
                    netamnt = netamnt + 0;
            } else {
                netamnt = netamnt + 0;
            }

            if (list.get(i).getVoucherPartyName() != null && !list.get(i).getVoucherPartyName().equalsIgnoreCase("") && !list.get(i).getVoucherPartyName().equalsIgnoreCase(" ")) {
                universalPojos.add(new UniversalPojo(list.get(i).getVoucherPartyName(), String.valueOf(amount).substring(0, String.valueOf(amount).indexOf('.') + 2)));
                universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), String.valueOf(amount.intValue())));
                superAmount = superAmount + amount;
            }

        }
        if (netamnt != 0 || superAmount != null) {
            netAmount.setText(String.valueOf(netamnt) + ".00");
            mAmount.setText(String.valueOf(superAmount.intValue()) + ".00");
        }
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCreidtVoucher(this);
        // System.out.println(universalPojos);
        System.out.println(universalPojoArrayList);
        CalenderUtilsCal.getCount(universalPojoArrayList, getApplicationContext(), "test");

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commitAllowingStateLoss();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commitAllowingStateLoss();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commitAllowingStateLoss();
        progressBar.setVisibility(View.GONE);

        // System.out.println();

        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        ShowCalender.listGetter(newList);
        getClick();
//       ,
    }

//    @Override
//    public void getvoucherListName(List<VouchrerDataUniv> list) {
//       // System.out.println(list);
//        HashSet<VouchrerDataUniv> listFilter= new HashSet<>(list) ;
//        HashMap<String, String> hashMap = new HashMap<>();
//        ArrayList<UniversalPojo> universalPojos=new ArrayList<>();
////        for(int i=0;i<list.size();i++)
////        {
////           // System.out.println(list.get(i).getVoucherPartyName()+" "+list.get(i).getVoucherAmount());
////        }
//        Double amount=0.0;
//        System.out.println(listFilter);
//        for (int i = 0; i < list.size(); i++) {
//            try {
//                amount =   Double.valueOf(list.get(i).getVoucherAmount().replaceAll("-",""));
//            }catch (Exception e)
//            {
//                int question = list.get(i).getVoucherAmount().lastIndexOf('?');
//                int length =(list.get(i).getVoucherAmount().length());
//                amount = Double.valueOf(list.get(i).getVoucherAmount().substring(question+1,length).replaceAll("-",""));
//            }
//            for (int j = i + 1; j < list.size(); j++) {
//                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
//                        try {
//                            amount =  amount + Double.valueOf(list.get(j).getVoucherAmount().replaceAll("-",""));
//                        }catch (Exception e)
//                        {
//                            int question = list.get(j).getVoucherAmount().lastIndexOf('?');
//                            int length =(list.get(j).getVoucherAmount().length());
//                            amount = amount + Double.valueOf(list.get(j).getVoucherAmount().substring(question+1,length).replaceAll("-",""));
//                        }
//                    list.remove(j);
//                }
//
//            }
//            universalPojos.add(new UniversalPojo(list.get(i).getVoucherPartyName(),String.valueOf(amount).substring(0,String.valueOf(amount).indexOf('.')+2)));
//        }
//      // System.out.println(universalPojos);
//        progressBar.setVisibility(View.INVISIBLE);
//
//       // System.out.println();
//        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
//        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        listVoucher.setAdapter(voucherAdapter);
//        getClick();
//
//    }

    @Override
    public void getPaymentVoucherData(List<PaymentVoucher> list) {
        System.out.println(list.size());
        ArrayList<ArrayList<PaymentVoucher>> filterVouchers=new ArrayList<>();
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, ArrayList<PaymentVoucher>> hashMap = new HashMap<>();
        ArrayList<PaymentVoucher> paymentList = new ArrayList<>();
        float hadAmount=0.0f;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null)
                if (list.get(i).getLedger() != null)
                    if (list.get(i).getLedger().get(0).getAmount() != null && !list.get(i).getLedger().get(0).getAmount().isEmpty())
                    {
                        hadAmount=hadAmount+Float.parseFloat(list.get(i).getLedger().get(0).getAmount().replace("-",""));
                        universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), list.get(i).getLedger().get(0).getAmount().replace("-","")));

                    }

            paymentList = new ArrayList<>();
            String st1=list.get(i).getVoucherPartyName();
            for (int j = 0; j < list.size(); j++) {
                String st2=list.get(j).getVoucherPartyName();
                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
                    paymentList.add(list.get(j));
                    list.remove(j);
                }


            }
            filterVouchers.add(paymentList);

            hashMap.put(paymentList.get(0).getVoucherPartyName(), paymentList);
//           // System.out.println("the ledger value"+paymentList);
//            if (paymentList.get(0).getVoucherPartyName()!=null && !paymentList.get(0).getVoucherPartyName().equalsIgnoreCase("")&&!paymentList.get(0).getVoucherPartyName().equalsIgnoreCase(" ")) {
//
//            }


        }

        for (ArrayList<PaymentVoucher> filterVoucherslist:filterVouchers) {
            ArrayList<PaymentVoucher> arrayList = filterVoucherslist;
            Float value = 0.0f;

            System.out.println(arrayList);

                        for (int z = 0; z < arrayList.size(); z++) {
                if (arrayList.get(z).getLedger() != null ) {
                    if (!arrayList.get(z).getLedger().get(0).equals("") || !arrayList.get(z).getLedger().get(0).equals(null))
                        value = value + Float.valueOf(arrayList.get(z).getLedger().get(0).getAmount());
                } else {
                    value = 0.0f;
                }
            }
            universalPojos.add(new UniversalPojo(filterVoucherslist.get(0).getVoucherPartyName(), String.valueOf(value)));
            //System.out.println("the ledger value"+paymentList);
        }
//System.out.println("the ledger value"+universalPojos);
//        System.out.println("the ledger value"+universalPojos);
        mAmount.setText(String.valueOf(hadAmount));

        CalenderUtilsCal.getCount(universalPojoArrayList, getApplicationContext(), "test");

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();

        progressBar.setVisibility(View.GONE);
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        getClick();
    }

    @Override
    public void getCreditVoucherData(List<CreditNoteVoucher> list) {
        Double amount = 0.0;
        System.out.println("the size of list of cerid note" + list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLedger() != null) {
                if (list.get(i).getLedger().get(0).getAmount() != null)
                    amount = amount + Double.valueOf(list.get(i).getLedger().get(0).getAmount());
                else
                    amount = amount + 0.0;
            } else {
                amount = amount + 0.0;
            }
        }
        returnSales.setText(String.valueOf(amount));
    }

    @Override
    public void getDebitVOucherData(List<DebitNoteVoucher> list) {
        Double amount = 0.0;
        System.out.println("the size of list of debit note" + list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLedger() != null) {
                if (list.get(i).getLedger().get(0).getAmount() != null)
                    amount = amount + Double.valueOf(list.get(i).getLedger().get(0).getAmount());
                else
                    amount = amount + 0.0;
            } else {
                amount = amount + 0.0;
            }
        }
        returnSales.setText(String.valueOf(amount));
    }

    @Override
    public void getSalesOrderVoucherData(List<SalesOrderVoucher> list) {

        int netamnt = 0;
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<UniversalPojo> universalPojos = new ArrayList<>();
        ArrayList<ReceiptVoucher> salesVoucherArrayList = new ArrayList<>();


        Double superAmount = 0.0;
        Double amount = 0.0;
        //System.out.println(listFilter);
        for (int i = 0; i < list.size(); i++) {


            if (list.get(i).getLedger() != null) {
                try {
                    amount = Double.valueOf(list.get(i).getLedger().get(0).getLedgerAmount().replaceAll("-", ""));

                } catch (Exception e) {
                    int question = list.get(i).getLedger().get(0).getLedgerAmount().lastIndexOf('?');
                    int length = (list.get(i).getLedger().get(0).getLedgerAmount().length());
                    amount = Double.valueOf(list.get(i).getLedger().get(0).getLedgerAmount().substring(question + 1, length).replaceAll("-", ""));
                }
            }
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
                    if (list.get(j).getLedger() != null) {
                        try {

                            amount = amount + Double.valueOf(list.get(j).getLedger().get(0).getLedgerAmount().replaceAll("-", ""));
                        } catch (Exception e) {
                            int question = list.get(j).getLedger().get(0).getLedgerAmount().lastIndexOf('?');
                            int length = (list.get(j).getLedger().get(0).getLedgerAmount().length());
                            amount = amount + Double.valueOf(list.get(j).getLedger().get(0).getLedgerAmount().substring(question + 1, length).replaceAll("-", ""));
                        }
                        list.remove(j);
                    }

                }

            }
            if (list.get(i).getVoucherInventories() != null) {
                if (list.get(i).getVoucherInventories().get(0).getAmount() != null && !list.get(i).getVoucherInventories().get(0).getAmount().isEmpty()) {
                    try {
                        netamnt = netamnt + Double.valueOf(list.get(i).getVoucherInventories().get(0).getAmount().replace("-", "")).intValue();
                    } catch (Exception e) {
                        int question = list.get(i).getVoucherInventories().get(0).getAmount().lastIndexOf('?');
                        int length = list.get(i).getVoucherInventories().get(0).getAmount().length();
                        netamnt = netamnt + Double.valueOf(list.get(i).getVoucherInventories().get(0).getAmount().substring(question + 1, length).replaceAll("-", "")).intValue();
                    }
                } else
                    netamnt = netamnt + 0;
            } else {
                netamnt = netamnt + 0;
            }

            if (list.get(i).getVoucherPartyName() != null && !list.get(i).getVoucherPartyName().equalsIgnoreCase("") && !list.get(i).getVoucherPartyName().equalsIgnoreCase(" ")) {
                universalPojos.add(new UniversalPojo(list.get(i).getVoucherPartyName(), String.valueOf(amount).substring(0, String.valueOf(amount).indexOf('.') + 2)));
                universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), String.valueOf(amount.intValue())));

                superAmount = superAmount + amount;
            }

        }
        netAmount.setText(String.valueOf(netamnt) + ".00");
        mAmount.setText(String.valueOf(superAmount.intValue()) + ".00");
        //  com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCreidtVoucher(this);
        // System.out.println(universalPojos);
        CalenderUtilsCal.getCount(universalPojoArrayList, getApplicationContext(), "test");

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();
        progressBar.setVisibility(View.GONE);

        // System.out.println();
        progressBar.setVisibility(View.GONE);
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        getClick();
    }

    @Override
    public void getPurchaseOrderVoucherData(List<PurchaseOrderVoucher> list) {
        int netamnt = 0;
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<UniversalPojo> universalPojos = new ArrayList<>();
//        for(int i=0;i<list.size();i++)
//        {
//            // universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate()));
//            System.out.println(list.get(i).getVoucherPartyName()+" "+list.get(i).getVoucherAmount());
//        }
        Double superAmount = 0.0;
        Double amount = 0.0;
        //System.out.println(listFilter);
        for (int i = 0; i < list.size(); i++) {


            if (list.get(i).getLedger() != null) {
                try {
                    amount = Double.valueOf(list.get(i).getLedger().get(0).getAmount().replaceAll("-", ""));

                } catch (Exception e) {
                    int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                    int length = (list.get(i).getLedger().get(0).getAmount().length());
                    amount = Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-", ""));
                }
            }
            universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate()));
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
                    if (list.get(j).getLedger() != null) {
                        try {

                            amount = amount + Double.valueOf(list.get(j).getLedger().get(0).getAmount().replaceAll("-", ""));
                        } catch (Exception e) {
                            int question = list.get(j).getLedger().get(0).getAmount().lastIndexOf('?');
                            int length = (list.get(j).getLedger().get(0).getAmount().length());
                            amount = amount + Double.valueOf(list.get(j).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-", ""));
                        }
                        list.remove(j);
                    }

                }

            }
            if (list.get(i).getPurchaseOrderInventoriesLists() != null) {
                if (list.get(i).getPurchaseOrderInventoriesLists().get(0).getAmount() != null && !list.get(i).getPurchaseOrderInventoriesLists().get(0).getAmount().isEmpty()) {
                    try {
                        netamnt = netamnt + Double.valueOf(list.get(i).getPurchaseOrderInventoriesLists().get(0).getAmount().replace("-", "")).intValue();
                    } catch (Exception e) {
                        int question = list.get(i).getPurchaseOrderInventoriesLists().get(0).getAmount().lastIndexOf('?');
                        int length = list.get(i).getPurchaseOrderInventoriesLists().get(0).getAmount().length();
                        netamnt = netamnt + Double.valueOf(list.get(i).getPurchaseOrderInventoriesLists().get(0).getAmount().substring(question + 1, length).replaceAll("-", "")).intValue();
                    }
                } else
                    netamnt = netamnt + 0;
            } else {
                netamnt = netamnt + 0;
            }

            if (list.get(i).getVoucherPartyName() != null && !list.get(i).getVoucherPartyName().equalsIgnoreCase("") && !list.get(i).getVoucherPartyName().equalsIgnoreCase(" ")) {
                universalPojos.add(new UniversalPojo(list.get(i).getVoucherPartyName(), String.valueOf(amount).substring(0, String.valueOf(amount).indexOf('.') + 2)));
                universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), String.valueOf(amount.intValue())));

                superAmount = superAmount + amount;
            }

        }
        netAmount.setText(String.valueOf(netamnt) + ".00");
        mAmount.setText(String.valueOf(superAmount.intValue()) + ".00");
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveDebitVoucher(this);
        // System.out.println(universalPojos);
        CalenderUtilsCal.getCount(universalPojoArrayList, getApplicationContext(), "test");

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();
        progressBar.setVisibility(View.GONE);

        // System.out.println();
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        getClick();
    }

    @Override
    public void getReceiptNoteVoucherData(List<ReceiptVoucher> list) {

        int netamnt = 0;
        ArrayList<UniversalPojo> universalPojoArrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<UniversalPojo> universalPojos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate()));

            if (list.get(i).getLedger() != null)
                System.out.println(list.get(i).getVoucherPartyName() + " " + list.get(i).getLedger().get(0).getAmount());
        }
        Double superAmount = 0.0;
        Double amount = 0.0;
        //System.out.println(listFilter);
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) != null)
                if (list.get(i).getLedger() != null)
                    if (list.get(i).getLedger().get(0).getAmount() != null && !list.get(i).getLedger().get(0).getAmount().isEmpty())
                        universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), list.get(i).getLedger().get(0).getAmount()));

            try {
                amount = Double.valueOf(list.get(i).getLedger().get(0).getAmount().replaceAll("-", ""));

            } catch (Exception e) {
                if (list.get(i) != null ) {
                    if (list.get(i).getLedger() != null ) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount = Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-", ""));
                    }
                }
            }

            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getVoucherPartyName().equalsIgnoreCase(list.get(j).getVoucherPartyName())) {
                    try {

                        amount = amount + Double.valueOf(list.get(j).getLedger().get(0).getAmount().replaceAll("-", ""));
                    } catch (Exception e) {
                        if(list.get(j)!=null)
                        {
                            if(list.get(j).getLedger()!=null)
                            {
                                int question = list.get(j).getLedger().get(0).getAmount().lastIndexOf('?');
                                int length = (list.get(j).getLedger().get(0).getAmount().length());
                                amount = amount + Double.valueOf(list.get(j).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-", ""));
                            }
                        }

                    }
                    list.remove(j);
                }

            }


            if (list.get(i).getVoucherPartyName() != null && !list.get(i).getVoucherPartyName().equalsIgnoreCase("") && !list.get(i).getVoucherPartyName().equalsIgnoreCase(" ")) {
                universalPojos.add(new UniversalPojo(list.get(i).getVoucherPartyName(), String.valueOf(amount).substring(0, String.valueOf(amount).indexOf('.') + 2)));
                universalPojoArrayList.add(new UniversalPojo(list.get(i).getVoucherDate(), String.valueOf(amount.intValue())));

                superAmount = superAmount + amount;
            }

        }
        netAmount.setText(String.valueOf(netamnt) + ".00");
        mAmount.setText(String.valueOf(superAmount.intValue()) + ".00");
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).retrieveCreidtVoucher(this);
        // System.out.println(universalPojos);
        CalenderUtilsCal.getCount(universalPojoArrayList, getApplicationContext(), "test");

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new BarFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new PieFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.myFrameLayout, new LineFragment())
                .commit();
        progressBar.setVisibility(View.GONE);
       // setAdapter(universalPojos);
        // System.out.println();
        voucherAdapter.setVoucherGroupListAdapter(universalPojos, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        voucherAdapter.notifyDataSetChanged();
        getClick();
    }

    @Override
    public void getProfitAndLossData(List<ProfitAndLoss> list) {

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




    void setAdapter(ArrayList<UniversalPojo> arrayList) {
        System.out.println(arrayList);
        voucherAdapter.setVoucherGroupListAdapter(arrayList, getApplicationContext());
        listVoucher.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listVoucher.setAdapter(voucherAdapter);
        getClick();
    }


//    public void setDate(Date date)
//    {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        String dt = simpleDateFormat.format(date);
//        System.out.println("DT IS"+dt);
//        String date1 = String.valueOf(dt).replace("/","");
//        System.out.println("DT2 IS"+date1);
//        for(int j=0;j<universalPojos.size();j++)
//        {
//
//
//            String date2 = universalPojos.get(j).getBillOverdue();
//            System.out.println("Date 2 is"+date2);
//            if(date2.equalsIgnoreCase(date1))
//            {
//                System.out.println("THIS VALUES ARE COMPARED"+ date2);
//            }
//        }
//    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<UniversalPojo> newList = new ArrayList<>();
        for (UniversalPojo pojo : universalPojos) {
            if (pojo.getPartyName().toLowerCase().contains(userInput)) {
                newList.add(pojo);
            }
        }
        voucherAdapter.updateList(newList);
        return true;
    }

    @Override
    public void call(Date date) {

    }

    public void getCalendarUtil() {
//        ArrayList<UniversalPojo> pojo = new ArrayList<>();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//         String  dt = simpleDateFormat.format(date);
//        String  date1 = String.valueOf(dt).replace("/","");
//    System.out.println(date1 +"this is date1");
//        System.out.println(newList.size());
//        for(int i=0;i<newList.size();i++)
//        {
//
//            String getDate = newList.get(i).getPartyName();
//            if(newList.get(i).getAmount().equalsIgnoreCase(date1))
//            {
//                pojo.add(new UniversalPojo(newList.get(i).getPartyName(),newList.get(i).getAmount()));
//
//
//            }
//            else
//            {
//                System.out.println("Not match");
//            }
//        }
//        voucherAdapter.filterByDate(pojo);
//
//
//    }

    }
}

