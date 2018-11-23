package com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PayableAdapter extends RecyclerView.Adapter<PayableAdapter.PayableViewHolder> {

    private Context mContext;
    private List<UniversalPojo> voucherGroupList;
    private Subject<UniversalPojo> voucherGroupSubject;

    @Inject
    PayableAdapter()
    {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }


    public Observable<UniversalPojo> ResponseClick()
    {
        return voucherGroupSubject;
    }

    public void voucherGroupClick(List<UniversalPojo> list,Context context)
    {
        this.mContext=context;
        this.voucherGroupList=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PayableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sales_layout_summary_fragment,viewGroup,false);
        PayableViewHolder viewHolder = new PayableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PayableViewHolder payableViewHolder, int i) {
        payableViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class PayableViewHolder extends BaseViewHolder
    {
        @BindView(R.id.party_Name)
        TextView partyName;

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.item)
        RelativeLayout item;
        PayableViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        protected void clear() {

        }


        @Override
        public void onBind(int position) {
            super.onBind(position);
            if(position==0)
            {
                partyName.setTextSize(18f);
            }
            partyName.setText(voucherGroupList.get(position).getPartyName());
            amount.setText(voucherGroupList.get(position).getAmount());
            item.setOnClickListener(v->voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }
}
