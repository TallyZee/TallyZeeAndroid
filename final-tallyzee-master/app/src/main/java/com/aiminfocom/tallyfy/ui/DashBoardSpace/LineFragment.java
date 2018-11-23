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
        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.AxisBase;
        import com.github.mikephil.charting.components.XAxis;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.formatter.IAxisValueFormatter;

        import java.util.ArrayList;
        import java.util.List;

        /**
         * A simple {@link Fragment} subclass.
         */
        public class LineFragment extends BaseFragment {


            public LineFragment() {
                // Required empty public constructor
            }


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_line, container, false);
                LineChart lineChart = view.findViewById(R.id.lineChart);
                if (GraphsValue.getDashtype().equalsIgnoreCase("Bills Payable") ||
                        GraphsValue.getDashtype().equalsIgnoreCase("Bills Receivable")) {
                    ArrayList<Entry> entries = new ArrayList<>();
                    entries.add(new Entry(0, GraphsValue.getCount()));
                    entries.add(new Entry(1, GraphsValue.getCount2()));
                    entries.add(new Entry(2, GraphsValue.getCount3()));
                    entries.add(new Entry(3, GraphsValue.getCount4()));
                    entries.add(new Entry(4, GraphsValue.getCount5()));
                    entries.add(new Entry(5, GraphsValue.getCount6()));
                    entries.add(new Entry(6, GraphsValue.getCount7()));
                    entries.add(new Entry(7, GraphsValue.getCount8()));
                    XAxis xAxis = lineChart.getXAxis();
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
                    xAxis.setGranularity(1f);
                    LineDataSet lineDataSet = new LineDataSet(entries,"");
                    lineDataSet.setColor(Color.rgb(150,65,120));
                    LineData lineData = new LineData(lineDataSet);
                    YAxis yAxisRight = lineChart.getAxisRight();
                    yAxisRight.setEnabled(false);
                    YAxis yAxisLeft = lineChart.getAxisLeft();
                    float max = (float) GraphsValue.getHighestCountPay() + 100;
                    yAxisLeft.setAxisMaximum(max);
                    LineData data = new LineData(lineDataSet);
                    lineChart.setData(data);
                } else {
                    ArrayList<Entry> entries = new ArrayList<>();
                    entries.add(new Entry(0, GraphsValue.getCount()));
                    entries.add(new Entry(1, GraphsValue.getCount2()));
                    entries.add(new Entry(2, GraphsValue.getCount3()));
                    entries.add(new Entry(3, GraphsValue.getCount4()));
                    entries.add(new Entry(4, GraphsValue.getCount5()));
                    entries.add(new Entry(5, GraphsValue.getCount6()));
                    entries.add(new Entry(6, GraphsValue.getCount7()));
                    entries.add(new Entry(7, GraphsValue.getCount8()));
                    entries.add(new Entry(8, GraphsValue.getCount9()));
                    entries.add(new Entry(9, GraphsValue.getCount10()));
                    entries.add(new Entry(10, GraphsValue.getCount11()));
                    entries.add(new Entry(11, GraphsValue.getCount12()));
                    XAxis xAxis = lineChart.getXAxis();
                    LineDataSet dataSet = new LineDataSet(entries, "Customized values");
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
                    LineDataSet lineDataSet = new LineDataSet(entries,"");
                    lineDataSet.setColor(Color.rgb(150,65,120));
                    xAxis.setGranularity(1f);
                    YAxis yAxisRight = lineChart.getAxisRight();
                    yAxisRight.setEnabled(false);
                    yAxisRight.setAxisMaximum((float) GraphsValue.getHighestCount() + 1000000);
                    LineData data = new LineData(lineDataSet);
                    lineChart.setData(data);
                }
                return view;
            }

            @Override
            protected void setUp(View view) {

            }

        }

