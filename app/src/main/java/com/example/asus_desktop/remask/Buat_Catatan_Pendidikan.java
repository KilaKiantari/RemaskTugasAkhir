package com.example.asus_desktop.remask;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.asus_desktop.remask.Model.ModelGroupJoined;
import com.example.asus_desktop.remask.Model.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
  //  String selectedGroup;
    String namagroup;
    private ArrayList<Result> result;
    ModelGroupJoined modelGroupJoined;
    ArrayList<String> id_group = null;
    Integer id_tugas;

    LocalData localData;
    SwitchCompat reminderSwitch;
    TextView tvTime;
    LinearLayout ll_set_time, ll_terms;
    int hour, min;
    ClipboardManager myClipboard;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_catatan_pendidikan);

        sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        siswa_id = sharedPreferences.getString("siswa_id", "");

        id_group = new ArrayList<>();
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

      //  pickerTime = (TimePicker) findViewById(R.id.timePickerpen);
        txtkat = (TextView) findViewById(R.id.txtkat);

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
                    NotificationScheduler.setReminder(Buat_Catatan_Pendidikan.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    ll_set_time.setAlpha(1f);
                } else {
                    Log.d("", "onCheckedChanged: false");
                    NotificationScheduler.cancelReminder(Buat_Catatan_Pendidikan.this, AlarmReceiver.class);
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

        initSpinnerGroup();


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
             //   String selectedGroup = id_group.get(position);

//                requestDetailDosen(selectedName);
                // Toast.makeText(Buat_Catatan_Pendidikan.this, "Kamu memilih group " + selectedName, Toast.LENGTH_SHORT).show();
                //   Toast.makeText(Buat_Catatan_Pendidikan.this, "id = " + selectedGroup, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                        NotificationScheduler.setReminder(Buat_Catatan_Pendidikan.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());


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


    private void initSpinnerGroup(){

        ApiClient.services_get_group_joined_spinner.getGroupJoinedSpinner(siswa_id).enqueue(new Callback<ModelGroupJoined>() {
            @Override
            public void onResponse(Call<ModelGroupJoined> call, Response<ModelGroupJoined> response) {
                if (response.isSuccessful()) {
                    List<Result> groupJoined = response.body().getResults();
                    List<String> listspinner = new ArrayList<>();
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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(Buat_Catatan_Pendidikan.this, MainActivityBackup.class);
                ab.putExtra("Extra","Tools");
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
                                Log.d("pendidikan", "pendidikan");
                                Log.d("desc", mDescriptionText.getText().toString());
                                Log.d("date", date);
                                ApiClient.services_post.creatependidikan(
                                       id_group,
                                        siswa_id,
                                        mTitleText.getText().toString(),
                                        "3",
                                        mDescriptionText.getText().toString(),
                                        date,
                                        "0").enqueue(new Callback<ModelCreateTugas>() {
                                    @Override
                                    public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
                                        if (response.isSuccessful()) {
                                            id_tugas = response.body().getIdTugas();
                                         //   status = response.body().getStatus();
                                            Toast.makeText(Buat_Catatan_Pendidikan.this, "" + id_tugas, Toast.LENGTH_SHORT).show();
                                        //    Toast.makeText(BuatCatatan.this, "" + status, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Buat_Catatan_Pendidikan.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                            //return true;
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ModelCreateTugas> call, Throwable t) {

                                    }
                                });
                                Intent intent = new Intent(Buat_Catatan_Pendidikan.this, MainActivityBackup.class);
                                startActivity(intent);
                            }

                        })

                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                String date = sharedPreferences.getString("date", "") + " " + String.valueOf(localData.get_hour()) + ":" + String.valueOf(localData.get_min()) + ":00";

                                Log.d("id", "1");
                                Log.d("title", mTitleText.getText().toString());
                                Log.d("pendidikan", "pendidikan");
                                Log.d("desc", mDescriptionText.getText().toString());
                                Log.d("date", date);

                                ApiClient.services_post.creatependidikan(
                                        id_group,
                                        siswa_id,
                                        mTitleText.getText().toString(),
                                        "3",
                                        mDescriptionText.getText().toString(),
                                        date,
                                        "0").enqueue(new Callback<ModelCreateTugas>() {
                                    @Override
                                    public void onResponse(Call<ModelCreateTugas> call, Response<ModelCreateTugas> response) {
                                        if (response.isSuccessful()) {
                                            id_tugas = response.body().getIdTugas();
                                            Log.d("status", response.body().toString());
                                            Toast.makeText(Buat_Catatan_Pendidikan.this, ""+id_tugas, Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(Buat_Catatan_Pendidikan.this, CustomDialog.class);
                                            intent.putExtra("id_tugas", id_tugas);
                                            startActivity(intent);
                                        } else {

                                            Toast.makeText(Buat_Catatan_Pendidikan.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
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
