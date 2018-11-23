package com.aiminfocom.tallyfy.ui.Settings;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.SettingPojo;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SettingFragment extends BaseFragment implements SettingsMvp {

    public static final String TAG="SettingFragment";
@BindView(R.id.myRecyclerView)
RecyclerView recyclerView;


    @BindView(R.id.goBack)
    ImageView goBack;
    @Inject
    SettingPresenter<SettingsMvp> mPresenter;
    @Inject
    SettingAdapter settingAdapter;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            settingAdapter.setVoucherGroupListAdapter(setArrayList(),getBaseActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

            recyclerView.setAdapter(settingAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    new LinearLayoutManager(getBaseActivity()).getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
getClick();
        }
        setUp(view);
        return view;
    }

    @Override
    protected void setUp(View view) {
        preapare();


    }
    void preapare()
    {
        Map<String,Integer> data=new HashMap<String, Integer>();
        data.put("General",R.drawable.ic_user);
        data.put("Notifications",R.drawable.ic_user);
        data.put("Data & Sync",R.drawable.ic_sync);

    }
    void getClick()
    {
        settingAdapter.getVoucherGroupListclick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response)->
                        {
                            Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                        }
                );

    }

    @OnClick(R.id.goBack)
    void onBackPressed(View view)
    {
        getBaseActivity().onFragmentDetached(TAG);
    }
    ArrayList<SettingPojo> setArrayList()
    {
        ArrayList<SettingPojo> arrayList = new ArrayList<>();
        arrayList.add(new SettingPojo(R.drawable.ic_user_profile,"Currency","Configuration for currency and value format."));
        arrayList.add(new SettingPojo(R.drawable.ic_invoice_report,"Invoice","Configuration fields you would like to show in your invoice."));
        arrayList.add(new SettingPojo(R.drawable.ic_user_profile,"Outstanding","Enables you to configure outstanding report."));
        arrayList.add(new SettingPojo(R.drawable.ic_share_white_24px,"Share","Additional setting while sharing emailing your customer."));
        arrayList.add(new SettingPojo(R.drawable.ic_user_profile,"Notification","Configure your notification."));
        arrayList.add(new SettingPojo(R.drawable.ic_ledger_report,"Ledger Report","Configure while sharing ledger report."));
        arrayList.add(new SettingPojo(R.drawable.ic_stock,"Stock Items","Configure your items."));
        arrayList.add(new SettingPojo(R.drawable.ic_default_screen,"Default Screen","Choose the default screen to open while operating this app."));
        arrayList.add(new SettingPojo(R.drawable.ic_user_profile,"Date Settings","Date Settings."));
        return arrayList;
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
