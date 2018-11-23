package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;

import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContanierMvp;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContanierPresenter;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment implements CategoryMvpView {

    private static final String TAG = "CategoryFragment";
//    @Inject
//    CategoryAdapter categoryAdapter;
//    @BindView(R.id.category_recyclerview)
//    RecyclerView recyclerView;
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Inject
    CategoryPresenter<CategoryMvpView> mPresenter;

    public static CategoryFragment getInstance()
    {
        CategoryFragment categoryFragment = new CategoryFragment();

        return categoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ActivityComponent component = getActivityComponent();
//        if (component != null) {
//            component.inject(this);
//            setUnBinder(ButterKnife.bind(this, view));
//            mPresenter.onAttach(this);
//        }
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                new LinearLayoutManager(getBaseActivity()).getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
//        recyclerView.setAdapter(categoryAdapter);
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    protected void setUp(View view) {

    }


//    @Override
//    public void getItemQuery(List<StockItem> list) {
//
//    }
//
//    @Override
//    public void getGroupQuery(List<StockItem> list) {
//
//    }
//
//    @Override
//    public void getCategoryQuery(List<StockItem> list) {
//        Log.d(TAG, "getCategoryQuery: "+list);
//        ArrayList<ItemsModel> arrayList = new ArrayList<>();
//        for(int i=0;i<list.size();i++)
//        {
//            arrayList.add(new ItemsModel(list.get(i).getStockItemName(),list.get(i).getRate(),list.get(i).getOpeningRate(),
//                    list.get(i).getOpeningValue(),list.get(i).getOpeningBalance(),list.get(i).getReorderValue(),list.get(i).getMinimumOrderBase(),list.get(i).getClosingBalance()));
//        }
//        categoryAdapter.setClick(arrayList,getContext());
//        getClick();
//    }
//
//    void getClick()
//    {
//        categoryAdapter.getClick().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((res)->
//                {
//
//                });
//    }
}
