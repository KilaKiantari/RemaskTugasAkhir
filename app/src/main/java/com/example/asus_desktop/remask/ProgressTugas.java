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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelProgressHistori;

/**
 * Created by Asus-Desktop on 6/7/2018.
 */

public class ProgressTugas extends Fragment {

    public ProgressTugas() {
    }

    private static final String TAG = ProgressTugas.class.getSimpleName();

    private RecyclerView recyclerView;
    private ProgressAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ModelProgressHistori modelProgressHistori;
    private String siswa_id;
    private String tugas_id;
    private String status_tugas;
    private String tanggal_selesai;
    private String nama_progress;
    private TextView txtNama, txtTgl, txTglSelesai;
    ApiClient apiClient;
    private CheckBox checkBox;
    private Button btn_show;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_progress, container, false);
        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        //adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getActivity().setTitle("Progress Tugas");


        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        Bundle b = getArguments();
        String id_tugas = b.getString("id_tugas");

        Toast.makeText(getActivity(), ""+id_tugas, Toast.LENGTH_SHORT).show();

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
//        Log.d("id_tugas", String.valueOf(sharedPreferences.getInt("id_tugas", 0)));
//        ApiClient.services_get_progress.getProgress(id_tugas).enqueue(new Callback<ModelProgressHistori>() {
//
//            //progressDialog.setMessage("Please wait...");
//            //progressDialog.show();
//
//            @Override
//            public void onResponse(Call<ModelProgressHistori> call, Response<ModelProgressHistori> response) {
//                modelProgressHistori = response.body();
//
//                adapter = new ProgressAdapter(getActivity(),modelProgressHistori.getResults());
//                adapter.notifyDataSetChanged();
//                recyclerView.setAdapter(adapter);
//                progressDialog.dismiss();
//
//            }
//
//            @Override
//            public void onFailure(Call<ModelProgressHistori> call, Throwable t) {
//
//            }
//        });
//

//        btn_show.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setMessage("Please wait...");
//                progressDialog.show();
//                String data = "";
//                // userHistoriSiswa = response.body();
//                List<Result> result = (((ProgressAdapter) adapter).getStudentist());
//
//                for (int i = 0; i < result.size(); i++) {
//                    Result singleStudent = result.get(i);
//                    if (singleStudent.isSelected() == true) {
//                        tanggal_selesai = singleStudent.getTanggalSelesai();
//                        status_tugas = singleStudent.getStatusTugas();
//                        nama_tugas = singleStudent.getNamaTugas();
//                        id_tugas = singleStudent.getIdTugas();
//                        ApiClient.services_post.checklist(
//                                id_tugas,
//                                status_tugas,
//                                tanggal_selesai
//                        ) .enqueue(new Callback<ModelActionJoin>() {
//                            @Override
//                            public void onResponse(Call<ModelActionJoin> call, Response<ModelActionJoin> response) {
//                                if(response.isSuccessful()) {
//                                    Fragment fragment = new HistoriBaru();
//                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                    fragmentTransaction.replace(R.id.frame_container,fragment);
//                                    fragmentTransaction.addToBackStack(null);
//                                    fragmentTransaction.commit();
//
//
//                                    Toast.makeText(getActivity(), ""+response.body().getStatus(),Toast.LENGTH_SHORT).show();
//                                    progressDialog.dismiss();
//                                    //Log.d("name",nama_tugas);
//                                    // id_guru = response.body().getIdGuru();
//                                }else{
//                                    Toast.makeText(getActivity(), "SALAH", Toast.LENGTH_SHORT).show();
//                                    progressDialog.dismiss();
//                                }
//
//
////
//                                // Toast.makeText(mContext, ""+namagroup, Toast.LENGTH_SHORT).show();
//
//
//                                // progressDialog.dismiss();
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<ModelActionJoin> call, Throwable t) {
//                                Toast.makeText(getActivity(), "" +t, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//

//
//                    }
//
//                }
//
//
//            }
//
//        });

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

    public static ProgressTugas newInstance() {
        ProgressTugas fragment = new ProgressTugas();
        return fragment;
    }
}





