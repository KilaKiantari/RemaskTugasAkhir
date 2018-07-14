package com.example.asus_desktop.remask;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.SessionManager;
import com.example.asus_desktop.remask.Model.UserProfilSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Profil extends Fragment {

    public Profil(){}
    RelativeLayout view;
    private UserProfilSiswa modelProfilSiswa;
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnLogin ;
    private TextView NamaSiswa,Status,Sekolah,Email;
    private UserProfilSiswa modelUserProfile;
    private String siswa_id;
    private TextView Tentang;
    private TextView Logout;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profil, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");


        NamaSiswa = (TextView) view.findViewById(R.id.tvNamaLengkap);
        Status = (TextView) view.findViewById(R.id.tvStatus);
        Sekolah = (TextView) view.findViewById(R.id.tvSekolah);
        Email = (TextView) view.findViewById(R.id.tvEmail);
        Tentang = (TextView) view.findViewById(R.id.tvtentang);
        Logout = (TextView) view.findViewById(R.id.tvLogout);

        Tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Tools();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = new SessionManager(getActivity());
                session.logoutUser();
               onStop();
            }
        });



        getActivity().setTitle("Profil Saya");

        ApiClient.services_get_profil.getProfile(siswa_id).enqueue(new Callback<UserProfilSiswa>() {
            @Override
            public void onResponse(Call<UserProfilSiswa> call, Response<UserProfilSiswa> response) {
                modelUserProfile = response.body();
                NamaSiswa.setText(modelUserProfile.getNamaLengkap());
                Status.setText(modelUserProfile.getLevel());
                Sekolah.setText(modelUserProfile.getSekolah());
                Email.setText(modelUserProfile.getEmail());

                switch (modelUserProfile.getLevel()) {
                    case "1":
                      Status.setText("Siswa");
                        break;
                    case "2":
                        Status.setText("Guru");
                        break;
                    case "3":
                        Status.setText("Orangtua");
                        break;
                }
            }
            public void onFailure(Call<UserProfilSiswa> call, Throwable t) {

            }


        });


        return view;
    }
}