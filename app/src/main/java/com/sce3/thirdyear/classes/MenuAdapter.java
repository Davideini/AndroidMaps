package com.sce3.thirdyear.classes;

import android.app.Activity;
import android.content.Context;

import android.view.*;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.internal.po;
import com.sce3.thirdyear.androidmaps.R;

import java.util.ArrayList;


/**
 * Created by win7 on 12/05/2015.
 */
public class MenuAdapter extends BaseAdapter{
    private ArrayList<MenuItemTemplate> menuitems;
    private Context context;

    public MenuAdapter(Context context, ArrayList<MenuItemTemplate> values)
    {
        //
        // super(context,R.layout.drawer_list_item,values);
        this.context=context;
        this.menuitems=values;
    }
    @Override
    public Object getItem(int position) {
        return menuitems.get(position);
    }
    @Override
    public int getCount() {
        return menuitems.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(context);

        View theView = theInflater.inflate(R.layout.drawer_list_item, parent, false);

        TextView theTextView = (TextView) theView.findViewById(R.id.text1);
        theTextView.setText(menuitems.get(position).getTitle());

        ImageView img=(ImageView) theView.findViewById(R.id.new_icon);
        //int drawableResourceId = getContext().getResources().getIdentifier(icon, "drawable",getContext().getPackageName());
        img.setImageResource(menuitems.get(position).getIcon());
        return theView;
    }
}
