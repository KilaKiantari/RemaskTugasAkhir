package com.example.asus_desktop.remask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelProgress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 6/6/2018.
 */
public class CustomDialog extends  Activity{
    private String nama_progress;
    private String siswa_id;
    private Integer id_tugas;
    public Activity activity;
    public Button btnSimpan, btnNo, btnTambah;
    public EditText txtProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompts);

        SharedPreferences sharedPreferences = CustomDialog.this.getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");

        btnTambah = (Button) findViewById(R.id.btn_tambah);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);
        btnNo = (Button) findViewById(R.id.btn_no);
        txtProgress = (EditText) findViewById(R.id.edt_comment);

        Intent tugas = getIntent();
        id_tugas = tugas.getIntExtra("id_tugas", 0);

    btnSimpan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ApiClient.services_post.createprogresstambah(
                    txtProgress.getText().toString(),
                    siswa_id,
                    "0",
                    id_tugas.toString()
            ).enqueue(new Callback<ModelProgress>() {
                @Override
                public void onResponse(Call<ModelProgress> call, Response<ModelProgress> response) {
                    if (response.isSuccessful()) {
                        Log.d("status", response.body().toString());
                        Toast.makeText(CustomDialog.this, ""+response.body().getStatus(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CustomDialog.this, MainActivityBackup.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ModelProgress> call, Throwable t) {

                }
            });

        }
    });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(CustomDialog.this, ""+id_tugas, Toast.LENGTH_SHORT).show();
                ApiClient.services_post.createprogresstambah(
                        txtProgress.getText().toString(),
                        siswa_id,
                        "0",
                        id_tugas.toString()
                ).enqueue(new Callback<ModelProgress>() {
                    @Override
                    public void onResponse(Call<ModelProgress> call, Response<ModelProgress> response) {
                        if (response.isSuccessful()) {
                            txtProgress.getText().clear();
                            Log.d("status", response.body().toString());



                        }
                    }

                    @Override
                    public void onFailure(Call<ModelProgress> call, Throwable t) {

                    }
                });
//                Intent intent = new Intent(CustomDialog.this, CustomDialog.class);
//                startActivity(intent);

        }
    });

        btnNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent (CustomDialog.this,MainActivityBackup.class);
                startActivity(intent);

            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .2));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);

    }
}