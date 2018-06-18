package com.example.asus_desktop.remask.Progress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.asus_desktop.remask.R;

/**
 * Created by Asus-Desktop on 6/7/2018.
 */

public class MainActivityProgress extends AppCompatActivity implements TabLayout.OnTabSelectedListener{



   // private static final String TAG = HistoriBaru.class.getSimpleName();

    //This is our tablayout
    private TabLayout tabLayout;
    FragmentManager fragmentManager;
    Fragment fragment = null;
    private String id_tugas;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainprogress);
        //Adding toolbar to the activity
          Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarprogress);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        MainActivityProgress.this.setTitle("Proses Tugas");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Tugas Belum"));
        tabLayout.addTab(tabLayout.newTab().setText("Tugas Sudah"));
        //  tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        PagerProgress adapter = new PagerProgress(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        Intent Extra = getIntent();
        if(Extra.hasExtra("Extra")){
            String goTo =  Extra.getStringExtra("Extra");
            if(goTo.equals("ProgressTugas")){
                id_tugas = Extra.getStringExtra("id_tugas");
                Toast.makeText(this, ""+id_tugas, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putString("id_tugas",id_tugas);
                ProgressTugas newFragment = new ProgressTugas();
                newFragment.setArguments(args);
               // fragment = new ProgressTugas();
               // callFragment(fragment);

            }
        }else{

        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void callFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_containerprogress, fragment)
                .commit();
    }

}