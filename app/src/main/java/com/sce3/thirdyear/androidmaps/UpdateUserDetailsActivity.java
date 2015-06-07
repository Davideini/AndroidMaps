package com.sce3.thirdyear.androidmaps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sce3.thirdyear.classes.InputValidator;
import com.sce3.thirdyear.classes.JSONRequest;
import com.sce3.thirdyear.classes.SQLiteDB;
import com.sce3.thirdyear.classes.User;

import org.json.JSONException;
import org.json.JSONObject;


public class UpdateUserDetailsActivity extends ActionBarActivity {
    private Button finish;
    private Button revert;
    private View.OnClickListener FinishButtonListener;
    private OnClickListener RevertChangesButtonListener;
    private EditText FN;
    private EditText LN;
    private EditText Phone1;
    private EditText Phone2;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_details);
        user=new User(new SQLiteDB(getApplicationContext()));
        revert=(Button)findViewById(R.id.buttonRevertDetailsChanges);
        finish=(Button)findViewById(R.id.buttonFinish);
        FN=(EditText)findViewById(R.id.TextBoxFN);
        LN=(EditText)findViewById(R.id.TextBoxLN);
        Phone1=(EditText)findViewById(R.id.TextBoxPN1);
        Phone2=(EditText)findViewById(R.id.TextBoxPN2);
        FN.setText(user.getFname());
        LN.setText(user.getLname());
        Phone1.setText((user.getPhone1()));
        Phone2.setText((user.getPhone2()));

       FinishButtonListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String FirstName = FN.getText().toString();
                final String LastName = LN.getText().toString();
                final String PhoneNumber1 = Phone1.getText().toString();
                final String PhoneNumber2 = Phone2.getText().toString();
                if (!InputValidator.EmptyField(FirstName)) {
                    FN.requestFocus();
                    FN.setError("FIELD CANNOT BE EMPTY");
                } else if (!InputValidator.Name(FirstName)) {
                    FN.requestFocus();
                    FN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (!InputValidator.EmptyField(LastName)) {
                    LN.requestFocus();
                    LN.setError("FIELD CANNOT BE EMPTY");
                } else if (!InputValidator.Name(LastName)) {
                    LN.requestFocus();
                    LN.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }  else if (!InputValidator.EmptyField(PhoneNumber1)) {
                    Phone1.requestFocus();
                    Phone1.setError("FIELD CANNOT BE EMPTY");
                } else if (!InputValidator.Phone(PhoneNumber1)) {
                    Phone1.requestFocus();
                    Phone1.setError("Please enter a valid Phone number");
                } else if (!InputValidator.EmptyField(PhoneNumber2)) {
                    Phone2.requestFocus();
                    Phone2.setError("FIELD CANNOT BE EMPTY"
                            + "you can right the same number as in phone number 1");
                } else if (!InputValidator.Phone(PhoneNumber2)) {
                    Phone2.requestFocus();
                    Phone2.setError("Please enter a valid Phone number\n" +
                            "you can right the same number as in phone number 1");
                } else {
                    String address=String.format("https://%s/JavaWeb/api?action=UpdateUserDetails&email=%s&fname=%s&lname=%s&phone1=%s&phone2=%s", JSONRequest.SERVER,user.getEmail(),FirstName,LastName,PhoneNumber1,PhoneNumber2);

                    System.out.println(address);
                    try {
                        JSONRequest json=new JSONRequest(address);
                        JSONObject jobj = new JSONObject(json.getJSON());
                        if(jobj.getString("result").equals("success")){
                            Toast.makeText(UpdateUserDetailsActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(UpdateUserDetailsActivity.this, MenuActivity.class);
                            startActivity(i);
                            finish();
                        }

                        else if(jobj.getString("result").equals("error")){
                            Toast.makeText(UpdateUserDetailsActivity.this, jobj.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        Toast.makeText(UpdateUserDetailsActivity.this, "Error receiving data.", Toast.LENGTH_LONG).show();

                    }
                }

            }
            };
        RevertChangesButtonListener=new OnClickListener()
        {

            @Override
            public void onClick(View v) {
                FN.setText(user.getFname());
                LN.setText(user.getLname());
                Phone1.setText((user.getPhone1()));
                Phone2.setText((user.getPhone2()));
            }
        };
        finish.setOnClickListener(FinishButtonListener);
        revert.setOnClickListener(RevertChangesButtonListener);
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
