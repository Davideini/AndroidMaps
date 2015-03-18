package com.sce3.thirdyear.androidmaps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;

import org.json.JSONObject;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginOnClick(View v){
        TextView emailText = (TextView) findViewById(R.id.emailText);
        TextView passText = (TextView) findViewById(R.id.passText);
        String address=String.format("http://192.168.3.170:8080/JavaWeb/api?action=Login&email=%s&password=%s",emailText.getText(),passText.getText());
        TextView res=(TextView)findViewById(R.id.textView3);
        ExecutorService pool = Executors.newFixedThreadPool(1);
        JSONRequest json=new JSONRequest(address);
        Future<String> future=pool.submit(json);
        System.out.println(address);
        try {
            JSONObject jobj = new JSONObject(future.get());
            if(jobj.getString("result").equals("success")){
                res.setText(jobj.getString("session"));
                SQLiteDB db=new SQLiteDB(getApplicationContext());
                db.open();
                db.updateSession(jobj.getString("session"));
                db.close();
            }
            else{
                res.setText(jobj.getString("message"));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Toast.makeText(this.getApplicationContext(),"test",Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
