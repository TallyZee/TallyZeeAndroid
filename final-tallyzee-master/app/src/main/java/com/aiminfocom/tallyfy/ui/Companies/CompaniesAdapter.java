package com.aiminfocom.tallyfy.ui.Companies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashSet;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by GulshanPC on 25/06/2018.
 */

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.DataViewHolder> {


    private Context mContext;
    private HashSet<String> voucherGroupList;
    private Subject<String> voucherGroupSubject;
    @Inject
    public CompaniesAdapter() {
        voucherGroupList = new HashSet<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<String> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(HashSet<String> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return voucherGroupList.size();
    }

    class DataViewHolder extends BaseViewHolder
    {
        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.voucher_text)
        TextView voucherType;
        @BindView(R.id.card_view)
        RelativeLayout cvClickSingleView;

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
            ArrayList<String> newList = new ArrayList<>(voucherGroupList);
            voucherType.setText(newList.get(position));
            image.setOnClickListener(v->voucherGroupSubject.onNext(newList.get(position)));

        }
    }

}
