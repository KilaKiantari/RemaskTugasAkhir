package com.example.asus_desktop.remask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelRegisterNext;
import com.example.asus_desktop.remask.Model.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 6/24/2018.
 */

public class RegisterNext extends AppCompatActivity {
    private Button btnRegister ;
    private ProgressDialog progressDialog;
    private EditText input_name;
    private EditText sekolah;
    private EditText nama_matpel;
    private EditText input_email;
    private EditText input_password;
    private EditText username;
    private SessionManager sessionManager;
    private String siswa_id;
    private Integer id_siswa;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");

        btnRegister = (Button) findViewById(R.id.btn_register);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        username = (EditText) findViewById(R.id.username);

        Intent intent = getIntent();
        id_siswa = intent.getIntExtra("id_siswa",0);


        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLogin()) {
            Intent intent2 = new Intent(RegisterNext.this, MainActivity.class);
            startActivity(intent2);
            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Log.d("nama_guru",input_name.getText().toString());
                Log.d("sekolah",sekolah.getText().toString());
                Log.d("nama_matpel",nama_matpel.getText().toString());
                */


                Log.d("username",username.getText().toString());
                Log.d("email",input_email.getText().toString());
                Log.d("password",input_password.getText().toString());
                // Log.d("guru_id",username.getText().toString());
                progressDialog = new ProgressDialog(RegisterNext.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                ApiClient.services_post.registernext(
                        username.getText().toString(),
                        input_email.getText().toString(),
                        input_password.getText().toString(),
                        id_siswa.toString())
                        .enqueue(new Callback<ModelRegisterNext>() {
                            @Override
                            public void onResponse(Call<ModelRegisterNext> call, Response<ModelRegisterNext> response) {
                                if(response.isSuccessful()) {
                                    response.body();
                                    Toast.makeText(RegisterNext.this, ""+response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterNext.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                    progressDialog.dismiss();
                                    //return true;
                                }
                                else{
                                    Toast.makeText(RegisterNext.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }

                            }
                            public void onFailure(Call<ModelRegisterNext> call, Throwable t) {

                                Toast.makeText(RegisterNext.this, "Akun anda gagal registrasi", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }


                        });

            }
        });

    }
}
