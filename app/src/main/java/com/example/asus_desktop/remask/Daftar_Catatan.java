package com.example.asus_desktop.remask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;
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
    private String siswa_id;
    private Calendar calendar;
    private FloatingActionButton fab;
    private Date currentTime = Calendar.getInstance().getTime();
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
        siswa_id = sharedPreferences.getString("siswa_id","");
        edit =sharedPreferences.edit();



        setCurrentNote();
//        refreshRecycler();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                month = month+1;
                String sMonth,sDay;
                if (String.valueOf(month).length()==1){
                    sMonth = "0"+String.valueOf(month);
                }
                else {
                    sMonth = String.valueOf(month);
                }
                if (String.valueOf(dayOfMonth).length()==1){
                    sDay = "0"+String.valueOf(dayOfMonth);
                }
                else {
                    sDay = String.valueOf(dayOfMonth);
                }
                Toast.makeText(getActivity().getApplicationContext(),
                        "Year: " + year + "\n" +
                                "Month: " + month + "\n" +
                                "Day of Month: " + dayOfMonth,
                        Toast.LENGTH_LONG).show();

                fillRecycler(String.valueOf(year),sMonth,sDay);

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



        return view;
    }


    private void setCurrentNote() {

        final String month, day;
        int m = currentTime.getMonth() + 1;
        int d = currentTime.getDate();

        if (String.valueOf(m).length() == 1) {
            month = "0" + String.valueOf(m);
        } else {
            month = String.valueOf(m);
        }
        if (String.valueOf(d).length() == 1) {
            day = "0" + String.valueOf(d);
        } else {
            day = String.valueOf(d);
        }

        date = String.valueOf(1900 + currentTime.getYear()) + "-" + month + "-" + day;
        edit.putString("date", date);
        edit.commit();
        Log.d("date_awal", date);
  //  }

        //Toast.makeText(getActivity(), ""+siswa_id, Toast.LENGTH_SHORT).show();


        ApiClient.services_get_daftar_catatan.getDaftarCatatan(siswa_id).enqueue(new Callback<ModelDaftarCatatan>() {
            @Override
            public void onResponse(Call<ModelDaftarCatatan> call, Response<ModelDaftarCatatan> response) {
                if(response.isSuccessful()) {
                    modelDaftarCatatan = response.body();
                    fillRecycler(String.valueOf(1900 + currentTime.getYear()), month, day);

                }


            }

            @Override
            public void onFailure(Call<ModelDaftarCatatan> call, Throwable t) {

            }
        });
    }



    private void fillRecycler(String year,String sMonth, String sDay) {
        date = String.valueOf(year)+"-"+sMonth+"-"+sDay;
        edit.putString("date", date);
        edit.commit();

        Log.d("date_akhir",date);

        Log.d("date_shared",sharedPreferences.getString("date",""));

        ArrayList<Result> filterResult = new ArrayList<>();
        for(int i= 0;i<modelDaftarCatatan.getResults().size();i++){
            Log.d("maxString",modelDaftarCatatan.getResults().get(i).getTanggalTugas());
            Log.d("subString",modelDaftarCatatan.getResults().get(i).getTanggalTugas().substring(0,10));
            if (modelDaftarCatatan.getResults().get(i).getTanggalTugas().substring(0,10).equals(date)) {
                Log.d("modelDaftarCatatan", modelDaftarCatatan.getResults().get(i).getTanggalTugas().substring(0, 10));
                Log.d("date", date);
                filterResult.add(modelDaftarCatatan.getResults().get(i));
                Log.d("filterResult", filterResult.get(0).getTanggalTugas());


            }
            }


        adapter = new DaftarAdapter(getActivity(),filterResult);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private void refreshRecycler() {

//        siswa_id = sharedPreferences.getString("siswa_id","");
//        Toast.makeText(getActivity(), ""+siswa_id, Toast.LENGTH_SHORT).show();

        ApiClient.services_get_daftar_catatan.getDaftarCatatan(siswa_id).enqueue(new Callback<ModelDaftarCatatan>() {
            @Override
            public void onResponse(Call<ModelDaftarCatatan> call, Response<ModelDaftarCatatan> response) {
                modelDaftarCatatan = response.body();
//                adapter = new DaftarAdapter(getActivity(),modelDaftarCatatan.getResults());
//                adapter.notifyDataSetChanged();
//                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ModelDaftarCatatan> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshRecycler();
    }

}



