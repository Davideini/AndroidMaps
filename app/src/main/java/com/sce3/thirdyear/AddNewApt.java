package com.sce3.thirdyear;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sce3.thirdyear.androidmaps.R;

public class AddNewApt extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_apt);
        //Utility.SetupUIKeyboard(findViewById(R.id.mainLayout), AddNewApt.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_apt, menu);
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


    public void addNewNewApt2(View view) {
        Toast.makeText(this, "dkjsakjdshkjas", Toast.LENGTH_SHORT).show();
//        EditText street, city, house_num, apt_num, room_num, area, price, floor_num, territory, descr;
//        street = (EditText) findViewById(R.id.streetNameEditText);
//        city = (EditText) findViewById(R.id.cityEditText);
//        house_num = (EditText) findViewById(R.id.houseNumEditText);
//
//        Address add2 = new Address(street, house_num, city);
//        double lng = add2.getLng();
//        double lat = add2.getLat();
    }

}
