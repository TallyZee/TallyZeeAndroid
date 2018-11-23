package com.aiminfocom.tallyfy.ui.DashBoardSpace;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ClientAdapter  extends RecyclerView.Adapter<ClientAdapter.DataViewHolder> {


    private Context mContext;
    private List<String> voucherGroupList;
    private Subject<String> voucherGroupSubject;
    @Inject
    public ClientAdapter() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<String> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(List<String> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public ClientAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClientAdapter.DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ClientAdapter.DataViewHolder holder, int position) {
        holder.onBind(position);
    }
   public void updateList(List<String> newList)
    {
        voucherGroupList = new ArrayList<>();
        voucherGroupList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class DataViewHolder extends BaseViewHolder
    {
        @BindView(R.id.client_name)
        TextView partyName;

        @BindView(R.id.single_view)
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
//            ArrayList<String> newList = new ArrayList<>(voucherGroupList);

            partyName.setText(voucherGroupList.get(position));
           // amount.setText(voucherGroupList.get(position).getBillCl());
            // voucherType.setText(voucherGroupList.get(position));
            item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
        }
    }

}