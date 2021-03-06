package com.aiminfocom.tallyfy.ui.ClientDetails.PurchaseFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
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
import com.aiminfocom.tallyfy.ui.ClientDetails.SoldFragment.NoPurchaseFragment;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.base.MvpView;
import com.aiminfocom.tallyfy.ui.main.NullFragment.NoSummaryFragment;
import com.aiminfocom.tallyfy.utils.AppConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PurchaseFragment extends BaseFragment implements PurchaseFragmentMvp,RoomDbCallback {

    private static final String TAG = "PurchaseFragment";
    @Inject
    PurchaseAdapter purchaseAdapter;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    String key;
    @Inject
    PurchaseFragmentPresenter<PurchaseFragmentMvp> mPresenter;

    public static PurchaseFragment newInstance(String billsPayable) {
        Bundle args = new Bundle();
        args.putString(AppConstants.PARTY_NAME, billsPayable);
        PurchaseFragment fragment = new PurchaseFragment();
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
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);

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
        getAllData();
        //replace default fragment
        //replaceFragment(new ApplicationFragment());
//setCompanyList();
        return view;
    }

    void initializeRecyclerView(ArrayList<UniversalPojo> arrayList) {
        purchaseAdapter.setClick(arrayList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getBaseActivity()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration2);
        recyclerView.setAdapter(purchaseAdapter);
        getItemClick();
    }

    void getItemClick() {
        purchaseAdapter.getClick().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((res) ->
                {
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_LONG).show();
                });
    }

    void getAllData() {
        LocalCacheManager.getInstance(getContext()).purachaseQueryExecute(key, this);
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
        Log.d(TAG, "getSalesQueryData: " + list);
        ArrayList<UniversalPojo> newList = new ArrayList<>();
        String partyName = "",unit = "",rate="",lastSold = "";
        if(list.size()!=0)
        {
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i)!=null) {
                    if (list.get(i).getVoucherInventories() != null) {
                        for(int j=0;j<list.get(i).getVoucherInventories().size();j++)
                        {
                            if (list.get(i).getVoucherInventories().get(j) != null) {
                                partyName = list.get(i).getVoucherInventories().get(j).getStockItemName();
                                unit = list.get(i).getVoucherInventories().get(j).getActualyQty();
                                rate = list.get(i).getVoucherInventories().get(j).getAmount().replaceAll("-","");
                                lastSold = list.get(i).getVoucherDate();
                                newList.add(new UniversalPojo(partyName,unit,lastSold,rate));
                            }
                        }
                    }
                }
            }
            initializeRecyclerView(newList);
        }
        else
        {
            getChildFragmentManager()
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .add(R.id.purchaseLayout,new NoPurchaseFragment())
                    .commit();
        }
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