package com.example.asus_desktop.remask.Grafik;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelGrafikKerajinan;
import com.example.asus_desktop.remask.Model.ModelGrafikKeterangan;
import com.example.asus_desktop.remask.Model.ModelGrafikKeteranganLain;
import com.example.asus_desktop.remask.Model.ModelGrafikKeteranganOrganisasi;
import com.example.asus_desktop.remask.Model.Result;
import com.example.asus_desktop.remask.NotificationService;
import com.example.asus_desktop.remask.R;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import im.dacer.androidcharts.LineView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Asus-Desktop on 7/3/2018.
 */

public class GarfikKerajinan extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String siswa_id;
    private Intent serviceIntent;


    public GarfikKerajinan() {
    }

    private static final String TAG = Grafik.class.getSimpleName();

    int randomint = 100;

    ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
    LineDataSet Linedataset ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_line, container, false);
        serviceIntent = new Intent(getActivity(), NotificationService.class);

        //  final LineView lineView = (LineView) rootView.findViewById(R.id.line_view);
        final LineView lineViewFloat = (LineView) rootView.findViewById(R.id.line_view_float);
        final TextView tvSudah = (TextView) rootView.findViewById(R.id.textViewpend2);
        final TextView tvSudahor = (TextView) rootView.findViewById(R.id.textViewor2);
        final TextView tvSudahla = (TextView) rootView.findViewById(R.id.textView2);
        sharedPreferences = getActivity().getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");
        edit = sharedPreferences.edit();
        // initLineView(lineView);
        initLineView(lineViewFloat);
