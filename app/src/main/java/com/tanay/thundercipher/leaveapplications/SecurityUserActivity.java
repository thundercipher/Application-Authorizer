package com.tanay.thundercipher.leaveapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecurityUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_user);

         /*

         logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserActivity.this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserActivity.this, MainActivity.class));
            }
        });

        */
    }
}