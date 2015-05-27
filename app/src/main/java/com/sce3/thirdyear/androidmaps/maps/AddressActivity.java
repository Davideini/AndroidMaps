package com.sce3.thirdyear.androidmaps.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.maps.data.Address;
import com.sce3.thirdyear.maps.data.tools.Utility;

import java.text.DecimalFormat;

public class AddressActivity extends ActionBarActivity {
    static final int GET_ADDRESS_FROM_MAP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Utility.SetupUIKeyboard(findViewById(R.id.mainLayout), AddressActivity.this);

        clickEventSetup();
    }

    private void clickEventSetup() {
        Button clear = (Button) findViewById(R.id.btnClear);
        Button find = (Button) findViewById(R.id.btnFindInMap);
        Button show = (Button) findViewById(R.id.btnShowOnMap);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClear(v);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFind(v);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShow(v);
            }
        });
    }

    private void onFind(View v) {
        EditText street = (EditText) findViewById(R.id.tbStreet);
        EditText city = (EditText) findViewById(R.id.tbCity);
        EditText country = (EditText) findViewById(R.id.tbCountry);
        EditText houseNumber = (EditText) findViewById(R.id.tbhouseNumber);

        Address address = new Address(street, houseNumber, city, country);

        Intent i = new Intent(AddressActivity.this, FindByAddressActivity.class);

        i.putExtra(Address.FORMATTED_ADDRESS, address.toString());

        startActivityForResult(i, GET_ADDRESS_FROM_MAP);
    }

    private void onShow(View v) {
        EditText lat = (EditText) findViewById(R.id.tbLat);
        EditText lng = (EditText) findViewById(R.id.tbLng);

        Intent i = new Intent(AddressActivity.this, FindByAddressActivity.class);
        try {
            double lat_val = Double.parseDouble(lat.getText().toString());
            double lng_val = Double.parseDouble(lng.getText().toString());

            Address israel = Address.SearchApi("Israel").get(0);
            Address address = Address.AddressByLatLng(new LatLng(lat_val, lng_val));
            if (address == null) return;
            if (!address.toString().toLowerCase().contains(israel.getCountry().toLowerCase())) {
                Toast.makeText(AddressActivity.this, "Location not in israel bounds select other lat lng", Toast.LENGTH_LONG).show();
                return;
            }

            i.putExtra(Address.LAT, lat_val);
            i.putExtra(Address.LNG, lng_val);

            startActivityForResult(i, GET_ADDRESS_FROM_MAP);
        } catch (Exception e) {
            Toast.makeText(AddressActivity.this, "Set lat and lng corectly", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_ADDRESS_FROM_MAP) {
            if (resultCode == RESULT_OK) {
                EditText street = (EditText) findViewById(R.id.tbStreet);
                EditText city = (EditText) findViewById(R.id.tbCity);
                EditText country = (EditText) findViewById(R.id.tbCountry);
                EditText houseNumber = (EditText) findViewById(R.id.tbhouseNumber);
                EditText lat = (EditText) findViewById(R.id.tbLat);
                EditText lng = (EditText) findViewById(R.id.tbLng);
                EditText distance = (EditText) findViewById(R.id.tbDistance);


                Address address = new Address(data.getExtras().getDouble(Address.LAT), data.getExtras().getDouble(Address.LNG));

                street.setText(data.getExtras().getString(Address.STREET));
                city.setText(data.getExtras().getString(Address.CITY));
                country.setText(data.getExtras().getString(Address.COUNTRY));
                houseNumber.setText(data.getExtras().getString(Address.STREET_NUMBER));
                lat.setText("" + data.getExtras().getDouble(Address.LAT));
                lng.setText("" + data.getExtras().getDouble(Address.LNG));
                distance.setText("" + new DecimalFormat("#0.00").format(address.getDistance(this)) + " km");


            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_address, menu);
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


    public void onFindOnMap(View view) {
        EditText street = (EditText) findViewById(R.id.tbStreet);
        EditText city = (EditText) findViewById(R.id.tbCity);
        EditText country = (EditText) findViewById(R.id.tbCountry);
        EditText houseNumber = (EditText) findViewById(R.id.tbhouseNumber);

        Address address = new Address(street, houseNumber, city, country);
    }

    public void onClear(View view) {
        ViewGroup group = (ViewGroup) findViewById(R.id.mainLayout);
        Utility.ClearForm(group);
    }

}
