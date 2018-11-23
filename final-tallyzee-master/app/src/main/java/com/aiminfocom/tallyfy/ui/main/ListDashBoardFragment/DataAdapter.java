package com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by GulshanPC on 27/06/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {


    private Context mContext;
    private ArrayList<ItemsModel> voucherGroupList;
    private Subject<ItemsModel> voucherGroupSubject;
    @Inject
    public DataAdapter() {
        voucherGroupList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();
    }

    public Observable<ItemsModel> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }


    public void setVoucherGroupListAdapter(ArrayList<ItemsModel> quickActions, Context context) {
        this.voucherGroupList = quickActions;
        this.mContext = context;
        notifyDataSetChanged();
    }



    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_parent_recyclerview_layout, parent, false));
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
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.name)
        TextView voucherType;
        @BindView(R.id.parcentage)
                TextView percent;
        @BindView(R.id.pandl)
                ImageView pandl;
        @BindView(R.id.uppar)
        RelativeLayout backgroud;
        @BindView(R.id.single_card)
        CardView cvClickSingleView;
        ArrayList<Integer> c;

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

            ItemsModel itemsModel=voucherGroupList.get(position);
            System.out.println(itemsModel.getAmount()+" "+itemsModel.getImage()+" "+itemsModel.getCurrency()+" "+itemsModel.getModuleName());
           percent.setText(itemsModel.getPecrent());
           pandl.setImageResource(itemsModel.getPandl());
            image.setImageResource(itemsModel.getImage());
            amount.setText(String.valueOf(itemsModel.getAmount()));
            voucherType.setText(itemsModel.getModuleName());



//            backgroud.setBackgroundColor(ContextCompat.getColor(this,itemsModel.getColor()));
//            backgroud.setBackgroundResource(c.get(position));
            cvClickSingleView.setOnClickListener(v -> voucherGroupSubject.onNext(voucherGroupList.get(position)));


        }
    }

}
