package com.sce3.thirdyear.classes;

import org.json.JSONObject;

public class User {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String phone1;
    private String phone2;
    private String session;
    /*do not remove the default constructor*/
    public User()
    {}
    /*st*/
    public User(SQLiteDB db) {
        String address = String.format("http://%s/JavaWeb/api?action=GetUserUsingSession&session=%s", JSONRequest.SERVER, db.getSavedSession());

        try {
            JSONRequest json = new JSONRequest(address);
            JSONObject jobj = new JSONObject(json.getJSON());
            fname=jobj.getString("fname");
            lname=jobj.getString("lname");
            email=jobj.getString("email");
            password=jobj.getString("password");
            phone1=jobj.getString("phone1");
            phone2=jobj.getString("phone2");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone2() {
        return phone2;
    }
}
