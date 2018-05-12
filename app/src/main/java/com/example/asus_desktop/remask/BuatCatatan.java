package com.example.asus_desktop.remask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;
import com.example.asus_desktop.remask.Model.ModelLoginUser;

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
    TextView time;
    ModelCreateTugas modelCreateTugas;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat_catatan);
        sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        edit =sharedPreferences.edit();
        edit.clear();
        edit.apply();

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



        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.kategori_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        BuatCatatan.this.setTitle("Buat Catatan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));



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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(BuatCatatan.this, MainActivity.class);
                ab.putExtra("Extra","Tools");
                startActivity(ab);
                return true;
            case R.id.action_save:
                ApiClient.services_post.login(mTitleText.getText().toString(),mDescriptionText.getText().toString() ).enqueue(new Callback<ModelCreateTugas>() {
                    @Override
                    public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {

                        edit.putString("nama_tugas", modelCreateTugas.getResults().getNamaTugas());
                        edit.putString("siswa_id", String.valueOf(modelCreateTugas.getResults().getSiswaId()));
                        edit.commit();

                        Log.d("username", modelCreateTugas.getResults().getUsername());
                        Log.d("siswa_id", String.valueOf(modelCreateTugas.getResults().getSiswaId()));

                        Intent openMainScreen = new Intent(this, BuatCatatan.class);
                        openMainScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(openMainScreen);
                        //return true;
                    }

                    @Override
                    public void onFailure(Call<ModelCreateTugas> call, Throwable t) {

                    }


        });

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
