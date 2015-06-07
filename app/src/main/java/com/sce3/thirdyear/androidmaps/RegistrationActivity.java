package com.sce3.thirdyear.androidmaps;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.sce3.thirdyear.androidmaps.maps.AddressActivity;
import com.sce3.thirdyear.classes.InputValidator;
import com.sce3.thirdyear.classes.JSONRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends ActionBarActivity {
    private CheckBox buy;
    private Button next;
    private Button finish;
    private OnClickListener checkBoxListener;
    private OnClickListener FinishButtonListener;
    private OnClickListener NextButtonListener;
    private EditText FN;
    private EditText LN;
    private EditText Phone1;
    private EditText Phone2;
    private EditText Pass;
    private EditText PassConfirm;
    private EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buy=(CheckBox)findViewById(R.id.checkBoxBuyer);
        buy.setVisibility(View.INVISIBLE);
        next = (Button)findViewById(R.id.buttonNext);

        next.setEnabled(false);
        next.setVisibility(View.INVISIBLE);
        finish=(Button)findViewById(R.id.buttonFinish);
        FN=(EditText)findViewById(R.id.TextBoxFN);
        LN=(EditText)findViewById(R.id.TextBoxLN);
        Phone1=(EditText)findViewById(R.id.TextBoxPN1);
        Phone2=(EditText)findViewById(R.id.TextBoxPN2);
        Email=(EditText)findViewById(R.id.TextBoxEmail);
        Pass=(EditText)findViewById(R.id.TextBoxPass);
        PassConfirm=(EditText)findViewById(R.id.TextBoxPassConfirm);
        NextButtonListener=new OnClickListener() {
            @Override
            public void onClick(View view) {

                 String FirstName=FN.getText().toString();
                 String LastName=LN.getText().toString();
                 String PhoneNumber1=Phone1.getText().toString();
                 String PhoneNumber2=Phone2.getText().toString();
                 String EmailAddress=Email.getText().toString();
                 String Password=Pass.getText().toString();
                 String PasswordConfirmation=PassConfirm.getText().toString();

                if(!InputValidator.EmptyField(FirstName))
                {
                    FN.requestFocus();
                    FN.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Name(FirstName))
                {
                    FN.requestFocus();
                    FN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(!InputValidator.EmptyField(LastName))
                {
                    LN.requestFocus();
                    LN.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Name(LastName))
                {
                    LN.requestFocus();
                    LN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(!InputValidator.EmptyField(EmailAddress))
                {
                    Email.requestFocus();
                    Email.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Email(EmailAddress))
                {
                    Email.requestFocus();
                    Email.setError("Please enter a valid Email");
                }
                else if(!InputValidator.EmptyField(PhoneNumber1))
                {
                    Phone1.requestFocus();
                    Phone1.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Phone(PhoneNumber1))
                {
                    Phone1.requestFocus();
                    Phone1.setError("Please enter a valid Phone number");
                }
                else if(!InputValidator.EmptyField(PhoneNumber2))
                {
                    Phone2.requestFocus();
                    Phone2.setError("FIELD CANNOT BE EMPTY"
                            +"you can right the same number as in phone number 1");
                }
                else if(!InputValidator.Phone(PhoneNumber2))
                {
                    Phone2.requestFocus();
                    Phone2.setError("Please enter a valid Phone number \n" +
                            "you can write the same number as in phone number 1");
                }
                else if(!InputValidator.EmptyField(Password))
                {
                    Pass.requestFocus();
                    Pass.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Password(Password))
                {
                    Pass.requestFocus();
                    Pass.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!InputValidator.EmptyField(PasswordConfirmation))
                {
                    PassConfirm.requestFocus();
                    PassConfirm.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Password(PasswordConfirmation))
                {
                    PassConfirm.requestFocus();
                    PassConfirm.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!PasswordConfirmation.equals(Password))
                {
                    PassConfirm.requestFocus();
                    PassConfirm.setError("Wrong Password Confirmation");
                }
                else
                {

                    String address=String.format("https://%s/JavaWeb/api?action=Registration&email=%s&password=%s&fname=%s&lname=%s&phone1=%s&phone2=%s", JSONRequest.SERVER,EmailAddress,Password,FirstName,LastName,PhoneNumber1,PhoneNumber2);

                    System.out.println(address);
                    try {
                        JSONRequest json=new JSONRequest(address);
                        JSONObject jobj = new JSONObject(json.getJSON());
                        if(jobj.getString("result").equals("success")){
                            Toast.makeText(RegistrationActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(RegistrationActivity.this, AddressActivity.class);
                            RegistrationActivity.this.startActivity(myIntent);
                        }

                        else if(jobj.getString("result").equals("error")){
                            Toast.makeText(RegistrationActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        Toast.makeText(RegistrationActivity.this, "Error receiving data.", Toast.LENGTH_LONG).show();

                    }
                }
            }
        };
        FinishButtonListener=new OnClickListener() {
            @Override
            public void onClick(View view) {

                 String FirstName=FN.getText().toString();
                 String LastName=LN.getText().toString();
                 String PhoneNumber1=Phone1.getText().toString();
                 String PhoneNumber2=Phone2.getText().toString();
                 String EmailAddress=Email.getText().toString();
                 String Password=Pass.getText().toString();
                 String PasswordConfirmation=PassConfirm.getText().toString();

                if(!InputValidator.EmptyField(FirstName))
                {
                    FN.requestFocus();
                    FN.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Name(FirstName))
                {
                    FN.requestFocus();
                    FN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(!InputValidator.EmptyField(LastName))
                {
                    LN.requestFocus();
                    LN.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Name(LastName))
                {
                    LN.requestFocus();
                    LN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(!InputValidator.EmptyField(EmailAddress))
                {
                    Email.requestFocus();
                    Email.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Email(EmailAddress))
                {
                    Email.requestFocus();
                    Email.setError("Please enter a valid Email");
                }
                else if(!InputValidator.EmptyField(PhoneNumber1))
                {
                    Phone1.requestFocus();
                    Phone1.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Phone(PhoneNumber1))
                {
                    Phone1.requestFocus();
                    Phone1.setError("Please enter a valid Phone number");
                }
                else if(!InputValidator.EmptyField(PhoneNumber2))
                {
                    Phone2.requestFocus();
                    Phone2.setError("FIELD CANNOT BE EMPTY"
                             +"you can right the same number as in phone number 1");
                }
                else if(!InputValidator.Phone(PhoneNumber2))
                {
                    Phone2.requestFocus();
                    Phone2.setError("Please enter a valid Phone number \n" +
                            "you can write the same number as in phone number 1");
                }
                else if(!InputValidator.EmptyField(Password))
                {
                    Pass.requestFocus();
                    Pass.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Password(Password))
                {
                    Pass.requestFocus();
                    Pass.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!InputValidator.EmptyField(PasswordConfirmation))
                {
                    PassConfirm.requestFocus();
                    PassConfirm.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Password(PasswordConfirmation))
                {
                    PassConfirm.requestFocus();
                    PassConfirm.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!PasswordConfirmation.equals(Password))
                {
                    PassConfirm.requestFocus();
                    PassConfirm.setError("Wrong Password Confirmation");
                }
                else
                {

                    String address=String.format("https://%s/JavaWeb/api?action=Registration&email=%s&password=%s&fname=%s&lname=%s&phone1=%s&phone2=%s", JSONRequest.SERVER,EmailAddress,Password,FirstName,LastName,PhoneNumber1,PhoneNumber2);

                    System.out.println(address);
                    try {
                        JSONRequest json=new JSONRequest(address);
                        JSONObject jobj = new JSONObject(json.getJSON());
                        if(jobj.getString("result").equals("success")){
                            Toast.makeText(RegistrationActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(RegistrationActivity.this, MenuActivity.class);
                            startActivity(i);

                        }

                        else if(jobj.getString("result").equals("error")){
                            Toast.makeText(RegistrationActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        Toast.makeText(RegistrationActivity.this, "Error receiving data.", Toast.LENGTH_LONG).show();

                    }
                }
            }
        };
        checkBoxListener =new OnClickListener() {

            @Override
            public void onClick(View v) {


                if(buy.isChecked()) {
                    finish.setEnabled(false);
                    next.setEnabled(true);
                }

                else{
                    finish.setEnabled(true);
                    next.setEnabled(false);
                }


            }
        };

        buy.setOnClickListener(checkBoxListener);
        finish.setOnClickListener(FinishButtonListener);
        next.setOnClickListener(NextButtonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
