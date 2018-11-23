package com.aiminfocom.tallyfy.ui.DashBoardSpace;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class PagerAdapter extends FragmentPagerAdapter {

private static int NUM_ITEMS = 3;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new BarFragment();

            case 1:
                return new PieFragment();


            case 2:
                return new LineFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
