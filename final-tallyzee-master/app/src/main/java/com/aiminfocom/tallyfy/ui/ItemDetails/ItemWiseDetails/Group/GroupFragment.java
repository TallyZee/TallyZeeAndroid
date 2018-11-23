package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Group;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class GroupFragment extends BaseFragment implements GroupFragmentMvp
{
    public static final String TAG="GroupFragment";

    @Inject
    GroupFragmentPresenter<GroupFragmentMvp> mPresenter;
    public static GroupFragment newInstance() {
        Bundle args = new Bundle();
        // args.putParcelableArrayList(AppConstants.VOUCHER_LIST, list);
        GroupFragment fragment = new GroupFragment();
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
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        MainActivity.searchView.setVisibility(View.INVISIBLE);
        ActivityComponent component = getActivityComponent();
//        LocalCacheManager.getInstance(getContext()).itemQueryData("Group",this);
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        //replace default fragment
        //replaceFragment(new ApplicationFragment());
//setCompanyList();
        return view;
    }

//    @Override
//    public void getItemQuery(List<StockItem> list) {
//
//    }
//
//    @Override
//    public void getGroupQuery(List<StockItem> list) {
//        Log.d(TAG, "getGroupQuery: "+list);
//    }
//
//    @Override
//    public void getCategoryQuery(List<StockItem> list) {
//
//    }
}