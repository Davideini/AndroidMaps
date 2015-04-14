package com.sce3.thirdyear.androidmaps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sce3.thirdyear.classes.InputValidator;


public class UpdateUserDetailsActivity extends ActionBarActivity {
    private Button finish;

    private View.OnClickListener FinishButtonListener;
    private EditText FN;
    private EditText LN;
    private EditText Phone1;
    private EditText Phone2;
    private EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_details);
        finish=(Button)findViewById(R.id.buttonFinish);
        FN=(EditText)findViewById(R.id.TextBoxFN);
        LN=(EditText)findViewById(R.id.TextBoxLN);
        Phone1=(EditText)findViewById(R.id.TextBoxPN1);
        Phone2=(EditText)findViewById(R.id.TextBoxPN2);
        Email=(EditText)findViewById(R.id.TextBoxEmail);
        FinishButtonListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String FirstName = FN.getText().toString();
                final String LastName = LN.getText().toString();
                final String PhoneNumber1 = Phone1.getText().toString();
                final String PhoneNumber2 = Phone2.getText().toString();
                final String EmailAddress = Email.getText().toString();
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
                } else if (!InputValidator.EmptyField(EmailAddress)) {
                    Email.requestFocus();
                    Email.setError("FIELD CANNOT BE EMPTY");
                } else if (!InputValidator.Email(EmailAddress)) {
                    Email.requestFocus();
                    Email.setError("Please enter a valid Email");
                } else if (!InputValidator.EmptyField(PhoneNumber1)) {
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
                    Toast.makeText(UpdateUserDetailsActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
                }

            }
            };
        finish.setOnClickListener(FinishButtonListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_user_details, menu);
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
