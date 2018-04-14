package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 2/25/2018.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Kuncoro on 22/03/2016.
 */
public class Profil extends Fragment {

    public Profil(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.activity_custom_calendar, container, false);

        getActivity().setTitle("Profil Saya");

        return view;
    }
}