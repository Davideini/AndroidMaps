package com.sce3.thirdyear.classes;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sce3.thirdyear.maps.data.Address;
import com.sce3.thirdyear.maps.data.parser_Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 25/05/2015.
 */

public class Apartment implements Serializable {

    // private const
    private static final String SERVER_URL = "http://%s/JavaWeb/api?action=SearchAddress&%s";
    private static final String BY_LATLNG_DIST = "latlng=%s,%s&distance=%s";
    private static final String BY_ADDRESS = "address=%s";
    private static final String UPDATE = "update=true";

    int id;
    int user_id;
    int type_id;
    String city;
    float price;
    String territory;
    String address;
    boolean aircondition;
    boolean elevator;
    boolean balcony;
    boolean isolated_room;
    boolean parking;
    boolean handicap_access;
    boolean storage;
    boolean bars;
    boolean sun_balcony;
    boolean renovated;
    boolean furnished;
    boolean unit;
    boolean pandoor;
    float rooms;
    int floor;
    float longitude;
    float latitude;
    float sizem2;
    String comment;

    Marker marker;


    public Apartment(int id, int user_id, int type_id, String city, int price, String territory, String address, boolean aircondition, boolean elevator, boolean balcony, boolean isolated_room, boolean parking, boolean handicap_access, boolean storage, boolean bars, boolean sun_balcony, boolean renovated, boolean furnished, boolean unit, boolean pandoor, float rooms, int floor, float longitude, float latitude, float sizem2, String comment) {
        this.id = id;
        this.user_id = user_id;
        this.type_id = type_id;
        this.city = city;
        this.price = price;
        this.territory = territory;
        this.address = address;
        this.aircondition = aircondition;
        this.elevator = elevator;
        this.balcony = balcony;
        this.isolated_room = isolated_room;
        this.parking = parking;
        this.handicap_access = handicap_access;
        this.storage = storage;
        this.bars = bars;
        this.sun_balcony = sun_balcony;
        this.renovated = renovated;
        this.furnished = furnished;
        this.unit = unit;
        this.pandoor = pandoor;
        this.rooms = rooms;
        this.floor = floor;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sizem2 = sizem2;
        this.comment = comment;
    }

    public Apartment() {

    }

