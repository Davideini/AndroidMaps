package com.sce3.thirdyear.maps.data;

import android.content.Context;
import android.location.Geocoder;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.sce3.thirdyear.classes.JSONRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by David on 21/05/2015.
 */
public class Address {

    // private variables
    private String city;
    private String country;
    private String zip;
    private String street;
    private double lat;
    private double lng;

    private String address1;
    private String state;
    private String address2;
    private String county;
    private String pin;

    public Address() {

    }

    public Address(EditText street, EditText city, EditText country, EditText zip) {
        this.city = city.getText().toString();
        this.street = street.getText().toString();
        this.country = country.getText().toString();
        this.zip = zip.getText().toString();
    }

    public void GetFromDb(Object dbData) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (street != null && !street.isEmpty()) {
            sb.append(street).append(" ");
        }
        if (city != null && !city.isEmpty()) {
            sb.append(city).append(" ");
        }
        if (country != null && !country.isEmpty()) {
            sb.append(country).append(" ");
        }

        return sb.toString();
    }

    // get functions
    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    // set functions
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    public static List<Address> Search(Context context, String address) {
        List<Address> list = new ArrayList<>();

        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<android.location.Address> addresses = null;
            addresses = geocoder.getFromLocationName(address, 10);

            for (android.location.Address item : addresses) {
                Address ad = new Address();
                ad.setLat(item.getLatitude());
                ad.setLng(item.getLongitude());
                ad.setStreet(item.getLocality());
                ad.setCountry(item.getCountryName());
                ad.getAddress();
                list.add(ad);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCounty() {
        return county;
    }

    public String getPin() {
        return pin;
    }

    public String getState() {
        return state;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static List<Address> SearchApi(String address) {

        List<Address> locations = new ArrayList<>();

        JSONObject jsonObject = null;
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=true";
        try {
            JSONRequest request = new JSONRequest(url);
            //jsonObject = new JSONObject(request.getJSON());

            jsonObject = parser_Json.getJSONfromURL(url);

            String Status = jsonObject.getString("status");
            if (Status.equalsIgnoreCase("OK")) {
                JSONArray Results = jsonObject.getJSONArray("results");
                for (int j = 0; j < Results.length(); j++) {

                    JSONObject zero = Results.getJSONObject(j);
                    if (zero == null) {
                        break;
                    }

                    Address location = new Address();


                    JSONArray address_components = zero.getJSONArray("address_components");

                    JSONObject geo = zero.getJSONObject("geometry");
                    JSONObject locat = geo.getJSONObject("location");

                    location.setLat(locat.getDouble("lat"));
                    location.setLng(locat.getDouble("lng"));

                    for (int i = 0; i < address_components.length(); i++) {
                        JSONObject zero2 = address_components.getJSONObject(i);
                        String long_name = zero2.getString("long_name");
                        JSONArray mtypes = zero2.getJSONArray("types");
                        String Type = mtypes.getString(0);

                        if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                            if (Type.equalsIgnoreCase("street_number")) {
                                location.setAddress1(long_name + " ");
                            } else if (Type.equalsIgnoreCase("route")) {
                                location.setAddress1(location.getAddress1() + long_name);
                            } else if (Type.equalsIgnoreCase("sublocality")) {
                                location.setAddress2(long_name);
                            } else if (Type.equalsIgnoreCase("locality")) {
                                // Address2 = Address2 + long_name + ", ";
                                location.setCity(long_name);
                            } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                                location.setCounty(long_name);
                            } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                                location.setState(long_name);
                            } else if (Type.equalsIgnoreCase("country")) {
                                location.setCountry(long_name);
                            } else if (Type.equalsIgnoreCase("postal_code")) {
                                location.setPin(long_name);
                            }
                        }
                    }

                    locations.add(location);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return locations;
    }

    public static Address GetMyLocation(Context context) {
        Address address = null;
        /*
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<android.location.Address> addresses = null;
            //addresses = geocoder.

            for(android.location.Address item : addresses){
                Address ad = new Address();
                ad.setLat(item.getLatitude());
                ad.setLng(item.getLongitude());
                list.add(ad);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return address;
    }

    public LatLng getPosition() {
        return new LatLng(lat, lng);
    }


    public void getAddress() {
        address1 = "";
        address2 = "";
        city = "";
        state = "";
        country = "";
        county = "";
        pin = "";

        try {

            JSONObject jsonObj = parser_Json.getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + ","
                    + lng + "&sensor=true");
            String Status = jsonObj.getString("status");
            if (Status.equalsIgnoreCase("OK")) {
                JSONArray Results = jsonObj.getJSONArray("results");
                JSONObject zero = Results.getJSONObject(0);
                JSONArray address_components = zero.getJSONArray("address_components");

                for (int i = 0; i < address_components.length(); i++) {
                    JSONObject zero2 = address_components.getJSONObject(i);
                    String long_name = zero2.getString("long_name");
                    JSONArray mtypes = zero2.getJSONArray("types");
                    String Type = mtypes.getString(0);

                    if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                        if (Type.equalsIgnoreCase("street_number")) {
                            address1 = long_name + " ";
                        } else if (Type.equalsIgnoreCase("route")) {
                            address1 = address1 + long_name;
                        } else if (Type.equalsIgnoreCase("sublocality")) {
                            address2 = long_name;
                        } else if (Type.equalsIgnoreCase("locality")) {
                            // Address2 = Address2 + long_name + ", ";
                            city = long_name;
                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                            county = long_name;
                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                            state = long_name;
                        } else if (Type.equalsIgnoreCase("country")) {
                            county = long_name;
                        } else if (Type.equalsIgnoreCase("postal_code")) {
                            pin = long_name;
                        }
                    }

                    // JSONArray mtypes = zero2.getJSONArray("types");
                    // String Type = mtypes.getString(0);
                    // Log.e(Type,long_name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
