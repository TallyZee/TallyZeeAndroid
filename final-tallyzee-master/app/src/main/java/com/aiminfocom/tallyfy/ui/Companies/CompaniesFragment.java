package com.aiminfocom.tallyfy.ui.Companies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Company;
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
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.CompanyCallBack;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.data.network.ApiEndPoint;
import com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser.getCompanyName;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.ui.main.ClientFrg.RoomClientCallBack;
import com.aiminfocom.tallyfy.ui.main.CompanyList.Companiess;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.ui.main.NullFragment.RobotFragment;
import com.aiminfocom.tallyfy.ui.main.callbackCompany;
import com.aiminfocom.tallyfy.utils.ConnectionUtil;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;


public class CompaniesFragment extends BaseFragment implements CompaniesMvp,CompanyCallBack,getCompanyName,CompanyNameCallback {


    public static final String TAG="CompaniesFragment";
    callbackCompany cmp=new MainActivity();

    @BindView(R.id.hadder_text)
    TextView text;

    FragmentManager fragmentManager;
    @BindView(R.id.search_text)
    EditText searchText;

    @BindView(R.id.nav_back_btn)
    ImageView back;

//    @BindView(R.id.search)
//    ImageView searchButton;
    @BindView(R.id.companies_list)
    RecyclerView companyList;


      ArrayList<String> listDemo = new ArrayList<>();

    @Inject
    CompaniesPresenter<CompaniesMvp> mPresenter;
    @Inject
    CompaniesAdapter companiesAdapter;
    HashSet<String> companySet = new HashSet<>();
    String urlforCompany = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>List of Companies</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
    OkHttpClient client=new OkHttpClient();


