package com.example.asus_desktop.remask;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
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
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus_desktop.remask.Alarm.AlarmReceiver;
import com.example.asus_desktop.remask.Alarm.LocalData;
import com.example.asus_desktop.remask.Alarm.NotificationScheduler;
import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private String siswa_id;
    private Integer id_tugas;
    private String status;
    private String nama_tugas;
    private String keterangan;
    private Context context = this;
    CustomDialog customDialog;

    LocalData localData;
    SwitchCompat reminderSwitch;
    TextView tvTime;
    LinearLayout ll_set_time, ll_terms;
    int hour, min;
    ClipboardManager myClipboard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat_catatan);
        sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");
        edit = sharedPreferences.edit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("Buat Catatan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        Log.d("date", sharedPreferences.getString("date", ""));


        mTitleText = (EditText) findViewById(R.id.txttitle);
        mDescriptionText = (EditText) findViewById(R.id.description);
        mSpinner = (Spinner) findViewById(R.id.spinnerAlarm);
     //   pickerTime = (TimePicker) findViewById(R.id.timePicker);

        Intent grup = getIntent();
        id_tugas = grup.getIntExtra("id_tugas", 0);
        nama_tugas = grup.getStringExtra("nama_tugas");
        keterangan = grup.getStringExtra("keterangan");

        mTitleText.setText(nama_tugas);
        mDescriptionText.setText(keterangan);

        ll_set_time = (LinearLayout) findViewById(R.id.ll_set_time);
        localData = new LocalData(getApplicationContext());
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        tvTime = (TextView) findViewById(R.id.tv_reminder_time_desc);
        reminderSwitch = (SwitchCompat) findViewById(R.id.timerSwitch);
        hour = localData.get_hour();
        min = localData.get_min();


        tvTime.setText(getFormatedTime(hour, min));
        reminderSwitch.setChecked(localData.getReminderStatus());

        if (!localData.getReminderStatus())
            ll_set_time.setAlpha(0.4f);

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                localData.setReminderStatus(isChecked);
                if (isChecked) {
                    Log.d("", "onCheckedChanged: true");
                    NotificationScheduler.setReminder(BuatCatatan.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    ll_set_time.setAlpha(1f);
                } else {
                    Log.d("", "onCheckedChanged: false");
                    NotificationScheduler.cancelReminder(BuatCatatan.this, AlarmReceiver.class);
                    ll_set_time.setAlpha(0.4f);
                }

            }
        });

        ll_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (localData.getReminderStatus())
                    showTimePickerDialog(localData.get_hour(), localData.get_min());
            }
        });
//        update();
    }


    private void showTimePickerDialog(int h, int m) {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker_header, null);

        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        Log.d("", "onTimeSet: hour " + hour);
                        Log.d("", "onTimeSet: min " + min);
                        localData.set_hour(hour);
                        localData.set_min(min);
                        tvTime.setText(getFormatedTime(hour, min));
                        NotificationScheduler.setReminder(BuatCatatan.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());


                    }
                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();

    }

    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;

        }
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivityBackup.class);
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

//    public void update() {
//        mTitleText.getText().toString();
//        mDescriptionText.getText().toString();
//
//
////        mTitleText.setText(nama_tugas);
////        mDescriptionText.setText(keterangan);
//
//        ApiClient.services_post.updatetugas(
//                id_tugas,
//                siswa_id,
//                mTitleText.getText().toString(),
//                mDescriptionText.getText().toString()
//        ).enqueue(new Callback<ModelCreateTugas>() {
//            @Override
//            public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
//                String status = response.body().getStatus();
//                if (status.equals("1")) {
//                    Toast.makeText(BuatCatatan.this, "berhasil", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//
//                    Toast.makeText(BuatCatatan.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                    //return true;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ModelCreateTugas> call, Throwable t) {
//
//            }
//        });
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(BuatCatatan.this, MainActivityBackup.class);
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
                                        String date = sharedPreferences.getString("date", "") + " " + String.valueOf(localData.get_hour()) + ":" + String.valueOf(localData.get_min()) + ":00";
                                        Log.d("id", "1");
                                        Log.d("title", mTitleText.getText().toString());
                                        Log.d("organisasi", "org");
                                        Log.d("desc", mDescriptionText.getText().toString());
                                        Log.d("date", date);


                                        ApiClient.services_post.create(
                                                siswa_id,
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

                                        Intent intent = new Intent(BuatCatatan.this, MainActivityBackup.class);
                                        startActivity(intent);
                                    }

                        })


                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                String date = sharedPreferences.getString("date", "") + " " + String.valueOf(localData.get_hour()) + ":" + String.valueOf(localData.get_min()) + ":00";

                                Log.d("id", "1");
                                Log.d("title", mTitleText.getText().toString());
                                Log.d("organisasi", "org");
                                Log.d("desc", mDescriptionText.getText().toString());
                                Log.d("date", date);

                                ApiClient.services_post.create(
                                        siswa_id,
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
