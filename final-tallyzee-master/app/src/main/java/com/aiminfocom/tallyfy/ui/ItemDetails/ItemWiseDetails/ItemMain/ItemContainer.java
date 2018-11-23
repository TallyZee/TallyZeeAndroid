package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetails;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category.CategoryFragment;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Group.GroupFragment;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemDesc;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemContainer extends BaseFragment implements ItemContanierMvp
{


    public static final String TAG="ItemContainer";
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Inject
    ItemContanierPresenter<ItemContanierMvp> mPresenter;

    public static ItemContainer newInstance() {
        Bundle args = new Bundle();
        // args.putParcelableArrayList(AppConstants.VOUCHER_LIST, list);
        ItemContainer fragment = new ItemContainer();
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
        View view = inflater.inflate(R.layout.fragment_item_container, container, false);
        MainActivity.searchView.setVisibility(View.INVISIBLE);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        //replace default fragment
        //replaceFragment(new ApplicationFragment());
//setCompanyList();
        return view;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(ItemDesc.newInstance(), "Item");
        adapter.addFragment(GroupFragment.newInstance(), "Group");
        adapter.addFragment(CategoryFragment.getInstance(), "Category");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}