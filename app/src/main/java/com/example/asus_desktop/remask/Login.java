package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/17/2018.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Api.ApiInterface;
import com.example.asus_desktop.remask.Model.ModelLoginUser;
import com.example.asus_desktop.remask.Model.PrefConfig;
import com.example.asus_desktop.remask.Model.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.view.WindowManager;



public class Login extends AppCompatActivity {


    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    private ModelLoginUser modelLogin;
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnLogin ;
    private TextView txtViewSingUp;
    private  SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;

    /*OnLoginFormActivityListener loginFormActivityListener;
    public interface OnLoginFormActivityListener{
        public void performLogin(String Username);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("Remask", MODE_PRIVATE);
        edit =sharedPreferences.edit();
        edit.clear();
        edit.apply();

        //prefConfig = new PrefConfig(this);
        //apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtViewSingUp = (TextView) findViewById(R.id.txtViewSingUp);
        txtViewSingUp.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

       /* if (findViewById(R.id.constraintContainer)!=null)
        {
            if(savedInstanceState!=null){
                return;
            }

            if(prefConfig.readLoginStatus())
            {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(Login.this, Login.class);
                startActivity(intent);
            }
        }
        */
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLogin()) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }



        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            progressDialog = new ProgressDialog(Login.this);
                                            progressDialog.setMessage("Please wait...");
                                            progressDialog.show();



            ApiClient.services_post.login(inputEmail.getText().toString(),inputPassword.getText().toString() ).enqueue(new Callback<ModelLoginUser>() {
                @Override
                public void onResponse(Call<ModelLoginUser> call, Response<ModelLoginUser> response) {
                    modelLogin = response.body();
                  //  if (modelLogin != null) {
                      //  sessionManager.setUid(modelLogin.getResult().getId());
                     //   sessionManager.setLogin(true);
                      //  sessionManager.setUsername(modelLogin.getResult().getUsername());

                        edit.putString("username", modelLogin.getResult().getUsername());
                        edit.putString("siswa_id", String.valueOf(modelLogin.getResult().getSiswaId()));
                        edit.commit();

                        Log.d("username", modelLogin.getResult().getUsername());
                        Log.d("siswa_id", modelLogin.getResult().getSiswaId());

                            String siswa_id = sharedPreferences.getString("siswa_id","");
                             Toast.makeText(Login.this, "siswa id = " +siswa_id, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                  //  }else {
                        Toast.makeText(Login.this, "Akun belum terdaftar", Toast.LENGTH_SHORT).show();
                  //  }

                    progressDialog.dismiss();

                }

                public void onFailure(Call<ModelLoginUser> call, Throwable t) {
                    Toast.makeText(Login.this, "Password dan Username salah", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            });


                }
            });

        txtViewSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
        }
    });


        }
    }
