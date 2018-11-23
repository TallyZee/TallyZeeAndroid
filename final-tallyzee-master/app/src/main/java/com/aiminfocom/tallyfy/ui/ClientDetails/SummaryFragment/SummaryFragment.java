package com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
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
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherInfo;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWise;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.utils.AppConstants;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class  SummaryFragment extends BaseFragment implements SummaryFragmentMvp, RoomDbCallback {


    ArrayList<String> amountList;
    ArrayList<String> amountList2 = new ArrayList<>();
    ArrayList<String> amountList3 = new ArrayList<>();
    ArrayList<String> amountList4 = new ArrayList<>();
    ArrayList<String> amountList5 = new ArrayList<>();
    ArrayList<String> amountList6 = new ArrayList<>();
    ArrayList<String> amountList7 = new ArrayList<>();


    @BindView(R.id.card2)
    CardView cardView;

    @BindView(R.id.cardd)
    CardView cardView2;
    @Inject
    SummaryFragmentPresenter<SummaryFragmentMvp> mPresenter;
    @BindView(R.id.list)
    RecyclerView list;
//@BindView(R.id.Tabs)
//    TabLayout tabLayout;

    @BindView(R.id.recyclerViewNew)
    RecyclerView recyclerViewNew;
    @Inject RecyclerAdapter recyclerAdapter;
    @Inject
    ReceiptAdapter receiptAdapter;

    @Inject
    PayableAdapter payableAdapter;

    @Inject
    DebitNoteAdapter debitNoteAdapter;

    @BindView(R.id.purchaseAmount)
    TextView purchaseAmount;

    @BindView(R.id.pendingAmount)
    TextView salesAmount;

    @BindView(R.id.receiptRecyclerView)
    RecyclerView receiptRecyclerView;
    @BindView(R.id.payableRecyclerView)
    RecyclerView payableRecyclerView;
    @BindView(R.id.receivableRecyclerView)
    RecyclerView receivableRecyclerView;
    @BindView(R.id.debitRecyclerView)
    RecyclerView debitRecyclerView;
    @BindView(R.id.creditRecyclerView)
    RecyclerView creditRecyclerView;
    @Inject
    hadderAdapter hadderAdapter;
    @Inject
    CreditNoteAdapter creditNoteAdapter;
    @Inject
    ReceivableAdapter receivableAdapter;
    @Inject
    PaymentAdapter paymentAdapter;
    @BindView(R.id.paymentRecyclerView)
    RecyclerView paymentRecyclerView;
    DashType dashType = DashType.SALES;
    String key;
    //    @BindView(R.id.intent_amount)
//    TextView intentAmount;
    List<UniversalPojo> billList;
    List<UniversalPojo> receiptList;
    List<UniversalPojo> debitList;
    List<UniversalPojo> paymentList;
    List<UniversalPojo> payableList;
    List<UniversalPojo> receivableList;
    List<UniversalPojo> creditList;
    ArrayList<UniversalPojo> newList = new ArrayList<>();
    List<UniversalPojo> fullList = new ArrayList<>();
    public static String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    public static String[] overDue = {"0<",">0", ">30", ">60", ">90", ">120", ">150", ">180"};
    public static final String TAG = "SummaryFragment";

    public static SummaryFragment newInstance(String billsPayable) {
        Bundle args = new Bundle();
        args.putString(AppConstants.PARTY_NAME, billsPayable);
        SummaryFragment fragment = new SummaryFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                key = bundle.getString(AppConstants.PARTY_NAME);
                System.out.println(key + "is the key");
            } else {
                System.out.println(" key is null");
            }
        }

//        setupTabIcons();
//onItemClick();


        getALlData();


        // intentAmount.setText(amount);
        //replace default fragment
        //replaceFragment(new ApplicationFragment());
