package com.example.asus_desktop.remask.Grafik;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelGrafikKerajinan;
import com.example.asus_desktop.remask.Model.Result;
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
        //  final LineView lineView = (LineView) rootView.findViewById(R.id.line_view);
        final LineView lineViewFloat = (LineView) rootView.findViewById(R.id.line_view_float);
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
                Color.parseColor("#2196F3"), Color.parseColor("#009688")
        });
       lineView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(), "coba", Toast.LENGTH_SHORT).show();

           }
       });
       // lineView.setDrawDotLine(false);
        //lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);


        }


    private void randomSet(final LineView lineViewFloat) {
//        ArrayList<Integer> dataList = new ArrayList<>();
//        float random = (float) (Math.random() * 9 + 1);
//        for (int i = 0; i < randomint; i++) {
//            dataList.add((int) (Math.random() * random));
//        }
//
//        ArrayList<Integer> dataList2 = new ArrayList<>();
//        random = (int) (Math.random() * 9 + 1);
//        for (int i = 0; i < randomint; i++) {
//            dataList2.add((int) (Math.random() * random));
//        }
//
//        ArrayList<Integer> dataList3 = new ArrayList<>();
//        random = (int) (Math.random() * 9 + 1);
//        for (int i = 0; i < randomint; i++) {
//            dataList3.add((int) (Math.random() * random));
//        }
//
//        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();
//        dataLists.add(dataList);
//        dataLists.add(dataList2);
//        dataLists.add(dataList3);
//
//        lineView.setDataList(dataLists);
//        //merah=pendidikan
//        ArrayList<Float> dataListF = new ArrayList<>();
//        float coba = (-5);
//        for (int i = 0; i < randomint; i++) {
//            dataListF.add(coba + 1);
//        }
//
//        //ungu = lain lain
//        ArrayList<Float> dataListF2 = new ArrayList<>();
//        coba = 100;
//        for (int i = 0; i < randomint; i++) {
//            dataListF2.add(coba + 1);
//        }
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
                        dataListF2.add(selisih.get(i).getSelisihpendidikan());

                    }

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

