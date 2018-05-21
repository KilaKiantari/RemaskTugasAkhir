package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asus_desktop.remask.Model.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    Fragment fragment = null;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    CollapsingToolbarLayout collapsingToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //collapsingToolbar.setTitle("TWOH's Engineering");
        //collapsingToolbar.setExpandedTitleColor(Color.parseColor("#44ffffff"));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent Extra = getIntent();
        if(Extra.hasExtra("Extra")){
            String goTo =  Extra.getStringExtra("Extra");
            if(goTo.equals("Tools")){
                fragment = new Tools();
                callFragment(fragment);
            }
        }else{
            fragment = new Daftar_Catatan();
            callFragment(fragment);
        }
//        // tampilan default awal ketika aplikasii dijalankan
//        if (savedInstanceState == null) {
//
//        }

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent ab = new Intent(MainActivity.this, MainActivity.class);
                ab.putExtra("Extra","Tools");
                startActivity(ab);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Untuk memanggil layout dari menu yang dipilih
        if (id == R.id.nav_daftar) {
            fragment = new Daftar_Catatan();
            callFragment(fragment);
        }else if(id==R.id.nav_histori){
            fragment = new HistoriTugas();
            callFragment(fragment);
        } else if (id == R.id.nav_skala) {
            fragment = new Skala_Prioritas();
            callFragment(fragment);
        } else if (id == R.id.nav_grafik) {
            fragment = new Grafik();
            callFragment(fragment);
        } else if (id == R.id.nav_profil) {
            fragment = new Profil();
            callFragment(fragment);
        }
        else if (id == R.id.nav_mygroup) {
            fragment = new MyGroup();
            callFragment(fragment);
            }
        else if (id == R.id.nav_manage) {
            fragment = new Tools();
            callFragment(fragment);}
        else if (id == R.id.nav_logout) {
            sessionManager = new SessionManager(getApplicationContext());
            sessionManager.setLogin(false);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // untuk mengganti isi kontainer menu yang dipiih
    private void callFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }

}