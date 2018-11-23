package com.aiminfocom.tallyfy.ui.main.CalenderFilter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceActivity;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.Fragments.DatePickerFragment;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.OncallBack;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.utils.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import  java.util.*;


public class ShowCalender extends DialogFragment implements DatePickerFragment.OnDateRangeSelectedListener {

    private static final String TAG = "Show Calendar";
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener listener;
    @BindView(R.id.list_calender)
    RecyclerView recyclerViewNearby;
    OncallBack callback;
    DatePickerFragment datePickerFragment1;
    public static  ArrayList<UniversalPojo> List;
    public ShowCalender()
    {

    }

//    public static ShowCalender instance(Context context)
//    {
//        Bundle bundle = new Bundle();
//        ShowCalender showCalender = new ShowCalender();
//        showCalender.setArguments(bundle);
//
//        return showCalender;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        return dialog;
        //return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         datePickerFragment1 = DatePickerFragment.newInstance(this,false);
        View rootView = inflater.inflate(R.layout.fragment_show_calender, container, false);
        ButterKnife.bind(this, rootView);
        DashBoardSpaceActivity dashBoardSpaceActivity = new DashBoardSpaceActivity();
        recyclerViewNearby.setLayoutManager(new LinearLayoutManager(getActivity()));
        Calendar calendar = Calendar.getInstance();
        DashBoardSpaceActivity dashBoardSpaceActivity1 = new DashBoardSpaceActivity();

        CalenderListAdapter calenderListAdapter=new CalenderListAdapter(getList());
recyclerViewNearby.setAdapter(calenderListAdapter);

        recyclerViewNearby.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerViewNearby, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position)
                        {

                            case 0:
                                calendar.add(Calendar.DATE,-375);
//                                calendar.add(Calendar.DATE,0);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();
//                                CalendarValues.setDate1(calendar.getTime());
                                dismiss();
                                break;
                            case 1:
                                calendar.add(Calendar.DATE,-382);
//                                calendar.add(Calendar.DATE,-1);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();
                                dismiss();
                                break;
                            case  2:
//                                calendar.add(Calendar.DATE,-7);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();
                                dismiss();
                                break;
                            case 3:
                                calendar.add(Calendar.DATE,-395);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();
                                dismiss();
                                break;
                            case  4:
                                calendar.add(Calendar.DATE,-30);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();
                                dismiss();
                                break;
                            case  5:
                                calendar.add(Calendar.DATE,-395);
//                                calendar.add(Calendar.DATE,-90);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();
                                dismiss();
                                break;
                            case 6:
                                calendar.add(Calendar.DATE,-730);
//                                calendar.add(Calendar.DATE,-365);
                                Toast.makeText(getContext(),""+calendar.getTime(),Toast.LENGTH_SHORT).show();

                                break;
                            case  7:
                                Toast.makeText(getContext()," CUSTOM DATE",Toast.LENGTH_SHORT).show();
                                datePickerFragment1.show(getChildFragmentManager(),"datePicker");

//                                int year = calendar.get(Calendar.YEAR);
//                                int month = calendar.get(Calendar.MONTH);
//                                int date = calendar.get(Calendar.DAY_OF_MONTH);
//                                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                                        getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,listener,year,month,date);
//                                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                                datePickerDialog.show();
//
//                                listener = new DatePickerDialog.OnDateSetListener() {
//                                    @Override
//                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                                        String date = month +"/" + day + "/" + year;
//                                        System.out.println(date);
//                                        Toast.makeText(getContext(),"Selected Date is"+date,Toast.LENGTH_SHORT).show();
//                                    }
//                                };

                                break;

                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        return rootView;
    }


    public static  void listGetter(ArrayList<UniversalPojo> newList)
    {
       List = new ArrayList<>();
       for(int i=0;i<newList.size();i++) {
           List.add(new UniversalPojo(newList.get(i).getPartyName(),newList.get(i).getAmount()));
       }
    }
    ArrayList<String> getList()
    {
        ArrayList<String> list=new ArrayList<>();
        list.add("Today");
        list.add("Yesterday");
        list.add("This week");
        list.add("Last week");
        list.add("Last month");
        list.add("This quarter");
        list.add("This year");
        list.add("Custom Date");




        return list;
    }

    public void passData(int data) {

    }

    public void getDate(ArrayList<UniversalPojo> newList,Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dt = simpleDateFormat.format(date);
        System.out.println("DT IS"+dt);
        String date1 = String.valueOf(dt).replace("/","");
        System.out.println("DT2 IS"+date1);
        for(int j=0;j<newList.size();j++)
        {

            String date2 = newList.get(j).getBillOverdue();
            System.out.println("Date 2 is"+date2);
            if(date2.equalsIgnoreCase(date1))
            {
                System.out.println("THIS VALUES ARE COMPARED"+ date2);
            }
        }
    }


    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        Log.d("range : ","from: "+startDay+"-"+startMonth+"-"+startYear+" to : "+endDay+"-"+endMonth+"-"+endYear );
        Toast.makeText(getContext(),startDay+"and"+endDay,Toast.LENGTH_LONG).show();
        dismiss();
    }
}



