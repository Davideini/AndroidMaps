package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sce3.thirdyear.androidmaps.R;

/**
 * Created by win7 on 06/06/2015.
 */
public class NoResultFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.noresult_fragment,container,false);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
