package com.aiminfocom.tallyfy.ui.Users;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfiles;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
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
import com.aiminfocom.tallyfy.ui.about.AboutFragment;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.ui.main.MainMvpPresenter;
import com.aiminfocom.tallyfy.ui.main.MainMvpView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends BaseFragment implements RoomDbCallback {


    @BindView(R.id.goBack)
    ImageView goback;

    @BindView(R.id.userRecyclerView)
    RecyclerView userRecyclerView;

    @Inject
    UserAdapter userAdapter;
    HashSet<UserProfile> hashSet = new HashSet<>();
    public static final String TAG = "UsersFragment";
    public UsersFragment() {
        // Required empty public constructor
    }


    public static UsersFragment newInstance() {
        Bundle args = new Bundle();
        UsersFragment fragment = new UsersFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_users, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            LocalCacheManager.getInstance(getContext()).retrieveUserProfile(this);



        }
        getCLick();
        return view;
    }

    @Override
    protected void setUp(View view) {

    }


    void getCLick()
    {
        userAdapter.getClick().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {
                    Toast.makeText(getContext(),"Clicked",Toast.LENGTH_LONG).show();
                });
    }

    @OnClick(R.id.goBack)
    void getBack(View view)
    {
        getBaseActivity().onFragmentDetached(TAG);
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
        hashSet.add(new UserProfile(list.get(0).getUsername(),list.get(0).getEmail()));
        userAdapter.OnClick(hashSet,getBaseActivity().getApplicationContext());
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userRecyclerView.setAdapter(userAdapter);
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
    public void getSalesDateBetween(List<SalesVoucher> list) {

    }
}
