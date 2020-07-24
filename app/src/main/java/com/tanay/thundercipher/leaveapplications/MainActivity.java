package com.tanay.thundercipher.leaveapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView websiteImageView;

    private void clickedButton(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        try
        {
            startActivity(Intent.createChooser(intent, "Open With:"));
        }

        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Couldn't open the link!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Application Authorizer");

        websiteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                clickedButton("https://www.iiitm.ac.in/index.php/en/");
            }
        });
    }
}
