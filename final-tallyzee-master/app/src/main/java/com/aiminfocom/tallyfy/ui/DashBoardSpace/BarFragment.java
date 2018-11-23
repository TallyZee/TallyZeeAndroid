package com.aiminfocom.tallyfy.ui.DashBoardSpace;


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
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarFragment extends BaseFragment  {


    public BarFragment() {
        // Required empty public constructor
    }


    public static BarFragment instance(ArrayList<Integer> arrayList)
    {
        Bundle bundle = new Bundle();
        BarFragment barFragment = new BarFragment();
        barFragment.setArguments(bundle);
        bundle.putIntegerArrayList("arrayList",arrayList);
        return barFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bar, container, false);
        BarChart barChart = view.findViewById(R.id.barChart);
        if(GraphsValue.getDashtype().equalsIgnoreCase("Bills Payable") ||
                GraphsValue.getDashtype().equalsIgnoreCase("Bills Receivable"))
        {
            ArrayList<BarEntry> barList = new ArrayList<>();
            barList.add(new BarEntry(1f, GraphsValue.getCount()));
            barList.add(new BarEntry(2f, GraphsValue.getCount2()));
            barList.add(new BarEntry(3f, GraphsValue.getCount3()));
            barList.add(new BarEntry(4f, GraphsValue.getCount4()));
            barList.add(new BarEntry(5f, GraphsValue.getCount5()));
            barList.add(new BarEntry(6f, GraphsValue.getCount6()));
            barList.add(new BarEntry(7f, GraphsValue.getCount7()));
            barList.add(new BarEntry(7f, GraphsValue.getCount8()));
            XAxis xAxis = barChart.getXAxis();
            final ArrayList<String> xLabel = new ArrayList<>();
            xLabel.add("No Due");
            xLabel.add("0-30");
            xLabel.add("30-60");
            xLabel.add("60-90");
            xLabel.add("90-120");
            xLabel.add("120-150");
            xLabel.add("150-180");
            xLabel.add("180>");
            xAxis.setDrawGridLines(false);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xLabel.get((int) value);
                }
            });
            BarDataSet barDataSet = new BarDataSet(barList, "BAR GRAPH");
            BarData barData = new BarData(barDataSet);
            barDataSet.setColor(Color.rgb(150,65,120));
            xAxis.setGranularity(1f);
            YAxis yAxisRight = barChart.getAxisRight();
            yAxisRight.setEnabled(false);
            YAxis yAxisLeft = barChart.getAxisLeft();
            yAxisLeft.setGranularity(1f);
            Float max = (float) GraphsValue.getHighestCountPay()+100;
            yAxisLeft.setAxisMaximum(max);
            barChart.setData(barData);
        }
        else {
            ArrayList<BarEntry> barList = new ArrayList<>();
            barList.add(new BarEntry(1f, GraphsValue.getCount()));
            barList.add(new BarEntry(2f, GraphsValue.getCount2()));
            barList.add(new BarEntry(3f, GraphsValue.getCount3()));
            barList.add(new BarEntry(4f, GraphsValue.getCount4()));
            barList.add(new BarEntry(5f, GraphsValue.getCount5()));
            barList.add(new BarEntry(6f, GraphsValue.getCount6()));
            barList.add(new BarEntry(7f, GraphsValue.getCount7()));
            barList.add(new BarEntry(8f, GraphsValue.getCount8()));
            barList.add(new BarEntry(9f, GraphsValue.getCount9()));
            barList.add(new BarEntry(10f, GraphsValue.getCount10()));
            barList.add(new BarEntry(11f, GraphsValue.getCount11()));
            barList.add(new BarEntry(12f, GraphsValue.getCount12()));
            XAxis xAxis = barChart.getXAxis();
            final ArrayList<String> xLabel = new ArrayList<>();
            xLabel.add("Jan");
            xLabel.add("Feb");
            xLabel.add("Mar");
            xLabel.add("Apr");
            xLabel.add("May");
            xLabel.add("Jun");
            xLabel.add("Jul");
            xLabel.add("Aug");
            xLabel.add("Sep");
            xLabel.add("Oct");
            xLabel.add("Nov");
            xLabel.add("Dec");
            xLabel.add("Dec");
            xAxis.setDrawGridLines(false);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xLabel.get((int) value);
                }
            });
            BarDataSet barDataSet = new BarDataSet(barList, "BAR GRAPH");
            BarData barData = new BarData(barDataSet);
            barDataSet.setColor(Color.rgb(150,65,120));
            xAxis.setGranularity(1f);
            YAxis yAxisRight = barChart.getAxisRight();
            yAxisRight.setEnabled(false);
            YAxis yAxisLeft = barChart.getAxisLeft();
            yAxisLeft.setGranularity(1f);
            yAxisLeft.setAxisMaximum((float) GraphsValue.getHighestCount() + 1000000);
            barChart.setData(barData);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    public ArrayList<String> getData()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Jan");
        arrayList.add("Feb");
        arrayList.add("Mar");
        arrayList.add("Apr");
        arrayList.add("May");
        arrayList.add("Jun");
        arrayList.add("Jul");
        arrayList.add("Aug");
        arrayList.add("Sep");
        arrayList.add("Oct");
        arrayList.add("Nov");
        arrayList.add("Dec");
        return arrayList;

    }



}