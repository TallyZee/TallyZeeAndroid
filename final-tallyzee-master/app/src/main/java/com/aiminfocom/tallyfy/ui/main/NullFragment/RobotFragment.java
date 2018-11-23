package com.aiminfocom.tallyfy.ui.main.NullFragment;


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
public class RobotFragment extends Fragment {


    public RobotFragment() {
        // Required empty public constructor
    }


    public static RobotFragment instance()
    {
        RobotFragment robotFragment = new RobotFragment();

        return robotFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_robot, container, false);
    }


}
