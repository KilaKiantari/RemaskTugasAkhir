package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.UserHistoriSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoriTugas extends Fragment {

    public HistoriTugas() {
    }

    private static final String TAG = HistoriTugas.class.getSimpleName();

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserHistoriSiswa userHistoriSiswa;
    private TextView txtNama, txtTgl, txTglSelesai;
    ApiClient apiClient;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histori_tugas, container, false);
        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.activity_histori_tugas);
        Menu menu = toolbar.getMenu();



        //adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getActivity().setTitle("Histori Tugas");

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();


//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
//        Log.d("id_tugas", String.valueOf(sharedPreferences.getInt("id_tugas", 0)));
        ApiClient.services_get_hitori.getHistori(1).enqueue(new Callback<UserHistoriSiswa>() {

                //progressDialog.setMessage("Please wait...");
                //progressDialog.show();

            @Override
            public void onResponse(Call<UserHistoriSiswa> call, Response<UserHistoriSiswa> response) {
                userHistoriSiswa = response.body();


                adapter = new MahasiswaAdapter(userHistoriSiswa.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserHistoriSiswa> call, Throwable t) {

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





