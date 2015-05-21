package com.sce3.thirdyear.androidmaps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sce3.thirdyear.classes.InputValidator;


public class AddApartment extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment);

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
        EditText street, city, house_num, apt_num, room_num, area, price;
        CheckBox elevator, sunBalcony, mamad, serviceBalcony, parking, hadicappedAccess, storage, rent, sell;

        street = (EditText) findViewById(R.id.streetNameEditText);
        city = (EditText) findViewById(R.id.cityEditText);
        house_num = (EditText) findViewById(R.id.houseNumEditText);
        apt_num = (EditText) findViewById(R.id.aptNumEditText);
        room_num = (EditText) findViewById(R.id.roomNumEditText);
        area = (EditText) findViewById(R.id.aptAreaEditText);
        price = (EditText) findViewById(R.id.priceEditText);
        elevator = (CheckBox) findViewById(R.id.elevatorCheckBox);
        sunBalcony = (CheckBox) findViewById(R.id.sunBalconyCheckBox);
        mamad = (CheckBox) findViewById(R.id.mamadCheckBox);
        serviceBalcony = (CheckBox) findViewById(R.id.serviceBalconyCheckBox);
        parking = (CheckBox) findViewById(R.id.parkingCheckBox);
        hadicappedAccess = (CheckBox) findViewById(R.id.handicappedAccessCheckBox);
        storage = (CheckBox) findViewById(R.id.storageCheckBox);
        rent = (CheckBox) findViewById(R.id.rentCheckBox);
        sell = (CheckBox) findViewById(R.id.sellCheckBox);

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
