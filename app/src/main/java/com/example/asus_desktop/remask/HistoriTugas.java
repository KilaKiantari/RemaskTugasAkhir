package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Asus-Desktop on 4/14/2018.
 */

public class HistoriTugas extends Fragment {

    public HistoriTugas() {
    }

    private static final String TAG = HistoriTugas.class.getSimpleName();

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histori_tugas, container, false);
        addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getActivity().setTitle("Histori Tugas");
        return view;


    }


    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Tugas Matematika"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Kimia"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Sejarah"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Biologi"));
    }


}

