package com.aiminfocom.tallyfy.ui.main.NullFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoSummaryFragment extends Fragment {


    public NoSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_summary, container, false);
    }

}
