package com.piedpiper.epimaps;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Graph extends AppCompatActivity  {

    BarChart barChart;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        barChart = (BarChart) findViewById(R.id.bargraph);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
//        barChart.set
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        barChart.setTouchEnabled(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 44f));
        barEntries.add(new BarEntry(3, 45f));
        barEntries.add(new BarEntry(4, 34f));
        barEntries.add(new BarEntry(5, 25f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data Set1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);

//        barChart.setFitBars(true);
        barChart.setData(data);

        String[] months = new String[]{"Jan", "Feb", "March", "April", "May"};
        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyAxis(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setOnChartValueSelectedListener( new OnChartValueSelectedListener() {
            public void onValueSelected(Entry e, Highlight h) {
                lineChart=(LineChart)findViewById(R.id.linegraph);
                lineChart.setVisibility(View.GONE);
                setData(40,60);
                lineChart.animateX(1000);
            }

            @Override
            public void onNothingSelected() {

            }
        });


//        xAxis.setGranularity(1f);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setAxisMinimum(1);


//        lineChart=(LineChart)findViewById(R.id.linegraph);
//        lineChart.setVisibility(View.GONE);
//        setData(40,60);
//        lineChart.animateX(1000);
    }
    private void setData(int count,int range){
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        ArrayList<Entry>Yvalue=new ArrayList<>();
        for(int i=0;i<count;i++){
            float val=(float)(Math.random()*range)+250;
            Yvalue.add(new Entry(i,val));
        }
        LineDataSet set=new LineDataSet(Yvalue,"Data Set");

        LineData data=new LineData(set);
        lineChart.setData(data);
        lineChart.setVisibility(View.VISIBLE);


    }

    public class MyAxis implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxis(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }
}

