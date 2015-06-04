package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.classes.User;

/**
 * Created by win7 on 04/06/2015.
 */
public class UserDetailsFragment extends Fragment {

    public UserDetailsFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_update_user_details,container,false);


        return view;
    }
}
