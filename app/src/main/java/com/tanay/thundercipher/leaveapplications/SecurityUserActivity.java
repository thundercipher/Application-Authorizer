package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SecurityUserActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuActionLogout :
            {
                /*
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserActivity.this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                */
                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_user);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

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