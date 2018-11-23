package com.aiminfocom.tallyfy.ui.VoucherItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.CostCenter;
import com.aiminfocom.tallyfy.data.Model.BankItem;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class CostCenterList extends RecyclerView.Adapter<CostCenterList.DataViewHolder> {


    private Context mContext;
    private ArrayList<CostCenter> voucherGroupList;
    private Subject<CostCenter> voucherGroupSubject;
    @Inject
    public CostCenterList() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<CostCenter> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<CostCenter> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public CostCenterList.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CostCenterList.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cost_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CostCenterList.DataViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class DataViewHolder extends BaseViewHolder
    {
        @BindView(R.id.cost_name)
        TextView itemName;

        @BindView(R.id.cost_amount)
        TextView amount;



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
            itemName.setText(voucherGroupList.get(position).getCostname());
            amount.setText(voucherGroupList.get(position).getCostamount());

            //item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}