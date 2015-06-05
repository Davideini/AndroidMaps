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
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.la;
import com.google.android.gms.plus.model.people.Person;
import com.sce3.thirdyear.androidmaps.HouseDetailsActivity;
import com.sce3.thirdyear.androidmaps.MenuActivity;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.showImgsActivity;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.classes.DownloadImageTask;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment  {

    ArrayList<Ad> ads;
    int numOfAds=0;
    ImageView img;
    ArrayList<String> urls;
    TextView desc,address;
    Button moreBtn;
    boolean isImageFitToScreen;
    Bitmap b;
    ImageButton okBtn,cancelBtn;
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
        okBtn=(ImageButton) view.findViewById(R.id.okBtn);
        cancelBtn=(ImageButton) view.findViewById(R.id.cancelBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDecisionButtonClick(v);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDecisionButtonClick(v);
                updateTextView();
            }
        });
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

    public void onDecisionButtonClick(View v)
    {
        if(numOfAds<ads.size()) {
            SQLiteDB db = new SQLiteDB(getActivity().getApplicationContext());
            String session_str = db.getSavedSession();
            int desc = 0;
            if (v.getTag().equals("ok")) {
                desc = 0;
                Toast.makeText(getActivity().getBaseContext(), "ok pressed!!", Toast.LENGTH_LONG).show();

            } else if (v.getTag().equals("no")) {
                desc = 1;
                Toast.makeText(getActivity().getBaseContext(), "no pressed!!", Toast.LENGTH_LONG).show();
            }

            String address = String.format("http://%s/JavaWeb/api?action=addHistory&apartment_id=%s&deleted=%s&session=%s", JSONRequest.SERVER, String.valueOf(ads.get(numOfAds).getApartment().getId()), String.valueOf(desc), session_str);
            JSONRequest json = new JSONRequest(address);
            try {
                JSONObject jobj = new JSONObject(json.getJSON());
                if (jobj.getString("result").equals("success")) {
                    Log.d("transferToHistory", "decision done!!!");
                    Toast.makeText(getActivity().getApplicationContext(), "decision done!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    Log.d("ErroTansfer", "error to tranfer");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
