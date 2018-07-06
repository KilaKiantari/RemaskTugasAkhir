package com.example.asus_desktop.remask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelGroupAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 5/5/2018.
 */

public class JoinGroup extends AppCompatActivity implements SearchView.OnQueryTextListener{

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private JoinGroupAdapter adapter;
    private ModelGroupAll modelGroupAll;
    ApiClient apiClient;
    SearchView searchView;
    private String namagroup;
    private String guru_id;
    private String siswa_id;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingroup);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        SharedPreferences sharedPreferences = JoinGroup.this.getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(JoinGroup.this);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
        JoinGroup.this.setTitle("Daftar Group");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = (SearchView) findViewById(R.id.searchView1);
        searchView.setQueryHint("Cari nama group");
       // searchView.setOnClickListener(JoinGroup.this);
        searchView.setFocusable(true);
        searchView.setOnQueryTextListener(JoinGroup.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(JoinGroup.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        ApiClient.services_get_group_all.getGroupAll(siswa_id).enqueue(new Callback<ModelGroupAll>() {
            @Override
            public void onResponse(Call<ModelGroupAll> call, Response<ModelGroupAll> response) {
                if (response.isSuccessful()) {
                    modelGroupAll = response.body();
//                adapter = new JoinGroupAdapter(this,modelGroupAll.getResults());
                    adapter = new JoinGroupAdapter(JoinGroup.this, modelGroupAll.getResults());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    //Log.d("A",response.body());
                    //Toast.makeText(JoinGroup.this, "" + namagroup, Toast.LENGTH_SHORT).show();
                       progressDialog.dismiss();
                } else {
                    Toast.makeText(JoinGroup.this, "KESALAHAN BIASA", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelGroupAll> call, Throwable t) {
                Toast.makeText(JoinGroup.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_join, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_new:
                Intent openCreateNote = new Intent(JoinGroup.this, Buat_Catatan_Pendidikan.class);
                startActivity(openCreateNote);
                return true;
            case R.id.action_settings:
                Intent ab = new Intent(JoinGroup.this, MainActivity.class);
                ab.putExtra("Extra","Tools");
                startActivity(ab);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerView.setVisibility(View.GONE);
        ApiClient.services_get_search_group.getSearchGroup(newText).enqueue(new Callback<ModelGroupAll>() {
            @Override
            public void onResponse(Call<ModelGroupAll> call, Response<ModelGroupAll> response) {
                Log.e("Response Grafik " , "Code : " + response.code());
                if(response.isSuccessful()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter = new JoinGroupAdapter(JoinGroup.this, response.body().getResults());
                    recyclerView.setAdapter(adapter);
                   // progressDialog.dismiss();
                }
                else {
                    Toast.makeText(JoinGroup.this, "Terjadi Kesalahan Search", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelGroupAll> call, Throwable t) {
                Toast.makeText(JoinGroup.this, ""+t, Toast.LENGTH_LONG).show();

                //progressDialog.dismiss();
            }
        });
        return true;
    }
}