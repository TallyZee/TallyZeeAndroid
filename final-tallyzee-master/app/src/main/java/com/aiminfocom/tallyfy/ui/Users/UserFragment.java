package com.aiminfocom.tallyfy.ui.Users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
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
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDatabaseHelper;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbCallback;
import com.aiminfocom.tallyfy.data.network.UserData.UserDataFirebase;
import com.aiminfocom.tallyfy.data.network.UserData.onDataInterface;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.Companies.CompaniesFragment;
import com.aiminfocom.tallyfy.ui.ContactUs.ContactUsFragment;
import com.aiminfocom.tallyfy.ui.Rating.RatingFragment;
import com.aiminfocom.tallyfy.ui.Settings.SettingFragment;
import com.aiminfocom.tallyfy.ui.about.AboutFragment;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class UserFragment extends BaseFragment implements UserMvp,onDataInterface,onClickInterface,RoomDbCallback {
    public static final String TAG="UserFragment";

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_email)
    TextView email;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String[] names = {"Users","Company","About","Rate Us","Settings","Logout","Tutorial","Help/Search","Contact Us","Report/Request"};
    private int[] images = {R.drawable.ic_user_profile,R.drawable.ic_company,R.drawable.ic_about,R.drawable.ic_review
    ,R.drawable.ic_settings,R.drawable.ic_logout,R.drawable.ic_video_tutorial,
            R.drawable.ic_search,R.drawable.ic_logout,R.drawable.ic_exclamation};
    String name,emai;

    @Inject
    UserPresenter<UserMvp> mPresenter;
    public static UserFragment newInstance() {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ActivityComponent component = getActivityComponent();

        GridView list = view.findViewById(R.id.intent);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        CustomAdapter customAdapter = new CustomAdapter(getContext(),names,images);
        CustomGrid customGrid = new CustomGrid(getContext(),getList(),fragmentManager,this);
        list.setAdapter(customGrid);

        LocalCacheManager.getInstance(getContext()).retrieveUserProfile(this);
//        for(int i=0;i<userProfiles.;i++)
//        {
//            name = userProfiles.get(i).getUsername();
//            emai = userProfiles.get(i).getEmail();
//        }


        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            progressBar.setVisibility(View.VISIBLE);
        }





//            onClickGrid();

        return view;
    }

    @Override
    protected void setUp(View view) {

    }
ArrayList<ItemsModel> getList()
{
    ArrayList<ItemsModel> itemsModels=new ArrayList<>();
  //   public ItemsModel(int image, String currency, String moduleName, double amount,String color,String percent,int pandl,DashType dashType)

    itemsModels.add(new ItemsModel(R.drawable.ic_user_profile,"","Users",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_company,"","Company",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_about,"","About",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_review,"","Rate Us",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_settings,"","Settings",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_exit,"","Logout",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_video_tutorial,"","Tutorial",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_baseline_search_24px,"","Help/Search",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_baseline_phone_24px,"","Contact us",12,"","",12, DashType.BANK));
    itemsModels.add(new ItemsModel(R.drawable.ic_baseline_report_24px,"","Report",12,"","",12,DashType.BANK));





    return itemsModels;
}
    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void retrieveUserName(String name,String email) {
        progressBar.setVisibility(View.GONE);
        Log.d(TAG, "retrieveUserName: "+Thread.currentThread().getName());


    }


    @Override
    public void onUserCallback() {
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, UsersFragment.newInstance(), UsersFragment.TAG)
                .commit();
    }

    @Override
    public void onCompanyCallback() {
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, CompaniesFragment.newInstance(), CompaniesFragment.TAG)
                .commit();

    }

    @Override
    public void onAboutCallback() {
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();

    }

    @Override
    public void onRateUsCallback() {
//        getBaseActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .disallowAddToBackStack()
//                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
//                .add(R.id.cl_root_view, RatingFragment.getInstance(), RatingFragment.TAG)
//                .commit();
    }

    @Override
    public void onSettingCallback() {
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, SettingFragment.newInstance(), SettingFragment.TAG)
                .commit();
    }

    @Override
    public void onTutorialCallback() {

    }

    @Override
    public void onHelpCallback() {

    }

    @Override
    public void onContactUsCallback() {
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, ContactUsFragment.newInstance(), ContactUsFragment.TAG)
                .commit();
    }

    @Override
    public void onRequestCallback() {

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
        if(list.size()!=0)
        {
            progressBar.setVisibility(View.INVISIBLE);
            userName.setText(list.get(0).getEmail());
            email.setText(list.get(0).getUsername());
        }
        else
        {
            mPresenter.getUserProfileData();
        }
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



    @Override
    public void getUserProfileResponse(ArrayList<UserProfile> arrayList) {
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).insertUserProfile(arrayList);
        if(arrayList.size()!=0)
        {
            progressBar.setVisibility(View.INVISIBLE);
            userName.setText(arrayList.get(0).getEmail());
            email.setText(arrayList.get(0).getUsername());
        }

    }
}