package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.sce3.thirdyear.classes.InputValidator;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;
import com.sce3.thirdyear.classes.User;
import com.sce3.thirdyear.maps.data.Address;

import org.json.JSONException;
import org.json.JSONObject;


public class AddApartment extends ActionBarActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment);

        user = new User(new SQLiteDB(getApplicationContext()));

        initializeMapFeature();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_apartment, menu);
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

    public void addNewApt() {
        EditText street, city, house_num, apt_num, room_num, area, price, floor_num, territory, descr;
        CheckBox elevator, sunBalcony, mamad, serviceBalcony, parking, hadicappedAccess, storage, rent, sell, ac, renovated, furnished, unit, pandoor, bars;


        street = (EditText) findViewById(R.id.streetNameEditText);
        city = (EditText) findViewById(R.id.cityEditText);
        house_num = (EditText) findViewById(R.id.houseNumEditText);
        apt_num = (EditText) findViewById(R.id.aptNumEditText);
        room_num = (EditText) findViewById(R.id.roomNumEditText);
        area = (EditText) findViewById(R.id.aptAreaEditText);
        price = (EditText) findViewById(R.id.priceEditText);
        floor_num = (EditText) findViewById(R.id.floorEditText);
        territory = (EditText) findViewById(R.id.territoryEditText);
        descr = (EditText) findViewById(R.id.descriptionEditText);
        elevator = (CheckBox) findViewById(R.id.elevatorCheckBox);
        sunBalcony = (CheckBox) findViewById(R.id.sunBalconyCheckBox);
        mamad = (CheckBox) findViewById(R.id.mamadCheckBox);
        serviceBalcony = (CheckBox) findViewById(R.id.serviceBalconyCheckBox);
        parking = (CheckBox) findViewById(R.id.parkingCheckBox);
        hadicappedAccess = (CheckBox) findViewById(R.id.handicappedAccessCheckBox);
        storage = (CheckBox) findViewById(R.id.storageCheckBox);
        rent = (CheckBox) findViewById(R.id.rentCheckBox);
        sell = (CheckBox) findViewById(R.id.sellCheckBox);
        ac = (CheckBox) findViewById(R.id.airConditionCheckBox);
        renovated = (CheckBox) findViewById(R.id.renovatedCheckBox);
        furnished = (CheckBox) findViewById(R.id.furnishedCheckBox);
        unit = (CheckBox) findViewById(R.id.unitCheckBox);
        pandoor = (CheckBox) findViewById(R.id.pandoorCheckBox);
        bars = (CheckBox) findViewById(R.id.barsCheckBox);
        sunBalcony = (CheckBox) findViewById(R.id.sunBalconyCheckBox);
        hadicappedAccess = (CheckBox) findViewById(R.id.handicappedAccessCheckBox);
        storage = (CheckBox) findViewById(R.id.storageCheckBox);
        renovated = (CheckBox) findViewById(R.id.renovatedCheckBox);
        furnished = (CheckBox) findViewById(R.id.furnishedCheckBox);
        unit = (CheckBox) findViewById(R.id.unitCheckBox);
        pandoor = (CheckBox) findViewById(R.id.pandoorCheckBox);


        if (!InputValidator.EmptyField(street.getText().toString())) {
            street.requestFocus();
            street.setError("FIELD CANNOT BE EMPTY");
        }
        if (!InputValidator.EmptyField(city.getText().toString())) {
            city.requestFocus();
            city.setError("FIELD CANNOT BE EMPTY");
        }
        if (!InputValidator.EmptyField(house_num.getText().toString())) {
            house_num.requestFocus();
            house_num.setError("FIELD CANNOT BE EMPTY");
        }
        if (!InputValidator.EmptyField(apt_num.getText().toString())) {
            apt_num.requestFocus();
            apt_num.setError("FIELD CANNOT BE EMPTY");
        }
        if (!InputValidator.EmptyField(room_num.getText().toString())) {
            room_num.requestFocus();
            room_num.setError("FIELD CANNOT BE EMPTY");
        }
        if (!InputValidator.EmptyField(area.getText().toString())) {
            area.requestFocus();
            area.setError("FIELD CANNOT BE EMPTY");
        }
        if (!InputValidator.EmptyField(price.getText().toString())) {
            price.requestFocus();
            price.setError("FIELD CANNOT BE EMPTY");
        }

        if (!(rent.isChecked() || sell.isChecked())) {
            Toast.makeText(getApplicationContext(), "Must Choose Rent\\Sell!", Toast.LENGTH_SHORT).show();
        }

        Address add2 = new Address(street, house_num, city);
        double lng = add2.getLng();
        double lat = add2.getLat();


        String apartment = String.format("http://%s/JavaMaps/api?action=AddApt&city=%s&price=%s&territory=%s&street=%s&house_num=%s&apt_num=%s&rooms=%s&floor=%s" +
                        "&sizem2=%s&desc=%s&aircondition=%s&elevator=%s&balcony=%s&isolated_room=%s&parking=%s&handicap_access=%s&storage=%s" +
                        "&bars=%s&sun_balcony=%s&renovated=%s&furnished=%s&unit=%s&pandoor=%s", JSONRequest.SERVER, user.getID() + "", city.getText().toString(), price.getText().toString(),
                territory.getText().toString(), street.getText().toString(), house_num.getText().toString(), apt_num.getText().toString(), room_num.getText().toString(),
                floor_num.getText().toString(), area.getText().toString(), descr.getText().toString(), ac.isChecked() ? '0' : '1', elevator.isChecked() ? '0' : '1', serviceBalcony.isChecked() ? '0' : '1',
                mamad.isChecked() ? '0' : '1', parking.isChecked() ? '0' : '1', hadicappedAccess.isChecked() ? '0' : '1', storage.isChecked() ? '0' : '1', bars.isChecked() ? '0' : '1', sunBalcony.isChecked() ? '0' : '1',
                renovated.isChecked() ? '0' : '1', furnished.isChecked() ? '0' : '1', unit.isChecked() ? '0' : '1', pandoor.isChecked() ? '0' : '1');

        System.out.println(apartment);
        try {
            JSONRequest json = new JSONRequest(apartment);
            JSONObject jobj = new JSONObject(json.getJSON());
            if (jobj.getString("result").equals("success")) {
                Toast.makeText(AddApartment.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(AddApartment.this, RegistrationBuyerActivity.class);
                AddApartment.this.startActivity(myIntent);
            } else if (jobj.getString("result").equals("error")) {
                Toast.makeText(AddApartment.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Toast.makeText(AddApartment.this, "Error receiving data.", Toast.LENGTH_LONG).show();

        }

    }

    public void initializeMapFeature() {
        Button btn = (Button) findViewById(R.id.getFromMaps);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
