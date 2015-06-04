package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sce3.thirdyear.classes.InputValidator;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;
import com.sce3.thirdyear.classes.User;
import com.sce3.thirdyear.maps.data.Address;
import com.sce3.thirdyear.maps.data.tools.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddApartment extends ActionBarActivity {
    private User user;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView mImageView;
    ImageButton mImageButton;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment);
        Utility.SetupUIKeyboard(findViewById(R.id.mainLayout), AddApartment.this);

//        user = new User(new SQLiteDB(getApplicationContext()));
        mImageView = (ImageView) findViewById(R.id.camImageView);
//        mImageButton = (ImageButton) findViewById(R.id.imageButton);

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


    public void takePicture(View view){

        mImageButton = (ImageButton) findViewById(R.id.imageButton);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
//            mImageView.setImageBitmap(imageBitmap);

//            ImageButton imageButton;
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

    public void addNewApt(View view) {
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
//        else if (!InputValidator.EmptyField(city.getText().toString())) {
//            city.requestFocus();
//            city.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(house_num.getText().toString())) {
//            house_num.requestFocus();
//            house_num.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(apt_num.getText().toString())) {
//            apt_num.requestFocus();
//            apt_num.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(floor_num.getText().toString())) {
//            apt_num.requestFocus();
//            apt_num.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(territory.getText().toString())) {
//            apt_num.requestFocus();
//            apt_num.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(room_num.getText().toString())) {
//            room_num.requestFocus();
//            room_num.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(area.getText().toString())) {
//            area.requestFocus();
//            area.setError("FIELD CANNOT BE EMPTY");
//        }
//        else if (!InputValidator.EmptyField(price.getText().toString())) {
//            price.requestFocus();
//            price.setError("FIELD CANNOT BE EMPTY");
//        }
//
//        else if (!(rent.isChecked() && !(sell.isChecked()))) {
//            Toast.makeText(getApplicationContext(), "Must Choose Rent\\Sell!", Toast.LENGTH_SHORT).show();
//        }

        else {
            Address add2 = new Address(street, house_num, city);
            double lng = add2.getLng();
            double lat = add2.getLat();


            Toast.makeText(this, "Lat: " + lat + ", Lng: " + lng, Toast.LENGTH_SHORT).show();

            SQLiteDB db = new SQLiteDB(getApplicationContext());
            user = new User(db);



            Map<String, String> dict = new HashMap<>();

            dict.put("action", "AddApt");
            dict.put("user_id", String.valueOf(user.getID()));
            dict.put("type_id", "2");
            dict.put("territory", territory.getText().toString());
            dict.put("city", add2.getCity());
            dict.put("address", add2.getFormattedAddress());
            dict.put("rooms", room_num.getText().toString());
            dict.put("floor", floor_num.getText().toString());
            dict.put("latitude", String.valueOf(lat));
            dict.put("longitude", String.valueOf(lng));
            dict.put("sizem2", area.getText().toString());
            dict.put("comment", descr.getText().toString());
            dict.put("price", price.getText().toString());
            dict.put("aircondition", String.valueOf(ac.isChecked()));
            dict.put("elevator", String.valueOf(elevator.isChecked()));
            dict.put("balcony", String.valueOf(serviceBalcony.isChecked()));
            dict.put("isolated_room", String.valueOf(mamad.isChecked()));
            dict.put("parking", String.valueOf(parking.isChecked()));
            dict.put("handicap_access", String.valueOf(hadicappedAccess.isChecked()));
            dict.put("storage", String.valueOf(storage.isChecked()));
            dict.put("bars", String.valueOf(bars.isChecked()));
            dict.put("sun_balcony", String.valueOf(sunBalcony.isChecked()));
            dict.put("renovated", String.valueOf(renovated.isChecked()));
            dict.put("furnished", String.valueOf(furnished.isChecked()));
            dict.put("unit", String.valueOf(unit.isChecked()));
            dict.put("pandoor", String.valueOf(pandoor.isChecked()));
            dict.put("add_date", Calendar.getInstance().getTime().toString());
            String imageBase64=getImageBase64(imageBitmap);
            dict.put("image",imageBase64);
            dict.put("session",db.getSavedSession());
//
//            dict.put("user_id", "");
//            dict.put("type_id", "");
//            dict.put("territory", "");
//            dict.put("city", "");
//            dict.put("address", "");
//            dict.put("rooms", "");
//            dict.put("floor", "");
//            dict.put("latitude", "");
//            dict.put("longitude", "");
//            dict.put("sizem2", "");
//            dict.put("comment", "");
//            dict.put("price", "");
//            dict.put("add_date", "");
//            dict.put("aircondition", "");
//            dict.put("elevator", "");
//            dict.put("balcony", "");
//            dict.put("isolated_room", "");
//            dict.put("parking", "");
//            dict.put("handicap_access", "");
//            dict.put("storage", "");
//            dict.put("bars", "");
//            dict.put("sun_balcony", "");
//            dict.put("renovated", "");
//            dict.put("furnished", "");
//            dict.put("unit", "");
//            dict.put("pandoor", "");


//        dict.put("Lat", String.valueOf(lat));
//        dict.put("Lng", String.valueOf(lng));
            /*
            boolean first = true;
            String delim = "";
            StringBuilder sb = new StringBuilder(String.format("http://%s/JavaWeb/api?", JSONRequest.SERVER));
            for (String key : dict.keySet()) {

                if (!first) {
                    delim = "&";
                }
                String parm = delim + key + "=" + dict.get(key);
                sb.append(parm);
                first = false;
            }
            sb.append("&session=" + db.getSavedSession());
            String result = sb.toString();

            String link = result.replaceAll(" ", "%20");
*/
            String link = String.format("http://%s/JavaWeb/api?action=AddApt",JSONRequest.SERVER);
//            String encodedUrl = null;
//            try {
//                encodedUrl = URLEncoder.encode(result, "UTF-8");
//            } catch (UnsupportedEncodingException ignored) {
//                // Can be safely ignored because UTF-8 is always supported
//            }

//            String apartment = String.format("http://%s/JavaMaps/api?action=AddApt&city=%s&price=%s&territory=%s&street=%s&house_num=%s&apt_num=%s&rooms=%s&floor=%s" +
//                            "&sizem2=%s&desc=%s&aircondition=%s&elevator=%s&balcony=%s&isolated_room=%s&parking=%s&handicap_access=%s&storage=%s" +
//                            "&bars=%s&sun_balcony=%s&renovated=%s&furnished=%s&unit=%s&pandoor=%s", JSONRequest.SERVER, user.getID() + "", city.getText().toString(), price.getText().toString(),
//                    territory.getText().toString(), street.getText().toString(), house_num.getText().toString(), apt_num.getText().toString(), room_num.getText().toString(),
//                    floor_num.getText().toString(), area.getText().toString(), descr.getText().toString(), ac.isChecked() ? '0' : '1', elevator.isChecked() ? '0' : '1', serviceBalcony.isChecked() ? '0' : '1',
//                    mamad.isChecked() ? '0' : '1', parking.isChecked() ? '0' : '1', hadicappedAccess.isChecked() ? '0' : '1', storage.isChecked() ? '0' : '1', bars.isChecked() ? '0' : '1', sunBalcony.isChecked() ? '0' : '1',
//                    renovated.isChecked() ? '0' : '1', furnished.isChecked() ? '0' : '1', unit.isChecked() ? '0' : '1', pandoor.isChecked() ? '0' : '1');

            System.out.println(link);
            try {
                JSONObject data = new JSONObject(dict);
                JSONRequest json = new JSONRequest(link,data);
                JSONObject jobj = new JSONObject(json.getJSON());
                if (jobj.getString("result").equals("success")) {
                    Toast.makeText(AddApartment.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
//                    Intent myIntent = new Intent(AddApartment.this, RegistrationBuyerActivity.class);
//                    AddApartment.this.startActivity(myIntent);
                    finish();
                } else if (jobj.getString("result").equals("error")) {
                    Toast.makeText(AddApartment.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                Toast.makeText(AddApartment.this, "Error receiving data.", Toast.LENGTH_LONG).show();

            }
        }


    }

    public void initializeMapFeature() {
//        Button btn = (Button) findViewById(R.id.getFromMaps);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
    private String getImageBase64(Bitmap bmp){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            String base64encoded= Base64.encodeToString(byteArray, Base64.DEFAULT);
            return base64encoded;
    }
}
