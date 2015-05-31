package com.sce3.thirdyear.maps.data;

import android.content.Context;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.sce3.thirdyear.maps.data.tools.GPSTracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 21/05/2015.
 */
public class Address {

    // private const
    private static final String GEOCODE_URL = "http://maps.googleapis.com/maps/api/geocode/json?%s";
    private static final String BY_LATLNG = "latlng=%s,%s";
    private static final String BY_ADDRESS = "address=%s";
    private static final String SENSOR = "sensor=%s";

    // variables const

    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String STREET = "street";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String STREET_NUMBER = "streetNumber";
    public static final String POSTAL_CODE = "postalCode";
    public static final String FORMATTED_ADDRESS = "formattedAddress";

    // For matan
    public static final String SET_ZOOM = "setzoom"; // number between 1-19
    public static final String SHOW_DAILOG = "showdailog"; // true of false
    public static final String SEARCHABLE = "searchable"; // true of false
    public static final String FOR_DEPARTMANTS = "fordepartmants"; // true of false



    // private variables
    private String city;
    private String country;
    private String street;
    private double lat;
    private double lng;
    private String streetNumber;
    private String postalCode;
    private String formattedAddress;

    public Address() {

    }

    public Address(EditText street, EditText city, EditText country, EditText zip) {
        this.city = city.getText().toString();
        this.street = street.getText().toString();
        this.country = country.getText().toString();
    }

    public Address(double lat, double lng) {
        this.lng = lng;
        this.lat = lat;
    }

    // set functions
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    // get functions
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public LatLng getPosition() {
        return new LatLng(lat, lng);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (street != null && !street.isEmpty()) {
            sb.append(street).append(" ");
        }
        if (streetNumber != null && !streetNumber.isEmpty()) {
            sb.append(streetNumber).append(" ");
        }
        if (city != null && !city.isEmpty()) {
            sb.append(city).append(" ");
        }
        if (country != null && !country.isEmpty()) {
            sb.append(country).append(" ");
        }

        return sb.toString();
    }

    // for extras


    // Static Functions
    public static List<Address> SearchApi(String address) {
        address = address.replaceAll("[' ']", "%20");
        String parm1 = String.format(BY_ADDRESS, address);
        String parm2 = String.format(SENSOR, true);
        String params = parm1 + "&" + parm2;
        String url = String.format(GEOCODE_URL, params);

        return GetLocations(url);
    }

    public static Address AddressByLatLng(LatLng latlng) {
        String parm1 = String.format(BY_LATLNG, latlng.latitude, latlng.longitude);
        String parm2 = String.format(SENSOR, true);
        String params = parm1 + "&" + parm2;
        String url = String.format(GEOCODE_URL, params);

        List<Address> list = GetLocations(url);
        Address address = null;
        if (list.size() > 0) {
            address = list.get(0);
        }

        return address;
    }

    private static List<Address> GetLocations(String url) {
        List<Address> locations = new ArrayList<>();

        try {
            JSONObject jsonObject = parser_Json.getJSONfromURL(url);

            String Status = jsonObject.getString("status");
            if (Status.equalsIgnoreCase("OK")) {
                JSONArray results = jsonObject.getJSONArray("results");
                for (int j = 0; j < results.length(); j++) {
                    JSONObject json = results.getJSONObject(j);
                    locations.add(AddressFromJSON(json));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locations;
    }

    private static Address AddressFromJSON(JSONObject json) {
        Address address = new Address();
        try {
            JSONArray address_components = json.getJSONArray("address_components");
            JSONObject geometry = json.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");

            for (int i = 0; i < address_components.length(); i++) {
                JSONObject item = address_components.getJSONObject(i);
                String type = item.getJSONArray("types").getString(0);
                String name = item.getString("long_name");

                switch (type) {
                    case "street_number":
                        address.setStreetNumber(name);
                        break;
                    case "route":
                        address.setStreet(name);
                        break;
                    case "locality":
                        address.setCity(name);
                        break;
                    case "country":
                        address.setCountry(name);
                        break;
                    case "postal_code":
                        address.setPostalCode(name);
                        break;
                    default:
                        break;
                }
            }
            String formatted_address = json.getString("formatted_address");
            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");

            address.setFormattedAddress(formatted_address);
            address.setLat(lat);
            address.setLng(lng);
        } catch (Exception e) {
        }
        return address;
    }

    public static List<Address> AddressByLatLngArray(LatLng latlng) {
        String parm1 = String.format(BY_LATLNG, latlng.latitude, latlng.longitude);
        String parm2 = String.format(SENSOR, true);
        String params = parm1 + "&" + parm2;
        String url = String.format(GEOCODE_URL, params);

        List<Address> list = GetLocations(url);

        return list;
    }

    public static LatLng GetMyPosition(Context context) {
        GPSTracker mGPS = new GPSTracker(context);

        if (mGPS.canGetLocation()) {
            mGPS.getLocation();
        } else {
            System.out.println("Unable");
        }

        return mGPS.getPosition();
    }

    public double getDistance(Context context) {
        return distance(getPosition(), Address.GetMyPosition(context), 'K');
    }

    private double distance(LatLng one, LatLng two, char unit) {
        double theta = one.longitude - two.longitude;
        double dist = Math.sin(deg2rad(one.latitude)) * Math.sin(deg2rad(two.latitude)) + Math.cos(deg2rad(one.latitude)) * Math.cos(deg2rad(two.latitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
