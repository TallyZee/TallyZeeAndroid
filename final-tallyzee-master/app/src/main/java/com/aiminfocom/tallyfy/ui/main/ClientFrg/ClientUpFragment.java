package com.aiminfocom.tallyfy.ui.main.ClientFrg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetails;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.ClientAdapter;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.ui.main.NoCompanyFragment;
import com.aiminfocom.tallyfy.ui.main.NullFragment.DataNotSync;
import com.aiminfocom.tallyfy.ui.main.NullFragment.RobotFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ClientUpFragment extends BaseFragment implements RoomClientCallBack, android.support.v7.widget.SearchView.OnQueryTextListener{

    @BindView(R.id.clientProgress)
    ProgressBar progressBar;
    private static final String TAG = "ClientUpFragment";
    private static final String ARG_PARAM2 = "param2";
@BindView(R.id.list_newdash)
RecyclerView child_recyclerview;
    private String mParam1;
    private String mParam2;
    @Inject
    ClientAdapter clientAdapter;
    @Inject
    ClientUpPresenter<ClientUpMvp> mPresenter;
    HashSet<String> sortList=new HashSet<>();
    public ClientUpFragment() {
        // Required empty public constructor
    }


    public static ClientUpFragment newInstance() {
        ClientUpFragment fragment = new ClientUpFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_up, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
        }
        progressBar.setVisibility(View.VISIBLE);
        com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager.getInstance(getContext()).retrieveClientList(this);
        MainActivity.searchView.setVisibility(View.VISIBLE);
        MainActivity.searchView.setOnQueryTextListener(this);
        return view;
}



    @Override
    protected void setUp(View view) {

    }

    @Override
    public void getListClient(List<Client> list) {
    if(list.size()!=0) {
        progressBar.setVisibility(View.INVISIBLE);
        for (Client cl : list) {
            if (!cl.getCompanyName().equalsIgnoreCase(" ") && cl.getCompanyName() != null && !cl.getCompanyName().isEmpty()) {
                sortList.add(cl.getCompanyName());
            }

        }
        ArrayList<String> forList = new ArrayList<>(sortList);
        clientAdapter.setVoucherGroupListAdapter(forList, getBaseActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseActivity());
        child_recyclerview.setLayoutManager(linearLayoutManager);
        child_recyclerview.setAdapter(clientAdapter);
        click();
    }else {
        progressBar.setVisibility(View.INVISIBLE);
        getChildFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.clientFragment,new DataNotSync())
                .commit();
    }

    }



    void click()
    {
        clientAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response)->{
                    System.out.println(TAG+"Response: of the String is:"+response);
                    startActivity(ClientDetails.getStartIntent(getContext(), response));

                });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<String> newList = new ArrayList<>();
        for (String pojo : sortList) {
            if (pojo.toLowerCase().contains(userInput)) {
                newList.add((pojo));
            }
        }
         clientAdapter.updateList(newList);
        return true;
    }
}
