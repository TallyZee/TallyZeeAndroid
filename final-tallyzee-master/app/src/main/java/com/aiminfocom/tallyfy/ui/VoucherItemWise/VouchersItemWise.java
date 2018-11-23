package com.aiminfocom.tallyfy.ui.VoucherItemWise;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.BeanModels.ValueSales;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DashType;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DateRetrival;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbCallback;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbFindPartyNameInterface;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.SingleCallBack;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherInfo;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.main.CalenderFilter.ShowCalender;
import com.aiminfocom.tallyfy.utils.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VouchersItemWise extends BaseActivity implements VouchersItemWiseMvp,RoomDbFindPartyNameInterface,SingleCallBack,RoomDbCallback,DateRetrival {

    private static final String TAG = "VouchersItemWise";
    @BindView(R.id.HadderText)
    TextView hadderText;
    @BindView(R.id.list_voucher)
    RecyclerView voucherList;
    @BindView(R.id.amountSales)
    TextView amountSales;
    @BindView(R.id.amountValue)
    TextView amountReturn;

    @BindView(R.id.bo)
    ImageView back;

    String partyName;
        Double totalamount;
    @BindView(R.id.calender)
    ImageView calender;
    @BindView(R.id.prieod)
    TextView period;
    String dt;
    String date,year,start_year,end_year,start_date="01";
    @BindView(R.id.amount)
    TextView amount;


    @Inject
    VoucherItemWiseAdapter voucherItemWiseAdapter;
ArrayList<UniversalPojo> filterList=new ArrayList<>();
    ArrayList<SalesVoucher> voucherArrayList;
    int month;
    String check;
    @Inject
    VouchersItemWisePresenterMVp<VouchersItemWiseMvp> mPresenter;
    public static String[] monthsCount = {"0","31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};
    public static Intent getStartIntent(Context context, String ty, String  partyName) {
        Intent intent = new Intent(context, VouchersItemWise.class);
        Bundle b = new Bundle();
        b.putString(AppConstants.VOUCHER_TEXT, ty.toString());
        b.putString(AppConstants.PARTY_NAME,partyName);
       // b.putParcelableArrayList(AppConstants.VOUCHERSFULLLIST,voucherList);
        intent.putExtra(AppConstants.PRODUCT_VALUE, b);
        // intent.putParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST, list);
        return intent;
    }

    public static Intent getStartIntent(Context context, String ty, String  partyName,String date,String year) {
        Intent intent = new Intent(context, VouchersItemWise.class);
        Bundle b = new Bundle();
        b.putString(AppConstants.VOUCHER_TEXT, ty.toString());
        b.putString(AppConstants.PARTY_NAME,partyName);
        b.putString(AppConstants.Date,date);
        b.putString(AppConstants.orderDueDate,year);
        // b.putParcelableArrayList(AppConstants.VOUCHERSFULLLIST,voucherList);
        intent.putExtra(AppConstants.PRODUCT_VALUE, b);
        // intent.putParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST, list);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vouchers_item_wise);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(VouchersItemWise.this);
        dt = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.VOUCHER_TEXT);
        date = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.Date);
        year = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.orderDueDate);
        partyName=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.PARTY_NAME);
        hadderText.setText(partyName);
        System.out.println(date +"is the date");
        if(date!=null ) {
            try {
                Date date1 = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date1);
                System.out.println(month+"before +1");
                month = cal.get(Calendar.MONTH) + 1;
                System.out.println(month + "Is the Month");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String endDate = monthsCount[month];
        System.out.println("THIS IS END DATE"+endDate);
        if(month==10 || month==11 || month==12) {
            start_year = year  + month + start_date;
            end_year = year +  month + endDate;
        }else
        {
            start_year = year + "0" + month + start_date;
            end_year = year + "0" + month + endDate;
        }
        System.out.println("THIS IS start DATE"+start_year);
        System.out.println("THIS IS END DATE"+end_year);
        UniversalPojo billsPaybale=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getParcelable(AppConstants.VOUCHER_LIST);

        System.out.println(billsPaybale+" "+billsPaybale+" "+getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE));
        voucherArrayList=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getParcelableArrayList(AppConstants.VOUCHERSFULLLIST);
        filter(dt,partyName,start_year,end_year);
        System.out.println("PARTY NAME IS"+partyName);
        System.out.println(dt +"is dt"+partyName +"is partyname" +start_year +"is start date" +end_year +"is end date");
        System.out.println(billsPaybale+" "+voucherArrayList);
        System.out.println(dt);



    }
    void filter(String dashType,String partyName,String start_date,String end_date)
    {
        switch (DashType.valueOf(dashType))
        {
            case SALES:
                System.out.print("Sales Test:"+start_date+" "+end_date);
                try
                {
                    Integer.parseInt(start_date);
                    com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).fetchSalesDate(partyName,start_date,end_date,this);

                }catch (Exception e)
                {
                    com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPartyNameList(this,partyName);

                }

                break;
            case RECEIVABLE:

                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPartyNameList(this,partyName);
                break;

            case PAYABLE:
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPartyNameList(this,partyName);
                break;

            case Payment:
                try{
                    Integer.parseInt(start_date);
                    LocalCacheManager.getInstance(getApplicationContext()).fetchPaymentDate(partyName,start_date,end_date,this);
                }catch (Exception e)
                {
                    com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPaymentPartyNameList(this,partyName);
                }
                break;
            case PURCHASE:
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPurchasePartyNameList(this,partyName);
                break;


            case EXPENSES:
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPaymentPartyNameList(this,partyName);
                break;

            case DELIVERYNOTE:
            LocalCacheManager.getInstance(getApplicationContext()).fetchDebitNote(partyName,start_date,end_date,this);
                break;

            case SO:
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getSalesOrderPartyNameList(this,partyName);
                break;

            case PO:
                com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getPurchaseOrderPartyNameList(this,partyName);
                break;

            case RECEIPTNOTE:
                try{
                    Integer.parseInt(start_date);
                    LocalCacheManager.getInstance(getApplicationContext()).fetchReceiptDate(partyName,start_date,end_date,this);
                }catch (Exception e)
                {
                    com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getApplicationContext()).getReciepPartyNameList(this,partyName);
                }

                break;
        }
    }


    @OnClick({R.id.bo,R.id.calender})
