package com.aiminfocom.tallyfy.ui.Rating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends BaseFragment {

    public static final String TAG = "RatingFragment";

    public RatingFragment() {
        // Required empty public constructor
    }


    public static RatingFragment getInstance()
    {
        Bundle bundle = new Bundle();
        RatingFragment ratingFragment = new RatingFragment();
        ratingFragment.setArguments(bundle);
        return ratingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false);
    }

    @Override
    protected void setUp(View view) {

    }
}
