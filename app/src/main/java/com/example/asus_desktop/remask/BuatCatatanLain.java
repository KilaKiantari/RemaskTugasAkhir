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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 5/15/2018.
 */

public class BuatCatatanLain extends AppCompatActivity {
    Toolbar toolbar;
    SQLiteDatabase db;
    //DbHelper mDbHelper;
    EditText mTitleText;
    EditText mDescriptionText;
    TextView mSpinner;
    TimePicker pickerTime;
    TextView time;
    ModelCreateTugas modelCreateTugas;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_catatan_lain);
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
        mSpinner = (TextView) findViewById(R.id.spinnerNoteType);
        pickerTime = (TimePicker) findViewById(R.id.timePicker);

        BuatCatatanLain.this.setTitle("Buat Catatan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        Log.d("date",sharedPreferences.getString("date",""));


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

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(BuatCatatanLain.this, MainActivity.class);
                ab.putExtra("Extra","Tools");
                startActivity(ab);
                return true;
            case R.id.action_save:
                String date = sharedPreferences.getString("date","")+" "+String.valueOf(pickerTime.getHour())+":"+String.valueOf(pickerTime.getMinute())+":00";


                Log.d("id","1");
                Log.d("title",mTitleText.getText().toString());
                Log.d("lain lain","org");
                Log.d("desc",mDescriptionText.getText().toString());
                Log.d("date",date);


                ApiClient.services_post.create(
                        "1",
                        mTitleText.getText().toString(),
                        "1",
                        mDescriptionText.getText().toString(),
                        date,
                        "0").enqueue(new Callback<ModelCreateTugas>() {
                    @Override
                    public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
                        Log.d("status",response.body().toString());

                        //return true;
                    }

                    @Override
                    public void onFailure(Call<ModelCreateTugas> call, Throwable t) {
                    }

                });

                Intent intent = new Intent(BuatCatatanLain.this, MainActivity.class);
                startActivity(intent);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
