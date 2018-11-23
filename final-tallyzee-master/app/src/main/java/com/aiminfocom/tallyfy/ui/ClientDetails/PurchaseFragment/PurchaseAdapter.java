package com.aiminfocom.tallyfy.ui.ClientDetails.PurchaseFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.ClientDetails.SoldFragment.SolddAdapter;
import com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment.CreditNoteAdapter;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.SoldViewHolder> {


    private List<UniversalPojo> list;
    private Subject<UniversalPojo> subject;
    private Context context;
    @Inject
    PurchaseAdapter()
    {
        list = new ArrayList<>();
        subject = PublishSubject.create();
    }

    public Observable<UniversalPojo> getClick()
    {
        return subject;
    }

    public void setClick(List<UniversalPojo> list,Context context)
    {
        this.context=context;
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchaseAdapter.SoldViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.purcahse_layout,viewGroup,false);
        SoldViewHolder viewHolder = new SoldViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.SoldViewHolder soldViewHolder, int i) {
        soldViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SoldViewHolder extends BaseViewHolder {


        @BindView(R.id.relativeLayout)
        RelativeLayout relativeLayout;
        @BindView(R.id.partyName)
        TextView partyName;

        @BindView(R.id.unit_price)
        TextView unit;

        @BindView(R.id.lastSold)
        TextView lastSold;

        @BindView(R.id.rate)
        TextView rate;

        public SoldViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }


        @Override
        public void onBind(int position) {
            super.onBind(position);
            partyName.setText(list.get(position).getPartyName());
            unit.setText(list.get(position).getAmount());
            lastSold.setText("Last Sold :"+list.get(position).getGetBillRef());
            rate.setText("Rate :"+list.get(position).getBillOverdue());

            relativeLayout.setOnClickListener(v->subject.onNext(list.get(position)));
        }
    }
}
