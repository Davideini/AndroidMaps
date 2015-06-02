package com.sce3.thirdyear.androidmaps.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.internal.la;
import com.google.android.gms.plus.model.people.Person;
import com.sce3.thirdyear.androidmaps.HouseDetailsActivity;
import com.sce3.thirdyear.androidmaps.MenuActivity;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.showImgsActivity;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.classes.DownloadImageTask;

import java.io.InputStream;
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
    Button moreBtn;
    boolean isImageFitToScreen;
    Bitmap b;
    public ResultFragment() {

        ads = new ArrayList<Ad>();
        urls =new ArrayList<String>();
        urls.add("http://images.yad2.co.il/Pic/201505/29/2_1/o/o2_1_1_177227_20150529220536.jpg");
        urls.add("http://images.yad2.co.il/Pic/201505/29/2_1/o/o2_1_1_171994_20150529230551.jpg");
        urls.add("http://images.yad2.co.il/Pic/201505/29/2_1/o/o2_1_1_174092_20150529210544.jpg");
        ads.add(new Ad(new Apartment(1,555,0,"yavne",120200, "begin", "duani 8",true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f,5,123.123f, 34.215661f, 70.5f,"most beautiful apartment"),null ) );
        ads.add(new Ad(new Apartment(2,444,1, "asd",120200, "yud", "wdd 8",true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f,5,123.123f, 34.215661f, 70.5f,"beautiful apartment"),null ) );
        ads.add(new Ad(new Apartment(3,333,2, "yawfaw",120200, "alef", "wrwfw 1523b",true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f,5,123.123f, 34.215661f, 70.5f,"efewf eeefefe"),null ) );



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
        moreBtn=(Button) view.findViewById(R.id.mDetailsBtn);
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
            desc.setText(ads.get(numOfAds).getApartment().getComment().toString());
            setMainImg(numOfAds);
            numOfAds++;
        }
    }
    public void setMainImg(int index)
    {
        img.setClickable(false);
         new downloadImg(this.img,b).execute(urls.get(index));

       // scaleImage();

    }
    public void moreDetails()
    {
        if((numOfAds-1) < ads.size()) {
            Intent mIntent = new Intent(this.getActivity().getApplicationContext(), HouseDetailsActivity.class);
            mIntent.putExtra(MenuActivity.SER_KEY,ads.get(numOfAds-1).getApartment());
            startActivity(mIntent);
        }


    }
    public void sentToFullscreenActivity(Context con)
    {
        //startActivity(new Intent(Intent.,Uri.parse(urls.get(numOfAds))));

       Intent intent = new Intent(con, showImgsActivity.class);
       intent.putExtra("aaa",b);
       startActivity(intent);

    }


    public class downloadImg extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Bitmap b;
        public downloadImg(ImageView bmImage,Bitmap b) {
            this.bmImage = bmImage;
            this.b=b;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            b=result;
            bmImage.setImageBitmap(result);
            bmImage.setClickable(true);
        }

    }

}
