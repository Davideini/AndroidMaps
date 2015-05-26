package com.sce3.thirdyear.androidmaps.fragments;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.internal.la;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.classes.DownloadImageTask;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    ArrayList<Ad> ads;
    int numOfAds=0;
    ImageView img;
    ArrayList<String> urls;
    TextView desc,address;
    public ResultFragment() {
        ads = new ArrayList<Ad>();
        urls =new ArrayList<String>();
        urls.add("http://upload.wikimedia.org/wikipedia/commons/f/f8/Ellen_H._Swallow_Richards_House_Boston_MA_01.jpg");
        urls.add("http://ourhouse.biz/wp-content/uploads/2013/06/House-with-a-Lot-of-Windows.jpg");
        urls.add("http://cmbuilders.com.ph/articles/public_html/images/modern-zen/03.jpg");
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
        img=(ImageView)view.findViewById(R.id.imgResButton);
        updateTextView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void updateTextView(){

        if(numOfAds<ads.size()) {
            address.setText(ads.get(numOfAds).getApartment().getAddress().toString());
            desc.setText(ads.get(numOfAds).getApartment().getDesc().toString());
            setMainImg(numOfAds);
            numOfAds++;
        }
    }
    public void setMainImg(int index)
    {

        new DownloadImageTask(this.img).execute(urls.get(index));
       // scaleImage();

    }
    public void getResults()
    {



    }
    public void getHousedetails() {

    }

}