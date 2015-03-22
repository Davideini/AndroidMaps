package com.sce3.thirdyear.maps.data;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 22/03/2015.
 */
public class MapData {

    private List<MarkerOptions> markers;

    public MapData() {
        markers = new ArrayList<>();

        markers.add(new MarkerOptions()
                .title("Houes 1")
                .snippet("For rent 2000 nis/month")
                .position(new LatLng(31.801174, 34.649934)));
        markers.add(new MarkerOptions()
                .title("House 2")
                .snippet("For rent 1800 nis/month")
                .position(new LatLng(31.795885, 34.642466)));
        markers.add(new MarkerOptions()
                .title("Studio 1")
                .snippet("For rent 2000 nis/month")
                .position(new LatLng(31.786766, 34.638347)));
        markers.add(new MarkerOptions()
                .title("Villa 1")
                .snippet("For sell 4.5 million nis")
                .position(new LatLng(31.787714, 34.658388)));
        markers.add(new MarkerOptions()
                .title("House 4")
                .snippet("For sell 1.5 million nis")
                .position(new LatLng(31.779543, 34.629592)));
    }

    public List<MarkerOptions> getMarkers() {
        return markers;
    }
}
