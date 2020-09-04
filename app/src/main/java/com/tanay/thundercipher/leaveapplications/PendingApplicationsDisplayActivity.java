package com.tanay.thundercipher.leaveapplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PendingApplicationsDisplayActivity extends AppCompatActivity {

    TextView tellNameTextView, tellRollNumberTextView, tellPlaceTextView, tellFromDateTextView, tellToDateTextView, tellPurposeTextView;
    Button approveButton, declineButton;
    String name = "", roll= "", fromDate= "", toDate = "", place = "", purpose = "", studentID = "", mode = "";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_applications_display);

        approveButton = (Button)findViewById(R.id.approveButton);
        declineButton = (Button)findViewById(R.id.declineButton);
        tellNameTextView  = (TextView)findViewById(R.id.tellNameTextView);
        tellRollNumberTextView = (TextView)findViewById(R.id.tellRollNumberTextView);
        tellFromDateTextView = (TextView)findViewById(R.id.tellFromDateTextView);
        tellToDateTextView = (TextView)findViewById(R.id.tellToDateTextView);
        tellPlaceTextView = (TextView)findViewById(R.id.tellPlaceTextView);
        tellPurposeTextView = (TextView)findViewById(R.id.tellPurposeTextView);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        studentID = i.getStringExtra("Student ID");
        mode = i.getStringExtra("Review Mode");

        //write code to fetch info from the studentID and display it in the textViews

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //write code to approve the application and notify the student about it (i.e. change the value of the bool variable to true)
                if(mode.equals("Warden"))
                {
                    //code
                }

                else if(mode.equals("Security"))
                {
                    //code
                }
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //write code to decline the application and notify the student about it (i.e. don't change the value of the bool variable)
                if(mode.equals("Warden"))
                {
                    //code
                }

                else if(mode.equals("Security"))
                {
                    //code
                }
            }
        });
    }
}