//setCompanyList();


        return view;
    }

    void getSalesData(String year) {
         billList = new ArrayList<>();
         String date = "";
        int gross = 0;
        for (int j = 0; j < amountList.size(); j++) {
            if (!amountList.get(j).equalsIgnoreCase("0") && !amountList.get(j).isEmpty()
                    && amountList.get(j) != null) {
                gross = gross + Integer.valueOf(amountList.get(j));
            }
        }
        if (gross != 0) {
            billList.add(new UniversalPojo("SALES", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList.size(); i++) {
            if (!amountList.get(i).equalsIgnoreCase("0") && !amountList.get(i).isEmpty()
                    && amountList.get(i) != null) {
                billList.add(new UniversalPojo(months[i] + " 18", amountList.get(i)));
                date = months[i];
            }


        }
        System.out.println(date+"Is the new Date");
        getClick(date,year);

        hadderAdapter.setVoucherGroupListAdapter(billList, getContext());
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setAdapter(hadderAdapter);

    }

    void getDebitNoteData(String year) {
        String date = "";
    debitList = new ArrayList<>();
        int gross = 0;
        for (int j = 0; j < amountList3.size(); j++) {
            if (!amountList3.get(j).equalsIgnoreCase("0") && !amountList3.get(j).isEmpty()
                    && amountList3.get(j) != null) {
                gross = gross + Integer.valueOf(amountList3.get(j));
            }
        }
        if (gross != 0) {
            debitList.add(new UniversalPojo("DEBIT NOTE", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList3.size(); i++) {
            if (!amountList3.get(i).equalsIgnoreCase("0") && !amountList3.get(i).isEmpty()
                    && amountList3.get(i) != null) {
                debitList.add(new UniversalPojo(months[i] + " 18", amountList3.get(i)));
                date = months[i];
            }
        }
        debitNoteAdapter.voucherGroupClick(debitList, getContext());
        debitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(debitRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        debitRecyclerView.addItemDecoration(dividerItemDecoration);
        debitRecyclerView.setAdapter(debitNoteAdapter);
        getDebitClick(date,year);
    }

    void getCreditData() {
        creditList = new ArrayList<>();
        int gross = 0;
        for (int j = 0; j < amountList4.size(); j++) {
            if (!amountList4.get(j).equalsIgnoreCase("0") && !amountList4.get(j).isEmpty()
                    && amountList4.get(j) != null) {
                gross = gross + Integer.valueOf(amountList4.get(j));
            }
        }
        if (gross != 0) {
            creditList.add(new UniversalPojo("CREDIT NOTE", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList4.size(); i++) {
            if (!amountList4.get(i).equalsIgnoreCase("0") && !amountList4.get(i).isEmpty()
                    && amountList4.get(i) != null) {
                creditList.add(new UniversalPojo(months[i] + " 18", amountList4.get(i)));
            }
        }
        creditNoteAdapter.setVoucherGroupListAdapter(creditList, getContext());
        creditRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(creditRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        creditRecyclerView.addItemDecoration(dividerItemDecoration);
        creditRecyclerView.setAdapter(creditNoteAdapter);
        getCreditClick();
    }

    void getCreditClick() {
        creditNoteAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    // System.out.println(TAG+"Response: of the String is:"+response);
                    //startActivity(VouchersItemWise.getStartIntent(getContext(),dashType.toString(),response));
                });
    }


    void getReceiptData(String year) {
 receiptList = new ArrayList<>();
        int gross = 0;
        String date = "";
        for (int j = 0; j < amountList2.size(); j++) {
            if (!amountList2.get(j).equalsIgnoreCase("0") && !amountList2.get(j).isEmpty()
                    && amountList2.get(j) != null) {
                gross = gross + Integer.valueOf(amountList2.get(j));
            }
        }
        if (gross != 0) {
            receiptList.add(new UniversalPojo("RECEIPT", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList2.size(); i++) {
            if (!amountList2.get(i).equalsIgnoreCase("0") && !amountList2.get(i).isEmpty()
                    && amountList2.get(i) != null) {
                receiptList.add(new UniversalPojo(months[i] + " 18", amountList2.get(i)));
                date = months[i];
            }

            System.out.println(date +"from summary fragment");
        }
        receiptAdapter.setCLick(receiptList, getContext());
        receiptRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(receiptRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        receiptRecyclerView.addItemDecoration(dividerItemDecoration2);
        receiptRecyclerView.setAdapter(receiptAdapter);
        System.out.println(year+"from summary fragment");
        getReceiptClick(date,year);
    }

    void getPaymentData(String year) {
        String date = "";
        paymentList = new ArrayList<>();
        int gross = 0;
        for (int j = 0; j < amountList5.size(); j++) {
            if (!amountList5.get(j).equalsIgnoreCase("0") && !amountList5.get(j).isEmpty()
                    && amountList5.get(j) != null) {
                gross = gross + Integer.valueOf(amountList5.get(j));
            }
        }
        if (gross != 0) {
            paymentList.add(new UniversalPojo("PAYMENT", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList5.size(); i++) {
            if (!amountList5.get(i).equalsIgnoreCase("0") && !amountList5.get(i).isEmpty()
                    && amountList5.get(i) != null) {
                paymentList.add(new UniversalPojo(months[i] + " 18", amountList5.get(i)));
                date = months[i];
            }
        }
        paymentAdapter.setVoucherGroupListAdapter(paymentList, getContext());
        paymentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(paymentRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        paymentRecyclerView.addItemDecoration(dividerItemDecoration2);
        paymentRecyclerView.setAdapter(paymentAdapter);
        getPaymentClick(date,year);
    }

    void getPaymentClick(String date,String year) {
        paymentAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    // System.out.println(TAG+"Response: of the String is:"+response);
                    //startActivity(VouchersItemWise.getStartIntent(getContext(),dashType.toString(),response));
                    startActivity(VouchersItemWise.getStartIntent(getContext(),"Payment",key,date,year));
                });
    }

    void getALlData() {
        LocalCacheManager.getInstance(getContext()).salesQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).billsPayableQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).billsReceivableQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).receiptNoteQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).debitNoteQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).creditNoteQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).paymentQueryExecute(key, this);
        LocalCacheManager.getInstance(getContext()).purchaseOrderQueryExecute(key,this);
        LocalCacheManager.getInstance(getContext()).salesOrderQueryExecute(key,this);
    }

    void getPayableData(List<BillsPayable> list) {
        payableList = new ArrayList<>();
        int gross = 0;
        for (int j = 0; j < amountList6.size(); j++) {
            if (!amountList6.get(j).equalsIgnoreCase("0") && !amountList6.get(j).isEmpty()
                    && amountList6.get(j) != null) {
                gross = gross + Integer.valueOf(amountList6.get(j));
            }
        }
        if (gross != 0) {
            payableList.add(new UniversalPojo("PAYABLE", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList6.size(); i++) {
            if (!amountList6.get(i).equalsIgnoreCase("0") && !amountList6.get(i).isEmpty()
                    && amountList6.get(i) != null) {

                payableList.add(new UniversalPojo(overDue[i] , amountList6.get(i)));
            }
        }
        payableAdapter.voucherGroupClick(payableList, getContext());
        payableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(payableRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        payableRecyclerView.addItemDecoration(dividerItemDecoration);
        payableRecyclerView.setAdapter(payableAdapter);
        getPayableClick();
    }

    void getReceivableData() {
        receivableList = new ArrayList<>();
        int gross = 0;
        for (int j = 0; j < amountList7.size(); j++) {
            if (!amountList7.get(j).equalsIgnoreCase("0") && !amountList7.get(j).isEmpty()
                    && amountList7.get(j) != null) {
                gross = gross + Integer.valueOf(amountList7.get(j));
            }
        }
        if (gross != 0) {
            receivableList.add(new UniversalPojo("RECEIVABLE", String.valueOf(gross)));
        }
        for (int i = 0; i < amountList7.size(); i++) {
            if (!amountList7.get(i).equalsIgnoreCase("0") && !amountList7.get(i).isEmpty()
                    && amountList7.get(i) != null) {
                receivableList.add(new UniversalPojo(overDue[i] , amountList7.get(i)));
            }
        }
        receivableAdapter.setCLick(receivableList, getContext());
        receivableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(receivableRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        receivableRecyclerView.addItemDecoration(dividerItemDecoration);
        receivableRecyclerView.setAdapter(receivableAdapter);
        getReceivableClick();
    }

    void getDebitClick(String date,String year) {
        debitNoteAdapter.ResponseClick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    // System.out.println(TAG+"Response: of the String is:"+response);
                    //startActivity(VouchersItemWise.getStartIntent(getContext(),dashType.toString(),response));
                    startActivity(VouchersItemWise.getStartIntent(getContext(),"DELIVERYNOTE",key,date,year));
                });

    }

    void getReceivableClick() {
        receivableAdapter.getReceivableClick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {

                });
    }

    void getClick(String date,String year) {
        hadderAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    // System.out.println(TAG+"Response: of the String is:"+response);
//                    startActivity(VoucherInfo.getStartIntent(getContext(),"SALES",key,date));
                    String new_month = response.getPartyName().substring(0,3);
                    String new_year = "20"+response.getPartyName().substring(4,6);
                    System.out.println(response +"IS THE DATE RESPONSE");
                    System.out.println("PARTY NAME IS"+key);
                    startActivity(VouchersItemWise.getStartIntent(getContext(),"SALES",key,new_month,year));
                });

    }

    void getReceiptClick(String date,String year) {
        receiptAdapter.getListClick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {
                    startActivity(VouchersItemWise.getStartIntent(getContext(),"RECEIPTNOTE",key,date,year));
                });
    }

    void getPayableClick() {
        payableAdapter.ResponseClick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res) ->
                {

                });
    }

    ArrayList<BillsPaybale> getListInitData() {
        ArrayList<BillsPaybale> list = new ArrayList<>();
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Sep 2018", "1280"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Dec", "12980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Nov", "980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Mar", "2980"));
        return list;
    }

    ArrayList<BillsPaybale> getListInitDataForrecipt() {
        ArrayList<BillsPaybale> list = new ArrayList<>();
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Aug 2018", "1280"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Nov", "12980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Sep", "980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", "Oct", "2980"));

        return list;
    }

    ArrayList<BillsPaybale> getListInitDataPayable() {
        ArrayList<BillsPaybale> list = new ArrayList<>();
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", ">180", "1280"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", ">90", "12980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", ">45", "980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", ">30", "2980"));
        list.add(new BillsPaybale("AISPL123SB", "12/04/2017", ">10", "1260"));

        return list;
    }

    ArrayList<ItemsModel> getList1() {
        //    public ItemsModel(int image, String currency, String moduleName, double amount,String color,String percent,int pandl,DashType dashType)
        ArrayList<ItemsModel> list = new ArrayList<>();
        //list.add(new ItemsModel(new ItemsModel(R.drawable.ic_user,"","Last Sales Date")));
        return new ArrayList<>();
    }

    @Override
    public void getBillsPayableData(List<BillsPayable> list) {

    }

    @Override
    public void getBillsReceivableData(List<BillsReceables> list) {

    }

    @Override
    public void getPurchaseVoucherData(List<PurchaseVoucher> list) {

    }

    @Override
    public void getSalesVoucherByPartyName(List<SalesVoucher> list) {

    }

    @Override
    public void getSalesVoucherData(List<SalesVoucher> list) {

    }

    @Override
    public void getPaymentVoucherData(List<PaymentVoucher> list) {

    }

    @Override
    public void getCreditVoucherData(List<CreditNoteVoucher> list) {

    }

    @Override
    public void getDebitVOucherData(List<DebitNoteVoucher> list) {

    }

    @Override
    public void getSalesOrderVoucherData(List<SalesOrderVoucher> list) {

    }

    @Override
    public void getPurchaseOrderVoucherData(List<PurchaseOrderVoucher> list) {

    }

    @Override
    public void getReceiptNoteVoucherData(List<ReceiptVoucher> list) {

    }

    @Override
    public void getProfitAndLossData(List<ProfitAndLoss> list) {

    }



    @Override
    public void getUserData(List<UserProfile> list) {

    }


    void getNewRecyclerViewData()
    {

        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.setCLick(fullList, getContext());
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(payableRecyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        recyclerViewNew.addItemDecoration(dividerItemDecoration);
        recyclerViewNew.setAdapter(recyclerAdapter);
    }

    @Override
    public void getSalesQueryData(List<SalesVoucher> list) throws ParseException {
        ArrayList<Integer> intList = new ArrayList<>();
        String year = "";
        int max=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        System.out.println(list + "is the list");
        Log.d(TAG, "getSalesQueryData: " + list);
         amountList = new ArrayList<>();
        if (list.size() != 0) {
            int Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;

            for (int i = 0; i < list.size(); i++) {
                try {
                    int date = Integer.valueOf(list.get(i).getVoucherDate());
                     year = list.get(i).getVoucherDate().substring(0,4);
                    intList.add(date);
                    if(intList.get(i)>max)
                    {
                        max=intList.get(i);
                    }
                    System.out.println("MAX DATE IS"+max);
//                    Date date1 = new Date(max.toString());
//                    System.out.println(date1+"is the date1");
//                    String new_date = new SimpleDateFormat("yyyy/MMM/dd").format(date1);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalDate date1 = LocalDate.parse(String.valueOf(max), DateTimeFormatter.BASIC_ISO_DATE);
                        System.out.println(date1+"IS THE DATE");
                    }


                }catch (Exception e)
                {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

                String month_date = list.get(i).getVoucherDate().substring(4, 6);
                String full_date = list.get(i).getVoucherDate();
                String Amount = list.get(i).getLedger().get(0).getAmount().replaceAll("-", "");
                System.out.println("AMOUNT IS " + Amount);
                System.out.println(month_date + "IS MONTH_DATE");
                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());
                switch (month_date) {
                    case "01":
                        Jan = Jan + Integer.valueOf(Amount);
                        break;
                    case "02":
                        Feb = Feb + Integer.valueOf(Amount);
                        break;
                    case "03":
                        Mar = Mar + Integer.valueOf(Amount);
                        break;
                    case "04":
                        Apr = Apr + Integer.valueOf(Amount);
                        break;
                    case "05":
                        May = May + Integer.valueOf(Amount);
                        break;
                    case "06":
                        Jun = Jun + Integer.valueOf(Amount);
                        break;
                    case "07":
                        Jul = Jul + Integer.valueOf(Amount);
                        break;
                    case "08":
                        Aug = Aug + Integer.valueOf(Amount);
                        break;
                    case "09":
                        Sep = Sep + Integer.valueOf(Amount);
                        break;
                    case "10":
                        Oct = Oct + Integer.valueOf(Amount);
                        break;
                    case "11":
                        Nov = Nov + Integer.valueOf(Amount);
                        break;
                    case "12":
                        Dec = Dec + Integer.valueOf(Amount);
                        break;
                }

            }
            fullList.add(new UniversalPojo("Last Sales Date",String.valueOf(max)));
            getNewRecyclerViewData();
            amountList.clear();
            amountList.add(String.valueOf(Jan));
            amountList.add(String.valueOf(Feb));
            amountList.add(String.valueOf(Mar));
            amountList.add(String.valueOf(Apr));
            amountList.add(String.valueOf(May));
            amountList.add(String.valueOf(Jun));
            amountList.add(String.valueOf(Jul));
            amountList.add(String.valueOf(Aug));
            amountList.add(String.valueOf(Sep));
            amountList.add(String.valueOf(Oct));
            amountList.add(String.valueOf(Nov));
            amountList.add(String.valueOf(Dec));

            System.out.println(amountList + "IS AMOUNT LIST");
            getSalesData(year);
        }

    }

    void parseDate(String date1, String Amount) {

    }

    @Override
    public void getBillsPayableQueryData(List<BillsPayable> list) throws ParseException {
        Log.d(TAG, "getBillsPayableQueryData: " + list);
        int overDue = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        if (list.size() != 0) {
            int total = 0, total1 = 0, total2 = 0, total3 = 0, total4 = 0, total5 = 0, total6 = 0, total7 = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;
            for (int i = 0; i < list.size(); i++) {
                String month_date1 = list.get(i).getBillDate();
                System.out.println("THIS IS PAYAABLE MONTH" + month_date1);

                Date date = new Date(month_date1);
                System.out.println("THIS IS PAYABLE DATE" + date);
                System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(date));
                String new_date = new SimpleDateFormat("yyyy/MM/dd").format(date);


                String new_date1 = new_date.replaceAll("/", "").substring(4, 6);
                System.out.println(new_date1 + "IS THE DATE FOR SWITCH");
                String Amount = list.get(i).getBillOverDue();
                System.out.println("AMOUNT IS " + Amount);

                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());

            } for(int j=0;j<list.size();j++)
            {
                int overDue1 = Integer.valueOf(list.get(j).getBillOverDue());
                int amount = Integer.valueOf(list.get(j).getBillCl());
                if(overDue1<0)
                {
                    total = total + amount;
                }
                else if(overDue1>0 && overDue1<30)
                {
                    total1 = total1 + amount;
                }
                else if(overDue1>31 && overDue1<60)
                {
                    total2 = total2 + amount;
                }
                else if(overDue1>61&& overDue1<90)
                {
                    total3 = total3 + amount;
                }
                else if(overDue1>91&& overDue1<120)
                {
                    total4 = total4 + amount;
                }
                else   if(overDue1>121&& overDue1<150)
                {
                    total5 = total5 + amount;
                }
                else  if(overDue1>151&& overDue1<180)
                {
                    total6 = total6 + amount;
                }
                else  if(overDue1>180)
                {  total7 = total7 + amount;
                }
            }
            amountList6.clear();
            amountList6.add(String.valueOf(total));
            amountList6.add(String.valueOf(total1));
            amountList6.add(String.valueOf(total2));
            amountList6.add(String.valueOf(total3));
            amountList6.add(String.valueOf(total4));
            amountList6.add(String.valueOf(total5));
            amountList6.add(String.valueOf(total6));
            amountList6.add(String.valueOf(total7));


            System.out.println(amountList + "IS AMOUNT LIST");
            getPayableData(list);
        }
    }

    @Override
    public void getBillsReceivableQueryData(List<BillsReceables> list) {
        Log.d(TAG, "getBillsReceivableQueryData: " + list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        if (list.size() != 0) {
            int total = 0, total1 = 0, total2 = 0, total3 = 0, total4 = 0, total5 = 0, total6 = 0, total7 = 0;
            for (int i = 0; i < list.size(); i++) {
                String month_date1 = list.get(i).getBillDate();
                System.out.println("THIS IS PAYAABLE MONTH" + month_date1);

                Date date = new Date(month_date1);
                System.out.println("THIS IS PAYABLE DATE" + date);
                System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(date));
                String new_date = new SimpleDateFormat("yyyy/MM/dd").format(date);


                String new_date1 = new_date.replaceAll("/", "").substring(4, 6);
                System.out.println(new_date1 + "IS THE DATE FOR SWITCH");
                String Amount = list.get(i).getBillOverDue();
                System.out.println("AMOUNT IS " + Amount);

                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());
            } for(int j=0;j<list.size();j++)
            {
                int overDue1 = Integer.valueOf(list.get(j).getBillOverDue());
                int amount = Integer.valueOf(list.get(j).getBillCl().replaceAll("-",""));
                if(overDue1<0)
                {
                    total = total + amount;
                }
                else if(overDue1>0 && overDue1<30)
                {
                    total1 = total1 + amount;
                }
                else if(overDue1>31 && overDue1<60)
                {
                    total2 = total2 + amount;
                }
                else if(overDue1>61&& overDue1<90)
                {
                    total3 = total3 + amount;
                }
                else if(overDue1>91&& overDue1<120)
                {
                    total4 = total4 + amount;
                }
                else   if(overDue1>121&& overDue1<150)
                {
                    total5 = total5 + amount;
                }
                else  if(overDue1>151&& overDue1<180)
                {
                    total6 = total6 + amount;
                }
                else  if(overDue1>180)
                {  total7 = total7 + amount;
                }
            }
            amountList7.clear();
            amountList7.add(String.valueOf(total));
            amountList7.add(String.valueOf(total1));
            amountList7.add(String.valueOf(total2));
            amountList7.add(String.valueOf(total3));
            amountList7.add(String.valueOf(total4));
            amountList7.add(String.valueOf(total5));
            amountList7.add(String.valueOf(total6));
            amountList7.add(String.valueOf(total7));

            System.out.println(amountList + "IS AMOUNT LIST");
            getReceivableData();
        }
    }


    @Override
    public void getReceiptQueryData(List<ReceiptVoucher> list) {
        ArrayList<Integer> intList = new ArrayList<>();
        int max=0;
        String year = "";
        Log.d(TAG, "getReceiptQueryData: " + list);
        if (list.size() != 0) {
            int Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;

            for (int i = 0; i < list.size(); i++) {
                year = list.get(i).getVoucherDate().substring(0,4);
                try {
                    int date = Integer.valueOf(list.get(i).getVoucherDate());
                    intList.add(date);
                    if(intList.get(i)>max)
                    {
                        max=intList.get(i);
                    }
                    System.out.println("MAX DATE IS"+max);
//                    Date date1 = new Date(max.toString());
//                    System.out.println(date1+"is the date1");
//                    String new_date = new SimpleDateFormat("yyyy/MMM/dd").format(date1);
                    System.out.println("Max date of Receipt is"+max);


                }catch (Exception e)
                {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
                String month_date = list.get(i).getVoucherDate().substring(4, 6);
                String full_date = list.get(i).getVoucherDate();
                String Amount = list.get(i).getLedger().get(0).getAmount().replaceAll("-", "");
                System.out.println("AMOUNT IS " + Amount);
                System.out.println(month_date + "IS MONTH_DATE");
                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());
                switch (month_date) {
                    case "01":
                        Jan = Jan + Integer.valueOf(Amount);
                        break;
                    case "02":
                        Feb = Feb + Integer.valueOf(Amount);
                        break;
                    case "03":
                        Mar = Mar + Integer.valueOf(Amount);
                        break;
                    case "04":
                        Apr = Apr + Integer.valueOf(Amount);
                        break;
                    case "05":
                        May = May + Integer.valueOf(Amount);
                        break;
                    case "06":
                        Jun = Jun + Integer.valueOf(Amount);
                        break;
                    case "07":
                        Jul = Jul + Integer.valueOf(Amount);
                        break;
                    case "08":
                        Aug = Aug + Integer.valueOf(Amount);
                        break;
                    case "09":
                        Sep = Sep + Integer.valueOf(Amount);
                        break;
                    case "10":
                        Oct = Oct + Integer.valueOf(Amount);
                        break;
                    case "11":
                        Nov = Nov + Integer.valueOf(Amount);
                        break;
                    case "12":
                        Dec = Dec + Integer.valueOf(Amount);
                        break;
                }

            }
            fullList.add(new UniversalPojo("Last Receipt Date",String.valueOf(max)));
            getNewRecyclerViewData();
            amountList2.clear();
            amountList2.add(String.valueOf(Jan));
            amountList2.add(String.valueOf(Feb));
            amountList2.add(String.valueOf(Mar));
            amountList2.add(String.valueOf(Apr));
            amountList2.add(String.valueOf(May));
            amountList2.add(String.valueOf(Jun));
            amountList2.add(String.valueOf(Jul));
            amountList2.add(String.valueOf(Aug));
            amountList2.add(String.valueOf(Sep));
            amountList2.add(String.valueOf(Oct));
            amountList2.add(String.valueOf(Nov));
            amountList2.add(String.valueOf(Dec));

            getReceiptData(year);
        }
    }

    @Override
    public void getDebitNoteQueryData(List<DebitNoteVoucher> list) {
        ArrayList<Integer> intList = new ArrayList<>();
        String year = "";
        int max=0;
        Log.d(TAG, "getDebitNoteQueryData: " + list);
        if (list.size() != 0) {
            int Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;

            for (int i = 0; i < list.size(); i++) {
                int date = Integer.valueOf(list.get(i).getVoucherDate());
                intList.add(date);
                if(intList.get(i)>max)
                {
                    max=intList.get(i);
                }
                System.out.println("MAX DATE IS"+max);
                String month_date = list.get(i).getVoucherDate().substring(4, 6);
                String full_date = list.get(i).getVoucherDate();
                year = list.get(i).getVoucherDate().substring(0,4);
                String Amount = list.get(i).getLedger().get(0).getAmount().replaceAll("-", "");
                System.out.println("AMOUNT IS " + Amount);
                fullList.add(new UniversalPojo("Last Debit Date",String.valueOf(max)));
                getNewRecyclerViewData();
                System.out.println(month_date + "IS MONTH_DATE");
                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());
                switch (month_date) {
                    case "01":
                        Jan = Jan + Integer.valueOf(Amount);
                        break;
                    case "02":
                        Feb = Feb + Integer.valueOf(Amount);
                        break;
                    case "03":
                        Mar = Mar + Integer.valueOf(Amount);
                        break;
                    case "04":
                        Apr = Apr + Integer.valueOf(Amount);
                        break;
                    case "05":
                        May = May + Integer.valueOf(Amount);
                        break;
                    case "06":
                        Jun = Jun + Integer.valueOf(Amount);
                        break;
                    case "07":
                        Jul = Jul + Integer.valueOf(Amount);
                        break;
                    case "08":
                        Aug = Aug + Integer.valueOf(Amount);
                        break;
                    case "09":
                        Sep = Sep + Integer.valueOf(Amount);
                        break;
                    case "10":
                        Oct = Oct + Integer.valueOf(Amount);
                        break;
                    case "11":
                        Nov = Nov + Integer.valueOf(Amount);
                        break;
                    case "12":
                        Dec = Dec + Integer.valueOf(Amount);
                        break;
                }

            }
            amountList3.clear();
            amountList3.add(String.valueOf(Jan));
            amountList3.add(String.valueOf(Feb));
            amountList3.add(String.valueOf(Mar));
            amountList3.add(String.valueOf(Apr));
            amountList3.add(String.valueOf(May));
            amountList3.add(String.valueOf(Jun));
            amountList3.add(String.valueOf(Jul));
            amountList3.add(String.valueOf(Aug));
            amountList3.add(String.valueOf(Sep));
            amountList3.add(String.valueOf(Oct));
            amountList3.add(String.valueOf(Nov));
            amountList3.add(String.valueOf(Dec));

            getDebitNoteData(year);
        }
    }

    @Override
    public void getCreditNoteQueryData(List<CreditNoteVoucher> list) {
        ArrayList<Integer> intList = new ArrayList<>();
        Log.d(TAG, "getCreditNoteQueryData: " + list);
        String year = "";
        int max=0;
        if (list.size() != 0) {
            int Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;

            for (int i = 0; i < list.size(); i++) {
                int date = Integer.valueOf(list.get(i).getVoucherDate());
                intList.add(date);
                if(intList.get(i)>max)
                {
                    max=intList.get(i);
                }
                System.out.println("MAX DATE IS"+max);
                String month_date = list.get(i).getVoucherDate().substring(4, 6);
                String full_date = list.get(i).getVoucherDate();
                String Amount = list.get(i).getLedger().get(0).getAmount().replaceAll("-", "");
                System.out.println("AMOUNT IS " + Amount);
                System.out.println(month_date + "IS MONTH_DATE");

                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());
                switch (month_date) {
                    case "01":
                        Jan = Jan + Integer.valueOf(Amount);
                        break;
                    case "02":
                        Feb = Feb + Integer.valueOf(Amount);
                        break;
                    case "03":
                        Mar = Mar + Integer.valueOf(Amount);
                        break;
                    case "04":
                        Apr = Apr + Integer.valueOf(Amount);
                        break;
                    case "05":
                        May = May + Integer.valueOf(Amount);
                        break;
                    case "06":
                        Jun = Jun + Integer.valueOf(Amount);
                        break;
                    case "07":
                        Jul = Jul + Integer.valueOf(Amount);
                        break;
                    case "08":
                        Aug = Aug + Integer.valueOf(Amount);
                        break;
                    case "09":
                        Sep = Sep + Integer.valueOf(Amount);
                        break;
                    case "10":
                        Oct = Oct + Integer.valueOf(Amount);
                        break;
                    case "11":
                        Nov = Nov + Integer.valueOf(Amount);
                        break;
                    case "12":
                        Dec = Dec + Integer.valueOf(Amount);
                        break;
                }

            }
            fullList.add(new UniversalPojo("Last Credit Date",String.valueOf(max)));
            getNewRecyclerViewData();
            amountList4.clear();
            amountList4.add(String.valueOf(Jan));
            amountList4.add(String.valueOf(Feb));
            amountList4.add(String.valueOf(Mar));
            amountList4.add(String.valueOf(Apr));
            amountList4.add(String.valueOf(May));
            amountList4.add(String.valueOf(Jun));
            amountList4.add(String.valueOf(Jul));
            amountList4.add(String.valueOf(Aug));
            amountList4.add(String.valueOf(Sep));
            amountList4.add(String.valueOf(Oct));
            amountList4.add(String.valueOf(Nov));
            amountList4.add(String.valueOf(Dec));

            getCreditData();
        }

    }

    @Override
    public void getPaymentQueryData(List<PaymentVoucher> list) {
        ArrayList<Integer> intList = new ArrayList<>();
        String year = "";
        int max=0;
        if (list.size() != 0) {
            int Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;

            for (int i = 0; i < list.size(); i++) {
                int date = Integer.valueOf(list.get(i).getVoucherDate());
                intList.add(date);
                if(intList.get(i)>max)
                {
                    max=intList.get(i);
                }
                System.out.println("MAX DATE IS"+max);
                String month_date = list.get(i).getVoucherDate().substring(4, 6);
                String full_date = list.get(i).getVoucherDate();
                year = list.get(i).getVoucherDate().substring(0,4);
                String Amount = list.get(i).getLedger().get(0).getAmount().replaceAll("-", "");
                System.out.println("AMOUNT IS " + Amount);
                System.out.println(month_date + "IS MONTH_DATE");

                // parseDate(month_date,list.get(i).getLedger().get(0).getAmount());
                switch (month_date) {
                    case "01":
                        Jan = Jan + Integer.valueOf(Amount);
                        break;
                    case "02":
                        Feb = Feb + Integer.valueOf(Amount);
                        break;
                    case "03":
                        Mar = Mar + Integer.valueOf(Amount);
                        break;
                    case "04":
                        Apr = Apr + Integer.valueOf(Amount);
                        break;
                    case "05":
                        May = May + Integer.valueOf(Amount);
                        break;
                    case "06":
                        Jun = Jun + Integer.valueOf(Amount);
                        break;
                    case "07":
                        Jul = Jul + Integer.valueOf(Amount);
                        break;
                    case "08":
                        Aug = Aug + Integer.valueOf(Amount);
                        break;
                    case "09":
                        Sep = Sep + Integer.valueOf(Amount);
                        break;
                    case "10":
                        Oct = Oct + Integer.valueOf(Amount);
                        break;
                    case "11":
                        Nov = Nov + Integer.valueOf(Amount);
                        break;
                    case "12":
                        Dec = Dec + Integer.valueOf(Amount);
                        break;
                }

            }
            fullList.add(new UniversalPojo("Last Payment Date",String.valueOf(max)));
            getNewRecyclerViewData();
            amountList5.clear();
            amountList5.add(String.valueOf(Jan));
            amountList5.add(String.valueOf(Feb));
            amountList5.add(String.valueOf(Mar));
            amountList5.add(String.valueOf(Apr));
            amountList5.add(String.valueOf(May));
            amountList5.add(String.valueOf(Jun));
            amountList5.add(String.valueOf(Jul));
            amountList5.add(String.valueOf(Aug));
            amountList5.add(String.valueOf(Sep));
            amountList5.add(String.valueOf(Oct));
            amountList5.add(String.valueOf(Nov));
            amountList5.add(String.valueOf(Dec));

        getPaymentData(year);
        }
    }

    @Override
    public void getPurchaseQueryData(List<PurchaseVoucher> list) {

    }

    @Override
    public void getPurchaseOrderQueryData(List<PurchaseOrderVoucher> list) {
        Log.d(TAG, "getPurchaseOrderQueryData: "+list);
        if(list.size()!=0)
        {
            for(int i=0;i<list.size();i++) {
                if(list.get(i)!=null)
                {
                    if(list.get(i).getLedger()!=null)
                    {
                        if(list.get(i).getLedger().get(0)!=null)
                        {
                            String ledger = list.get(i).getLedger().get(0).getAmount();
                            purchaseAmount.setText(ledger);
                        }
                    }
                }

            }
        }
        else
        {
            cardView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void getSalesOrderQueryData(List<SalesOrderVoucher> list) {
        Log.d(TAG, "getSalesOrderQueryData: "+list);
        if(list.size()!=0)
        {
            for(int i=0;i<list.size();i++) {
                if(list.get(i)!=null)
                {
                    if(list.get(i).getLedger()!=null)
                    {
                        if(list.get(i).getLedger().get(0)!=null)
                        {
                            String ledger = list.get(i).getLedger().get(0).getLedgerAmount().replaceAll("-","");
                            salesAmount.setText(ledger);
                        }
                    }
                }

            }
        }
        else
        {
            cardView2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void getgetClientCompanyData(List<CompanyList> list) {

    }

    @Override
    public void getSalesDateBetween(List<SalesVoucher> list) {

    }


//    private void setupTabIcons() {
//        tabLayout.addTab(tabLayout.newTab().setText("Sales"));
//        tabLayout.addTab(tabLayout.newTab().setText("Receipt"));
//        tabLayout.addTab(tabLayout.newTab().setText("Payable"));
////        // clickFragment();
////        tabLayout.getTabAt(0).setIcon(R.drawable.ic_sales);
////        tabLayout.getTabAt(1).setIcon(R.drawable.ic_receipt_note);
////        tabLayout.getTabAt(2).setIcon(R.drawable.ic_payable);
//
//    }
//    void onItemClick()
//    {
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getPosition() == 0) {
//                    dashType=DashType.SALES;
//                    hadderAdapter.setVoucherGroupListAdapter(billList, getContext());
//
//                    list.setLayoutManager(new LinearLayoutManager(getContext()));
//                    list.setAdapter(hadderAdapter);
//                    //replaceFragment(new ApplicationFragment());
//                } else if (tab.getPosition() == 1) {
//                    dashType=DashType.RECEIPTNOTE;
//                    hadderAdapter.setVoucherGroupListAdapter(billList, getContext());
//
//                    list.setLayoutManager(new LinearLayoutManager(getContext()));
//                    list.setAdapter(hadderAdapter);
//                } else {
//                    dashType=DashType.PAYABLE;
//                    hadderAdapter.setVoucherGroupListAdapter(billList, getContext());
//
//                    list.setLayoutManager(new LinearLayoutManager(getContext()));
//                    list.setAdapter(hadderAdapter);
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//    }

}
