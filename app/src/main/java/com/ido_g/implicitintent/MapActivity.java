package com.ido_g.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    private EditText editLatitude;
    private EditText editLongitude;
    private EditText editAddress;
    private Button btnOpenMap;
    private Button btnPinAddress;
    private Button btnReturnToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        editLatitude = findViewById(R.id.editLatitude);
        editLongitude = findViewById(R.id.editLongitude);
        editAddress = findViewById(R.id.editAddress);
        btnOpenMap = findViewById(R.id.btnOpenMap);
        btnPinAddress = findViewById(R.id.btnPinAddress);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // Open map with coordinates
        btnOpenMap.setOnClickListener(v -> {
            String latitude = editLatitude.getText().toString().trim();
            String longitude = editLongitude.getText().toString().trim();

            if (latitude.isEmpty() || longitude.isEmpty()) {
                Toast.makeText(this, "Please enter both latitude and longitude", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double lat = Double.parseDouble(latitude);
                double lon = Double.parseDouble(longitude);

                Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon + "?q=" + lat + "," + lon);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid coordinates. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            }
        });

        // Open map with address
        btnPinAddress.setOnClickListener(v -> {
            String address = editAddress.getText().toString().trim();

            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        // Return to main activity
        btnReturnToMain.setOnClickListener(v -> {
            finish();
        });
    }
}
