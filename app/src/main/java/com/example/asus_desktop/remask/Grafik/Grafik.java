package com.example.asus_desktop.remask.Grafik;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.asus_desktop.remask.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



public class Grafik extends AppCompatActivity implements OnChartValueSelectedListener {

    public Grafik(){}
    private static final String TAG = Grafik.class.getSimpleName();
    private int jmlprgs = 5;
    private int belum = 3;
    private int sudah = 2;
    private int hasilsudah = (sudah * 10)/jmlprgs;
    private int hasilbelum = (belum * 10)/jmlprgs;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Grafik.this.setTitle("Grafik Progress");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);



        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        if(sudah<belum) {
             pieChart.setDescription("Belum Mengerjakan Seluruhnya");
            yvalues.add(new Entry( hasilsudah, 0));
        }
            yvalues.add(new Entry(hasilbelum, 1));


        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Sudah");
        xVals.add("Belum");


        PieData data = new PieData(xVals, dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
      //  pieChart.setOnChartValueSelectedListener(getActivity());

        pieChart.animateXY(1400, 1400);



    }

    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }


    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");




    }
}