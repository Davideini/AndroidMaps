package com.sce3.thirdyear.androidmaps.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.sce3.thirdyear.androidmaps.HouseDetailsActivity;
import com.sce3.thirdyear.androidmaps.MenuActivity;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.androidmaps.showImgsActivity;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    //ArrayList<Ad> ads;
    //int numOfAds = 0;
    ImageView img;
    //ArrayList<String> urls;
    TextView desc, address;
    Button moreBtn;
    boolean isImageFitToScreen;
    Bitmap b;
    ImageButton okBtn, cancelBtn;

    public ResultFragment() {
/*
        ads = new ArrayList<Ad>();
        urls = new ArrayList<String>();
        urls.add("http://images.yad2.co.il/Pic/201505/29/2_1/o/o2_1_1_177227_20150529220536.jpg");
        urls.add("http://images.yad2.co.il/Pic/201505/29/2_1/o/o2_1_1_171994_20150529230551.jpg");
        urls.add("http://images.yad2.co.il/Pic/201505/29/2_1/o/o2_1_1_174092_20150529210544.jpg");
        ads.add(new Ad(new Apartment(1, 555, 0, "yavne", 120200, "begin", "duani 8", true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f, 5, 123.123f, 34.215661f, 70.5f, "most beautiful apartment"), null));
        ads.add(new Ad(new Apartment(2, 444, 1, "asd", 120200, "yud", "wdd 8", true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f, 5, 123.123f, 34.215661f, 70.5f, "beautiful apartment"), null));
        ads.add(new Ad(new Apartment(3, 333, 2, "yawfaw", 120200, "alef", "wrwfw 1523b", true, false, true, true, true, false, true, true, false, true, true, true, false, 5.5f, 5, 123.123f, 34.215661f, 70.5f, "efewf eeefefe"), null));
*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        if(MenuActivity.resultIndex<MenuActivity.resultsAds.size()) {
            view = inflater.inflate(R.layout.noresult_fragment, container, false);
        }
        else {
            view = inflater.inflate(R.layout.fragment_result, container, false);
            //view.findViewById(R.id.tabs)
            //////////////////////////////////////////////////////////////
            TabHost t=((TabHost)view.findViewById(android.R.id.tabhost));
                    if(t.getTabWidget().getTabCount()!=0) {
                        t.setCurrentTab(0);
                        t.clearAllTabs();
                    }
            //////////////////////////////////////////////////////////////////
            desc = (TextView) view.findViewById(R.id.DescVal);
            address = (TextView) view.findViewById(R.id.AddressVal);
            img = (ImageView) view.findViewById(R.id.imgResButton);
            moreBtn = (Button) view.findViewById(R.id.mDetailsBtn);
            okBtn = (ImageButton) view.findViewById(R.id.okBtn);
            cancelBtn = (ImageButton) view.findViewById(R.id.cancelBtn);
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

                }
            });
            nextResult();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void nextResult() {
        if (MenuActivity.resultIndex < MenuActivity.resultsAds.size())
            updateTextView();
        else getFragmentManager().beginTransaction().replace(R.id.content_frame, new NoResultFragment()).commit();
    }

    /* public void updateTextView() {

         if (numOfAds < ads.size()) {
             address.setText(ads.get(numOfAds).getApartment().getAddress().toString());
             desc.setText(ads.get(numOfAds).getApartment().getComment().toString());
             setMainImg(numOfAds);
             numOfAds++;
         }
     }
     public void setMainImg(int index) {
         img.setClickable(false);
         new downloadImg(this.img, b).execute(urls.get(index));

         // scaleImage();

     }*/
    public void updateTextView() {

        if (MenuActivity.resultIndex < MenuActivity.resultsAds.size()) {
            address.setText(MenuActivity.resultsAds.get(MenuActivity.resultIndex).getApartment().getAddress().toString());
            desc.setText(MenuActivity.resultsAds.get(MenuActivity.resultIndex).getApartment().getComment().toString());
            setMainImg(MenuActivity.resultIndex);
            MenuActivity.resultIndex++;
        }
    }


    public void setMainImg(int index) {
        img.setClickable(false);
        String imgUrl = MenuActivity.resultsAds.get(index).getImgSrcs().get(0);
        if (imgUrl != null)
            new downloadImg(this.img, b).execute(imgUrl);
        // scaleImage();
    }

    public void moreDetails() {
        if ((MenuActivity.resultIndex - 1) < MenuActivity.resultsAds.size()) {
            Intent mIntent = new Intent(this.getActivity().getApplicationContext(), HouseDetailsActivity.class);
            mIntent.putExtra(MenuActivity.SER_KEY, MenuActivity.resultsAds.get(MenuActivity.resultIndex - 1).getApartment());
            startActivity(mIntent);
        }


    }

    public void sentToFullscreenActivity(Context con) {
        //startActivity(new Intent(Intent.,Uri.parse(urls.get(numOfAds))));

        Intent intent = new Intent(con, showImgsActivity.class);
        intent.putExtra("aaa", b);
        startActivity(intent);

    }


    public class downloadImg extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Bitmap b;

        public downloadImg(ImageView bmImage, Bitmap b) {
            this.bmImage = bmImage;
            this.b = b;
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
            b = result;
            bmImage.setImageBitmap(result);
            bmImage.setClickable(true);
        }

    }

    public void onDecisionButtonClick(View v) {

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

        String address = String.format("http://%s/JavaWeb/api?action=addHistory&apartment_id=%s&deleted=%s&session=%s", JSONRequest.SERVER, String.valueOf(MenuActivity.resultsAds.get(MenuActivity.resultIndex).getApartment().getId()), String.valueOf(desc), session_str);
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
        nextResult();
    }

}
