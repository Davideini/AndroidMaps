package com.sce3.thirdyear.androidmaps.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.internal.la;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.Apartment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    ArrayList<Ad> ads;
    int numOfAds=0;
    Context context;
    TextView desc,address;
    public ResultFragment() {
        ads = new ArrayList<Ad>();
        ads.add(new Ad(new Apartment(1, "yavne",120200, "begin", "duani 8",true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f,5,123.123f, 34.215661f, 70.5f,"most beautiful apartment"),null ) );
        ads.add(new Ad(new Apartment(2, "asd",120200, "yud", "wdd 8",true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f,5,123.123f, 34.215661f, 70.5f,"beautiful apartment"),null ) );
        ads.add(new Ad(new Apartment(3, "yawfaw",120200, "alef", "wrwfw 1523b",true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f,5,123.123f, 34.215661f, 70.5f,"efewf eeefefe"),null ) );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_result,container,false);
        desc=(TextView) view.findViewById(R.id.DescVal);
        address=(TextView) view.findViewById(R.id.AddressVal);
        if(numOfAds<=ads.size()) {
            address.setText(ads.get(numOfAds).getApartment().getAddress().toString());
            desc.setText(ads.get(numOfAds).getApartment().getDesc().toString());
            numOfAds++;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void updateTextView(){
        if(numOfAds<=ads.size()) {
            address.setText(ads.get(numOfAds).getApartment().getAddress().toString());
            desc.setText(ads.get(numOfAds).getApartment().getDesc().toString());
            numOfAds++;
        }
    }

}
