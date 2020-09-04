package com.tanay.thundercipher.leaveapplications;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class WardenListActivity extends AppCompatActivity {

    RecyclerView wardenListRecyclerView;
    RecyclerView.LayoutManager manager;
    WardenRecyclerAdapter wardenRecyclerAdapter;
    ActionBar actionBar;

    int[] wardenImages = {R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    String[] wardenNames = {"Prof. Pankaj Srivastava", "Dr. Vishal Vyas", "Dr. Pinku Ranjan", "Dr. KK Pattanaik", "Dr. Arun Kumar", "Prof. Manisha Pattanaik"};
    String[] designations = {"Chief Warden", "Warden", "Asst. Warden", "Warden", "Warden", "Warden"};
    String[] phoneNumbers = {"+91-751-2449814", "+91-751-2449750", "+91-0751-2449741", "+91-751-2449626", "+91-751-2449739", "+91-751-2449812"};
    String[] emailIds = {"pankajs@iiitm.ac.in", "vishal@iiitm.ac.in", "pinkuranjan@iiitm.ac.in", "kkpatnaik@iiitm.ac.in", "arunkumar@iiitm.ac.in", "manishapattanaik@iiitm.ac.in"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_list);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Application Authorizer");

        wardenListRecyclerView = (RecyclerView)findViewById(R.id.wardenListRecyclerView);
        manager = new LinearLayoutManager(this);

        wardenListRecyclerView.setLayoutManager(manager);
        //wardenListRecyclerView.setHasFixedSize(true);

        wardenRecyclerAdapter = new WardenRecyclerAdapter(wardenImages, wardenNames, designations, phoneNumbers, emailIds);
        wardenListRecyclerView.setAdapter(wardenRecyclerAdapter);
    }
}