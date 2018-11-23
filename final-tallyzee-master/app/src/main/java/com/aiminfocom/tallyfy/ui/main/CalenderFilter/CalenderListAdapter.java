package com.aiminfocom.tallyfy.ui.main.CalenderFilter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;

import java.util.List;

public class CalenderListAdapter extends RecyclerView.Adapter<CalenderListAdapter.MyViewHolder>{

private List<String> titles;

public CalenderListAdapter(List<String> titles) {
        this.titles = titles;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false));
        }

@Override
public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        String title = titles.get(i);
        myViewHolder.title.setText(title);
        }

@Override
public int getItemCount() {
        return titles.size();
        }

public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView title;

    public MyViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title_Tv);
    }
}

}