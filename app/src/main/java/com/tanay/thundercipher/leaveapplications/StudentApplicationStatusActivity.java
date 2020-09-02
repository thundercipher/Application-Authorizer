package com.tanay.thundercipher.leaveapplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class StudentApplicationStatusActivity extends AppCompatActivity {

    TextView tellNameStudentTextView, tellRollNumberStudentTextView, tellPlaceStudentTextView,
            tellFromDateStudentTextView, tellToDateStudentTextView, tellPurposeStudentTextView, tellStatusStudentTextView;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String userID = "";
    boolean wardenApproval, securityApproval;

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot)
        {
            for(DataSnapshot snap : snapshot.getChildren())
            {
                if(snap.getKey().equals("Name"))
                {
                    tellNameStudentTextView.setText(snap.getValue().toString());
                }

                else if(snap.getKey().equals("Roll Number"))
                {
                    tellRollNumberStudentTextView.setText(snap.getValue().toString());
                }

                else if(snap.getKey().equals("From Date"))
                {
                    tellFromDateStudentTextView.setText(snap.getValue().toString());
                }

                else if(snap.getKey().equals("To Date"))
                {
                    tellToDateStudentTextView.setText(snap.getValue().toString());
                }

                else if(snap.getKey().equals("Place"))
                {
                    tellPlaceStudentTextView.setText(snap.getValue().toString());
                }

                else if(snap.getKey().equals("Purpose"))
                {
                    tellPurposeStudentTextView.setText(snap.getValue().toString());
                }

                else if(snap.getKey().equals("Warden Approval"))
                {
                    if(snap.getValue().toString().equals("true"))
                    {
                        wardenApproval = true;
                    }

                    else
                    {
                        wardenApproval = false;
                    }
                }

                else if(snap.getKey().equals("Security Approval"))
                {
                    if(snap.getValue().toString().equals("true"))
                    {
                        securityApproval = true;
                    }

                    else
                    {
                        securityApproval = false;
                    }
                }
            }

            if(wardenApproval && securityApproval)
            {
                tellStatusStudentTextView.setText("Approved");
            }

            else
            {
                tellStatusStudentTextView.setText("Pending");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_application_status);

        tellNameStudentTextView = (TextView)findViewById(R.id.tellNameStudentTextView);
        tellRollNumberStudentTextView = (TextView)findViewById(R.id.tellRollNumberStudentTextView);
        tellPlaceStudentTextView = (TextView)findViewById(R.id.tellPlaceStudentTextView);
        tellFromDateStudentTextView = (TextView)findViewById(R.id.tellFromDateStudentTextView);
        tellToDateStudentTextView = (TextView)findViewById(R.id.tellToDateStudentTextView);
        tellPurposeStudentTextView = (TextView)findViewById(R.id.tellPurposeStudentTextView);
        tellStatusStudentTextView = (TextView)findViewById(R.id.tellStatusStudentTextView);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        userID = auth.getCurrentUser().getUid();
        reference = database.getReference().child("Users").child(userID).child("Application");
        reference.addListenerForSingleValueEvent(valueEventListener);
    }
}