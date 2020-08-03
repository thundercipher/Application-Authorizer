package com.tanay.thundercipher.leaveapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logInButton;
    ImageView googleLogInImageView;
    EditText logInEmailEditText, logInPasswordEditText;
    String logInEmail, logInPassword;

    public void logInUser(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult)
            {
                Toast.makeText(LogInActivity.this, "LogIn Successful!", Toast.LENGTH_SHORT);
                FirebaseUser user = auth.getCurrentUser();
                String userType = "";

                if(userType.equals("Student"))
                {
                    startActivity(new Intent(LogInActivity.this, StudentUserActivity.class));
                    finish();
                }

                else if(userType.equals("Warden"))
                {
                    startActivity(new Intent(LogInActivity.this, WardenUserActivity.class));
                    finish();
                }

                else if(userType.equals("Security Official"))
                {
                    startActivity(new Intent(LogInActivity.this, SecurityUserActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       logInButton = (Button)findViewById(R.id.logInButton);
       googleLogInImageView = (ImageView)findViewById(R.id.googleLogInImageView);
       logInEmailEditText = (EditText)findViewById(R.id.logInEmailEditText);
       logInPasswordEditText = (EditText)findViewById(R.id.logInPasswordEditText);
       auth = FirebaseAuth.getInstance();

       logInButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               logInEmail = logInEmailEditText.getText().toString();
               logInPassword = logInPasswordEditText.getText().toString();
               logInUser(logInEmail, logInPassword);
           }
       });


    }
}