package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContainer;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemDesc extends BaseFragment implements ItemDescMvp
{
    private static final String TAG = "ItemDesc";
    @Inject
    ItemDescPresenter<ItemDescMvp> mPresenter;
@BindView(R.id.list_Item)
RecyclerView list;
@Inject
ItemDescAdapter adapter;

    public static ItemDesc newInstance() {
        Bundle args = new Bundle();
        // args.putParcelableArrayList(AppConstants.VOUCHER_LIST, list);
        ItemDesc fragment = new ItemDesc();
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
        View view = inflater.inflate(R.layout.fragment_item_desc, container, false);
//        LocalCacheManager.getInstance(getContext()).itemQueryData("Item",this);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
adapter.setVoucherGroupListAdapter(getList(),getContext());
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(adapter);

        //replace default fragment
        //replaceFragment(new ApplicationFragment());
//setCompanyList();
        return view;
    }
    ArrayList<ItemVoucherModel> getList()
    {
        //public ItemVoucherModel(String name,String amount, String apr, String sCost, String sPrice, String reorder, String mOrder, String nos) {
        //
        ArrayList<ItemVoucherModel> list=new ArrayList<ItemVoucherModel>();
        list.add(new ItemVoucherModel("Amazon","56,780","125.12","55","47","1000","500","224"));
        list.add(new ItemVoucherModel("Microsoft","24,500","875.12","55","47","1000","500","24"));
        list.add(new ItemVoucherModel("Hp","45,880","345.12","55","47","1000","500","424"));
        list.add(new ItemVoucherModel("Oracle","89,710","135.12","55","47","1000","500","124"));
        list.add(new ItemVoucherModel("Nokia","98,730","245.12","55","47","1000","500","724"));
        list.add(new ItemVoucherModel("Alphbat","67,380","675.12","55","47","1000","500","364"));
        list.add(new ItemVoucherModel("Ali Express","34,480","895.12","55","47","1000","500","874"));

        return list;
    }

//    @Override
//    public void getItemQuery(List<StockItem> list) {
//        Log.d(TAG, "getItemQuery: "+list);
//    }
//
//    @Override
//    public void getGroupQuery(List<StockItem> list) {
//
//    }
//
//    @Override
//    public void getCategoryQuery(List<StockItem> list) {
//
//    }
}