    public Apartment(String id, String user_id, String type_id, String city, String price, String territory, String address, String aircondition, String elevator, String balcony, String isolated_room, String parking, String handicap_access, String storage, String bars, String sun_balcony, String renovated, String furnished, String unit, String pandoor, String rooms, String floor, String longitude, String latitude, String sizem2, String comment) {
        this.id = Integer.parseInt(id);
        this.user_id = Integer.parseInt(user_id);
        this.type_id = Integer.parseInt(type_id);
        this.city = city;
        this.price = Integer.parseInt(price);
        this.territory = territory;
        this.address = address;
        if (aircondition == "true" || aircondition == "1")
            this.aircondition = true;
        else this.aircondition = false;
        if (elevator == "true" || elevator == "1")
            this.elevator = true;
        else this.elevator = false;
        if (balcony == "true" || balcony == "1")
            this.balcony = true;
        else this.balcony = false;
        if (isolated_room == "true" || isolated_room == "1")
            this.isolated_room = true;
        else this.isolated_room = false;
        if (parking == "true" || parking == "1")
            this.parking = true;
        else this.parking = false;
        if (handicap_access == "true" || handicap_access == "1")
            this.handicap_access = true;
        else this.handicap_access = false;
        if (storage == "true" || storage == "1")
            this.storage = true;
        else this.storage = false;
        if (bars == "true" || bars == "1")
            this.bars = true;
        else this.bars = false;
        if (sun_balcony == "true" || sun_balcony == "1")
            this.sun_balcony = true;
        else this.sun_balcony = false;
        if (renovated == "true" || renovated == "1")
            this.renovated = true;
        else this.renovated = false;
        if (furnished == "true" || furnished == "1")
            this.furnished = true;
        else this.furnished = false;
        if (unit == "true" || unit == "1")
            this.unit = true;
        else this.unit = false;
        if (pandoor == "true" || pandoor == "1")
            this.pandoor = true;
        else this.pandoor = false;

        this.rooms = Float.parseFloat(rooms);
        this.floor = Integer.parseInt(floor);
        this.longitude = Float.parseFloat(longitude);
        this.latitude = Float.parseFloat(latitude);
        this.sizem2 = Float.parseFloat(sizem2);
        this.comment = comment;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAircondition() {
        return aircondition;
    }

    public void setAircondition(boolean aircondition) {
        this.aircondition = aircondition;
    }

    public boolean isElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isIsolated_room() {
        return isolated_room;
    }

    public void setIsolated_room(boolean isolated_room) {
        this.isolated_room = isolated_room;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isHandicap_access() {
        return handicap_access;
    }

    public void setHandicap_access(boolean handicap_access) {
        this.handicap_access = handicap_access;
    }

    public boolean isStorage() {
        return storage;
    }

    public void setStorage(boolean storage) {
        this.storage = storage;
    }

    public boolean isBars() {
        return bars;
    }

    public void setBars(boolean bars) {
        this.bars = bars;
    }

    public boolean isSun_balcony() {
        return sun_balcony;
    }

    public void setSun_balcony(boolean sun_balcony) {
        this.sun_balcony = sun_balcony;
    }

    public boolean isRenovated() {
        return renovated;
    }

    public void setRenovated(boolean renovated) {
        this.renovated = renovated;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public boolean isUnit() {
        return unit;
    }

    public void setUnit(boolean unit) {
        this.unit = unit;
    }

    public boolean isPandoor() {
        return pandoor;
    }

    public void setPandoor(boolean pandoor) {
        this.pandoor = pandoor;
    }

    public float getRooms() {
        return rooms;
    }

    public void setRooms(float rooms) {
        this.rooms = rooms;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getSizem2() {
        return sizem2;
    }

    public void setSizem2(float sizem2) {
        this.sizem2 = sizem2;
    }


    public static List<Apartment> SearchApi(String address) {
        address = address.replaceAll("[' ']", "%20");
        String parm1 = String.format(BY_ADDRESS, address);
        String params = parm1;
        String url = String.format(SERVER_URL, JSONRequest.SERVER, params);

        return GetApartments(url);
    }

    public static void Update(Apartment apartment, Address address) {
        String parm1 = String.format("id=%s&city=%s", apartment.getId(), address.getCity());
        String parm2 = String.format(BY_ADDRESS, address.getFormattedAddress());
        parm2 = parm2.replaceAll("[' ']", "%20");

        String parm3 = String.format(BY_LATLNG_DIST, address.getLat(), address.getLng(), 0);
        String params = UPDATE + "&" + parm1 + "&" + parm2 + "&" + parm3;
        String url = String.format(SERVER_URL, JSONRequest.SERVER, params);
        System.out.print(url);
        GetApartments(url);
    }

    public static List<Apartment> AddressByLatLng(LatLng latLng, float distance) {
        String parm1 = String.format(BY_LATLNG_DIST, latLng.latitude, latLng.longitude, distance);
        String params = parm1;
        String url = String.format(SERVER_URL, JSONRequest.SERVER, params);
        return GetApartments(url);
    }

    public static List<Apartment> GetApartments(String url) {

        List<Apartment> apartments = new ArrayList<>();

        try {
            JSONObject jsonObject = parser_Json.getJSONfromURL(url);

            String Status = jsonObject.getString("result");
            if (Status.equalsIgnoreCase("success")) {

                JSONArray results = null;
                JSONObject result = null;
                try {
                    results = jsonObject.getJSONArray("data");
                    for (int j = 0; j < results.length(); j++) {
                        JSONObject json = results.getJSONObject(j);
                        apartments.add(ApartmentFromJSON(json));
                    }
                } catch (Exception e) {
                }
                try {
                    if (results == null) {
                        result = jsonObject.getJSONObject("data");
                        apartments.add(ApartmentFromJSON(result));
                    }
                } catch (Exception e) {
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return apartments;
    }


    private static Apartment ApartmentFromJSON(JSONObject json) {
        Apartment apartment = new Apartment();
        try {
            apartment.setParking(getBool(json, "parking"));
            apartment.setRenovated(getBool(json, "renovated"));
            apartment.setRooms(((float) json.getDouble("rooms")));
            apartment.setCity(json.getString("city"));
            apartment.setAddress(json.getString("address"));
            apartment.setLatitude(((float) json.getDouble("latitude")));
            apartment.setLongitude(((float) json.getDouble("longitude")));
            apartment.setStorage(getBool(json, "storage"));
            apartment.setBalcony(getBool(json, "balcony"));
            apartment.setHandicap_access(getBool(json, "handicap_access"));
            apartment.setIsolated_room(getBool(json, "isolated_room"));
            apartment.setElevator(getBool(json, "elevator"));
            apartment.setFurnished(getBool(json, "furnished"));
            apartment.setPrice((float) json.getDouble("price"));
            apartment.setId(json.getInt("id"));
            apartment.setSizem2((float) json.getDouble("sizem2"));
            apartment.setFloor(json.getInt("floor"));
            apartment.setSun_balcony(getBool(json, "sun_balcony"));
            apartment.setType_id(json.getInt("type_id"));
            apartment.setAircondition(getBool(json, "aircondition"));
            apartment.setBars(getBool(json, "bars"));
            apartment.setUnit(getBool(json, "unit"));
            apartment.setUser_id(json.getInt("user_id"));
            apartment.setPandoor(getBool(json, "pandoor"));
            apartment.setComment(json.getString("comment"));
            apartment.setTerritory(json.getString("territory"));

        } catch (Exception e) {
        }
        return apartment;
    }


    private static boolean getBool(JSONObject json, String key) {
        boolean bool = true;
        try {
            bool = json.getBoolean(key);
        } catch (Exception e) {
            try {
                bool = new Integer(1).equals(json.get(key));
            } catch (JSONException e1) {
                try {
                    bool = "true" == json.getString(key).toLowerCase();
                } catch (JSONException e2) {

                }
            }
        }
        return bool;
    }

    @Override
    public String toString() {
        return address;
    }

    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }
}

