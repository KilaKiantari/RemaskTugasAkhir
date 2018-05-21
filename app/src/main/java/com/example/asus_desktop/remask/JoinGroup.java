package com.example.asus_desktop.remask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelGroupAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus-Desktop on 5/5/2018.
 */

public class JoinGroup extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private JoinGroupAdapter adapter;
    private ModelGroupAll modelGroupAll;
    ApiClient apiClient;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingroup);
        //addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

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

       // searchView = (SearchView) findViewById(R.id.searchView1);
        //searchView.setQueryHint("Search for sources, people, topics...");
        //searchView.setOnClickListener(JoinGroup.this);
        //searchView.setFocusable(true);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(JoinGroup.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        ApiClient.services_get_group_all.getGroupAll(1).enqueue(new Callback<ModelGroupAll>() {
            @Override
            public void onResponse(Call<ModelGroupAll> call, Response<ModelGroupAll> response) {
                modelGroupAll = response.body();
//                adapter = new JoinGroupAdapter(this,modelGroupAll.getResults());
                adapter = new JoinGroupAdapter(JoinGroup.this,modelGroupAll.getResults());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ModelGroupAll> call, Throwable t) {

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
/*
    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Dimas Maulana", "1414370309"));
        mahasiswaArrayList.add(new Mahasiswa("Fadly Yonk", "1214234560"));
        mahasiswaArrayList.add(new Mahasiswa("Ariyandi Nugraha", "1214230345"));
        mahasiswaArrayList.add(new Mahasiswa("Aham Siswana", "1214378098"));
    }
    */

}