package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class StudentUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home :
            {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }

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
        setContentView(R.layout.activity_student_user);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        drawerLayout = (DrawerLayout)findViewById(R.id.studentDrawerLayout);
        navigationView = (NavigationView)findViewById(R.id.studentNavigationView);

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