package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;

public class Daftar_Catatan extends Fragment {

    public Daftar_Catatan() {
    }


    Toolbar toolbar;
    CalendarView calendar;

    private static final String TAG = Daftar_Catatan.class.getSimpleName();

    private RecyclerView recyclerView;
    private DaftarAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_custom_calendar, container, false);
        addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        calendar = (CalendarView) view.findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Year: " + year + "\n" +
                                "Month: " + month + "\n" +
                                "Day of Month: " + dayOfMonth,
                        Toast.LENGTH_LONG).show();

            }
        });


        adapter = new DaftarAdapter(getActivity(),mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getActivity().setTitle("Kalender");
        return view;

    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Tugas Matematika","belum"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Kimia","belum"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Sejarah","belum"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Biologi","belum"));
    }


}



