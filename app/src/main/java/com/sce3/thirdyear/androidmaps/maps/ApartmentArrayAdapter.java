package com.sce3.thirdyear.androidmaps.maps;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.sce3.thirdyear.classes.Apartment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 03/06/2015.
 */
public class ApartmentArrayAdapter extends ArrayAdapter<Apartment> {

    HashMap<Apartment, Integer> mIdMap = new HashMap<Apartment, Integer>();

    public ApartmentArrayAdapter(Context context, int textViewResourceId, List<Apartment> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }


    @Override
    public long getItemId(int position) {
        Apartment item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
