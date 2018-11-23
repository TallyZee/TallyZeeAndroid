package com.aiminfocom.tallyfy.ui.DashBoardSpace;




import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.GraphsValue;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.OutstandingParameters;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.utils.AppConstants;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieFragment extends BaseFragment{


    public  PieFragment()
    {

    }


//    public static PieFragment instance(ArrayList<Integer> arrayList)
//    {
//        Bundle bundle = new Bundle();
//        PieFragment pieFragment = new PieFragment();
//        bundle.putIntegerArrayList("Value",arrayList);
//        pieFragment.setArguments(bundle);
//        return pieFragment;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie, container, false);
        PieChart pieChart = view.findViewById(R.id.pieChart);


        if(GraphsValue.getDashtype().equalsIgnoreCase("Bills Payable") ||
                GraphsValue.getDashtype().equalsIgnoreCase("Bills Receivable"))
        {
            ArrayList<PieEntry> pieList = new ArrayList<>();
            pieList.add(new PieEntry(GraphsValue.getCount(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount2(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount3(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount4(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount5(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount6(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount7(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount8(), ""));
            PieDataSet pieDataSet = new PieDataSet(pieList, "PIE CHART");
            final int[] MY_COLORS = {Color.rgb(180,115,167), Color.rgb(255,187,235), Color.rgb(252,0,168),
                    Color.rgb(214,100,188), Color.rgb(120,2,97), Color.rgb(145,60,111), Color.rgb(204,5,124)};
            ArrayList<Integer> colors = new ArrayList<Integer>();
            ArrayList<String> title = new ArrayList<String>();
            for(int c: MY_COLORS) colors.add(c);

            pieDataSet.setColors(colors);

            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
        }
        else {
            ArrayList<PieEntry> pieList = new ArrayList<>();
            pieList.add(new PieEntry(GraphsValue.getCount(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount2(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount3(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount4(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount5(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount6(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount7(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount8(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount9(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount10(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount11(), ""));
            pieList.add(new PieEntry(GraphsValue.getCount12(), ""));
            PieDataSet pieDataSet = new PieDataSet(pieList, "PIE CHART");
            final int[] MY_COLORS = {Color.rgb(180,115,167), Color.rgb(255,187,235), Color.rgb(252,0,168),
                    Color.rgb(214,100,188), Color.rgb(120,2,97), Color.rgb(145,60,111), Color.rgb(204,5,124)};
            ArrayList<Integer> colors = new ArrayList<Integer>();

            for(int c: MY_COLORS) colors.add(c);

            pieDataSet.setColors(colors);
            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

    }


    public ArrayList<Integer> compareValues(List<BillsReceables> list)
    {
        ArrayList<Integer> countList = new ArrayList<>();

        int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0;
        ArrayList<Integer> arrayList= new ArrayList<>();

        for(int i=0;i<list.size();i++)
        { if(!list.get(i).getBillOverDue().equals("") || !list.get(i).getBillOverDue().isEmpty()) {
            arrayList.add(Integer.valueOf(list.get(i).getBillOverDue()));
            System.out.println(arrayList.get(i));
        }



        }
        for(int j=0;j<arrayList.size();j++)
        {
            System.out.println("2nd ArrayList"+arrayList.get(j));
            if(arrayList.get(j)>0 && arrayList.get(j)<30)
            {
                count1++;
            }
            if(arrayList.get(j)>31 && arrayList.get(j)<60)
            {
                count2++;
            }
            if(arrayList.get(j)>61&& arrayList.get(j)<90)
            {
                count3++;
            }
            if(arrayList.get(j)>91&& arrayList.get(j)<120)
            {
                count4++;
            }
            if(arrayList.get(j)>121&& arrayList.get(j)<150)
            {
                count5++;
            }
            if(arrayList.get(j)>151&& arrayList.get(j)<180)
            {
                count6++;
            }
            if(arrayList.get(j)>180)
            {
                count7++;
            }


        }
        System.out.println("Data Between 0-30 are"+count1);
        System.out.println("Data Between 30-60 are"+count2);
        System.out.println("Data Between 60-90 are"+count3);
        System.out.println("Data Between 90-120 are"+count4);
        System.out.println("Data Between 120-150 are"+count5);
        System.out.println("Data Between 150-180 are"+count6);
        System.out.println("Data Between 180 and above are"+count7);
        countList.add(count1);
        countList.add(count2);
        countList.add(count3);
        countList.add(count4);
        countList.add(count5);
        countList.add(count6);
        countList.add(count7);
        return countList;
    }

}

