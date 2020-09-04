package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LogInActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logInButton;
    EditText logInEmailEditText, logInPasswordEditText;
    String logInEmail, logInPassword;
    FirebaseDatabase database;
    String userType = "";
    ActionBar actionBar;

    public void logInUser(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult)
            {

                Toast.makeText(LogInActivity.this, "LogIn Successful!", Toast.LENGTH_SHORT).show();

                FirebaseUser user = auth.getCurrentUser();
                DatabaseReference reference;
                String userID = "";

                if(user != null)
                {
                    userID = user.getUid();
                }

                //to get the userType
                if (!userID.equals(""))
                {
                    reference = database.getReference().child("Users").child(userID);

                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot)
                        {
                            for(DataSnapshot snap : snapshot.getChildren())
                            {
                                if(snap.getKey().equals("User Type"))
                                {
                                    userType = String.valueOf(snap.getValue());

                                    switch (userType)
                                    {
                                        case "Student":
                                            startActivity(new Intent(LogInActivity.this, StudentUserActivity.class));
                                            finish();
                                            break;

                                        case "Warden":
                                            startActivity(new Intent(LogInActivity.this, WardenUserActivity.class));
                                            finish();
                                            break;

                                        case "Security Official":
                                            startActivity(new Intent(LogInActivity.this, SecurityUserActivity.class));
                                            finish();
                                            break;
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {
                            Toast.makeText(LogInActivity.this, "Couldn't load user information! Try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       logInButton = (Button)findViewById(R.id.logInButton);
       logInEmailEditText = (EditText)findViewById(R.id.logInEmailEditText);
       logInPasswordEditText = (EditText)findViewById(R.id.logInPasswordEditText);
       auth = FirebaseAuth.getInstance();
       database = FirebaseDatabase.getInstance();

        actionBar = getSupportActionBar();
        actionBar.setTitle("Application Authorizer");

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