package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StudentProfileEditActivity extends AppCompatActivity {

    EditText studentProfileEditNameEditText, studentProfileEditRollNumberEditText, studentProfileEditHostelEditText, studentProfileEditPhoneEditText;
    Button studentProfileEditSaveButton;

    String studentProfileEditName, studentProfileEditRoll, studentProfileEditHostel, studentProfileEditPhone, userID;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_edit);

        studentProfileEditNameEditText = (EditText)findViewById(R.id.studentProfileEditNameEditText);
        studentProfileEditRollNumberEditText = (EditText)findViewById(R.id.studentProfileEditRollNumberEditText);
        studentProfileEditHostelEditText = (EditText)findViewById(R.id.studentProfileEditHostelEditText);
        studentProfileEditPhoneEditText = (EditText)findViewById(R.id.studentProfileEditPhoneEditText);
        studentProfileEditSaveButton = (Button)findViewById(R.id.studentProfileEditSaveButton);
        Intent intent = getIntent();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();

        studentProfileEditSaveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                studentProfileEditName = studentProfileEditNameEditText.getText().toString();
                studentProfileEditRoll = studentProfileEditRollNumberEditText.getText().toString();
                studentProfileEditHostel = studentProfileEditHostelEditText.getText().toString();
                studentProfileEditPhone = studentProfileEditPhoneEditText.getText().toString();

                Map<String, Object> studentData = new HashMap<>();
                studentData.put("Name", studentProfileEditName);
                studentData.put("Roll Number", studentProfileEditRoll);
                studentData.put("Hostel", studentProfileEditHostel);
                studentData.put("Phone Number", studentProfileEditPhone);

                database.getReference().child("Users").child(userID).updateChildren(studentData)

                        .addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid)
                            {
                                Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(), StudentProfileActivity.class);
                                startActivity(i);
                            }
                        })

                        .addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}