package com.example.asus_desktop.remask;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;
import com.example.asus_desktop.remask.Model.ModelGroupJoined;
import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 5/5/2018.
 */

public class Buat_Catatan_Pendidikan extends AppCompatActivity {
    Toolbar toolbar;
    SQLiteDatabase db;
    //DbHelper mDbHelper;
    EditText mTitleText;
    EditText mDescriptionText;
    Spinner mSpinner;
    TimePicker pickerTime;
    TextView time;
    TextView txtkat;
    ModelCreateTugas modelCreateTugas;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String siswa_id;
    private String group_id;
    String selectedGroup;
    String namagroup;
    ArrayList<String> id_group;
    private ArrayList<Result> result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_catatan_pendidikan);

        SharedPreferences sharedPreferences = Buat_Catatan_Pendidikan.this.getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id", "");

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
        mSpinner = (Spinner) findViewById(R.id.spinnerGroup);
        pickerTime = (TimePicker) findViewById(R.id.timePicker);
        txtkat = (TextView) findViewById(R.id.txtkat);
        id_group = new ArrayList<>();


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,final int i, long l) {
               // selectedGroup = id_group.get(i);
                group_id = result.get(i).getGroupId();
              //  namagroup = result.getNamagroup();

                ApiClient.services_get_group_joined.getGroupJoined(1).enqueue(new Callback<ModelGroupJoined>() {
                    @Override
                    public void onResponse(Call<ModelGroupJoined> call, Response<ModelGroupJoined> response) {
                        if(response.isSuccessful()){

                            Toast.makeText(Buat_Catatan_Pendidikan.this, ""+namagroup, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelGroupJoined> call, Throwable t) {


                    }
                });

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Buat_Catatan_Pendidikan.this.setTitle("Buat Catatan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        Log.d("date", sharedPreferences.getString("date", ""));
    }



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

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(Buat_Catatan_Pendidikan.this, MainActivity.class);
                ab.putExtra("Extra","Tools");
                startActivity(ab);
                return true;
            case R.id.action_save:
                String date = sharedPreferences.getString("date","")+" "+String.valueOf(pickerTime.getHour())+":"+String.valueOf(pickerTime.getMinute())+":00";
                ApiClient.services_post.creatependidikan(
                        group_id,
                        siswa_id,
                        mTitleText.getText().toString(),
                        "3",
                        mDescriptionText.getText().toString(),
                        date,
                        "0").enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("status",response.body().toString());
                        Toast.makeText(Buat_Catatan_Pendidikan.this, "Daftar Catatan telah ditambahkan", Toast.LENGTH_SHORT).show();

                        //return true;
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }


                });

                Intent intent = new Intent(Buat_Catatan_Pendidikan.this, MainActivity.class);
                startActivity(intent);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
