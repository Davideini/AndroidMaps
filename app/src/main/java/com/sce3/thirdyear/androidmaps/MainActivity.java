package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;

import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDB db = new SQLiteDB(getApplicationContext());
        db.open();
        String session = db.getSavedSession();
        db.close();
        System.out.println(session);
        if (!session.equals("")) {
            String address = String.format("http://192.168.3.170:8080/JavaWeb/api?action=Main&session=%s", session);
            ExecutorService pool = Executors.newFixedThreadPool(1);
            JSONRequest json = new JSONRequest(address);
            Future<String> future = pool.submit(json);
            System.out.println(address);
            try {
                JSONObject jobj = new JSONObject(future.get());
                if (jobj.getString("result").equals("success")) {
                    TextView txt = (TextView) findViewById(R.id.textView);
                    txt.setVisibility(View.VISIBLE); //logged by session.
                } else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            //no session in local db or session expired on server
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        //######### Test Shimi's activity ###########

        Intent intent = new Intent(this, AddApartment.class);
        startActivity(intent);

        //###########################################

        // Button btn = (Button) findViewById(R.id.btnMessage);

        //final Context context = getApplicationContext();

        //final CharSequence text = "Hello toast!";

        //final int duration = Toast.LENGTH_SHORT;

      /*  btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

            }
        });*/
/*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
*/
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
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
