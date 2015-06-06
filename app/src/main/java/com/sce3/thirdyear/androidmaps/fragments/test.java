package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sce3.thirdyear.androidmaps.R;


/**
 * Created by win7 on 25/04/2015.
 */
public class test extends Fragment {


    public test() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.test, container, false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}

