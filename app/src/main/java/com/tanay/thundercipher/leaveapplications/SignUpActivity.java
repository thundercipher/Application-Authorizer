package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Spinner usersListSpinner;
    String signUpMethod,signUpEmail,signUpPassword, userID;
    EditText signUpEmailEditText, signUpPasswordEditText;
    Button signUpButton;
    ImageView googleSignUpImageView;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    public void signUpUser(final String email, String password, final String userType)
    {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    userID = auth.getCurrentUser().getEmail();
                    Toast.makeText(SignUpActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                    DocumentReference reference = firestore.collection("Users").document(userID);
                    HashMap<String,Object> user = new HashMap<>();
                    user.put("User Type", userType);
                    user.put("Email ID", email);

                    reference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Log.i("Sign Up","Successful for" + userID);
                        }
                    });

                    switch (userType)
                    {
                        case "Student":
                            startActivity(new Intent(SignUpActivity.this, StudentUserActivity.class));
                            finish();
                            break;
                        case "Warden":
                            startActivity(new Intent(SignUpActivity.this, WardenUserActivity.class));
                            finish();
                            break;
                        case "Security Official":
                            startActivity(new Intent(SignUpActivity.this, SecurityUserActivity.class));
                            finish();
                            break;
                    }
                }

                else
                {
                    Toast.makeText(SignUpActivity.this, "Unsuccessful! Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usersListSpinner = (Spinner)findViewById(R.id.usersListSpinner);
        signUpEmailEditText = (EditText)findViewById(R.id.signUpEmailEditText);
        signUpPasswordEditText = (EditText)findViewById(R.id.signUpPasswordEditText);
        signUpButton = (Button)findViewById(R.id.signUpButton);
        googleSignUpImageView = (ImageView)findViewById(R.id.googleSignUpImageView);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.users, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        usersListSpinner.setAdapter(adapter);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                signUpMethod = usersListSpinner.getSelectedItem().toString();
                signUpEmail = signUpEmailEditText.getText().toString();
                signUpPassword = signUpPasswordEditText.getText().toString();

                if(TextUtils.isEmpty(signUpEmail) || TextUtils.isEmpty(signUpPassword))
                {
                    Toast.makeText(SignUpActivity.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                }

                else if(signUpPassword.length() < 8)
                {
                    Toast.makeText(SignUpActivity.this, "Password should be of at least 8 characters!", Toast.LENGTH_SHORT);
                }

                else
                {
                    signUpUser(signUpEmail, signUpPassword, signUpMethod);
                }
            }
        });
    }
}