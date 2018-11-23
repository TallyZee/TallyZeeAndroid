package com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment;

import android.content.Context;
import android.support.annotation.NonNull;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context ctx;
    private ArrayList<ItemsModel> arrayList;
    private Subject<ItemsModel> voucherGroupSubject;
    @Inject
    CustomAdapter()
    {
        arrayList = new ArrayList<>();
        voucherGroupSubject = PublishSubject.create();

    }
    public Observable<ItemsModel> getVoucherGroupListclick() {
        return voucherGroupSubject;
    }

    public void setVoucherGroupListAdapter(ArrayList<ItemsModel> quickActions, Context context) {
        this.arrayList = quickActions;
        this.ctx = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_layout,parent,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends BaseViewHolder {


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

        CustomViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, itemView);
            c=new ArrayList<Integer>();
            c.add(R.color.stockSummaryColor);
            c.add(R.color.deliveryColor);
            c.add(R.color.receiptColor);
        }

        @Override
        protected void clear() {

        }
        public void onBind(int position) {
            super.onBind(position);

            ItemsModel itemsModel=arrayList.get(position);
            System.out.println(itemsModel.getAmount()+" "+itemsModel.getImage()+" "+itemsModel.getCurrency()+" "+itemsModel.getModuleName());
            percent.setText(itemsModel.getPecrent());
            pandl.setImageResource(itemsModel.getPandl());
            image.setImageResource(itemsModel.getImage());
            amount.setText(String.valueOf(itemsModel.getAmount()));
            voucherType.setText(itemsModel.getModuleName());



//            backgroud.setBackgroundColor(ContextCompat.getColor(this,itemsModel.getColor()));
            backgroud.setBackgroundResource(c.get(position));
            cvClickSingleView.setOnClickListener(v -> voucherGroupSubject.onNext(arrayList.get(position)));


        }
    }
}
