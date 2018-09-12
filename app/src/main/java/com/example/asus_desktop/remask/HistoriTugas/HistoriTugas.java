package com.example.asus_desktop.remask.HistoriTugas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.ModelGrafikKerajinan;
import com.example.asus_desktop.remask.Model.Result;
import com.example.asus_desktop.remask.Model.UserHistoriSiswa;
import com.example.asus_desktop.remask.R;
import com.example.asus_desktop.remask.Tools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class HistoriTugas extends Fragment {

    public HistoriTugas() {
    }

    private static final String TAG = HistoriTugas.class.getSimpleName();

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserHistoriSiswa userHistoriSiswa;
    private String siswa_id;
    private String id_tugas;
    private String status_tugas;
    private String tanggal_selesai;
    private String tugas_id;
    private String nama_progress;
    private String nama_tugas;
    private TextView txtNama, txtTgl, txTglSelesai;
    ApiClient apiClient;
    private CheckBox checkBox;
    private Button btn_show;
    private ProgressDialog progressDialog;

    ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histori_tugas, container, false);
        //addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btn_show = (Button) view.findViewById(R.id.btn_show);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");



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
        ApiClient.services_get_hitori.getHistori(siswa_id).enqueue(new Callback<UserHistoriSiswa>() {

                //progressDialog.setMessage("Please wait...");
                //progressDialog.show();

            @Override
            public void onResponse(Call<UserHistoriSiswa> call, Response<UserHistoriSiswa> response) {
                userHistoriSiswa = response.body();
                adapter = new MahasiswaAdapter(getActivity(),userHistoriSiswa.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserHistoriSiswa> call, Throwable t) {

            }
        });


        btn_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                String data = "";
               // userHistoriSiswa = response.body();
                List<Result> result = (((MahasiswaAdapter) adapter).getStudentist());
                for (int i = 0; i < result.size(); i++) {
                    Result singleStudent = result.get(i);
                    if (singleStudent.isSelected() == true) {
                        tanggal_selesai = singleStudent.getTanggalSelesai();
                        status_tugas = singleStudent.getStatusTugas();
                        nama_tugas = singleStudent.getNamaTugas();
                        id_tugas = singleStudent.getIdTugas();
                        ApiClient.services_post.checklist(
                                id_tugas,
                                status_tugas,
                                tanggal_selesai
                        ) .enqueue(new Callback<ModelActionJoin>() {
                            @Override
                            public void onResponse(Call<ModelActionJoin> call, Response<ModelActionJoin> response) {
                                if(response.isSuccessful()) {
                                    Fragment fragment = new HistoriBaru();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.frame_container,fragment);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();

                                    grafiknotif();

                                    Toast.makeText(getActivity(), ""+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    //Log.d("name",nama_tugas);
                                    // id_guru = response.body().getIdGuru();
                                }else{
                                    Toast.makeText(getActivity(), "SALAH", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }


//
                                // Toast.makeText(mContext, ""+namagroup, Toast.LENGTH_SHORT).show();


                                // progressDialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<ModelActionJoin> call, Throwable t) {
                                Toast.makeText(getActivity(), "" +t, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }

        });


        return view;
    }

    public void grafiknotif() {
        ApiClient.services_get_grafik_pendidikan.getGrafikKerajinanPendidikan(siswa_id).enqueue(new Callback<ModelGrafikKerajinan>() {

            public void onResponse(Call<ModelGrafikKerajinan> call, Response<ModelGrafikKerajinan> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    List<Result> selisih = response.body().getResult();
                    ArrayList<Float> dataListF2 = new ArrayList<>();

                    for (int i = 0; i < selisih.size(); i++) {
                        if (selisih.get(i).getSelisihpendidikan() == 3 || selisih.get(i).getSelisihpendidikan() == 2 || selisih.get(i).getSelisihpendidikan() == 1 || selisih.get(i).getSelisihpendidikan() == 0) {
                            dataListF2.add(selisih.get(i).getSelisihpendidikan());

                            final NotificationManager mgr = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

                            Notification notification = new NotificationCompat.Builder(getContext())
                                    .setContentTitle("Remask")
                                    .setContentText("Grafik kamu menurun! Ayo kerjakan jangan dekat dengan deadline")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    //   .setContentIntent(pendingIntent)
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setAutoCancel(true)
                                    .setDefaults(Notification.DEFAULT_ALL)
                                    .build();

                            mgr.notify(1, notification);
                        } else {
                            dataListF2.add(selisih.get(i).getSelisihpendidikan());
                        }


                    }
                    Toast.makeText(getContext(), "Grafik Menurun", Toast.LENGTH_SHORT).show();
          //          Toast.makeText(getActivity(), "berhasil = " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
             //       progressDialog.dismiss();
                    dataListFs.add(dataListF2);


                } else {
                    Toast.makeText(getActivity(), "Gagal menampilkan grafik", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGrafikKerajinan> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });
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





