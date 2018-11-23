package com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment;

import android.content.Context;
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

public class CreditNoteAdapter extends RecyclerView.Adapter<CreditNoteAdapter.CreditViewHolder> {


    private Context mContext;
    private List<UniversalPojo> voucherGroupList;
    private Subject<UniversalPojo> voucherGroupSubject;

    @Inject
    public CreditNoteAdapter() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<UniversalPojo> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(List<UniversalPojo> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }


    @Override
    public CreditNoteAdapter.CreditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CreditNoteAdapter.CreditViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_layout_summary_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(CreditNoteAdapter.CreditViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class CreditViewHolder extends BaseViewHolder {
        @BindView(R.id.party_Name)
        TextView partyName;

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.item)
        RelativeLayout item;

        CreditViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            if (position == 0) {
                partyName.setTextSize(18f);
            }
            partyName.setText(voucherGroupList.get(position).getPartyName());
            amount.setText(voucherGroupList.get(position).getAmount());
            // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

    {
    }
}
