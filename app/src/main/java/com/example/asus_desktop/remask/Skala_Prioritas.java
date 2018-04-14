package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class Skala_Prioritas extends Fragment {

    public Skala_Prioritas(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.gellery, container, false);

        getActivity().setTitle("Skala Prioritas");

        return view;
    }
}