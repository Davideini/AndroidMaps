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


public class ChangePasswordActivity extends ActionBarActivity {
    private Button finish;
    private EditText OldPass;
    private EditText NewPass;
    private EditText NewPassConfirm;
    private View.OnClickListener FinishButtonListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        finish=(Button)findViewById(R.id.buttonFinish);
        OldPass=(EditText)findViewById(R.id.TextBoxOP);
        NewPass=(EditText)findViewById(R.id.TextBoxNP);
        NewPassConfirm=(EditText)findViewById(R.id.TextBoxConfirmNP);
        FinishButtonListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String NewPassword=NewPass.getText().toString();
                final String OldPassword=OldPass.getText().toString();
                final String NewPasswordConfirmation=NewPassConfirm.getText().toString();
                if(!InputValidator.EmptyField(OldPassword))
                {
                    OldPass.requestFocus();
                    OldPass.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Password(OldPassword))
                {
                    OldPass.requestFocus();
                    OldPass.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!InputValidator.EmptyField(NewPassword))
                {
                    NewPass.requestFocus();
                    NewPass.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!InputValidator.Password(NewPassword))
                {
                    NewPass.requestFocus();
                    NewPass.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!InputValidator.EmptyField(NewPasswordConfirmation))
                {
                    NewPassConfirm.requestFocus();
                    NewPassConfirm.setError("FIELD CANNOT BE EMPTY");

                }
                else if(!InputValidator.Password(NewPasswordConfirmation))
                {
                    NewPassConfirm.requestFocus();
                    NewPassConfirm.setError("Password can contain only 8 to 15 (included) alphabetical letters and/or digitis");
                }
                else if(!NewPasswordConfirmation.equals(NewPassword))
                {
                    NewPassConfirm.requestFocus();
                    NewPassConfirm.setError("Wrong Password Confirmation");
                }
                else
                {
                    Toast.makeText(ChangePasswordActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
                }
            }
        };
        finish.setOnClickListener(FinishButtonListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
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
