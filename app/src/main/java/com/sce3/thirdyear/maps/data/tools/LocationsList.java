package com.sce3.thirdyear.maps.data.tools;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sce3.thirdyear.maps.data.Address;

import java.util.List;

/**
 * Created by David on 27/05/2015.
 */
public class LocationsList {

    public static void MakeListView(String address, Context context, ListView listview, final GoogleMap mMap) {

        final List<Address> addresses = Address.SearchApi(address);

        final StableArrayAdapter adapter = new StableArrayAdapter(context,
                android.R.layout.simple_list_item_1, addresses);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Address item = (Address) parent.getItemAtPosition(position);

                Marker marker = item.getMarker();

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12));
            }

        });


    }

    public static void MakeListView(LatLng position, Context context, ListView listview, final GoogleMap mMap) {

        final List<Address> addresses = Address.AddressByLatLngArray(position);

        final StableArrayAdapter adapter = new StableArrayAdapter(context,
                android.R.layout.simple_list_item_1, addresses);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Address item = (Address) parent.getItemAtPosition(position);

                Marker marker = item.getMarker();

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12));

            }

        });

    }
}
