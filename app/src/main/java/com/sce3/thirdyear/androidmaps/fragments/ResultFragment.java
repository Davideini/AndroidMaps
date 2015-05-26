package com.sce3.thirdyear.androidmaps.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.classes.Ad;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    ArrayList<Ad> ads;

    public ResultFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result,container,false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
