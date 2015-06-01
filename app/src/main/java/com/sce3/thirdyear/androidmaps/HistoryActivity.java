package com.sce3.thirdyear.androidmaps;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        AlertDialog.Builder adb=new AlertDialog.Builder(HistoryActivity.this);
                        adb.setTitle("What to do?");
                        adb.setMessage("What you want to do?");
                        final int positionToRemove = position;
                        adb.setNegativeButton("Show", new AlertDialog.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){
                                
                            }
                        });
                        adb.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDB db = new SQLiteDB(getApplicationContext());
                                String session = db.getSavedSession();
                                String address = String.format("http://%s/JavaWeb/api?action=removeHistory&apartment_id=%d&session=%s", JSONRequest.SERVER, list.get(which+1).get("id"), session);
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
