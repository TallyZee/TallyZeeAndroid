package com.aiminfocom.tallyfy.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoCompanyFragment extends Fragment {


    public NoCompanyFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_company, container, false);

        return view;
    }

}
