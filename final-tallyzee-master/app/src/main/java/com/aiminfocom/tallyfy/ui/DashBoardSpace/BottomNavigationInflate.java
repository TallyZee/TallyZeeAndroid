package com.aiminfocom.tallyfy.ui.DashBoardSpace;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Group.GroupFragment;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContainer;
import com.aiminfocom.tallyfy.ui.Users.UserFragment;
import com.aiminfocom.tallyfy.ui.main.ClientFrg.ClientUpFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.DashBoardFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.ItemsModel;

import java.util.ArrayList;
import java.util.HashSet;

public class BottomNavigationInflate {


    public static void setUpNavigation(BottomNavigationView bottomNavigationView, ArrayList<ItemsModel> arrayList, FragmentManager fragmentManager, HashSet<String> hashSet)
    {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                            fragmentManager
                                    .beginTransaction()
                                    .disallowAddToBackStack()
                                    .add(R.id.container, DashBoardFragment.newInstance(arrayList,"home",hashSet),DashBoardFragment.TAG)
                                    .commit();
                        break;
                    case R.id.client:

                       fragmentManager
                                .beginTransaction()
                                .disallowAddToBackStack()
                                .add(R.id.container, ClientUpFragment.newInstance(), DashBoardFragment.TAG)
                                .commit();
                        break;


                    case R.id.Logo:
                        fragmentManager
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .replace(R.id.container, UserFragment.newInstance(), UserFragment.TAG)
                        .commit();
                        break;

                    case R.id.item:
                        fragmentManager
                                .beginTransaction()
                                .disallowAddToBackStack()
                                .replace(R.id.container, ItemContainer.newInstance(), ItemContainer.TAG)
                                .commit();
                            break;

                    case R.id.report:
                       fragmentManager
                                .beginTransaction()
                                .disallowAddToBackStack()
                                .add(R.id.container, GroupFragment.newInstance(), GroupFragment.TAG)
                                .commit();
                        break;
                }
                return false;
            }
        });
    }

}
