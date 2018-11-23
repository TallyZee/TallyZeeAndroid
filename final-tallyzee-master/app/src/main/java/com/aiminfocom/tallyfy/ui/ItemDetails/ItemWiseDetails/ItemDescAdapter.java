package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.VoucherItem.ItemAdapter;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ItemDescAdapter extends RecyclerView.Adapter<ItemDescAdapter.DataViewHolder> {


    private Context mContext;
    private ArrayList<ItemVoucherModel> voucherGroupList;
    private Subject<ItemVoucherModel> voucherGroupSubject;
    @Inject
    public ItemDescAdapter() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<ItemVoucherModel> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<ItemVoucherModel> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public ItemDescAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemDescAdapter.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_desc_child, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemDescAdapter.DataViewHolder holder, int position) {
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

        @BindView(R.id.total_amount)
        TextView amount;
        @BindView(R.id.item)
        RelativeLayout item;
        @BindView(R.id.avg_amount)
        TextView quantity;

        @BindView(R.id.std_cost)
        TextView cgst;
        @BindView(R.id.std_price)
        TextView sgst;
        @BindView(R.id.reorder_amount)
        TextView reOrder;
        @BindView(R.id.min_order_amount)
        TextView mOrder;
        @BindView(R.id.nos)
        TextView nos;



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
            itemName.setText(voucherGroupList.get(position).getName());
            amount.setText(voucherGroupList.get(position).getAmount());
            quantity.setText(voucherGroupList.get(position).getApr());
            cgst.setText(voucherGroupList.get(position).getsCost());
            sgst.setText(voucherGroupList.get(position).getsPrice());
            reOrder.setText(voucherGroupList.get(position).getReorder());
            mOrder.setText(voucherGroupList.get(position).getReorder());

            // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}

