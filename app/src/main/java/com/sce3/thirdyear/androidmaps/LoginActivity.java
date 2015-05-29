package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginOnClick(View v) {
        TextView emailText = (TextView) findViewById(R.id.emailText);
        TextView passText = (TextView) findViewById(R.id.passText);
        String address = String.format("http://%s/JavaWeb/api?action=Login&email=%s&password=%s", JSONRequest.SERVER, emailText.getText(), passText.getText());
        //TextView res=(TextView)findViewById(R.id.textView3);

        System.out.println(address);
        try {
            JSONRequest json = new JSONRequest(address);
            JSONObject jobj = new JSONObject(json.getJSON());
            if (jobj.getString("result").equals("success")) {
                //res.setText(jobj.getString("session"));
                SQLiteDB db = new SQLiteDB(getApplicationContext());
                db.updateSession(jobj.getString("session"));
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                /*
                SharedPreferences sessionPref=getSharedPreferences("session",MODE_PRIVATE);
                SharedPreferences.Editor edit=sessionPref.edit();
                edit.putString("session",jobj.getString("session"));
                edit.commit();
                 */
            } else {
                Toast.makeText(this, jobj.getString("message"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Toast.makeText(this, "Error receiving data.", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this.getApplicationContext(),"test",Toast.LENGTH_SHORT);
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
        if (id == R.id.action_maps_develop) {
            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}
