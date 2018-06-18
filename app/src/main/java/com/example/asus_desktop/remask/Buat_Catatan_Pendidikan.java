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
import android.widget.ArrayAdapter;
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
import java.util.List;

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
    private ArrayList<Result> result;
    ModelGroupJoined modelGroupJoined;
    ArrayList<String> id_group;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_catatan_pendidikan);

        sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        siswa_id = sharedPreferences.getString("siswa_id", "");
        //  sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Buat_Catatan_Pendidikan.this.setTitle("Buat Catatan Pendidikan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Log.d("date", sharedPreferences.getString("date", ""));

        mTitleText = (EditText) findViewById(R.id.txttitle);
        mDescriptionText = (EditText) findViewById(R.id.description);
        mSpinner = (Spinner) findViewById(R.id.spinnerGroup);

        pickerTime = (TimePicker) findViewById(R.id.timePickerpen);
        txtkat = (TextView) findViewById(R.id.txtkat);



         initSpinnerGroup();

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                selectedGroup = id_group.get(position);

//                requestDetailDosen(selectedName);
                Toast.makeText(Buat_Catatan_Pendidikan.this, "Kamu memilih group " + selectedName, Toast.LENGTH_SHORT).show();
                Toast.makeText(Buat_Catatan_Pendidikan.this, "id = " + selectedGroup, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initSpinnerGroup(){

        ApiClient.services_get_group_joined_spinner.getGroupJoinedSpinner(1).enqueue(new Callback<ModelGroupJoined>() {
            @Override
            public void onResponse(Call<ModelGroupJoined> call, Response<ModelGroupJoined> response) {
                if (response.isSuccessful()) {
                    List<Result> groupJoined = response.body().getResults();
                    List<String> listspinner = new ArrayList<String>();
                    for (int i = 0; i < groupJoined.size(); i++) {
                        listspinner.add(groupJoined.get(i).getNamagroup());
                        id_group.add(groupJoined.get(i).getIdGroup());


                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Buat_Catatan_Pendidikan.this, android.R.layout.simple_spinner_item, listspinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);
                } else {
                    Toast.makeText(Buat_Catatan_Pendidikan.this, "Gagal mengambil group", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelGroupJoined> call, Throwable t) {


            }
        });

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
                String date = sharedPreferences.getString("date", "") + " " + String.valueOf(pickerTime.getHour()) + ":" + String.valueOf(pickerTime.getMinute()) + ":00";

                Log.d("id","1");
                Log.d("title",mTitleText.getText().toString());
                Log.d("lain lain","org");
                Log.d("desc",mDescriptionText.getText().toString());
                Log.d("date",date);


                ApiClient.services_post.create(
                        "1",
                        mTitleText.getText().toString(),
                        "3",
                        mDescriptionText.getText().toString(),
                        date,
                        "0").enqueue(new Callback<ModelCreateTugas>() {
                    @Override
                    public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
                        Log.d("status",response.body().toString());
                        Toast.makeText(Buat_Catatan_Pendidikan.this, "Daftar Catatan telah ditambahkan", Toast.LENGTH_SHORT).show();

                        //return true;
                    }

                    @Override
                    public void onFailure(Call<ModelCreateTugas> call, Throwable t) {

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
