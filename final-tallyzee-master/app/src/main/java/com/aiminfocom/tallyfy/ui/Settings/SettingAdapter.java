package com.aiminfocom.tallyfy.ui.Settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.SettingPojo;
import com.aiminfocom.tallyfy.ui.Companies.CompaniesAdapter;
import com.aiminfocom.tallyfy.ui.Users.CustomListAdapter;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by GulshanPC on 21/07/2018.
 */

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.RecyclerViewHolder>{


    private List<SettingPojo> voucherGroupList;
    private Context mContext;
    private Subject<SettingPojo> voucherGroupSubject;
    @Inject
    public SettingAdapter()
    {

        voucherGroupSubject = PublishSubject.create();
    }


    public io.reactivex.Observable<SettingPojo> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }

    public void setVoucherGroupListAdapter(ArrayList<SettingPojo> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SettingAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custm_setting_recyclerview,viewGroup,false);
        SettingAdapter.RecyclerViewHolder recyclerViewHolder = new SettingAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.onBind(i);
    }


    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class RecyclerViewHolder extends BaseViewHolder
    {
        @BindView(R.id.settingImage)
        ImageView settingImage;
        @BindView(R.id.settingTitle)
        TextView settingTitle;
        @BindView(R.id.settingInfo)
        TextView settingInfo;
        @BindView(R.id.settingLienar)
        LinearLayout settingLienar;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }


        @Override
        public void onBind(int position) {
            super.onBind(position);
            settingImage.setImageResource(voucherGroupList.get(position).getImage());
            settingTitle.setText(voucherGroupList.get(position).getTitle());
            settingInfo.setText(voucherGroupList.get(position).getInfo());
            settingLienar.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}


