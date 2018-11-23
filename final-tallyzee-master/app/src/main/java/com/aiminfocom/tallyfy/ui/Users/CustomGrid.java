package com.aiminfocom.tallyfy.ui.Users;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.Rating.AlertDialogRating;
import com.aiminfocom.tallyfy.ui.about.AboutFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;

import java.util.ArrayList;

/**
 * Created by GulshanPC on 07/08/2018.
 */

public class CustomGrid extends BaseAdapter{
    private Context mContext;
    private onClickInterface onClickInterface;
    private FragmentManager fragmentManager;
    private UsersFragment usersFragment =new UsersFragment();
    ArrayList<ItemsModel> itemsModels;
    public CustomGrid(Context c, ArrayList<ItemsModel> itemsModels,FragmentManager fragmentManager,onClickInterface onClickInterface ) {
        mContext = c;
        this.itemsModels=itemsModels;
        this.fragmentManager=fragmentManager;
        this.onClickInterface = onClickInterface;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemsModels.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridd_child, null);
            TextView name = (TextView) grid.findViewById(R.id.title);
            ImageView logo = (ImageView)grid.findViewById(R.id.image);
            logo.setImageResource(itemsModels.get(position).getImage());
            name.setText(itemsModels.get(position).getModuleName());
            RelativeLayout relativeLayout = grid.findViewById(R.id.card_view);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                      onClickInterface.onUserCallback();
                        break;

                    case 1:
                        onClickInterface.onCompanyCallback();
                        break;
                    case 2:
                        onClickInterface.onAboutCallback();
                        break;
                    case 3:
                        AlertDialogRating alertDialogRating = new AlertDialogRating(mContext);
                        alertDialogRating.create();
                        alertDialogRating.show();
                        onClickInterface.onRateUsCallback();
                        break;
                    case 4:
                        onClickInterface.onSettingCallback();
                        break;
                    case 5:
                        com.aiminfocom.tallyfy.ui.Users.AlertDialog alertDialog = new com.aiminfocom.tallyfy.ui.Users.AlertDialog(mContext);
                        alertDialog.create();
                        alertDialog.show();
                        break;

                    case 6:
                        onClickInterface.onTutorialCallback();
                        break;
                    case 7:
                        onClickInterface.onHelpCallback();
                        break;
                    case 8:
                        onClickInterface.onContactUsCallback();
                        break;

                    case 9:
                        onClickInterface.onRequestCallback();
                        break;



                }
            }
        });



        return grid;
    }
}