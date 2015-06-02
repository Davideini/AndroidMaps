package com.sce3.thirdyear.maps.data.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.maps.data.Address;

/**
 * Created by David on 27/05/2015.
 */
public class MarkerUtility {

    public static void MarkerInfo(final Marker marker, GoogleMap mMap, final Activity activity) {

        if (marker.getSnippet() == null) {
            mMap.moveCamera(CameraUpdateFactory.zoomIn());
            return;
        }
        //arg0.showInfoWindow();
        final Address data = Address.AddressByLatLng(marker.getPosition());
        final Dialog d = new Dialog(activity);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);


        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));


        d.setContentView(R.layout.marker_info);


        TextView tvFullAddress = (TextView) d.findViewById(R.id.tvFullAddress);
        tvFullAddress.setText(data.getFormattedAddress());


        Button btn = (Button) d.findViewById(R.id.btnSelectFromList);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Address address = Address.AddressByLatLng(marker.getPosition());
                Intent returnIntent = new Intent();

                returnIntent.putExtra(Address.STREET_NUMBER, address.getStreetNumber());
                returnIntent.putExtra(Address.STREET, address.getStreet());
                returnIntent.putExtra(Address.CITY, address.getCity());
                returnIntent.putExtra(Address.COUNTRY, address.getCountry());
                returnIntent.putExtra(Address.LAT, address.getLat());
                returnIntent.putExtra(Address.LNG, address.getLng());

                activity.setResult(Activity.RESULT_OK, returnIntent);
                activity.finish();
            }
        });


        ListView lv = (ListView) d.findViewById(R.id.lvInfoLocations);

        LocationsList.MakeListView(marker.getPosition(), activity, lv, mMap);

        d.show();
    }
}
