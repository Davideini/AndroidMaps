package com.sce3.thirdyear.androidmaps.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.maps.data.Address;
import com.sce3.thirdyear.maps.data.tools.AddressArrayAdapter;
import com.sce3.thirdyear.maps.data.tools.MapUtility;
import com.sce3.thirdyear.maps.data.tools.MarkerUtility;
import com.sce3.thirdyear.maps.data.tools.Utility;

import java.util.ArrayList;
import java.util.List;

public class FindByAddressActivity extends ActionBarActivity implements SearchView.OnQueryTextListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private SupportMapFragment supMapFragment;

    private Marker userMarker = null;

    private Marker selectedMarker = null;

    private List<Address> locations = null;

    private List<Apartment> apartments = null;

    private ListView lvLocations = null;

    private MenuItem menuClear = null;

    private float zoom;
    private boolean searchable;
    private boolean dialog;
    private boolean forApt;

    private String mode = "";

    private TextView mStatusView;

    //region On Activity Events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_address);

        setUpMapIfNeeded();

        SetupMode(getIntent());
    }

    //endregion

    //region Initilize Functions

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            supMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            mMap = supMapFragment.getMap();

            Address address = Address.FindAddress("Ashdod Israel");
            zoom = getIntent().getExtras().getFloat(Address.SET_ZOOM, 10);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(address.getPosition(), zoom));

            mMap.setOnMapClickListener(this);
            mMap.setOnMarkerClickListener(this);
            //mMap.setOnMarkerDragListener(onMarkerDragListener);

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
        }

    }

    private void SetupMode(Intent intent) {
        Bundle data = intent.getExtras();
        zoom = data.getFloat(Address.SET_ZOOM, 10);
        double lat = data.getDouble(Address.LAT, 0);
        double lng = data.getDouble(Address.LNG, 0);

        searchable = data.getBoolean(Address.SEARCHABLE, true);
        dialog = data.getBoolean(Address.SHOW_DAILOG, true);
        forApt = data.getBoolean(Address.FOR_DEPARTMANTS, false);

        String formattedAddress = data.getString(Address.FORMATTED_ADDRESS, "");

        if (!forApt) {
            SearchMarkers(formattedAddress, lat, lng);
        } else {
            SearchApartments(formattedAddress, null, 0);
        }
        setTitle("Maps!");
    }

    //endregion

    //region On Options Menu Events

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_by_address, menu);

        Bundle data = getIntent().getExtras();

        menuClear = menu.findItem(R.id.action_clear_search);
        menuClear.setVisible(false);

        searchable = data.getBoolean(Address.SEARCHABLE, true);
        if (searchable) {
            MenuItem searchItem = (MenuItem) menu.findItem(R.id.action_search);
            SearchView mSearchView = (SearchView) searchItem.getActionView();
            Utility.SetupSearchView(searchItem, mSearchView, this, this);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_clear_search) {
            SearchMarkers("", 0, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion

    //region Search Operations

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

        for (Address item : locations) {
            if (item == null) {
                locations.remove(item);
                continue;
            }
            Marker marker = mMap.addMarker(MapUtility.CreateMarker(item, BitmapDescriptorFactory.HUE_BLUE));
            item.setMarker(marker);
            hasMarkers = true;
        }


        makeAddressListView();

        return hasMarkers;
    }


    private boolean SearchApartments(String address, LatLng latLng, float distance) {
        if (address.isEmpty() && latLng == null) {
            return false;
        }
        boolean hasMarkers = false;
        apartments = null;
        mMap.clear();

        if (apartments == null && address != null && !address.isEmpty()) {
            apartments = Apartment.SearchApi(address);
        }

        if (apartments == null && latLng != null) {
            apartments = new ArrayList<>();
            apartments = Apartment.AddressByLatLng(latLng, distance);
        }

        for (Apartment item : apartments) {
            if (item == null) {
                apartments.remove(item);
                continue;
            }
            Marker marker = mMap.addMarker(MapUtility.CreateMarker(item, BitmapDescriptorFactory.HUE_BLUE));
            marker.setSnippet(item.getId() + "");
            item.setMarker(marker);
            hasMarkers = true;
        }

        makeApartmentListView();

        return hasMarkers;
    }


    //endregion

    //region List


    private void makeApartmentListView() {
        if (lvLocations == null) {
            lvLocations = (ListView) findViewById(R.id.lvLocations);
        }


        final ApartmentArrayAdapter adapter = new ApartmentArrayAdapter(this, android.R.layout.simple_list_item_1, apartments);
        lvLocations.setAdapter(adapter);

        lvLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Apartment item = (Apartment) parent.getItemAtPosition(position);

                Marker marker = item.getMarker();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12));

            }

        });
    }

    private void makeAddressListView() {
        if (lvLocations == null) {
            lvLocations = (ListView) findViewById(R.id.lvLocations);
        }

        final AddressArrayAdapter adapter = new AddressArrayAdapter(this, android.R.layout.simple_list_item_1, locations);
        lvLocations.setAdapter(adapter);

        lvLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Address item = (Address) parent.getItemAtPosition(position);

                Marker marker = item.getMarker();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12));

            }

        });


        int size = RadioGroup.LayoutParams.MATCH_PARENT;
        int visible = View.VISIBLE;
        if (lvLocations.getCount() == 0) {
            visible = View.INVISIBLE;
        } else {
            size = 300;
        }

        lvLocations.setVisibility(visible);
        Utility.resizeFragment(supMapFragment, RadioGroup.LayoutParams.MATCH_PARENT, size, this);
        if (menuClear != null) {
            menuClear.setVisible(visible == View.VISIBLE);
        }
    }

    //endregion


    //region On Query For Search Events
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String address) {
        if (!forApt) {
            SearchMarkers(address, 0, 0);
        } else {
            SearchApartments(address, null, 0);
        }
        Utility.hideSoftKeyboard(FindByAddressActivity.this);
        return true;
    }

    //endregion

    //region On Google Maps Evants

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (dialog) {
            selectedMarker = marker;
            int id = 0;
            if (forApt) {
                try {
                    id = Integer.parseInt(marker.getSnippet());
                } catch (Exception e) {
                }
            }

            marker.setSnippet("true");
            MarkerUtility.MarkerInfo(marker, mMap, id, FindByAddressActivity.this, forApt);
        }
        return true;
    }

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

    //endregion
}
