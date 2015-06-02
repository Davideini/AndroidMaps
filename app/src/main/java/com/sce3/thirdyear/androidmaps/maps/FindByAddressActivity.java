package com.sce3.thirdyear.androidmaps.maps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

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

            // Animating to the touched position
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            // Placing a marker on the touched position
            userMarker = mMap.addMarker(markerOptions);

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

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();


            mMap.setOnMapClickListener(mapClickListener);
            mMap.setOnMarkerClickListener(markerClickListener);
            // Check if we were successful in obtaining the map.
        }


        String address = getIntent().getExtras().getString(Address.FORMATTED_ADDRESS);

        double lat = getIntent().getExtras().getDouble(Address.LAT);

        double lng = getIntent().getExtras().getDouble(Address.LNG);

        boolean hasMarkers = SearchMarkers(address, lat, lng);
        lvLocations = (ListView) findViewById(R.id.lvLocations);
        if (hasMarkers) {
            if (address != null)
                LocationsList.MakeListView(address, this, lvLocations, mMap);
            else
                LocationsList.MakeListView(new LatLng(lat, lng), this, lvLocations, mMap);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_address);


        SetupClickEvents();
        //Utility.SetupUIKeyboard(findViewById(R.id.mainLayout), FindByAddressActivity.this);

        setUpMapIfNeeded();
    }

    private void SetupClickEvents() {
//        Button btnReturn = (Button) findViewById(R.id.btnReturn);
//
//        btnReturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedMarker == null) {
//                    Toast.makeText(FindByAddressActivity.this, "Select location marker", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                Address address = Address.AddressByLatLng(selectedMarker.getPosition());
//                Intent returnIntent = new Intent();
//
//                returnIntent.putExtra(Address.STREET_NUMBER, address.getStreetNumber());
//                returnIntent.putExtra(Address.STREET, address.getStreet());
//                returnIntent.putExtra(Address.CITY, address.getCity());
//                returnIntent.putExtra(Address.COUNTRY, address.getCountry());
//                returnIntent.putExtra(Address.LAT, address.getLat());
//                returnIntent.putExtra(Address.LNG, address.getLng());
//
//                setResult(RESULT_OK, returnIntent);
//                finish();
//            }
//        });
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
        return hasMarkers;
    }

    public boolean onQueryTextChange(String newText) {

        return true;
    }

    public boolean onQueryTextSubmit(String address) {

        SearchMarkers(address, 0, 0);
        LocationsList.MakeListView(address, this, lvLocations, mMap);

        mStatusView.clearFocus();
        return true;
    }

    public boolean onClose() {

        return false;
    }

}