void onClick(View view)
{
    switch (view.getId())
    {
        case R.id.bo:
            finish();
            break;
        case R.id.calender:
            FragmentManager fragmentManager = getSupportFragmentManager();
            ShowCalender userPopUp = new ShowCalender();
            userPopUp.show(fragmentManager, "vouchersView");
            break;
    }

}

    ArrayList<BillsPaybale> getListInitData()
    {
        ArrayList<BillsPaybale> list=new ArrayList<>();
        list.add(new BillsPaybale("AISPL123SB","12/04/2017","Aim infocom","12980"));
        list.add(new BillsPaybale("RDBB","01/01/2018","VIkash Lcc.inc data","180"));
        list.add(new BillsPaybale("123SB","12/02/2018","D and C","980"));
        list.add(new BillsPaybale("3SB","12/04/2016","red bull","2980"));
        list.add(new BillsPaybale("PL12","12/03/2018","milton","180"));
        list.add(new BillsPaybale("156","16/02/2017","parx","1280"));
        list.add(new BillsPaybale("564","17/04/2017","venom","9980"));
        list.add(new BillsPaybale("9087","11/03/2017","lefters","112980"));
        list.add(new BillsPaybale("1995","14/04/2017","zumbak","22980"));
        list.add(new BillsPaybale("1994","16/04/2017","retro Fit","4980"));
        list.add(new BillsPaybale("2003","12/08/2018","kunal corp","67980"));
        list.add(new BillsPaybale("456","12/07/2018","wipro","89980"));
        list.add(new BillsPaybale("098","12/07/2018","tech M","12980"));
        return list;
    }

    @Override
    protected void setUp() {

    }

    public void getClick() {
        voucherItemWiseAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response)->{
                     System.out.println("Response: of the String is:"+response);
                    ValueSales.setName(response.getPartyName());
                    ValueSales.setDate(response.getGetBillRef());
                    System.out.println(response.getPartyName()+"IS PARTY "+response.getBillOverdue()+"IS DATE");
                    startActivity(VoucherInfo.getStartIntent(this,dt,response.getPartyName(),response.getGetBillRef()));

                });
    }

    @Override
    public void getvoucherListName(List<SalesVoucher> list) {
        if(list.size()!=0) {
            Log.d(TAG, "getvoucherListName: " + list);
            filterList = new ArrayList<>();
            totalamount = 0.0;
            for (SalesVoucher salesVoucher : list) {
                totalamount = totalamount + Double.valueOf(salesVoucher.getLedger().get(0).getAmount().replace("-", ""));
                filterList.add(new UniversalPojo(salesVoucher.getVoucherPartyName(),salesVoucher.getVoucherNumber(), salesVoucher.getVoucherDate(), salesVoucher.getLedger().get(0).getAmount() ));
            }

            // System.out.println(String.valueOf(totalamount).substring(0,String.valueOf(totalamount).charAt('.')+2));
            //amount.setText(String.valueOf(totalamount).substring(0,String.valueOf(totalamount).charAt('.')+2));
            voucherItemWiseAdapter.setVoucherGroupListAdapter(filterList, this);
            voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            voucherList.setAdapter(voucherItemWiseAdapter);
            amountSales.setText(String.valueOf(totalamount));
            amount.setText(String.valueOf(totalamount));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(voucherList.getContext(),
                    new LinearLayoutManager(this).getOrientation());
            voucherList.addItemDecoration(dividerItemDecoration);
            if (list.get(0) != null) {
                if (list.get(0).getVoucherPartyName() != null) {
                    LocalCacheManager.getInstance(getApplicationContext()).getCeditnotePaymentPartyNameList(this, list.get(0).getVoucherPartyName());
                }
            }
            getClick();
        }

    }

    @Override
    public void getSalesOrdervoucherListName(List<SalesOrderVoucher> list) {
     filterList=new ArrayList<>();
        totalamount=0.0;
        for(SalesOrderVoucher salesVoucher:list)
        {
            if(salesVoucher.getLedger()!=null)
            {
                totalamount=totalamount+Double.valueOf(salesVoucher.getLedger().get(0).getLedgerAmount().replace("-",""));
                filterList.add(new UniversalPojo(salesVoucher.getVoucherPartyName(),salesVoucher.getLedger().get(0).getLedgerAmount(),salesVoucher.getVoucherDate(),salesVoucher.getVoucherNumber()));

            }
                    }

       // amount.setText(String.valueOf(totalamount).substring(0,String.valueOf(totalamount).charAt('.')+2));
        amountSales.setText(String.valueOf(totalamount));
        amount.setText(String.valueOf(totalamount));
        voucherItemWiseAdapter.setVoucherGroupListAdapter(filterList,this);
        voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        voucherList.setAdapter(voucherItemWiseAdapter);
        getClick();



    }

    @Override
    public void getPaymentvoucherListName(List<PaymentVoucher> list) {

        filterList=new ArrayList<>();
        totalamount=0.0;

        for(PaymentVoucher salesVoucher:list)
        {
            if(salesVoucher.getLedger()!=null)
            {
                totalamount=totalamount+Double.valueOf(salesVoucher.getLedger().get(0).getAmount().replace("-",""));
                filterList.add(new UniversalPojo(salesVoucher.getVoucherPartyName(),salesVoucher.getLedger().get(0).getAmount(),salesVoucher.getVoucherDate(),salesVoucher.getVoucherNumber()));
            }
        }

        amountSales.setText(String.valueOf(totalamount));
        amount.setText(String.valueOf(totalamount));
        voucherItemWiseAdapter.setVoucherGroupListAdapter(filterList,this);
        voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        voucherList.setAdapter(voucherItemWiseAdapter);
        getClick();

    }

    @Override
    public void getPurchasevoucherListName(List<PurchaseVoucher> list) {
        filterList=new ArrayList<>();
        totalamount=0.0;

        for(PurchaseVoucher salesVoucher:list)
        {
            if(salesVoucher.getLedger()!=null)
            {
                totalamount=totalamount+Double.valueOf(salesVoucher.getLedger().get(0).getAmount().replace("-",""));
                filterList.add(new UniversalPojo(salesVoucher.getVoucherPartyName(),salesVoucher.getLedger().get(0).getAmount(),salesVoucher.getVoucherDate(),salesVoucher.getVoucherNumber()));

            }
        }

        amountSales.setText(String.valueOf(totalamount));
        amount.setText(String.valueOf(totalamount));
        voucherItemWiseAdapter.setVoucherGroupListAdapter(filterList,this);
        voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        voucherList.setAdapter(voucherItemWiseAdapter);
        getClick();
        LocalCacheManager.getInstance(getApplicationContext()).getDebitNOePaymentPartyNameList(this,list.get(0).getVoucherPartyName());

    }

    @Override
    public void getPurchaseOrdervoucherListName(List<PurchaseOrderVoucher> list) {
        filterList=new ArrayList<>();
        totalamount=0.0;

        for(PurchaseOrderVoucher salesVoucher:list)
        {
            if(salesVoucher.getLedger()!=null)
            {
                totalamount=totalamount+Double.valueOf(salesVoucher.getLedger().get(0).getAmount().replace("-",""));
                filterList.add(new UniversalPojo(salesVoucher.getVoucherPartyName(),salesVoucher.getLedger().get(0).getAmount(),salesVoucher.getVoucherDate(),salesVoucher.getVoucherNumber()));

            }
        }

        amountSales.setText(String.valueOf(totalamount));
        amount.setText(String.valueOf(totalamount));
        voucherItemWiseAdapter.setVoucherGroupListAdapter(filterList,this);
        voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        voucherList.setAdapter(voucherItemWiseAdapter);
        getClick();
    }

    @Override
    public void getReciptvoucherListName(List<ReceiptVoucher> list) {
        filterList=new ArrayList<>();
        totalamount=0.0;

        for(ReceiptVoucher salesVoucher:list)
        {
            if(salesVoucher.getLedger()!=null)
            {
                totalamount=totalamount+Double.valueOf(salesVoucher.getLedger().get(0).getAmount().replace("-",""));
                filterList.add(new UniversalPojo(salesVoucher.getVoucherPartyName(),salesVoucher.getLedger().get(0).getAmount(),salesVoucher.getVoucherDate(),salesVoucher.getVoucherNumber()));

            }
        }

        amountSales.setText(String.valueOf(totalamount));
        amount.setText(String.valueOf(totalamount));
       // LocalCacheManager.getInstance(getApplicationContext()).setFilter(this,"CreditNote",list.get(0).getVoucherPartyName(),list.get(0).getVoucherDate());
        voucherItemWiseAdapter.setVoucherGroupListAdapter(filterList,this);
        voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        voucherList.setAdapter(voucherItemWiseAdapter);
        getClick();
    }

    @Override
    public void getdebitNotevoucherListName(List<DebitNoteVoucher> list) {
        System.out.println(list);
        Double amount=0.0;
        for(int i=0;i<list.size();i++)
        {
            amount=amount+Double.valueOf(list.get(i).getLedger().get(0).getAmount());
        }
        System.out.println("the return value is"+amount);

        amountReturn.setText(String.valueOf(amount));

    }

    @Override
    public void getCreditNotevoucherListName(List<CreditNoteVoucher> list) {
        System.out.println(list);
        Double amount=0.0;
       for(int i=0;i<list.size();i++)
       {
           amount=amount+Double.valueOf(list.get(i).getLedger().get(0).getAmount());
       }
        System.out.println("the return value is"+amount);

       amountReturn.setText(String.valueOf(amount));
    }

    @Override
    public void getvSalesVoucher(SalesVoucher list) {

    }

    @Override
    public void getSalesOrdervoucher(SalesOrderVoucher list) {

    }

    @Override
    public void getPaymentvoucher(PaymentVoucher list) {

    }

    @Override
    public void getPurchasevoucher(PurchaseVoucher list) {

    }

    @Override
    public void getPurchaseOrdervoucher(PurchaseOrderVoucher list) {

    }

    @Override
    public void getReciptvoucher(ReceiptVoucher list) {

    }

    @Override
    public void getdebitNotevoucher(DebitNoteVoucher list) {

    }

    @Override
    public void getCreditNotevoucher(CreditNoteVoucher list) {

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

    @Override
    public void getSalesQueryData(List<SalesVoucher> list) throws ParseException {

    }

    @Override
    public void getBillsPayableQueryData(List<BillsPayable> list) throws ParseException {

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
    public void getSalesDateBetween(List<SalesVoucher> list) throws ParseException {
        ArrayList<UniversalPojo> arrayList = new ArrayList<>();
        if(list.size()!=0)
        {
            Log.d(TAG, "getSalesDateBetween: "+list);
            for(int i=0;i<list.size();i++) {
                String datee = list.get(i).getVoucherDate();
               Date mydate =  new SimpleDateFormat("yyyyMMdd").parse(datee);
               System.out.println("my Date is"+mydate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(mydate);
                String formatedDate = cal.get(Calendar.YEAR) + "" +
                        (cal.get(Calendar.MONTH) + 1) +
                        "" +         cal.get(Calendar.DATE);
                System.out.println(formatedDate +"is the formated date");
                if(formatedDate.length()<=7)
                {

                    String year = formatedDate.substring(0,4);
                    String new_month = "0"+formatedDate.substring(3,4);
                    String new_date = "0"+formatedDate.substring(5,6);
                    System.out.println(year);
                    System.out.println(new_month);
                    System.out.println(new_date);
                    String newFormatedDate = year+new_month+new_date;
                    arrayList.add(new UniversalPojo(list.get(i).getVoucherPartyName(),list.get(i).getVoucherNumber(),newFormatedDate,
                            list.get(i).getVoucherAmount()));

                }
                else
                {
                    arrayList.add(new UniversalPojo(list.get(i).getVoucherPartyName(),list.get(i).getVoucherNumber(),formatedDate,
                            list.get(i).getVoucherAmount()));
                }

            }
            Log.d(TAG, "getSalesDateBetween: "+arrayList);

            voucherItemWiseAdapter.setVoucherGroupListAdapter(arrayList,this);
            DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(voucherList.getContext(),
                    new LinearLayoutManager(getApplicationContext()).getOrientation());
            voucherList.addItemDecoration(dividerItemDecoration2);
            voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            voucherList.setAdapter(voucherItemWiseAdapter);
            getClick();
        }
        else
        {
            Log.d(TAG, "getSalesDateBetween: EMPTy");
        }
    }

    @Override
    public void getReceiptDateBetween(List<ReceiptVoucher> list) {
        Log.d(TAG, "getReceiptDateBetween: "+list);
        ArrayList<UniversalPojo> arrayList = new ArrayList<>();
        if(list.size()!=0)
        {
            Log.d(TAG, "getReceiptDateBetween: "+list);
            for(int i=0;i<list.size();i++) {
                arrayList.add(new UniversalPojo(list.get(i).getVoucherPartyName(),list.get(i).getLedger().get(0).getAmount(),
                        list.get(i).getVoucherDate()));
            }
            Log.d(TAG, "getReceiptDateBetween: "+arrayList);
            voucherItemWiseAdapter.setVoucherGroupListAdapter(arrayList,this);
            DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(voucherList.getContext(),
                    new LinearLayoutManager(getApplicationContext()).getOrientation());
            voucherList.addItemDecoration(dividerItemDecoration2);
            voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            voucherList.setAdapter(voucherItemWiseAdapter);
            getClick();
        }
        else
        {
            Log.d(TAG, "getReceiptDateBetween: EMPTy");
        }
    }

    @Override
    public void getPaymentDateBetween(List<PaymentVoucher> list) {
        Log.d(TAG, "getReceiptDateBetween: " + list);
        ArrayList<UniversalPojo> arrayList = new ArrayList<>();
        if (list.size() != 0) {
            Log.d(TAG, "getReceiptDateBetween: " + list);
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(new UniversalPojo(list.get(i).getVoucherPartyName(), list.get(i).getLedger().get(0).getAmount(),
                        list.get(i).getVoucherDate()));
            }
            Log.d(TAG, "getReceiptDateBetween: " + arrayList);
            voucherItemWiseAdapter.setVoucherGroupListAdapter(arrayList, this);
            voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            voucherList.setAdapter(voucherItemWiseAdapter);
            getClick();
        }
    }

    @Override
    public void getDebitDateBetween(List<DebitNoteVoucher> list) {
        Log.d(TAG, "getReceiptDateBetween: " + list);
        ArrayList<UniversalPojo> arrayList = new ArrayList<>();
        if (list.size() != 0) {
            Log.d(TAG, "getReceiptDateBetween: " + list);
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(new UniversalPojo(list.get(i).getVoucherPartyName(), list.get(i).getLedger().get(0).getAmount(),
                        list.get(i).getVoucherDate()));
            }
            Log.d(TAG, "getReceiptDateBetween: " + arrayList);
            voucherItemWiseAdapter.setVoucherGroupListAdapter(arrayList, this);
            voucherList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            voucherList.setAdapter(voucherItemWiseAdapter);
            getClick();
        }
    }


}
