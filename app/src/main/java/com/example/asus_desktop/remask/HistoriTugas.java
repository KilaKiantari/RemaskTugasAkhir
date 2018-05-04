package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.activity_histori_tugas);
        Menu menu = toolbar.getMenu();


        adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getActivity().setTitle("Histori Tugas");
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
        mahasiswaArrayList.add(new Mahasiswa("Tugas Matematika","tanggal selesai"));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Kimia",""));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Sejarah",""));
        mahasiswaArrayList.add(new Mahasiswa("Tugas Biologi",""));
    }


}