//        Button lineButton = (Button) rootView.findViewById(R.id.line_button);
//        lineButton.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                randomSet(lineView, lineViewFloat);
//            }
//        });

        ApiClient.services_get_grafik_keterangan_pendidikan.getGrafikket(siswa_id).enqueue(new Callback<ModelGrafikKeterangan>() {

            public void onResponse(Call<ModelGrafikKeterangan> call, Response<ModelGrafikKeterangan> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    Integer sudah = response.body().getJmlpendidikan();
                    Integer belum = response.body().getJmlblmpendidikan();
                    if(sudah>belum){
                        tvSudah.setText("Tingkatkan Kerajinan Anda");
                    }else if (belum>sudah){
                        tvSudah.setText("Masih banyak yang harus anda kerjakan");
                    }
                } else {
                    Toast.makeText(getActivity(), "Gagal menampilkan grafik", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGrafikKeterangan> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });

        ApiClient.services_get_grafik_keterangan_organisasi.getGrafikketor(siswa_id).enqueue(new Callback<ModelGrafikKeteranganOrganisasi>() {

            public void onResponse(Call<ModelGrafikKeteranganOrganisasi> call, Response<ModelGrafikKeteranganOrganisasi> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    Integer sudah = response.body().getJmlor();
                    Integer belum = response.body().getJmlblmor();
                    if(sudah>belum){
                        tvSudahor.setText("Tingkatkan Kerajinan Anda");
                    }else if (belum>sudah){
                        tvSudahor.setText("Masih banyak yang harus anda kerjakan");
                    }
                } else {
                    Toast.makeText(getActivity(), "Gagal menampilkan grafik", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGrafikKeteranganOrganisasi> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });

        ApiClient.services_get_grafik_keterangan_lain.getGrafikketla(siswa_id).enqueue(new Callback<ModelGrafikKeteranganLain>() {

            public void onResponse(Call<ModelGrafikKeteranganLain> call, Response<ModelGrafikKeteranganLain> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    Integer sudah = response.body().getJmllain();
                    Integer belum = response.body().getJmlblmlain();
                    if(sudah>belum){
                        tvSudahla.setText("Tingkatkan Kerajinan Anda");
                    }else if (belum>sudah){
                        tvSudahla.setText("Masih banyak yang harus anda kerjakan");
                    }
                } else {
                    Toast.makeText(getActivity(), "Gagal menampilkan grafik", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGrafikKeteranganLain> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });



        randomSet(lineViewFloat);
        return rootView;
    }

    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < randomint; i++) {
            test.add(String.valueOf(i + 1));
        }

        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[]{
                Color.parseColor("#F44336"), Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F3")
        });
        lineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "coba", Toast.LENGTH_SHORT).show();

            }
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);


    }


    private void randomSet(final LineView lineViewFloat) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        ApiClient.services_get_grafik_organisasi.getGrafikKerajinanOrganisasi(siswa_id).enqueue(new Callback<ModelGrafikKerajinan>() {

            public void onResponse(Call<ModelGrafikKerajinan> call, Response<ModelGrafikKerajinan> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    List<Result> selisih = response.body().getResult();

                    ArrayList<Float> dataListF3 = new ArrayList<>();
                    for (int i = 0; i < selisih.size(); i++) {
                        dataListF3.add(selisih.get(i).getSelisihorganisasi());

                    }

                    // Toast.makeText(getActivity(), "berhasil = " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    dataListFs.add(dataListF3);


                } else {
                    Toast.makeText(getActivity(), "Gagal menampilkan grafik", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGrafikKerajinan> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });

        ApiClient.services_get_grafik_pendidikan.getGrafikKerajinanPendidikan(siswa_id).enqueue(new Callback<ModelGrafikKerajinan>() {

            public void onResponse(Call<ModelGrafikKerajinan> call, Response<ModelGrafikKerajinan> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    List<Result> selisih = response.body().getResult();
                    ArrayList<Float> dataListF2 = new ArrayList<>();

                    for (int i = 0; i < selisih.size(); i++) {
                   //     if (selisih.get(i).getSelisihpendidikan() == 3 || selisih.get(i).getSelisihpendidikan() == 2 || selisih.get(i).getSelisihpendidikan() == 1 || selisih.get(i).getSelisihpendidikan() == 0) {
                            dataListF2.add(selisih.get(i).getSelisihpendidikan());

//                              final NotificationManager mgr = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//                            Notification notification = new NotificationCompat.Builder(getContext())
//                                    .setContentTitle("Remask")
//                                    .setContentText("Grafik kamu menurun! Ayo kerjakan jangan dekat dengan deadline")
//                                    .setSmallIcon(R.mipmap.ic_launcher)
//                                 //   .setContentIntent(pendingIntent)
//                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                                    .setAutoCancel(true)
//                                    .setDefaults(Notification.DEFAULT_ALL)
//                                    .build();
//
//                            mgr.notify(1, notification);
//                        }
//
//
//                        else{
//                            dataListF2.add(selisih.get(i).getSelisihpendidikan());
                        }



//                    }
                    Toast.makeText(getContext(), "Grafik Menurun", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "berhasil = " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
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

        ApiClient.services_get_grafik_lain.getGrafikKerajinanLain(siswa_id).enqueue(new Callback<ModelGrafikKerajinan>() {

            public void onResponse(Call<ModelGrafikKerajinan> call, Response<ModelGrafikKerajinan> response) {
                Log.e("Response Grafik Kerajin", "Code : " + response.code());
                if (response.isSuccessful()) {
                    // Toast.makeText(getActivity(), "Id siswa grafik = "+siswa_id, Toast.LENGTH_SHORT).show();
                    List<Result> selisih = response.body().getResult();
                    ArrayList<Float> dataListF1 = new ArrayList<>();
                    for (int i = 0; i < selisih.size(); i++) {
                        dataListF1.add(selisih.get(i).getSelisihlain());

                    }

                    Toast.makeText(getActivity(), "berhasil = " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    dataListFs.add(dataListF1);


                } else {
                    Toast.makeText(getActivity(), "Gagal menampilkan grafik", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGrafikKerajinan> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });



        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                lineViewFloat.setFloatDataList(dataListFs);

            }

        });
    }


}
