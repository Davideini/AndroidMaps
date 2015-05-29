package com.sce3.thirdyear.androidmaps.maps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FindByAddressActivity extends ActionBarActivity implements OnMapReadyCallback, SearchView.OnQueryTextListener {

    private GoogleMap mMap;

    private Marker userMarker = null;

    private Marker selectedMarker = null;

    private List<Address> locations = null;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_address);


        SetupClickEvents();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mStatusView = (TextView) findViewById(R.id.tbSearch);
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

    private ListView lvLocations = null;

    @Override
    public void onMapReady(final GoogleMap m) {
        mMap = MapUtility.GoogleMapsSetup(m, mapClickListener, markerClickListener);

        String address = getIntent().getExtras().getString(Address.FORMATTED_ADDRESS);

        double lat = getIntent().getExtras().getDouble(Address.LAT);

        double lng = getIntent().getExtras().getDouble(Address.LNG);

        boolean hasMarkers = SearchMarkers(address, lat, lng);
        lvLocations = (ListView) findViewById(R.id.lvLocations);
        if (hasMarkers) {
            if (address != null)
                LocationsList.MakeListView(address, this, lvLocations);
            else
                LocationsList.MakeListView(new LatLng(lat, lng), this, lvLocations);

        }
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

    public String getJSON(String address) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(FindByAddressActivity.class.toString(), "Failedet JSON object");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com'
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry
        while (iter.hasNext()) {
            //gets an entry in the params
            Map.Entry pairs = (Map.Entry) iter.next();

            //creates a key for Map
            String key = (String) pairs.getKey();

            //Create a new map
            Map m = (Map) pairs.getValue();

            //object for storing Json
            JSONObject data = new JSONObject();

            //gets the value
            Iterator iter2 = m.entrySet().iterator();
            while (iter2.hasNext()) {
                Map.Entry pairs2 = (Map.Entry) iter2.next();
                data.put((String) pairs2.getKey(), (String) pairs2.getValue());
            }

            //puts email and 'foo@bar.com'  together in map
            holder.put(key, data);
        }
        return holder;
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
            mMap.addMarker(MapUtility.CreateMarker(item, BitmapDescriptorFactory.HUE_BLUE));
            hasMarkers = true;
        }
        return hasMarkers;
    }


    public boolean onQueryTextChange(String newText) {

        return false;
    }

    public boolean onQueryTextSubmit(String address) {


        SearchMarkers(address, 0, 0);
        LocationsList.MakeListView(address, this, lvLocations);

        return false;
    }

    public boolean onClose() {

        return false;
    }

}
