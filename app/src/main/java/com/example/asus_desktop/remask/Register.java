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
import com.example.asus_desktop.remask.Model.ModelRegister;
import com.example.asus_desktop.remask.Model.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 4/18/2018.
 */

public class Register extends AppCompatActivity{
        private Button btnRegister ;
        private ProgressDialog progressDialog;
        private EditText input_name;
        private EditText sekolah;
        private EditText nama_matpel;
        private EditText input_email;
        private EditText input_password;
        private EditText username;
        private SessionManager sessionManager;
        private Integer id_siswa;
        private String siswa_id;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            SharedPreferences sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
            siswa_id = sharedPreferences.getString("siswa_id","");

            btnRegister = (Button) findViewById(R.id.btn_register);
            input_name = (EditText) findViewById(R.id.input_name);
            sekolah = (EditText) findViewById(R.id.sekolah);
            input_email = (EditText) findViewById(R.id.input_email);
            input_password = (EditText) findViewById(R.id.input_password);
            username = (EditText) findViewById(R.id.username);

            sessionManager = new SessionManager(getApplicationContext());
            if (sessionManager.isLogin()) {
                Intent intent = new Intent(Register.this, MainActivityBackup.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Log.d("nama_lengkap",input_name.getText().toString());
                    Log.d("sekolah",sekolah.getText().toString());


                    //Log.d("username",username.getText().toString());
                    //Log.d("email",input_email.getText().toString());
                    // Log.d("password",input_password.getText().toString());
                    // Log.d("guru_id",username.getText().toString());
//                progressDialog = new ProgressDialog(Register.this);
//                progressDialog.setMessage("Please wait...");
//                progressDialog.show();


                    ApiClient.services_post.register(
                            input_name.getText().toString(),
                            sekolah.getText().toString()).enqueue(new Callback<ModelRegister>() {

                        @Override
                        public void onResponse(Call<ModelRegister> call, Response<ModelRegister> response) {
                            //response.body();
                            //Log.d("RSP",response.body());
                            if(response.isSuccessful()) {
                                id_siswa = response.body().getIdSiswa();
                                Toast.makeText(Register.this, ""+id_siswa, Toast.LENGTH_SHORT).show();

                                // finish();
                                //progressDialog.dismiss();
                                Intent intent = new Intent(Register.this, RegisterNext.class);
                                intent.putExtra("id_siswa",id_siswa);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Register.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            }


                        }
                        @Override
                        public void onFailure(Call<ModelRegister> call, Throwable t) {
                            Toast.makeText(Register.this, ""+t, Toast.LENGTH_LONG).show();

                            //progressDialog.dismiss();
                        }


                    });


                }


            });



        }
}