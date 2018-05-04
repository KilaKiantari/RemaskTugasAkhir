package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Skala_Prioritas extends Fragment {

    public Skala_Prioritas(){}
    private static final String TAG = Skala_Prioritas.class.getSimpleName();

    private RecyclerView recyclerView;
    private SkalaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_skala, container, false);
        addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);




        adapter = new SkalaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getActivity().setTitle("Skala Prioritas Tugas");
        return view;


    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(getActivity(), Tools.class);
                startActivity(ab);
                return true;
            //case R.id.a:

            //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Tugas Matematika",""));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Kimia",""));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Sejarah",""));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Biologi",""));
    }


}

