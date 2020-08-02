package com.tanay.thundercipher.leaveapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logInButton;
    ImageView googleLogInImageView;
    EditText logInEmailEditText, logInPasswordEditText;
    String logInEmail, logInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       logInButton = (Button)findViewById(R.id.logInButton);
       googleLogInImageView = (ImageView)findViewById(R.id.googleLogInImageView);
       logInEmailEditText = (EditText)findViewById(R.id.logInEmailEditText);
       logInPasswordEditText = (EditText)findViewById(R.id.logInPasswordEditText);

       logInEmail = logInEmailEditText.getText().toString();
       logInPassword = logInPasswordEditText.getText().toString();
    }
}