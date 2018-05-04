package com.example.asus_desktop.remask;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class Tools extends Fragment {

    public Tools(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tools, container, false);

        getActivity().setTitle("Tools");



        return view;


    }
    public static Tools newInstance() {
        Tools fragment = new Tools();
        return fragment;
    }
}