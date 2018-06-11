package com.example.asus_desktop.remask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 4/12/2018.
 */


public class BuatCatatan extends AppCompatActivity {
    Toolbar toolbar;
    SQLiteDatabase db;
    //DbHelper mDbHelper;
    EditText mTitleText;
    EditText mDescriptionText;
    Spinner mSpinner;
    TimePicker pickerTime;
    EditText editAlert;
    TextView time;
    ModelCreateTugas modelCreateTugas;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private Integer id_tugas;
    private String status;
    private Context context = this;
    CustomDialog customDialog;
    LinearLayout ll_set_time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat_catatan);
        sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        edit =sharedPreferences.edit();




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mTitleText = (EditText) findViewById(R.id.txttitle);
        mDescriptionText = (EditText) findViewById(R.id.description);
        mSpinner = (Spinner) findViewById(R.id.spinnerNoteType);
        pickerTime = (TimePicker) findViewById(R.id.timePicker);
        ll_set_time = (LinearLayout) findViewById(R.id.ll_set_time);




        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.organisasi_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        BuatCatatan.this.setTitle("Buat Catatan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        Log.d("date",sharedPreferences.getString("date",""));


        ll_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_buat_catatan, menu);
        return true;
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(BuatCatatan.this, MainActivity.class);
                ab.putExtra("Extra", "Tools");
                startActivity(ab);
                return true;
            case R.id.action_save:
                new AlertDialog.Builder(this)
                        .setTitle("Penting !")
                        .setMessage("Apakah anda ingin menambah keterangan progress?")
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        String date = sharedPreferences.getString("date", "") + " " + String.valueOf(pickerTime.getHour()) + ":" + String.valueOf(pickerTime.getMinute()) + ":00";
                                        Log.d("id", "1");
                                        Log.d("title", mTitleText.getText().toString());
                                        Log.d("organisasi", "org");
                                        Log.d("desc", mDescriptionText.getText().toString());
                                        Log.d("date", date);


                                        ApiClient.services_post.create(
                                                "1",
                                                mTitleText.getText().toString(),
                                                "2",
                                                mDescriptionText.getText().toString(),
                                                date,
                                                "0").enqueue(new Callback<ModelCreateTugas>() {
                                            @Override
                                            public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
                                                if (response.isSuccessful()) {

                                                   id_tugas = response.body().getIdTugas();
                                                    status = response.body().getStatus();
                                                    Toast.makeText(BuatCatatan.this, "" + id_tugas, Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(BuatCatatan.this, "" + status, Toast.LENGTH_SHORT).show();
                                                } else {

                                                    Toast.makeText(BuatCatatan.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                                    //return true;
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ModelCreateTugas> call, Throwable t) {

                                            }
                                        });

                                        Intent intent = new Intent(BuatCatatan.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                        })


                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                String date = sharedPreferences.getString("date", "") + " " + String.valueOf(pickerTime.getHour()) + ":" + String.valueOf(pickerTime.getMinute()) + ":00";

                                Log.d("id", "1");
                                Log.d("title", mTitleText.getText().toString());
                                Log.d("organisasi", "org");
                                Log.d("desc", mDescriptionText.getText().toString());
                                Log.d("date", date);

                                ApiClient.services_post.create(
                                        "1",
                                        mTitleText.getText().toString(),
                                        "2",
                                        mDescriptionText.getText().toString(),
                                        date,
                                        "0").enqueue(new Callback<ModelCreateTugas>() {
                                    @Override
                                    public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
                                        if (response.isSuccessful()) {
                                            id_tugas = response.body().getIdTugas();
                                            Log.d("status", response.body().toString());
                                            Toast.makeText(BuatCatatan.this, ""+id_tugas, Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(BuatCatatan.this, CustomDialog.class);
                                            intent.putExtra("id_tugas", id_tugas);
                                            startActivity(intent);
                                        } else {

                                            Toast.makeText(BuatCatatan.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                            //return true;
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ModelCreateTugas> call, Throwable t) {

                                    }
                                });


                            }

                        }).create().show();



            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
