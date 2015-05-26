package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sce3.thirdyear.classes.InputValidator;
import com.sce3.thirdyear.classes.JSONRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class RegistrationBuyerActivity extends ActionBarActivity {
    private EditText Name1;
    private EditText Address1;
    private EditText Name2;
    private EditText Address2;
    private EditText Name3;
    private EditText Address3;
    private EditText Name4;
    private EditText Address4;
    private OnClickListener CancelButtonListener;
    private OnClickListener HelpTextViewListener;
    private OnClickListener RegisterButtonListener;
    private Button Cancel;
    private Button Register;
    private TextView Help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_buyer);
        Name1=(EditText)findViewById(R.id.TextBoxLocation1name);
        Address1=(EditText)findViewById(R.id.TextBoxLocation1address);

        Name2=(EditText)findViewById(R.id.TextBoxLocation2name);
        Address2=(EditText)findViewById(R.id.TextBoxLocation2address);

        Name3=(EditText)findViewById(R.id.TextBoxLocation3name);
        Address3=(EditText)findViewById(R.id.TextBoxLocation3address);

        Cancel=(Button)findViewById(R.id.ButtonCancel_Locations);
        Help=(TextView)findViewById(R.id.textViewRegistration_buyer_Help);
        Register=(Button)findViewById(R.id.ButtonRegister_Locations);

        RegisterButtonListener=new OnClickListener(){

            @Override
            public void onClick(View v) {
                String n1=Name1.getText().toString();
                String a1=Address1.getText().toString();
                String n2=Name2.getText().toString();
                String a2=Address2.getText().toString();
                String n3=Name3.getText().toString();
                String a3=Address3.getText().toString();
                String Latitude1="";
                String Longitude1="";
                String Latitude2="";
                String Longitude2="";
                String Latitude3="";
                String Longitude3="";

                if (!InputValidator.isEmpty(n1)&& !InputValidator.isEmpty(a1)) {

                    if (InputValidator.isBadForSQL(n1)) {
                        Name1.requestFocus();
                        Name1.setError("Not allowed signs");
                    } else if (InputValidator.isBadForSQL(a1)) {
                        Address1.requestFocus();
                        Address1.setError("Not allowed signs");
                    }
                    else{
                        String address=String.format("http://%s/JavaMaps/api?action=Registration&email=%s&name=%s&address=%s&latitude=%s&longitude=%s", JSONRequest.SERVER,n1,a1,Latitude1,Longitude1);
                        registerToServer(address);
                    }
                }
                     if (!InputValidator.isEmpty(n2) && !InputValidator.isEmpty(a2)) {
                        if (InputValidator.isBadForSQL(n2)) {
                            Name2.requestFocus();
                            Name2.setError("Not allowed signs");
                        } else if (InputValidator.isBadForSQL(a2)) {
                            Address2.requestFocus();
                            Address2.setError("Not allowed signs");
                        }
                         else{
                            String address=String.format("http://%s/JavaMaps/api?action=Registration&email=%s&name=%s&address=%s&latitude=%s&longitude=%s", JSONRequest.SERVER,n2,a2,Latitude2,Longitude2);
                            registerToServer(address);
                        }
                    }
                     if (!InputValidator.isEmpty(n3) && !InputValidator.isEmpty(a3)) {
                        if (InputValidator.isBadForSQL(n3)) {
                            Name3.requestFocus();
                            Name3.setError("Not allowed signs");
                        }else  if (InputValidator.isBadForSQL(a3)) {
                            Address3.requestFocus();
                            Address3.setError("Not allowed signs");
                        }
                         else{
                            String address=String.format("http://%s/JavaMaps/api?action=Registration&email=%s&name=%s&address=%s&latitude=%s&longitude=%s", JSONRequest.SERVER,n3,a3,Latitude3,Longitude3);
                            registerToServer(address);
                        }
                    }




            }
        };
        HelpTextViewListener=new OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationBuyerActivity.this, "To register a location, enter both\n its name and address, else the \nlocation will not be registered", Toast.LENGTH_LONG).show();

            }
        };
        CancelButtonListener =new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationBuyerActivity.this, "No locations registered", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(RegistrationBuyerActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
            }
        };
        Cancel.setOnClickListener(CancelButtonListener);
        Help.setOnClickListener(HelpTextViewListener);
        Register.setOnClickListener(RegisterButtonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_buyer, menu);
        return true;
    }
    public void registerToServer(String address)
    {
        System.out.println(address);
        try {
            JSONRequest json=new JSONRequest(address);
            JSONObject jobj = new JSONObject(json.getJSON());
            if(jobj.getString("result").equals("success")){
                Toast.makeText(RegistrationBuyerActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
            }

            else if(jobj.getString("result").equals("error")){
                Toast.makeText(RegistrationBuyerActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            Toast.makeText(RegistrationBuyerActivity.this, "Error receiving data.", Toast.LENGTH_LONG).show();

        }
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
