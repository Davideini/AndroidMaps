package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sce3.thirdyear.androidmaps.R;

public class frag extends Fragment {

    public frag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag,container,false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
