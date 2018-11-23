package com.aiminfocom.tallyfy.ui.VoucherItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BankBIll;
import com.aiminfocom.tallyfy.data.Model.BankItem;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class BankBill extends RecyclerView.Adapter<BankBill.DataViewHolder> {


    private Context mContext;
    private ArrayList<BankBIll> voucherGroupList;
    private Subject<BankBIll> voucherGroupSubject;
    @Inject
    public BankBill() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<BankBIll> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<BankBIll> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public BankBill.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankBill.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BankBill.DataViewHolder holder, int position) {
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

        TextView trancationType;

        TextView bankname;




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
            itemName.setText(voucherGroupList.get(position).getBankName());
            amount.setText(voucherGroupList.get(position).getBankamount());
            quantity.setText(voucherGroupList.get(position).getBankDate());
            bankname.setText(voucherGroupList.get(position).getBankAccNumber());
            trancationType.setText(voucherGroupList.get(position).getBankTypeTransction());

            //item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}