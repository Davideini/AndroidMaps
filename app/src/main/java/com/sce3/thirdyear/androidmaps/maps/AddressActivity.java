package com.sce3.thirdyear.androidmaps.maps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sce3.thirdyear.androidmaps.MenuActivity;
import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.maps.data.Address;

public class AddressActivity extends ActionBarActivity {
    static final int GET_ADDRESS_FROM_MAP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        setupUI(findViewById(R.id.mainLayout));

        clickEventSetup();
    }

    private void clickEventSetup() {
        Button clear = (Button) findViewById(R.id.btnClear);
        Button find = (Button) findViewById(R.id.btnFindInMap);

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
    }

    private void onFind(View v) {
        EditText street = (EditText) findViewById(R.id.tbStreet);
        EditText city = (EditText) findViewById(R.id.tbCity);
        EditText country = (EditText) findViewById(R.id.tbCountry);
        EditText houseNumber = (EditText) findViewById(R.id.tbhouseNumber);

        Address address = new Address(street, houseNumber, city, country);

        Intent i = new Intent(AddressActivity.this, FindByAddressActivity.class);

        i.putExtra("Address", address.toString());

        startActivityForResult(i, GET_ADDRESS_FROM_MAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_ADDRESS_FROM_MAP) {
            if (resultCode == RESULT_OK) {
                EditText street = (EditText) findViewById(R.id.tbStreet);
                EditText city = (EditText) findViewById(R.id.tbCity);
                EditText country = (EditText) findViewById(R.id.tbCountry);
                EditText houseNumber = (EditText) findViewById(R.id.tbhouseNumber);

                street.setText(data.getExtras().getString("street"));
                city.setText(data.getExtras().getString("city"));
                country.setText(data.getExtras().getString("country"));
                houseNumber.setText(data.getExtras().getString("houseNumber"));
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

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(AddressActivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void onFindOnMap(View view) {
        EditText street = (EditText) findViewById(R.id.tbStreet);
        EditText city = (EditText) findViewById(R.id.tbCity);
        EditText country = (EditText) findViewById(R.id.tbCountry);
        //EditText zip = (EditText) findViewById(R.id.tbZip);

        Address address = new Address(street, city, country, null);
    }

    public void onClear(View view) {
        ViewGroup group = (ViewGroup) findViewById(R.id.mainLayout);
        clearForm(group);
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }
}
