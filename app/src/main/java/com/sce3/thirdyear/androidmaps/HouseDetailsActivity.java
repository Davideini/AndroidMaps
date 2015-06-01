package com.sce3.thirdyear.androidmaps;

import android.app.Service;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.android.gms.plus.model.people.Person;
import com.sce3.thirdyear.classes.Ad;
import com.sce3.thirdyear.classes.Apartment;


public class HouseDetailsActivity extends ActionBarActivity {
    private EditText SN;
    private EditText City;
    private EditText HN;
    private EditText AN;
    private EditText Rooms;
    private EditText AA;
    private EditText price;
    private EditText CP;
    private EditText CE;
    private CheckBox Elevator;
    private CheckBox Balcony;
    private CheckBox Mamad;
    private CheckBox service;
    private CheckBox Parking;
    private CheckBox HandiAccess;
    private CheckBox Storage;
    private Button OK;
    private OnClickListener OKButtonListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
 ///////////////////////initial views
        SN = (EditText)findViewById(R.id.StreetN);
        City = (EditText)findViewById(R.id.city);
        HN = (EditText)findViewById(R.id.HouseN);
        AN = (EditText)findViewById(R.id.AptN);
        Rooms = (EditText)findViewById(R.id.rooms);
        AA = (EditText)findViewById(R.id.AptA);
        price = (EditText)findViewById(R.id.pr);
        CP = (EditText)findViewById(R.id.ContactP);
        CE = (EditText)findViewById(R.id.ContactE);
        service = (CheckBox)findViewById(R.id.ServiceBalcony);
        Mamad = (CheckBox)findViewById(R.id.mamad);
        Parking = (CheckBox)findViewById(R.id.parking);
        HandiAccess = (CheckBox)findViewById(R.id.HandicappedAccess);
        Storage = (CheckBox)findViewById(R.id.storage);
        Elevator = (CheckBox)findViewById(R.id.elevator);
        Balcony = (CheckBox)findViewById(R.id.SunBalcony);

        //OK = (Button)findViewById(R.id.OKButton);

///////////////////////////////////////////////////////////////////
        //get Apartment from some Activity
        Apartment ap = (Apartment) getIntent().getSerializableExtra(MenuActivity.SER_KEY);//this is the static key from menuActivity
        SN.setText(ap.getAddress());
        City.setText(ap.getCity());
        //HN.setText(ap.get);
        Rooms.setText(String.valueOf(ap.getRooms()));
        service.setChecked(ap.isBalcony());
        //Mamad.setActivated(ap.);
        Parking.setChecked(ap.isParking());
        HandiAccess.setChecked(ap.isHandicap_access());
        Storage.setChecked(ap.isStorage());
        Elevator.setChecked(ap.isStorage());
        Balcony.setChecked(ap.isBalcony());
        price.setText(String.valueOf(ap.getPrice()));

        //AA.setText();


/*
        OKButtonListner = new OnClickListener(){
            @Override
            public void onClick(View view) {
                final String StreetName=SN.getText().toString();
                final String CITY=City.getText().toString();
                final String HouseNumber=HN.getText().toString();
                final String ApartmentNumber=AN.getText().toString();
                final String ROOMS=Rooms.getText().toString();
                final String ApartmentArea=AA.getText().toString();
                final String PRICE=price.getText().toString();
                final String ContactPhone=CP.getText().toString();
                final String ContactEmail=CE.getText().toString();

                Toast.makeText(HouseDetailsActivity.this,"Success",Toast.LENGTH_LONG).show();

                OK.setEnabled(true);
            }
        };
        OK.setOnClickListener(OKButtonListner);
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
}
