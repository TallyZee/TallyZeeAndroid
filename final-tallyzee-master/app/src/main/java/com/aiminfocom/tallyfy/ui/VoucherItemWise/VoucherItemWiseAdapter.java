package com.aiminfocom.tallyfy.ui.VoucherItemWise;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;

import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class VoucherItemWiseAdapter extends RecyclerView.Adapter<VoucherItemWiseAdapter.DataViewHolder> {


    private Context mContext;
    private ArrayList<UniversalPojo> voucherGroupList;
    private Subject<UniversalPojo> voucherGroupSubject;
    @Inject
    public VoucherItemWiseAdapter() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<UniversalPojo> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<UniversalPojo> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public VoucherItemWiseAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VoucherItemWiseAdapter.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.voucher_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VoucherItemWiseAdapter.DataViewHolder holder, int position) {
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

        @BindView(R.id.voucher_date)
                TextView voucherDate;

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
           // System.out.println(voucherGroupList.get(position).getBillCl()+" "+position+" "+ voucherGroupList.get(position).getBillDate()+" "+voucherGroupList.get(position).getBillref());
            partyName.setText(voucherGroupList.get(position).getAmount());
            amount.setText(voucherGroupList.get(position).getBillOverdue().replace("-",""));
            voucherDate.setText(voucherGroupList.get(position).getGetBillRef());
            // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}

