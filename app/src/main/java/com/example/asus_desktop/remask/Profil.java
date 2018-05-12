package com.example.asus_desktop.remask;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.UserProfilSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profil extends Fragment {

    public Profil(){}
    RelativeLayout view;
    private UserProfilSiswa modelProfilSiswa;
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnLogin ;
    private TextView NamaSiswa,Status,Sekolah,Email;
    private UserProfilSiswa modelUserProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profil, container, false);

        NamaSiswa = (TextView) view.findViewById(R.id.name);
        Status = (TextView) view.findViewById(R.id.status);
        Sekolah = (TextView) view.findViewById(R.id.sekolah);
        Email = (TextView) view.findViewById(R.id.email);


        getActivity().setTitle("Profil Saya");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", Context.MODE_PRIVATE);
        Log.d("id_siswa", String.valueOf(sharedPreferences.getInt("id_siswa", 0)));
        ApiClient.services_get_profil.getProfile(1).enqueue(new Callback<UserProfilSiswa>() {
            @Override
            public void onResponse(Call<UserProfilSiswa> call, Response<UserProfilSiswa> response) {
                modelUserProfile = response.body();
                NamaSiswa.setText(modelUserProfile.getResults().getNamaLengkap());
                Status.setText(modelUserProfile.getResults().getNamaOrangtua());
                Sekolah.setText(modelUserProfile.getResults().getSekolah());
                Email.setText(modelUserProfile.getResults().getEmail());
                //Log.d("getSekolah", modelUserProfile.getSekolah());


            }


            public void onFailure(Call<UserProfilSiswa> call, Throwable t) {

            }


        });

        return view;
    }
}