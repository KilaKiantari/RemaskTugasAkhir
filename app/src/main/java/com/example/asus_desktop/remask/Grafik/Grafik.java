package com.example.asus_desktop.remask.Grafik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelGrafikProgress;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Grafik extends AppCompatActivity implements OnChartValueSelectedListener {

    public Grafik(){}
    private static final String TAG = Grafik.class.getSimpleName();
    ModelGrafikProgress modelGrafikProgress;
    String id_tugas;


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

        final PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        final ProgressDialog progressDialog = new ProgressDialog(Grafik.this);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        Intent tugas = getIntent();
        id_tugas = tugas.getStringExtra("id_tugas");

        ApiClient.services_get_grafik_progress.getGrafikProgress(id_tugas).enqueue(new Callback<ModelGrafikProgress>() {
            @Override
            public void onResponse(Call<ModelGrafikProgress> call, Response<ModelGrafikProgress> response) {
                modelGrafikProgress = response.body();
                Log.e("Response Search ", "Code : " + response.code());
                Integer sudah = response.body().getJml();
                Integer belum = response.body().getJmlblm();
                if (sudah + belum == 0) {
                    Toast.makeText(Grafik.this, "Tidak ada progress", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else if (sudah + belum != 0) {
                    Toast.makeText(Grafik.this, "sudah =" + sudah, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Grafik.this, "belum =" + belum, Toast.LENGTH_SHORT).show();


                    ArrayList<Entry> yvalues = new ArrayList<Entry>();


                    yvalues.add(new Entry(sudah,0));
                    yvalues.add(new Entry(belum,1));


                    PieDataSet dataSet = new PieDataSet(yvalues, "Data");
                    Log.e("Coba ", "Nilai : " + yvalues);

                    ArrayList<String> xVals = new ArrayList<String>();

                    xVals.add("Sudah");
                    xVals.add("Belum");

                    PieData data = new PieData(xVals, dataSet);
                    // In Percentage term
                    data.setValueFormatter(new PercentFormatter());
                    // Default value
                    //data.setValueFormatter(new DefaultValueFormatter(0));
                    pieChart.setData(data);

                    dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                    data.setValueTextSize(13f);
                    data.setValueTextColor(Color.DKGRAY);
                    progressDialog.dismiss();

                    if (belum > sudah) {
                        pieChart.setDescription("Belum Mengerjakan Seluruhnya!");
                    } else if (sudah > belum) {
                        pieChart.setDescription("Tingkatkan!");
                    }

                } else {
                    Toast.makeText(Grafik.this, "SALAH", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<ModelGrafikProgress> call, Throwable t) {

                Toast.makeText(Grafik.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });



        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        pieChart.animateXY(1400, 1400);

//        ArrayList<Entry> yvalues = new ArrayList<Entry>();
//
//        yvalues.add(new Entry(3, 1));
//        yvalues.add(new Entry(2,2));
//
//        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");
//
//        ArrayList<String> xVals = new ArrayList<String>();
//        xVals.add("Sudah");
//        xVals.add("Belum");
//        PieData data = new PieData(xVals, dataSet);
//        // In Percentage term
//        data.setValueFormatter(new PercentFormatter());
//        // Default value
//        //data.setValueFormatter(new DefaultValueFormatter(0));
//        pieChart.setData(data);
//        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        data.setValueTextSize(13f);
//        data.setValueTextColor(Color.DKGRAY);


        pieChart.setOnChartValueSelectedListener(this);
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