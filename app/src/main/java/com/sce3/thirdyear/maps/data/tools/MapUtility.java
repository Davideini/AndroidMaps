package com.sce3.thirdyear.maps.data.tools;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.maps.data.Address;

/**
 * Created by David on 27/05/2015.
 */
public class MapUtility {


    private static GoogleMap.OnInfoWindowClickListener infoClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {

        }
    };


    public static GoogleMap GoogleMapsSetup(final GoogleMap maps, GoogleMap.OnMapClickListener mapClickListener, GoogleMap.OnMarkerClickListener markerClickListener) {
        // Setup ui
        maps.getUiSettings().setZoomControlsEnabled(true);
        maps.getUiSettings().setMyLocationButtonEnabled(true);

        maps.setMyLocationEnabled(true);

        // Setup events
        maps.setOnMapClickListener(mapClickListener);
        maps.setOnInfoWindowClickListener(infoClickListener);
        maps.setOnMarkerClickListener(markerClickListener);


        LatLngBounds ISRAEL_BOUNDS = new LatLngBounds(new LatLng(31.0111963, 34.4454602), new LatLng(32.6318939, 35.0304821));
        maps.moveCamera(CameraUpdateFactory.newLatLngBounds(ISRAEL_BOUNDS, 0));

        return maps;
    }


    public static MarkerOptions CreateMarker(Apartment apartment, float style) {
        return new MarkerOptions()
                .title(apartment.getAddress())
                .position(apartment.getPosition())
                .icon(BitmapDescriptorFactory.defaultMarker(style));
    }

    public static MarkerOptions CreateMarker(Address address, float style) {
        return new MarkerOptions()
                .title(address.getFormattedAddress())
                .position(address.getPosition())
                .icon(BitmapDescriptorFactory.defaultMarker(style));
    }
}
