package com.sce3.thirdyear.classes;

import java.util.Date;

/**
 * Created by win7 on 25/05/2015.
 */
public class Apartment  {
    int id;
    int user_id;
    int type_id;
    String city;
    int price;
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

    public void setComment(String comment) {this.comment = comment;}
    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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


}

