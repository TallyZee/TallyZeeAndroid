package com.aiminfocom.tallyfy.ui.ClientDetails;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.ui.ClientDetails.PurchaseFragment.PurchaseFragment;
import com.aiminfocom.tallyfy.ui.ClientDetails.SoldFragment.SoldFragment;
import com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment.SummaryFragment;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.base.MvpView;
import com.aiminfocom.tallyfy.ui.main.CalenderFilter.ShowCalender;
import com.aiminfocom.tallyfy.utils.AppConstants;
import com.aiminfocom.tallyfy.utils.CalenderUtils;
import com.aiminfocom.tallyfy.utils.RecyclerItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientDetails extends BaseActivity implements ClientDetailsMvp {


    @Inject
ClientDetailsMvpPresenter<ClientDetailsMvp> mPresenter;

    @BindView(R.id.HadderText)
    TextView hadderText;
    @BindView(R.id.profile)
    ImageView profile;
    @BindView(R.id.calender)
    ImageView calender;
    @BindView(R.id.bo)
    ImageView back;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    String billsPaybale;

    public static Intent getStartIntent(Context context, String clickParty) {
        Intent intent = new Intent(context, ClientDetails.class);
        Bundle b = new Bundle();
        b.putString(AppConstants.VALUE,clickParty);
        intent.putExtra(AppConstants.VOUCHER_LIST,b);
        // intent.putParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST, list);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ClientDetails.this);


        tabLayout.setupWithViewPager(viewPager);
        //replaceFragment(SummaryFragment.newInstance());
         billsPaybale=getIntent().getBundleExtra(AppConstants.VOUCHER_LIST).getString(AppConstants.VALUE);
        Toast.makeText(getApplicationContext(),billsPaybale,Toast.LENGTH_LONG).show();
        hadderText.setText(billsPaybale);
        setupViewPager(viewPager);
        System.out.println(CalenderUtils.getCurrentSystemDate(this
        ));

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SummaryFragment.newInstance(billsPaybale), "Summary");
        adapter.addFragment(SoldFragment.newInstance(billsPaybale), "Sold");
        adapter.addFragment(PurchaseFragment.newInstance(billsPaybale), "Purchase");
        viewPager.setAdapter(adapter);
    }


    @OnClick({R.id.calender,R.id.bo,R.id.profile})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.profile:
                break;
            case R.id.calender:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ShowCalender userPopUp = new ShowCalender();
                userPopUp.show(fragmentManager, "Show fragment");
                break;
            case R.id.bo:
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    protected void setUp() {

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
