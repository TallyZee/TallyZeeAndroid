package com.aiminfocom.tallyfy.ui.VoucherItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillItem;
import com.aiminfocom.tallyfy.data.Model.BankItem;
import com.aiminfocom.tallyfy.data.Model.UniverselInventries;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class BillDetails extends RecyclerView.Adapter<BillDetails.DataViewHolder> {


    private Context mContext;
    private ArrayList<BillItem> voucherGroupList;
    private Subject<BillItem> voucherGroupSubject;
    @Inject
    public BillDetails() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<BillItem> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<BillItem> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public BillDetails.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BillDetails.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_details, parent, false));
    }

    @Override
    public void onBindViewHolder(BillDetails.DataViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class DataViewHolder extends BaseViewHolder
    {
        @BindView(R.id.item_name)
        TextView itemName;

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.item)
        RelativeLayout item;
        @BindView(R.id.quantity)
        TextView quantity;



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
            itemName.setText(voucherGroupList.get(position).getBillName());
            amount.setText(voucherGroupList.get(position).getBillAmount());
            quantity.setText(voucherGroupList.get(position).getBillType());

            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}