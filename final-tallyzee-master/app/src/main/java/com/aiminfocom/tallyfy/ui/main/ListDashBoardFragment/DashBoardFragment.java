package com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.DashType;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfinAndLoss;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.DatabaseCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetails;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.ClientAdapter;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceActivity;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.ProfitandLoss.ProfitnLossActivity;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.ui.main.NoCompanyFragment;
import com.aiminfocom.tallyfy.ui.main.NullFragment.RobotFragment;
import com.aiminfocom.tallyfy.utils.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.aiminfocom.tallyfy.ui.main.MainActivity.roomDatabaseHelper;


public class DashBoardFragment extends BaseFragment implements DashBoardMvpView, DatabaseCallback,RoomDbCallback,
        SearchView.OnQueryTextListener{
    public static final String TAG = "DashBoardFragment";


    @Inject
    DashBoardPresenter<DashBoardMvpView> mPresenter;
    List<BillsReceables> billList = new ArrayList<>();
    //    @BindView(R.id.list_voucher_list)
//    RecyclerView listVoucher;
    @Inject
    DataAdapter dataAdapter;
    ArrayList<ItemsModel> list;
    @BindView(R.id.list_dash)
    RecyclerView dashList;
    @Inject
    ClientAdapter clientAdapter;
//    @BindView(R.id.barr)
//    ProgressBar barr;
    @BindView(R.id.list_newdash)
    RecyclerView child_recyclerview;
    @Inject
    CustomAdapter customAdapter;
    double amount = 0,amount1=0,amount2=0,amount3=0,amount4=0,amount5=0,amount6=0,amount7=0,amount8=0;
    int salesAmount = 0,purchaseAmount=0,debitAmount=0,paymentAmount=0,salesOrderAmount=0,purchaseOrderAmount=0,receiptAmount=0,receivableAmount=0,payableAmount=0;
    ArrayList<String> finalSet = new ArrayList<>();
    ArrayList<BillsPaybale> listData=new ArrayList<BillsPaybale>();


    public static DashBoardFragment newInstance(ArrayList<ItemsModel> list,String type,HashSet<String> hashSet) {
        Bundle args = new Bundle();
       // args.putParcelableArrayList(AppConstants.VOUCHER_LIST, list);
        args.putString("fragment_type",type);
        ArrayList<String> arrayList = new ArrayList<>(hashSet);
        args.putStringArrayList("hashset",  arrayList);
        DashBoardFragment fragment = new DashBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

       // list = getArguments().getParcelableArrayList(AppConstants.VOUCHER_LIST);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            SyncData();
            //LocalCacheManager.getInstance(this.getBaseActivity()).getProfitandloss(this);
            LocalCacheManager.getInstance(this.getBaseActivity()).getListBillsReceivable(this);
            String type=getArguments().getString("fragment_type").toString();

//            finalSet =getArguments().getStringArrayList("hashset");
            System.out.println("type of string"+type);
            mPresenter.onAttach(this);
            ArrayList<ItemsModel> itemsModels=initData();
//            CustomGrid customGridAdapter=new CustomGrid(getBaseActivity(),itemsModels);
//            gridView.setAdapter(customGridAdapter);
//            onClickGrid();
                if(type.equals("home"))
                {

                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveSalesVoucherData(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrievePaymentVoucher(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveSalesVoucherData(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveDebitVoucher(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrievePurchaseOrder(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrievePurchaseVoucher(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveSalesOrderData(this);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveReceiptNote(this);
                            MainActivity.searchView.setVisibility(View.INVISIBLE);
                            dataAdapter.setVoucherGroupListAdapter(initData(),getBaseActivity());
                        dashList.setLayoutManager(new GridLayoutManager(getContext(),3));
                        dashList.setAdapter(dataAdapter);

//                    child_recyclerview.setItemAnimator(new SlideInUpAnimator());
//                    dashList.setNestedScrollingEnabled(false);
//                    child_recyclerview.setNestedScrollingEnabled(false);
                    getClick();
                }else if(type.equals("client"))
                {
                   MainActivity.searchView.setVisibility(View.VISIBLE);
                   MainActivity.searchView.setOnQueryTextListener(this);
                    clientAdapter.setVoucherGroupListAdapter(new ArrayList<>(), getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseActivity());
                    child_recyclerview.setLayoutManager(linearLayoutManager);
                    child_recyclerview.setAdapter(clientAdapter);
//                    LinearLayoutManager llm = new LinearLayoutManager(this);
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    list.setLayoutManager(llm);
//                    list.setAdapter( adapter );
                    //clientAdapter.notifyDataSetChanged();
// barr.setVisibility(View.VISIBLE);
                 // client_list.setVisibility(View.VISIBLE);

                   // com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveClientList(this);
//                    child_recyclerview.setItemAnimator(new SlideInUpAnimator());







//                    else
//                    {
//                        Log.d(TAG, "ArrayList: "+finalSet.size());
//                        getChildFragmentManager()
//                                .beginTransaction()
//                                .disallowAddToBackStack()
//                                .add(R.id.dashLayout,new RobotFragment())
//                                .commit();
//                    }



                }

        }
        return view;
    }



    void getClickClientList()
    {
        clientAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response)->{
                    System.out.println(TAG+"Response: of the String is:"+response);
                    startActivity(ClientDetails.getStartIntent(getContext(), response));

                });
    }

    void getClick()
    {

        dataAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response)->{
                    System.out.println(TAG+"Response: of the String is:"+response);
                    switch (response.getDashType())
                    {
                        case SALES:
                           // MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            mPresenter.onDetach();
                            break;
                        case RECEIVABLE:
                          //  MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;
                        case PAYABLE:
                           // MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                        break;
                        case STOCK:
                           //// MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                        break;
                        case DELIVERYNOTE:
                           // MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;
                        case PROFIT_LOSS:
                            //MainActivity.progressBar.setVisibility(View.VISIBLE);
                            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveProfitNLossData(this);
                            break;
                        case SO:
                           // MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;
                        case PO:
                            //MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;
                        case CASH:
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;
                        case BANK:
                            //MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;
                        case RECEIPTNOTE:
                            //MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                                break;

                        case PURCHASE:
                            //MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;

                        case EXPENSES:
                            //MainActivity.progressBar.setVisibility(View.VISIBLE);
                            startActivity(DashBoardSpaceActivity.getStartIntent(getContext(), response.getDashType()));
                            break;

                    }

                });



    }

    ArrayList<ItemsModel> initData()
    {
        ArrayList<ItemsModel> itemsModels=new ArrayList<ItemsModel>();
//        1.  E53935
//        2.  F4511E
//        3.  FF9800
//        4.  7CB342
//        5.  009688
//        6.  03A9F4
//        7.  5C6BC0
//        8.  CDDC39
//        9.  FFC107
//        10. 9E9D24
      //  #8BC34A #FFA726 #F44336 #FF5722 #26A69A #64B5F6 #7986CB #D4E157 #FFCA28 #AFB42B #28B463 #273746 #7B241C #616A6B
        //public ItemsModel(int image, String currency, String moduleName, double amount)
        itemsModels.add(new ItemsModel(R.drawable.ic_sales,"12.5","Sales",725432,"#8BC34A","12.5%",R.drawable.ic_up,DashType.SALES));
        itemsModels.add(new ItemsModel(R.drawable.ic_receivable,"12.5","Receivable",725432,"#FFA726","12.5%",R.drawable.ic_down,DashType.RECEIVABLE));
        itemsModels.add(new ItemsModel(R.drawable.ic_pay,"12.5","Payable",725432,"#F44336","12.5%",R.drawable.ic_down,DashType.PAYABLE));
        itemsModels.add(new ItemsModel(R.drawable.ic_choices,"12.5","Cash and Bank",725432,"#26A69A","12.5%",R.drawable.ic_down,DashType.CASH));
        itemsModels.add(new ItemsModel(R.drawable.ic_profit_n_loss,"12.5","Profit And Loss",432,"#64B5F6","12.5%",R.drawable.ic_down,DashType.PROFIT_LOSS));
        itemsModels.add(new ItemsModel(R.drawable.ic_expenses,"12.5","Expenses",725432,"#7986CB","12.5%",R.drawable.ic_up,DashType.EXPENSES));
        itemsModels.add(new ItemsModel(R.drawable.ic_sales_order,"12.5","PO",725432,"#D4E157","12.5%",R.drawable.ic_down,DashType.PO));
        itemsModels.add(new ItemsModel(R.drawable.ic_choices,"12.5","SO",725432,"#FFCA28","12.5%",R.drawable.ic_up,DashType.SO));
        itemsModels.add(new ItemsModel(R.drawable.ic_purchase,"12.5","Purchase",725432,"#AFB42B","12.5%",R.drawable.ic_up,DashType.PURCHASE));
        itemsModels.add(new ItemsModel(R.drawable.ic_stock,"12.5","Stock",895432,"#28B463","12.5%",R.drawable.ic_down,DashType.STOCK));
        itemsModels.add(new ItemsModel(R.drawable.ic_delivery_note,"12.5","Delivery Note",725432,"#273746","12.5%",R.drawable.ic_up,DashType.DELIVERYNOTE));
        itemsModels.add(new ItemsModel(R.drawable.ic_receipt_note,"12.5","Receipt Note",725432,"#7B241C","12.5%",R.drawable.ic_down,DashType.RECEIPTNOTE));

        return  itemsModels;
    }




    private void SyncData() {

    }

    @Override
    protected void setUp(View view) {


    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onUsersLoaded(User users) {

    }


    void initArrayList(int an)
    {

    }

    @Override
    public void onUserDeleted() {

    }

    @Override
    public void onUserAdded() {

    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onUserUpdated() {

    }

    @Override
    public void onListBillRecable(List<BillsPaybale> list) {
        double amount = 0;
        for (int i = 0; i < list.size(); i++) {
            BillsPaybale billsReceivable = list.get(i);
            System.out.println("the data:" + billsReceivable.getBillCl());
            amount = amount + Double.valueOf(billsReceivable.getBillCl());
        }
        for(int i=0;i<list.size();i++)
        {
            listData.add(list.get(i));
        }

        System.out.println(amount + " list of the current bill is:" + list);
    }

    @Override
    public void onListBillRecableSave() {

    }

    @Override
    public void onProfitandLoss(List<profitnloss> list) {
        System.out.println("the list is:" + list);
    }

    @Override
    public void onSalesOrdersCallBack(List<SalesOrders> list) {

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void getBillsPayableData(List<BillsPayable> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getBillCl()!=null) {

                        int question = list.get(i).getBillCl().lastIndexOf('?');
                        int length = (list.get(i).getBillCl().length());
                        amount6 = amount6 + Double.valueOf(list.get(i).getBillCl().substring(question + 1, length).replaceAll("-",""));

                }
            }
        }
        payableAmount = (int) Math.round(amount6);
        System.out.println("Amount for payable voucher is"+amount6);
        System.out.println("Amount for payable voucher is"+payableAmount);
    }

    @Override
    public void getBillsReceivableData(List<BillsReceables> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getBillCl()!=null) {

                    int question = list.get(i).getBillCl().lastIndexOf('?');
                    int length = (list.get(i).getBillCl().length());
                    amount7 = amount7 + Double.valueOf(list.get(i).getBillCl().substring(question + 1, length).replaceAll("-",""));

                }
            }
        }
        receivableAmount = (int) Math.round(amount7);
        System.out.println("Amount for receivable voucher is"+amount7);
        System.out.println("Amount for receivable voucher is"+receivableAmount);
    }

    @Override
    public void getPurchaseVoucherData(List<PurchaseVoucher> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount5 = amount5 + Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        purchaseAmount = (int) Math.round(amount5);
        System.out.println("Amount for purchase voucher is"+amount5);
        System.out.println("Amount for purchase voucher is"+purchaseAmount);
    }

