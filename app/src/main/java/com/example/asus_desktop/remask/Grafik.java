package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Kuncoro on 22/03/2016.
 */

/**
 * Created by Kuncoro on 22/03/2016.
 */
public class Grafik extends Fragment {

    public Grafik(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.slide_show, container, false);

        getActivity().setTitle("Grafik Kerajinan");

        return view;
    }
}