package com.sce3.thirdyear.maps.data.tools;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.sce3.thirdyear.maps.data.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 27/05/2015.
 */
public class LocationsList {

    public static void MakeListView(String address, Context context, ListView listview) {
//        if (locations == null)
//            locations = Address.SearchApi(address);
//
//        List<String> li = new ArrayList<String>();
//        for (Address item : locations) {
//            li.add(item.getFormattedAddress());
//        }
//        ArrayAdapter<String> strs = new ArrayAdapter<String>(this, R.layout.location_item, li.toArray(new String[li.size()]));
//
//        ListView lv = (ListView) findViewById(R.id.lvLocations);
//        lv.setAdapter(strs);


        List<Address> addresses = Address.SearchApi(address);
        String[] values = new String[addresses.size()];

        for (int i = 0; i < addresses.size(); i++) {
            Address a = addresses.get(i);
            values[i] = a.getFormattedAddress();
        }

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(context,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        });


    }

    public static void MakeListView(LatLng position, Context context, ListView listview) {

        List<Address> addresses = Address.AddressByLatLngArray(position);
        String[] values = new String[addresses.size()];

        for (int i = 0; i < addresses.size(); i++) {
            Address a = addresses.get(i);
            values[i] = a.getFormattedAddress();
        }

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(context,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        });

    }
}
