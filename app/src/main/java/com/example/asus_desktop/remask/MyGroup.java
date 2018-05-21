package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelGroupJoined;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 5/16/2018.
 */

public class MyGroup extends Fragment {

    public MyGroup() {
    }

    private static final String TAG = MyGroup.class.getSimpleName();

    private RecyclerView recyclerView;
    private MyGroupAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ModelGroupJoined modelGroupJoined;
    private TextView txtNama, txtTgl, txTglSelesai;
    ApiClient apiClient;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histori_tugas, container, false);
        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);




        //adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getActivity().setTitle("Group Saya");

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();


//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
//        Log.d("id_tugas", String.valueOf(sharedPreferences.getInt("id_tugas", 0)));
        ApiClient.services_get_group_joined.getGroupJoined(1).enqueue(new Callback<ModelGroupJoined>() {

            //progressDialog.setMessage("Please wait...");
            //progressDialog.show();

            @Override
            public void onResponse(Call<ModelGroupJoined> call, Response<ModelGroupJoined> response) {
                modelGroupJoined = response.body();


                adapter = new MyGroupAdapter(getActivity(),modelGroupJoined.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ModelGroupJoined> call, Throwable t) {

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

