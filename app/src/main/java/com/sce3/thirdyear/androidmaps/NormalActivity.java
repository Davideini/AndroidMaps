package com.sce3.thirdyear.androidmaps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.maps.data.Address;

import java.util.List;


public class NormalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);


        List<Apartment> list = Apartment.SearchApi("i");
        for (Apartment apt : list) {
            Address address = Address.FindAddress(apt.getAddress() + " " + apt.getCity());
            if (address.getLat() < 20 || address.getLng() < 0 || address.getFormattedAddress() == null || address.getFormattedAddress().isEmpty()) {
                continue;
            }
            Apartment.Update(apt, address);
        }

        Toast.makeText(this, "Success Update Apartments!", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_normal, menu);
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
