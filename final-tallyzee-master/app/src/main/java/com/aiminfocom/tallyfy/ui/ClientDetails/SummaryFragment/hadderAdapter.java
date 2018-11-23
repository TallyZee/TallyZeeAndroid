package com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.VoucherAdapter;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class hadderAdapter extends RecyclerView.Adapter<hadderAdapter.DataViewHolder> {


    private Context mContext;
    private List<UniversalPojo> voucherGroupList;
    private Subject<UniversalPojo> voucherGroupSubject;
    @Inject
    public hadderAdapter() {
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
    public hadderAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new hadderAdapter.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_layout_summary_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(hadderAdapter.DataViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class DataViewHolder extends BaseViewHolder
    {
        @BindView(R.id.party_Name)
        TextView partyName;

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.item)
        RelativeLayout item;

        DataViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, itemView);
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
            // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}
