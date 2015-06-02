package com.sce3.thirdyear.androidmaps.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.maps.data.Address;
import com.sce3.thirdyear.maps.data.tools.LocationsList;
import com.sce3.thirdyear.maps.data.tools.MapUtility;
import com.sce3.thirdyear.maps.data.tools.MarkerUtility;
import com.sce3.thirdyear.maps.data.tools.Utility;

import java.util.ArrayList;
import java.util.List;

public class FindByAddressActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    // From search results shwo on map
    private GoogleMap mMap;

    private Marker userMarker = null;

    private Marker selectedMarker = null;

    private List<Address> locations = null;

    private ListView lvLocations = null;


    private String mode = "";

    private TextView mStatusView;

    private GoogleMap.OnMapClickListener mapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            // Creating a marker
            MarkerOptions markerOptions = new MarkerOptions();

            // Setting the position for the marker
            markerOptions.position(latLng);

            // Setting the title for the marker.
            // This will be displayed on taping the marker
            Address address = Address.AddressByLatLng(latLng);
            if (address == null)
                return;

            markerOptions.title(address.getFormattedAddress());

            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

            // Clears the previously touched position
            if (userMarker != null) {
                userMarker.remove();
            }

            float zoom = mMap.getCameraPosition().zoom;
            if (zoom < 14) {
                zoom = 16;
            }

            // Animating to the touched position
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

            // Placing a marker on the touched position
            userMarker = mMap.addMarker(markerOptions);

            userMarker.setDraggable(true);


            selectedMarker = null;
        }
    };

    private GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            selectedMarker = marker;
            marker.setSnippet("true");
            MarkerUtility.MarkerInfo(marker, mMap, FindByAddressActivity.this);
            return true;
        }
    };
    private GoogleMap.OnMarkerDragListener onMarkerDragListener = new GoogleMap.OnMarkerDragListener() {
        @Override
        public void onMarkerDragStart(Marker marker) {
            Toast.makeText(FindByAddressActivity.this, "Position: " + marker.getPosition().toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onMarkerDrag(Marker marker) {

        }

        @Override
        public void onMarkerDragEnd(Marker marker) {

            Toast.makeText(FindByAddressActivity.this, "Position: " + marker.getPosition().toString(), Toast.LENGTH_SHORT).show();

        }
    };

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            Address address = Address.FindAddress("Ashdod Israel");

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(address.getPosition(), 10));

            mMap.setOnMapClickListener(mapClickListener);
            mMap.setOnMarkerClickListener(markerClickListener);
            mMap.setOnMarkerDragListener(onMarkerDragListener);

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
        }


//        String address = getIntent().getExtras().getString(Address.FORMATTED_ADDRESS);
//
//        double lat = getIntent().getExtras().getDouble(Address.LAT);
//
//        double lng = getIntent().getExtras().getDouble(Address.LNG);
//
//        boolean hasMarkers = SearchMarkers(address, lat, lng);
//        lvLocations = (ListView) findViewById(R.id.lvLocations);
//        if (hasMarkers) {
//            if (address != null)
//                LocationsList.MakeListView(address, this, lvLocations, mMap);
//            else
//                LocationsList.MakeListView(new LatLng(lat, lng), this, lvLocations, mMap);
//
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_address);

        //Utility.SetupUIKeyboard(findViewById(R.id.mainLayout), FindByAddressActivity.this);

        setUpMapIfNeeded();

        SetupMode(getIntent());
    }

    private void SetupMode(Intent intent) {
        Bundle data = intent.getExtras();
        double zoom = data.getDouble(Address.SET_ZOOM);
        double lat = data.getDouble(Address.LAT);
        double lng = data.getDouble(Address.LNG);

        boolean searchable = data.getBoolean(Address.SEARCHABLE, true);
        boolean dialog = data.getBoolean(Address.SHOW_DAILOG, true);
        boolean forApt = data.getBoolean(Address.FOR_DEPARTMANTS, false);

        String formattedAddress = data.getString(Address.FORMATTED_ADDRESS);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_by_address, menu);


        MenuItem searchItem = (MenuItem) menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) searchItem.getActionView();
        Utility.SetupSearchView(searchItem, mSearchView, this, this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean SearchMarkers(String address, double lat, double lng) {
        boolean hasMarkers = false;
        locations = null;
        mMap.clear();

        if (locations == null && address != null && !address.isEmpty()) {
            locations = Address.SearchApi(address);
        }

        if (locations == null) {
            locations = new ArrayList<>();
            locations.add(Address.AddressByLatLng(new LatLng(lat, lng)));
        }

        if (locations == null) {

        }

        for (Address item : locations) {
            if (item == null) continue;

            Marker marker = mMap.addMarker(MapUtility.CreateMarker(item, BitmapDescriptorFactory.HUE_BLUE));

            item.setMarker(marker);

            hasMarkers = true;
        }

        if (lvLocations == null) {
            lvLocations = (ListView) findViewById(R.id.lvLocations);
        }
        LocationsList.MakeListView(locations, this, lvLocations, mMap);

        return hasMarkers;
    }

    public boolean onQueryTextChange(String newText) {

        return true;
    }

    public boolean onQueryTextSubmit(String address) {

        SearchMarkers(address, 0, 0);


        return true;
    }

    public boolean onClose() {

        return false;
    }

}
