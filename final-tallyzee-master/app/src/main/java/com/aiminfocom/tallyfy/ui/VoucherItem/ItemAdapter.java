package com.aiminfocom.tallyfy.ui.VoucherItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.Voucher;
import com.aiminfocom.tallyfy.data.Model.Inventories;
import com.aiminfocom.tallyfy.data.Model.Ledger;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.Model.UniverselInventries;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VoucherItemWiseAdapter;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.DataViewHolder> {


    private Context mContext;
    private ArrayList<UniverselInventries> voucherGroupList;
    private Subject<UniverselInventries> voucherGroupSubject;
    @Inject
    public ItemAdapter() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<UniverselInventries> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<UniverselInventries> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public ItemAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemAdapter.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_single_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemAdapter.DataViewHolder holder, int position) {
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

        @BindView(R.id.cgst)
        TextView cgst;
        @BindView(R.id.sgst)
        TextView sgst;
        @BindView(R.id.productID)
        TextView productId;


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
            itemName.setText(voucherGroupList.get(position).getStockItemName());
            amount.setText(voucherGroupList.get(position).getAmount());
            productId.setText("HSB/SCA:"+voucherGroupList.get(position).getBasicUserDesc());
            cgst.setText("rate: "+voucherGroupList.get(position).getRate());
            sgst.setText("SGST: 0%");
            quantity.setText("Qty:"+voucherGroupList.get(position).getEdqty()+" Nos");

            // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}

