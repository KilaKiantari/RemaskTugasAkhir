package com.example.asus_desktop.remask.Progress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.asus_desktop.remask.Model.ModelProgressHistori;
import com.example.asus_desktop.remask.Model.Result;
import com.example.asus_desktop.remask.R;
import com.example.asus_desktop.remask.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String id_progress;
    private String status_progress;
    private String tgl_selesai;
    private String nama_progress;
    private TextView txtNama, txtTgl, txTglSelesai;
    ApiClient apiClient;
    private CheckBox checkBox;
    private Button btn_show;
    private ProgressDialog progressDialog;
    String id_tugas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_progress, container, false);
        //addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btn_show = (Button) view.findViewById(R.id.btnShow);




//
//            Toast.makeText(getActivity(), "" + id_tugas, Toast.LENGTH_SHORT).show();

//        }




        //adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getActivity().setTitle("Progress Tugas");


        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

//        Bundle args = getArguments();
//        if(args != null) {
//            id_tugas = getIntent().getExtras().getString("id_tugas");
//            tampilProgress();
//        }

//        Bundle args = new Bundle();
//        args.putString("id_tugas",id_tugas);
//        ProgressTugas newFragment = new ProgressTugas();
//        newFragment.setArguments(args);

//

//        getIntent();

//
//        Bundle b = getArguments();
//        if(b != null) {
//             id_tugas = b.getString("id_tugas");
//            tampilProgress();
//        }

            //    Toast.makeText(getActivity(), ""+id_tugas, Toast.LENGTH_SHORT).show();

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
//        Log.d("id_tugas", String.valueOf(sharedPreferences.getInt("id_tugas", 0)));

        ApiClient.services_get_progress.getProgress(139).enqueue(new Callback<ModelProgressHistori>() {


            //progressDialog.setMessage("Please wait...");
            //progressDialog.show();

            @Override
            public void onResponse(Call<ModelProgressHistori> call, Response<ModelProgressHistori> response) {
                Log.e("Response Progress tu", "Code : " + response.code());
                if (response.isSuccessful()) {
                    modelProgressHistori = response.body();
                    adapter = new ProgressAdapter(getActivity(), modelProgressHistori.getResults());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "SALAH", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelProgressHistori> call, Throwable t) {

                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });


        btn_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                List<Result> result = (((ProgressAdapter) adapter).getStudentist());

                for (int i = 0; i < result.size(); i++) {
                    Result singleStudent = result.get(i);
                    if (singleStudent.isSelected() == true) {
                        tgl_selesai = singleStudent.getTglSelesai();
                        status_progress = singleStudent.getStatusProgress();
                        nama_progress = singleStudent.getNamaProgress();
                        id_progress = singleStudent.getIdProgress();
                        ApiClient.services_post.checklistprogress(
                                id_progress,
                                status_progress,
                                tgl_selesai
                        ) .enqueue(new Callback<ModelActionJoin>() {
                            @Override
                            public void onResponse(Call<ModelActionJoin> call, Response<ModelActionJoin> response) {
                                if(response.isSuccessful()) {
                                    Intent intent = new Intent(getActivity(), MainActivityProgress.class);
                                    getActivity().startActivity(intent);
                                    getActivity().finish();

                                    progressDialog.dismiss();

                                }else{
                                    Toast.makeText(getActivity(), "SALAH", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
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





