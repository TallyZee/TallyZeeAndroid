//package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.aiminfocom.tallyfy.R;
//import com.aiminfocom.tallyfy.data.Model.ItemsModel;
//import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import io.reactivex.Observable;
//import io.reactivex.subjects.PublishSubject;
//import io.reactivex.subjects.Subject;
//
//
//public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
//
//    private ArrayList<ItemsModel> arrayList;
//    private Subject<ItemsModel> subject;
//    private Context mContext;
//
//
//    @Inject
//    public CategoryAdapter()
//    {
//        arrayList = new ArrayList<>();
//        subject = PublishSubject.create();
//    }
//
//    public Observable<ItemsModel> getClick()
//    {
//        return subject;
//    }
//
//    public void setClick(ArrayList<ItemsModel> arrayList,Context context)
//    {
//        this.arrayList=arrayList;
//        this.mContext=context;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_layout,viewGroup,false);
//        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
//        categoryViewHolder.onBind(i);
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    class CategoryViewHolder extends BaseViewHolder {
//
//        @BindView(R.id.item)
//        RelativeLayout item;
//
//        @BindView(R.id.item_name)
//        TextView itemName;
//        @BindView(R.id.avg_amount)
//        TextView avgAmount;
//
//        @BindView(R.id.total_amount)
//        TextView totalAmount;
//
//        @BindView(R.id.stdCost_amount)
//        TextView stdCost;
//
//        @BindView(R.id.stdCost_price)
//        TextView stdPrice;
//
//        @BindView(R.id.reorder_amount)
//        TextView reorder;
//        @BindView(R.id.min_order_amount)
//        TextView minOrder;
//        @BindView(R.id.amount)
//        TextView quantity;
//
//
//
//
//
//        public CategoryViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this,itemView);
//            }
//
//        @Override
//        protected void clear() {
//
//        }
//
//
//        @Override
//        public void onBind(int position) {
//            super.onBind(position);
//
//            itemName.setText(arrayList.get(position).getItemName());
//            totalAmount.setText(arrayList.get(position).getItemRate());
//            stdCost.setText(arrayList.get(position).getStandardCost());
//            stdPrice.setText(arrayList.get(position).getStandardPrice());
//            reorder.setText(arrayList.get(position).getReorder());
//            minOrder.setText(arrayList.get(position).getMinOrder());
//            avgAmount.setText(arrayList.get(position).getAvgPerRate());
//            quantity.setText(arrayList.get(position).getQuantityNo());
//
//            item.setOnClickListener(v->subject.onNext(arrayList.get(position)));
//
//        }
//    }
//}
