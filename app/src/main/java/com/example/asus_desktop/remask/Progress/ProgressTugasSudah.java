package com.example.asus_desktop.remask.Progress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelProgressHistori;
import com.example.asus_desktop.remask.R;
import com.example.asus_desktop.remask.Tools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Asus-Desktop on 6/7/2018.
 */

public class ProgressTugasSudah extends Fragment {

    public ProgressTugasSudah() {
    }

    private static final String TAG = ProgressTugasSudah.class.getSimpleName();

    private RecyclerView recyclerView;
    private ProgressSudahAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ModelProgressHistori modelProgressHistori;
    private TextView txtNama, txtTgl, txTglSelesai;
    ApiClient apiClient;
    private String siswa_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_histori_sudah, container, false);
        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");


        //adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getActivity().setTitle("Progress Tugas");

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();


//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
//        Log.d("id_tugas", String.valueOf(sharedPreferences.getInt("id_tugas", 0)));
        ApiClient.services_get_progressudah.getProgressudah(127).enqueue(new Callback<ModelProgressHistori>() {

            //progressDialog.setMessage("Please wait...");
            //progressDialog.show();

            @Override
            public void onResponse(Call<ModelProgressHistori> call, Response<ModelProgressHistori> response) {
                modelProgressHistori = response.body();
                adapter = new ProgressSudahAdapter(modelProgressHistori.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ModelProgressHistori> call, Throwable t) {

            }
        });


        return view;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
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