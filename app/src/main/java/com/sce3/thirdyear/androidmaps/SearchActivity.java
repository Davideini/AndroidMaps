package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sce3.thirdyear.classes.InputValidator;
import com.sce3.thirdyear.classes.JSONRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class SearchActivity extends ActionBarActivity {

    private EditText City;
    private EditText Rooms;
    private EditText MinPrice;
    private EditText MaxPrice;
    private EditText MinFloor;
    private EditText MaxFloor;
    private CheckBox Elevator;
    private CheckBox Balcony;
    private CheckBox Mamad;
    private CheckBox service;
    private CheckBox Parking;
    private CheckBox HandiAccess;
    private CheckBox Storage;
    private CheckBox AirCondition;
    private CheckBox AptPicOnly;
    private Button search;
////////////////////////////////////////////////////////
    private View.OnClickListener SEARCHButtonListner;
///////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        City = (EditText)findViewById(R.id.SearchCityeditText);
        Rooms = (EditText)findViewById(R.id.SearchRoomsEditText);
        service = (CheckBox)findViewById(R.id.service_balconyCheckBox);
        Mamad = (CheckBox)findViewById(R.id.SearchMamadCheckBox);
        Parking = (CheckBox)findViewById(R.id.SearchParkingCheckBox);
        HandiAccess = (CheckBox)findViewById(R.id.SearchHandicappedAccessCheckBox);
        Storage = (CheckBox)findViewById(R.id.SearchStorageCheckBox);
        Elevator = (CheckBox)findViewById(R.id.SearchElevatorCheckBox);
        Balcony = (CheckBox)findViewById(R.id.SearchSunBalconyCheckBox);
        AirCondition = (CheckBox)findViewById(R.id.SearchAirConditionCheckBox);
        MinPrice = (EditText)findViewById(R.id.MinPriceEditText);
        MaxPrice = (EditText)findViewById(R.id.MaxPriceEditText);
        MinFloor = (EditText)findViewById(R.id.MinFloorEditText);
        MaxFloor = (EditText)findViewById(R.id.MaxFloorEditText);
        AptPicOnly = (CheckBox)findViewById(R.id.AptPicCheckBox);
        search = (Button)findViewById(R.id.SearchingButton);


        SEARCHButtonListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String CityName = City.getText().toString();
                final String RoomsNum = Rooms.getText().toString();
                final String MinimumPrice = MinPrice.getText().toString();
                final String MaxinumPrice = MaxPrice.getText().toString();
                final String MinimumFloor = MinFloor.getText().toString();
                final String MaximumFloor = MaxFloor.getText().toString();

                if(InputValidator.isEmpty(CityName)){
                    City.requestFocus();
                    City.setError("FIELD CANNOT BE EMPTY");
                }
                else if(InputValidator.isEmpty(RoomsNum)){
                    Rooms.requestFocus();
                    Rooms.setError("FIELD CANNOT BE EMPTY");
                }
                else if(InputValidator.isEmpty(MinimumPrice)){
                    MinPrice.requestFocus();
                    MinPrice.setError("FIELD CANNOT BE EMPTY");
                }
                else if(InputValidator.isEmpty(MaxinumPrice)){
                    MaxPrice.requestFocus();
                    MaxPrice.setError("FIELD CANNOT BE EMPTY");
                }
                else if(InputValidator.isEmpty(MinimumFloor)){
                    MinFloor.requestFocus();
                    MinFloor.setError("FIELD CANNOT BE EMPTY");
                }
                else if(InputValidator.isEmpty(MaximumFloor)){
                    MaxFloor.requestFocus();
                    MaxFloor.setError("FIELD CANNOT BE EMPTY");
                }
                else if(InputValidator.NotNegative(RoomsNum)) {
                    Rooms.requestFocus();
                    Rooms.setError("FIELD CANNOT BE NEGATIVE");
                }
                else if(InputValidator.NotNegative(MinimumPrice)){
                    MinPrice.requestFocus();
                    MinPrice.setError("FIELD CANNOT BE NEGATIVE");
                }
                else if(InputValidator.NotNegative(MaxinumPrice)){
                    MaxPrice.requestFocus();
                    MaxPrice.setError("FIELD CANNOT BE NEGATIVE");
                }
                else if(InputValidator.NotNegative(MinimumFloor)){
                    MinFloor.requestFocus();
                    MinFloor.setError("FIELD CANNOT BE NEGATIVE");
                }
                else if(InputValidator.NotNegative(MaximumFloor)){
                    MaxFloor.requestFocus();
                    MaxFloor.setError("FIELD CANNOT BE NEGATIVE");
                }
                else if(InputValidator.MinNotMax(Integer.parseInt(MinimumPrice), Integer.parseInt(MaxinumPrice))){
                    MinPrice.requestFocus();
                    MinPrice.setError("FIELD MinPrice CANNOT BE larger than MaxPrice");
                }
                else if(InputValidator.MinNotMax(Integer.parseInt(MinimumFloor), Integer.parseInt(MaximumFloor))){
                    MinFloor.requestFocus();
                    MinFloor.setError("FIELD MinFloor CANNOT BE larger than MaxFloor");
                }
                else{
                    String address=String.format("http://%s/JavaWeb/api?action=Search&city=%s&rooms=%s&price1=%s&price2=%s&floor1=%s&floor2=%s", JSONRequest.SERVER,CityName,RoomsNum,MinimumPrice,MaxinumPrice,MinimumFloor,MaximumFloor);
                    System.out.println(address);

                    try {
                        JSONRequest json=new JSONRequest(address);
                        JSONObject jobj = new JSONObject(json.getJSON());
                        if(jobj.getString("result").equals("success")){
                            Toast.makeText(SearchActivity.this, "Apartments found", Toast.LENGTH_LONG).show();
///////////////////////////////////////////////////////////////////////////////////////////////
                            Intent i = new Intent(SearchActivity.this, MainActivity.class);
                            startActivity(i);
////////////////////////////////////////////////////////////////////////////////////////////////////
                        }

                        else if(jobj.getString("result").equals("error")){
                            Toast.makeText(SearchActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        Toast.makeText(SearchActivity.this, "Error receiving data.", Toast.LENGTH_LONG).show();

                    }

                }


            }
        };
        search.setOnClickListener(SEARCHButtonListner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
