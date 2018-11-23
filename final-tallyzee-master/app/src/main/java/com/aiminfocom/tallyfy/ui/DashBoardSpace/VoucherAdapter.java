package com.aiminfocom.tallyfy.ui.DashBoardSpace;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.Voucher;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by GulshanPC on 26/07/2018.
 */

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.DataViewHolder> {


    private Context mContext;
    private List<UniversalPojo> voucherGroupList;
    private Subject<UniversalPojo> voucherGroupSubject;
    @Inject
    public VoucherAdapter() {
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
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.voucher_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }


    void updateList(List<UniversalPojo> newList)
    {
        voucherGroupList = new ArrayList<>();
        voucherGroupList.addAll(newList);
        notifyDataSetChanged();
    }

    void filterByDate(List<UniversalPojo> pojo)
    { voucherGroupList = new ArrayList<>();
        voucherGroupList.clear();
        voucherGroupList.addAll(pojo);
        notifyDataSetChanged();
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

            partyName.setText(voucherGroupList.get(position).getPartyName());
            amount.setText(voucherGroupList.get(position).getAmount());
           // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}
