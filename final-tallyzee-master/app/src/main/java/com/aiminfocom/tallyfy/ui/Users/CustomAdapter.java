package com.aiminfocom.tallyfy.ui.Users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    String[] names;
    int[] images;
    Context context;
    public CustomAdapter(Context context,String[] names,int[] images)
    {
        this.context=context;
        this.names=names;
        this.images=images;
    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridViewAndroid;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridViewAndroid = new View(context);
            convertView = inflater.inflate(R.layout.gridd_child, parent, false);
            ImageView imageView = convertView.findViewById(R.id.image);
            TextView title = convertView.findViewById(R.id.title);
            RelativeLayout relativeLayout = convertView.findViewById(R.id.card_view);
            imageView.setImageResource(images[position]);
            title.setText(names[position]);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position)
                    {
                        case 0:
                            Toast.makeText(context,"User Clicked",Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            Toast.makeText(context,"Company Clicked",Toast.LENGTH_LONG).show();
                            break;

                    }
                }
            });

        }else
        {
            gridViewAndroid = (View) convertView;
        }
        return convertView;
    }
}
