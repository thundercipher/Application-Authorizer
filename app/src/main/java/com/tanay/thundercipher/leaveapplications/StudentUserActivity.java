package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class StudentUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    static TextView fileApplicationFromDateTextView, fileApplicationToDateTextView;
    EditText fileApplicationPlaceEditText, fileApplicationPurposeEditText;
    Button fileApplicationButton, fileApplicationFromDateButton, fileApplicationToDateButton;

    static String fileApplicationFromDate, fileApplicationToDate;
    String fileApplicationName, fileApplicationRoll, userID;
    Application application;

    public void fileApplication(Application app)
    {
        //code to file the application
    }

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
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(StudentUserActivity.this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StudentUserActivity.this, MainActivity.class));

                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public static class DatePickerFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            fileApplicationFromDate = dayOfMonth + "/" + (month+1) + "/" + year;
            fileApplicationFromDateTextView.setText(fileApplicationFromDate);
        }
    }

    public static class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public DatePickerDialog onCreateDialog(Bundle savedInstanceState)
        {
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            fileApplicationToDate = dayOfMonth + "/" + (month+1) + "/" + year;
            fileApplicationToDateTextView.setText(fileApplicationToDate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_user);

        fileApplicationFromDateTextView = (TextView)findViewById(R.id.fileApplicationFromDateTextView);
        fileApplicationToDateTextView = (TextView)findViewById(R.id.fileApplicationToDateTextView);
        fileApplicationPlaceEditText = (EditText)findViewById(R.id.fileApplicationPlaceEditText);
        fileApplicationPurposeEditText = (EditText)findViewById(R.id.fileApplicationPurposeEditText);
        fileApplicationButton = (Button)findViewById(R.id.fileApplicationButton);
        fileApplicationFromDateButton = (Button)findViewById(R.id.fileApplicationFromDateButton);
        fileApplicationToDateButton = (Button)findViewById(R.id.fileApplicationToDateButton);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        //creating application object
        userID = auth.getCurrentUser().getUid();
        reference = database.getReference().child("Users").child(userID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snap : snapshot.getChildren())
                {
                    if(snap.getKey().equals("Name"))
                    {
                        fileApplicationName = snap.getValue().toString();
                    }

                    else if(snap.getKey().equals("Roll Number"))
                    {
                        fileApplicationRoll = snap.getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getApplicationContext(), "Failed to retrieve information!", Toast.LENGTH_SHORT).show();
            }
        });

        fileApplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                application = new Application(fileApplicationName, fileApplicationRoll,
                        fileApplicationFromDateTextView.getText().toString(),
                        fileApplicationToDateTextView.getText().toString(),
                        fileApplicationPlaceEditText.getText().toString(),
                        fileApplicationPurposeEditText.getText().toString(),
                        false, false);

                fileApplication(application);
            }
        });

        fileApplicationFromDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment datePicker = new DatePickerFragment1();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        fileApplicationToDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment datePicker = new DatePickerFragment2();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });


        //toolbar and navigation drawer
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

                        Intent i = new Intent(getApplicationContext(), StudentProfileActivity.class);
                        startActivity(i);

                        drawerLayout.closeDrawers();
                        return true;
                    }


                    case R.id.navigationProfilePic:
                    {
                        item.setChecked(true);

                        Intent i = new Intent(getApplicationContext(), StudentProfileActivity.class);
                        startActivity(i);

                        drawerLayout.closeDrawers();
                        return true;
                    }

                    case R.id.navigationContactInfo:
                    {
                        item.setChecked(true);

                        Intent i = new Intent(getApplicationContext(), WardenListActivity.class);
                        startActivity(i);

                        drawerLayout.closeDrawers();
                        return true;
                    }
                }

                return false;
            }
        });
    }
}