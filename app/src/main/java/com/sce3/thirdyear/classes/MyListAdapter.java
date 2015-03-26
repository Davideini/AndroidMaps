package com.sce3.thirdyear.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sce3.thirdyear.androidmaps.R;

import java.util.Map;
import java.util.List;

public class MyListAdapter extends ArrayAdapter<Object> {

    public MyListAdapter(Context context,List<Object> list) {
        super(context, R.layout.historyitem,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View theView=inflater.inflate(R.layout.historyitem,parent,false);
        Map<String,String> map = (Map)getItem(position);

        String city=map.get("city");
        String address=map.get("address");
        String price=map.get("price");
        String filename=map.get("filename");


        TextView cityText = (TextView) theView.findViewById(R.id.textView6);
        TextView addressText = (TextView) theView.findViewById(R.id.textView7);
        TextView priceText = (TextView) theView.findViewById(R.id.textView8);

        cityText.setText(city);
        addressText.setText(address);
        priceText.setText(price);


        ImageView theImageView = (ImageView) theView.findViewById(R.id.imageView2);


        new DownloadImageTask(theImageView).execute(String.format("http://%s/%s/%s",JSONRequest.SERVER,JSONRequest.IMAGE_DIR,filename));

        return theView;
    }
}
