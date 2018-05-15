package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelDaftarCatatan;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daftar_Catatan extends Fragment {

    public Daftar_Catatan() {
    }


    Toolbar toolbar;
    CalendarView calendarView;

    private static final String TAG = Daftar_Catatan.class.getSimpleName();

    private RecyclerView recyclerView;
    private DaftarAdapter adapter;
    private ModelDaftarCatatan modelDaftarCatatan;
    ApiClient apiClient;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String date;
    private Calendar calendar;
    private FloatingActionButton fab;
    //private ArrayList<Mahasiswa> mahasiswaArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_custom_calendar, container, false);
        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        calendarView = (CalendarView) view.findViewById(R.id.calendar);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);


        sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
        edit =sharedPreferences.edit();

        Date currentTime = Calendar.getInstance().getTime();

        date = String.valueOf(1900+currentTime.getYear())+"-"+String.valueOf(currentTime.getMonth())+"-"+String.valueOf(currentTime.getDate());
        edit.putString("date", date);
        edit.commit();
        Log.d("date_awal",date);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Year: " + year + "\n" +
                                "Month: " + month + "\n" +
                                "Day of Month: " + dayOfMonth,
                        Toast.LENGTH_LONG).show();
                date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth);
                edit.putString("date", date);
                edit.commit();

                Log.d("date_akhir",date);
                Log.d("date_shared",sharedPreferences.getString("date",""));

            }
        });


        //adapter = new DaftarAdapter(getActivity(),mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getActivity().setTitle("Kalender");


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openCreateNote = new Intent(getActivity(), PopActivity.class);
                startActivity(openCreateNote);
            }
        });

        ApiClient.services_get_daftar_catatan.getDaftarCatatan(1).enqueue(new Callback<ModelDaftarCatatan>() {
            @Override
            public void onResponse(Call<ModelDaftarCatatan> call, Response<ModelDaftarCatatan> response) {
                modelDaftarCatatan = response.body();
                adapter = new DaftarAdapter(getActivity(),modelDaftarCatatan.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ModelDaftarCatatan> call, Throwable t) {

            }
        });

        return view;
    }

}



