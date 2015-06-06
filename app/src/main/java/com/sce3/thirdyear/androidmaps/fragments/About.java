package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sce3.thirdyear.androidmaps.R;

/**
 * Created by win7 on 05/06/2015.
 */
public class About extends Fragment {
    public About() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //
        // return super.onCreateView(R.layout.about_fragment, container, false);
        View v = inflater.inflate(R.layout.about_fragment, container, false);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
