package com.sce3.thirdyear.androidmaps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sce3.thirdyear.classes.Apartment;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.MyListAdapter;
import com.sce3.thirdyear.classes.SQLiteDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class HistoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SQLiteDB db = new SQLiteDB(getApplicationContext());
        String session_str=db.getSavedSession();
        String address=String.format("http://%s/JavaWeb/api?action=History&session=%s", JSONRequest.SERVER,session_str);
        JSONRequest json=new JSONRequest(address);
        System.out.println(address);
        try {
            JSONObject jobj = new JSONObject(json.getJSON());
            if(jobj.getString("result").equals("success")){
                JSONArray jarr=jobj.getJSONArray("data");
                final List<Map<String,String>> list=new ArrayList(jarr.length());
                for(int i=0;i<jarr.length();i++){
                    Map map=new HashMap();
                    JSONObject jo=(JSONObject)jarr.get(i);
                    Iterator it=jo.keys();
                    while(it.hasNext()){
                        String key=(String)it.next();
                        map.put(key,jo.get(key));
                    }
                    list.add(map);
                }
                    final ArrayAdapter theAdapter = new MyListAdapter(this, (List) list);
                    ListView lv = (ListView) findViewById(R.id.listView);
                    lv.setAdapter(theAdapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        AlertDialog.Builder adb=new AlertDialog.Builder(HistoryActivity.this);
                        adb.setTitle("What to do?");
                        adb.setMessage("What you want to do?");
                        final int positionToRemove = position;
                        adb.setNegativeButton("Show", new AlertDialog.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){
                                Intent mIntent = new Intent(HistoryActivity.this, HouseDetailsActivity.class);
                                SQLiteDB db = new SQLiteDB(getApplicationContext());
                                String session = db.getSavedSession();
                                String address = String.format("http://%s/JavaWeb/api?action=getApartment&apartment_id=%d", JSONRequest.SERVER, list.get(position).get("id"), session);
                                System.out.println(address);
                                JSONRequest json = new JSONRequest(address);
                                try {
                                    JSONObject jobj = new JSONObject(json.getJSON());
                                    JSONObject jdata= jobj.getJSONObject("data");
                                    Apartment apartment=null;
                                    if (jobj.getString("result").equals("success")) {
                                        //public Apartment(int id, int user_id, int type_id, String city, int price, String territory
                                        // String address, boolean aircondition, boolean elevator, boolean balcony, boolean isolated_room,
                                        // boolean parking, boolean handicap_access, boolean storage, boolean bars, boolean sun_balcony,
                                        // boolean renovated, boolean furnished, boolean unit, boolean pandoor, float rooms,
                                        // int floor, float longitude,
                                        // float latitude, float sizem2, String comment) {
                                        //CREATE TABLE Apartments(id integer primary key autoincrement,user_id integer,type_id integer,territory text,city text,address text,rooms real,floor integer,latitude real,longitude real,sizem2 real,comment text,price integer,add_date text,aircondition boolean,elevator boolean,balcony boolean,isolated_room boolean,parking boolean,handicap_access boolean,storage boolean,bars boolean,sun_balcony boolean,renovated boolean,furnished boolean,unit boolean,pandoor boolean,FOREIGN KEY(user_id) REFERENCES Users(id),FOREIGN KEY(type_id) REFERENCES Apartment_Types(id));

                                        apartment = new Apartment(
                                                jdata.getInt("id"),
                                                jdata.getInt("user_id"),
                                                jdata.getInt("type_id"),
                                                jdata.getString("city"),
                                                jdata.getInt("price"),
                                                jdata.getString("territory"),
                                                jdata.getString("address"),
                                                jdata.getInt("aircondition") == 1 ? true : false,
                                                jdata.getInt("elevator") == 1 ? true : false,
                                                jdata.getInt("balcony") == 1 ? true : false,
                                                jdata.getInt("isolated_room") == 1 ? true : false,
                                                jdata.getInt("parking") == 1 ? true : false,
                                                jdata.getInt("handicap_access") == 1 ? true : false,
                                                jdata.getInt("storage") == 1 ? true : false,
                                                jdata.getInt("bars") == 1 ? true : false,
                                                jdata.getInt("sun_balcony") == 1 ? true : false,
                                                jdata.getInt("renovated") == 1 ? true : false,
                                                jdata.getInt("furnished") == 1 ? true : false,
                                                jdata.getInt("unit") == 1 ? true : false,
                                                jdata.getInt("pandoor") == 1 ? true : false,
                                                Float.parseFloat(jdata.getString("rooms")),
                                                jdata.getInt("floor"),
                                                Float.parseFloat(jdata.getString("longitude")),
                                                Float.parseFloat(jdata.getString("latitude")),
                                                Float.parseFloat(jdata.getString("sizem2")),
                                                jdata.getString("comment")
                                                );
                                        mIntent.putExtra(MenuActivity.SER_KEY,apartment);
                                        startActivity(mIntent);
                                    }

                                   else{
                                        Toast.makeText(getApplicationContext(), "Error showing item.", Toast.LENGTH_SHORT).show();
                                   }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            }
                        });
                        adb.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDB db = new SQLiteDB(getApplicationContext());
                                String session = db.getSavedSession();
                                String address = String.format("http://%s/JavaWeb/api?action=removeHistory&apartment_id=%d&session=%s", JSONRequest.SERVER, list.get(position).get("id"), session);
                                System.out.println(address);
                                JSONRequest json = new JSONRequest(address);
                                JSONObject jobj;
                                try {
                                    jobj = new JSONObject(json.getJSON());
                                    if (!jobj.getString("result").equals("success")) {
                                        Toast.makeText(getApplicationContext(), "Error deleting item.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                list.remove(positionToRemove);
                                theAdapter.notifyDataSetChanged();
                            }});
                        adb.show();
                    }
                });
            }
            else{
                Toast.makeText(this,jobj.getString("message"),Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            Toast.makeText(this,"Error receiving data.",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
