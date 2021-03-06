package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class MainActivity extends AppCompatActivity {

    ImageView websiteImageView, linkedInImageView;
    Button logInButton, signUpButton;

    FirebaseAuth auth;
    FirebaseDatabase database;
    String userID = "", userType = "";
    ActionBar actionBar;
    LoadingDialog dialog;

    private void clickedButton(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        try
        {
            startActivity(Intent.createChooser(intent, "Open With:"));
        }

        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Couldn't open the link!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        dialog = new LoadingDialog(MainActivity.this);
        dialog.startLoadingDialog();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null)
        {
            userID = user.getUid();
            DatabaseReference reference = database.getReference().child("Users").child(userID);

            reference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    for(DataSnapshot snap : snapshot.getChildren())
                    {
                        if((snap.getKey() != null) && snap.getKey().equals("User Type"))
                        {
                            userType = String.valueOf(snap.getValue());
                        }
                    }

                    if(userType.equals("Student"))
                    {
                        dialog.dismissDialog();

                        Intent i = new Intent(getApplicationContext(), StudentUserActivity.class);
                        startActivity(i);
                    }

                    else if(userType.equals("Warden"))
                    {
                        dialog.dismissDialog();

                        Intent i = new Intent(getApplicationContext(), WardenUserActivity.class);
                        startActivity(i);
                    }

                    else if(userType.equals("Security Official"))
                    {
                        dialog.dismissDialog();

                        Intent i = new Intent(getApplicationContext(), SecurityUserActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                    dialog.dismissDialog();
                    Toast.makeText(getApplicationContext(), "Couldn't load data, log in again!", Toast.LENGTH_LONG).show();
                }
            });
        }

        else
        {
            dialog.dismissDialog();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Application Authorizer");
        websiteImageView = (ImageView)findViewById(R.id.websiteImageView);
        linkedInImageView = (ImageView)findViewById(R.id.linkedInImageView);
        logInButton = (Button)findViewById(R.id.logInButton);
        signUpButton = (Button)findViewById(R.id.signUpButton);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        actionBar = getSupportActionBar();
        actionBar.hide();

        websiteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                clickedButton("https://www.iiitm.ac.in/index.php/en/");
            }
        });

        linkedInImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                clickedButton("//https://www.linkedin.com/school/abv-indian-institute-of-information-technology-and-management/");
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(i);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}

