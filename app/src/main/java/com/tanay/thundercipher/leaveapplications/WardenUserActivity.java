package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class WardenUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    FirebaseDatabase database;

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
            /*case android.R.id.home :
            {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
             */

            case R.id.menuActionLogout :
            {
                auth.signOut();
                Toast.makeText(getApplicationContext(), "Successfully logged out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WardenUserActivity.this, MainActivity.class));

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
        setContentView(R.layout.activity_warden_user);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        /*ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }


        drawerLayout = (DrawerLayout)findViewById(R.id.wardenDrawerLayout);
        navigationView = (NavigationView)findViewById(R.id.wardenNavigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.navigationProfile:
                    {
                        item.setChecked(true);
                        //add code
                        drawerLayout.closeDrawers();
                        return true;
                    }


                    case R.id.navigationProfilePic:
                    {
                        item.setChecked(true);
                        //add code
                        drawerLayout.closeDrawers();
                        return true;
                    }

                    case R.id.navigationContactInfo:
                    {
                        item.setChecked(true);
                        //add code
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }

                return false;
            }
        });
         */
    }
}