package com.example.asus_desktop.remask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelSkalaPrioritas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Skala_Prioritas extends Fragment {

    public Skala_Prioritas() {
    }

    private static final String TAG = Skala_Prioritas.class.getSimpleName();

    private RecyclerView recyclerView;
    private SkalaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ModelSkalaPrioritas modelSkalaPrioritasa;
    private TextView txtNama, txtTgl, txtNoHp;
    ApiClient apiClient;
    private Spinner mSpinner;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_skala, container, false);


        Spinner mSpinner = (Spinner) view.findViewById(R.id.spinner);


        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getActivity().setTitle("Skala Prioritas Tugas");


        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this.getActivity(), R.array.sort_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    sortByKat();
                } else {
                    sortByTgl();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


          //  setupSort();
        return view;

    }


   // private void setupSort() {


    private void sortByKat() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();


        ApiClient.services_get_skala.getSkala(1).enqueue(new Callback<ModelSkalaPrioritas>() {
            @Override
            public void onResponse(Call<ModelSkalaPrioritas> call, Response<ModelSkalaPrioritas> response) {
                modelSkalaPrioritasa = response.body();
                adapter = new SkalaAdapter(modelSkalaPrioritasa.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ModelSkalaPrioritas> call, Throwable t) {

            }
        });


    }

    private void sortByTgl() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();


        ApiClient.services_get_skala_baru.getSkalaBaru(1).enqueue(new Callback<ModelSkalaPrioritas>() {
            @Override
            public void onResponse(Call<ModelSkalaPrioritas> call, Response<ModelSkalaPrioritas> response) {
                modelSkalaPrioritasa = response.body();
                adapter = new SkalaAdapter(modelSkalaPrioritasa.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ModelSkalaPrioritas> call, Throwable t) {

            }
        });

        // }

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

}