    public static CompaniesFragment newInstance() {
        Bundle args = new Bundle();
       CompaniesFragment fragment = new CompaniesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveCompanyName(this);

            getClick();
        }
//setCompanyList();
        return view;
    }

    void setCompanyList()
    {
        Observable.fromCallable(() -> {

            try {
                RequestBody body = RequestBody.create(ApiEndPoint.XML, urlforCompany);
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
                        LocalCacheManager.getInstance(this.getBaseActivity()).getCompanylist(this);
                        System.out.println("the request got error");
                    } else {
                        String cleanString = response.toString().replaceAll("\r", "").replaceAll("\n", "");
                        System.out.println("result is:" + ConnectionUtil.xmltoJson(cleanString));
                        List<Company> list = (ArrayList<Company>) ConnectionUtil.getInistance().convertCompanyObject(ConnectionUtil.xmltoJson(cleanString));
                        System.out.println("result is:" + response + " " + response.toString() + "list:" + list);
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                LocalCacheManager.getInstance(this.getBaseActivity()).setCompany(this, list.get(i));
                            }

                        }
                        LocalCacheManager.getInstance(this.getBaseActivity()).getCompanylist(this);
                  this.setUp(this.getView());
                    }
                });


    }

    @Override
    protected void setUp(View view) {
        getClick();
        deleteCompany();

    }

    void getClick()
    {
          companiesAdapter.getVoucherGroupListclick()
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe((response)->{
//                      com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getBaseActivity()).deleteCompany((response));
                      com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).deleteCompany(response);
                      companiesAdapter.notifyDataSetChanged();
                      companySet.remove(response);

                      //startActivity(MainActivity.getStartIntent(getContext()));

                  });



    }

    void deleteCompany()
    {
        companiesAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((res)->
                {

                    listDemo.remove(res);
                });
    }

    @OnClick({R.id.nav_back_btn,R.id.search_text})
    void onNavBackClick(View view) {

        switch (view.getId())
        {
            case R.id.nav_back_btn:
                getBaseActivity().onFragmentDetached(TAG);
                break;
            case R.id.search_text:

                break;

        }

    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onDataNotAvailable() {
        System.out.println("on dataNOtAVil");
    }

    @Override
    public void dataBaseCompanyList(List<Company> list) {
//        ArrayList<String> listComStr=new ArrayList<String>();
//        for (Company company:list)
//        {
//            listComStr.add(company.getCompanyName());
//        }
//        companiesAdapter.setVoucherGroupListAdapter(listComStr, getContext());
//        ArrayList<String> listDemo=new ArrayList<String>();
//        listDemo.add("Demo");
//        companiesAdapter.setVoucherGroupListAdapter(listDemo, getContext());
//        ArrayList<Company> billsReceivables= (ArrayList<Company>) list;
//        companyList.setLayoutManager(new LinearLayoutManager(getContext()));
//        companyList.setAdapter(companiesAdapter);

    }


    @Override
    public void getName(ArrayList<String> arrayList) {

    }

    @Override
    public void getCompanyList(List<CompanyList> list) {
        Log.d(TAG, "getCompanyList: "+list);
                        if(list.size()!=0) {
            for (int i = 0; i < list.size(); i++) {
                companySet.add(list.get(i).getCompanyName());
            }

            companiesAdapter.setVoucherGroupListAdapter(companySet, getContext());
            companyList.setLayoutManager(new LinearLayoutManager(getContext()));
            companyList.setAdapter(companiesAdapter);
        }
    }

    @Override
    public void getClientAfterDelete(List<CompanyList> list) {
        Log.d(TAG, "getClientAfterDelete: "+list);
        if(list.size()!=0) {
            for (int i = 0; i < list.size(); i++) {
                companySet.add(list.get(i).getCompanyName());
            }

            companiesAdapter.setVoucherGroupListAdapter(companySet, getContext());
            companyList.setLayoutManager(new LinearLayoutManager(getContext()));
            companyList.setAdapter(companiesAdapter);
        }
    }

//    @Override
//    public void getListClient(List<Client> list) {
//                if(list.size()!=0) {
//            for (int i = 0; i < list.size(); i++) {
//                companySet.add(list.get(i).getCompanyName());
//            }
//
//            companiesAdapter.setVoucherGroupListAdapter(companySet, getContext());
//            companyList.setLayoutManager(new LinearLayoutManager(getContext()));
//            companyList.setAdapter(companiesAdapter);
//        }else
//        {
//                    getChildFragmentManager()
//                .beginTransaction()
//                .disallowAddToBackStack()
//                .add(R.id.frameLayout,new RobotFragment())
//                .commit();
//        }
//    }
//
//    @Override
//    public void getClientAfterDelete(List<Client> list) {
//        if(list.size()!=0) {
//            for (int i = 0; i < list.size(); i++) {
//                companySet.add(list.get(i).getCompanyName());
//            }
//
//            companiesAdapter.setVoucherGroupListAdapter(companySet, getContext());
//            companyList.setLayoutManager(new LinearLayoutManager(getContext()));
//            companyList.setAdapter(companiesAdapter);
//        }
//    }


//    @Override
//    public void getCompanyList(List<CompanyList> list) {
//
//        if(list.size()!=0) {
//            for (int i = 0; i < list.size(); i++) {
//                companySet.add(list.get(i).getCompanyName());
//            }
//
//            companiesAdapter.setVoucherGroupListAdapter(companySet, getContext());
//            companyList.setLayoutManager(new LinearLayoutManager(getContext()));
//            companyList.setAdapter(companiesAdapter);
//        }else
//        {
//                    getChildFragmentManager()
//                .beginTransaction()
//                .disallowAddToBackStack()
//                .add(R.id.frameLayout,new RobotFragment())
//                .commit();
//        }
//    }
//
//
//    @Override
//    public void getClientAfterDelete(List<Client> list) {
//        if (list.size() != 0) {
//            for (int i = 0; i < list.size(); i++) {
//                companySet.add(list.get(i).getCompanyName());
//            }
//
//            companiesAdapter.setVoucherGroupListAdapter(companySet, getContext());
//            companyList.setLayoutManager(new LinearLayoutManager(getContext()));
//            companyList.setAdapter(companiesAdapter);
//        }
//    }
}
