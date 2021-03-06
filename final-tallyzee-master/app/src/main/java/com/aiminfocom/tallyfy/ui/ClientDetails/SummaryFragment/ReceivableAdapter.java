package com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
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

public class ReceivableAdapter extends RecyclerView.Adapter<ReceivableAdapter.RecivableViewHolder> {
    private List<UniversalPojo> universalPojoList;
    private Subject<UniversalPojo> subject;
    private Context context;

    @Inject
    ReceivableAdapter() {
        universalPojoList = new ArrayList<>();
        subject = PublishSubject.create();
    }


    public Observable<UniversalPojo> getReceivableClick() {
        return subject;
    }


    public void setCLick(List<UniversalPojo> arrayList, Context context) {
        this.context = context;
        this.universalPojoList = arrayList;
    }

    @NonNull
    @Override
    public ReceivableAdapter.RecivableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sales_layout_summary_fragment, viewGroup, false);
        RecivableViewHolder viewHolder = new RecivableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceivableAdapter.RecivableViewHolder receiptViewHolder, int i) {
        receiptViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return universalPojoList.size();
    }

    class RecivableViewHolder extends BaseViewHolder {
        @BindView(R.id.party_Name)
        TextView partyName;

        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.item)
        RelativeLayout item;

        RecivableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }


        @Override
        public void onBind(int position) {
            super.onBind(position);

            if (position == 0) {
                partyName.setTextSize(18f);
            }

            partyName.setText(universalPojoList.get(position).getPartyName());
            amount.setText(universalPojoList.get(position).getAmount());
            item.setOnClickListener(v -> subject.onNext(universalPojoList.get(position)));

        }
    }
}
