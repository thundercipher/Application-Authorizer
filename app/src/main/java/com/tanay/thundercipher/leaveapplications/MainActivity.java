package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    ImageView websiteImageView, linkedinImageView;
    Button logInButton, signUpButton;
    FirebaseAuth auth;

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

    protected void onStart()
    {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
           String userEmail = user.getEmail();
           DocumentReference docRef = null;

           if(userEmail != null)
           {
               docRef = FirebaseFirestore.getInstance().collection("Users").document(userEmail);
               docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task)
                   {
                       if(task.isSuccessful())
                       {
                           DocumentSnapshot doc = task.getResult();
                           String userType = doc.getString("User Type");

                           if(userType.equals("Student"))
                           {
                               Intent i = new Intent(getApplicationContext(), StudentUserActivity.class);
                               startActivity(i);
                           }

                           else if(userType.equals("Warden"))
                           {
                               Intent i = new Intent(getApplicationContext(), WardenUserActivity.class);
                               startActivity(i);
                           }

                           else if(userType.equals("Security Official"))
                           {
                               Intent i = new Intent(getApplicationContext(), SecurityUserActivity.class);
                               startActivity(i);
                           }
                       }
                   }
               });
           }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Application Authorizer");
        websiteImageView = (ImageView)findViewById(R.id.websiteImageView);
        linkedinImageView = (ImageView)findViewById(R.id.linkedinImageView);
        logInButton = (Button)findViewById(R.id.logInButton);
        signUpButton = (Button)findViewById(R.id.signUpButton);
        auth = FirebaseAuth.getInstance();

        websiteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                clickedButton("https://www.iiitm.ac.in/index.php/en/");
            }
        });

        linkedinImageView.setOnClickListener(new View.OnClickListener() {
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

