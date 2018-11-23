package com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by GulshanPC on 07/08/2018.
 */

public class CustomGrid extends BaseAdapter{
    private Context mContext;

    ArrayList<ItemsModel> itemsModels;
    public CustomGrid(Context c,ArrayList<ItemsModel> itemsModels ) {
        mContext = c;
        this.itemsModels=itemsModels;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemsModels.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridd_child, null);
//            TextView name = (TextView) grid.findViewById(R.id.grid_text);
//            ImageView logo = (ImageView)grid.findViewById(R.id.grid_image);
//            TextView balance=grid.findViewById(R.id.balance);
//            TextView percentage=grid.findViewById(R.id.parcentage);
//            name.setText(itemsModels.get(position).getModuleName());
//            logo.setImageResource(itemsModels.get(position).getImage());
//            balance.setText(String.valueOf(itemsModels.get(position).getAmount()));
//            percentage.setText(itemsModels.get(position).getCurrency());
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}