//    @Override
//    public void getPurchaseVoucherData(List<PurchaseVoucher> list) {
//        for(int i=0;i<list.size();i++)
//        {
//            String party = list.get(i).getVoucherPartyName();
//            finalSet.add(party);
//        }




    @Override
    public void getSalesVoucherByPartyName(List<SalesVoucher> list) {

    }

    @Override
    public void getSalesVoucherData(List<SalesVoucher> list) {


        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount = amount + Double.valueOf(list.get(i).getVoucherAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        String new_amount = String.valueOf(amount);
        int question = String.valueOf(amount).indexOf('E');
        salesAmount = (int) Math.round(amount);
        System.out.println("Amount for sales voucher is"+amount);
        System.out.println("Amount for sales voucher is"+salesAmount);

//        for(int i=0;i<list.size();i++)
//        {
//            String party = list.get(i).getVoucherPartyName();
//            finalSet.add(party);
//        }
//        Log.d(TAG, "getSalesVoucherData: "+finalSet);
    }

    @Override
    public void getPaymentVoucherData(List<PaymentVoucher> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount1 = amount1 + Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        paymentAmount = (int) Math.round(amount);
        System.out.println("Amount for payment voucher is"+amount);
        System.out.println("Amount for payment voucher is"+paymentAmount);
    }

    @Override
    public void getCreditVoucherData(List<CreditNoteVoucher> list) {
//        for(int i=0;i<list.size();i++)
//        {
//            String party = list.get(i).getVoucherPartyName();
//            finalSet.add(party);
//        }
//
//        Log.d(TAG, "getCreditVoucherData: "+finalSet);
    }

    @Override
    public void getDebitVOucherData(List<DebitNoteVoucher> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount = amount + Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        debitAmount = (int) Math.round(amount);
        System.out.println("Amount for debitAmount voucher is"+amount);
        System.out.println("Amount for debitAmount voucher is"+debitAmount);
    }

    @Override
    public void getSalesOrderVoucherData(List<SalesOrderVoucher> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getLedgerAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getLedgerAmount().length());
                        amount2 = amount2 + Double.valueOf(list.get(i).getLedger().get(0).getLedgerAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        salesOrderAmount = (int) Math.round(amount2);
        System.out.println("Amount for salesOrderAmount voucher is"+amount2);
        System.out.println("Amount for salesOrderAmount voucher is"+salesOrderAmount);

    }

    @Override
    public void getPurchaseOrderVoucherData(List<PurchaseOrderVoucher> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount3 = amount3 + Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        purchaseOrderAmount = (int) Math.round(amount3);
        System.out.println("Amount for purchaseOrderAmount voucher is"+amount3);
        System.out.println("Amount for purchaseOrderAmount voucher is"+purchaseOrderAmount);
    }

    @Override
    public void getReceiptNoteVoucherData(List<ReceiptVoucher> list) {
        for(int i=0;i<list.size();i++) {
            if (list.get(i) != null) {
                if(list.get(i).getLedger()!=null) {
                    if (list.get(i).getLedger().get(0) != null) {
                        int question = list.get(i).getLedger().get(0).getAmount().lastIndexOf('?');
                        int length = (list.get(i).getLedger().get(0).getAmount().length());
                        amount4 = amount4 + Double.valueOf(list.get(i).getLedger().get(0).getAmount().substring(question + 1, length).replaceAll("-",""));

                    }
                }
            }
        }
        receiptAmount = (int) Math.round(amount4);
        System.out.println("Amount for receiptAmount voucher is"+amount4);
        System.out.println("Amount for receiptAmount voucher is"+receiptAmount);
    }

    @Override
    public void getProfitAndLossData(List<ProfitAndLoss> list) {
        getProfitAndLOssClick(list);
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
//        Log.d(TAG, "getgetClientCompanyData: "+list);
//        child_recyclerview.setVisibility(View.VISIBLE);
//        clientAdapter.setVoucherGroupListAdapter(list, getContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        child_recyclerview.setLayoutManager(linearLayoutManager);
//        child_recyclerview.setAdapter(clientAdapter);
//        clientAdapter.notifyDataSetChanged();
    //getClickClientList();
    }

    @Override
    public void getSalesDateBetween(List<SalesVoucher> list) {

    }




    void getProfitAndLOssClick(List<ProfitAndLoss> list)
    {
        Intent intent = new Intent(getContext(), ProfitnLossActivity.class);
        intent.putParcelableArrayListExtra("ArrayList", (ArrayList<? extends Parcelable>) list);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        List<CompanyList> newList = new ArrayList<>();
        for (String pojo : finalSet) {
            if (pojo.toLowerCase().contains(userInput)) {
                newList.add(new CompanyList(pojo));
            }
//            else if(!pojo.toLowerCase().contains(userInput))
//            {
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.dashLayout,new NoCompanyFragment())
//                        .disallowAddToBackStack()
//                        .commit();
//                clientAdapter.notifyDataSetChanged();
//            }
        }
       // clientAdapter.updateList(newList);
        return true;
    }
}

