package com.example.asus_desktop.remask.HistoriTugas;

/**
 * Created by Asus-Desktop on 6/1/2018.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_desktop.remask.R;

//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views
public class HistoriBaru extends Fragment implements TabLayout.OnTabSelectedListener{

    public HistoriBaru() {
    }

    private static final String TAG = HistoriBaru.class.getSimpleName();

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_histori_baru, container, false);
        getActivity().setTitle("Histori Baru");

        //Adding toolbar to the activity
        //  Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //  getActivity().setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Tugas Belum"));
        tabLayout.addTab(tabLayout.newTab().setText("Tugas Sudah"));
        //  tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        //Creating our pager adapter
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        return view;
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
}