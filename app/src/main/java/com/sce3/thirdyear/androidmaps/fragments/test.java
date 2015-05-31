package com.sce3.thirdyear.androidmaps.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;



import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.sce3.thirdyear.androidmaps.AddApartment;
import com.sce3.thirdyear.androidmaps.R;

import java.util.ArrayList;
import java.util.ResourceBundle;



/**
 * Created by win7 on 25/04/2015.
 */
public class test extends Fragment  {


    public test(){

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

