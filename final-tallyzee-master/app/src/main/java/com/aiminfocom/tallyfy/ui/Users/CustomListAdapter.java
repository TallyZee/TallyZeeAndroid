package com.aiminfocom.tallyfy.ui.Users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.SettingPojo;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class CustomListAdapter  extends RecyclerView.Adapter<CustomListAdapter.RecyclerViewHolder>{

    private ArrayList<SettingPojo> arrayList;
    private List<SettingPojo> voucherGroupList;
    private Context mContext;
    private Subject<SettingPojo> voucherGroupSubject;
    @Inject
    public CustomListAdapter()
    {

        arrayList= new ArrayList<>();
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
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custm_setting_recyclerview,viewGroup,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.onBind(i);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
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


