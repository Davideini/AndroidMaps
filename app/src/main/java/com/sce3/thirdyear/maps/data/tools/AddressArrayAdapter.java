package com.sce3.thirdyear.maps.data.tools;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.sce3.thirdyear.maps.data.Address;

import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 27/05/2015.
 */
public class AddressArrayAdapter extends ArrayAdapter<Address> {

    HashMap<Address, Integer> mIdMap = new HashMap<Address, Integer>();

    public AddressArrayAdapter(Context context, int textViewResourceId,
                               List<Address> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        Address item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
