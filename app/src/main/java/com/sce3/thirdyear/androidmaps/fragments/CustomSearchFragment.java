package com.sce3.thirdyear.androidmaps.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sce3.thirdyear.androidmaps.R;
import com.sce3.thirdyear.classes.InputValidator;

/**
 * Created by win7 on 06/06/2015.
 */
public class CustomSearchFragment extends Fragment {
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


    public CustomSearchFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_search,container,false);

        City = (EditText) v.findViewById(R.id.SearchCityeditText);
        Rooms = (EditText) v.findViewById(R.id.SearchRoomsEditText);
        service = (CheckBox)v.findViewById(R.id.service_balconyCheckBox);
        Mamad = (CheckBox)v.findViewById(R.id.SearchMamadCheckBox);
        Parking = (CheckBox)v.findViewById(R.id.SearchParkingCheckBox);
        HandiAccess = (CheckBox)v.findViewById(R.id.SearchHandicappedAccessCheckBox);
        Storage = (CheckBox)v.findViewById(R.id.SearchStorageCheckBox);
        Elevator = (CheckBox)v.findViewById(R.id.SearchElevatorCheckBox);
        Balcony = (CheckBox)v.findViewById(R.id.SearchSunBalconyCheckBox);
        AirCondition = (CheckBox)v.findViewById(R.id.SearchAirConditionCheckBox);
        MinPrice = (EditText)v.findViewById(R.id.MinPriceEditText);
        MaxPrice = (EditText)v.findViewById(R.id.MaxPriceEditText);
        MinFloor = (EditText)v.findViewById(R.id.MinFloorEditText);
        MaxFloor = (EditText)v.findViewById(R.id.MaxFloorEditText);
        AptPicOnly = (CheckBox)v.findViewById(R.id.AptPicCheckBox);
        search = (Button)v.findViewById(R.id.SearchingButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void checkValidation()
    {
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
            setjson();
        }
    }

    public void setjson(){

    }
}
