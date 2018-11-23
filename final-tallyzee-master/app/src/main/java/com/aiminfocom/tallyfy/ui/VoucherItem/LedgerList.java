package com.aiminfocom.tallyfy.ui.VoucherItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.Model.SalesOrderLedgers;
import com.aiminfocom.tallyfy.data.db.model.Ledger;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class LedgerList  extends RecyclerView.Adapter<LedgerList.DataViewHolder> {


private Context mContext;
private ArrayList<UniversalPojo> voucherGroupList;
private Subject<UniversalPojo> voucherGroupSubject;
@Inject
public LedgerList() {
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
public LedgerList.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LedgerList.DataViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.ledger_list, parent, false));
        }

@Override
public void onBindViewHolder(LedgerList.DataViewHolder holder, int position) {
        holder.onBind(position);
        }

@Override
public int getItemCount() {
        return voucherGroupList.size();
        }

class DataViewHolder extends BaseViewHolder
{
    @BindView(R.id.ledgerName)
    TextView ledgerName;

    @BindView(R.id.amount)
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

           ledgerName.setText(voucherGroupList.get(position).getPartyName());
           amount.setText(voucherGroupList.get(position).getAmount());


       // item.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));
    }
}

}

