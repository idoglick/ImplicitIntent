package com.ido_g.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WazeActivity extends AppCompatActivity {
    private EditText editAddress;
    private Button btnNavigateWaze;
    private Button btnNavigateGoogleMaps;
    private Button btnReturnToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waze);

        editAddress = findViewById(R.id.editAddress);
        btnNavigateWaze = findViewById(R.id.btnNavigateWaze);
        btnNavigateGoogleMaps = findViewById(R.id.btnNavigateGoogleMaps);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // Navigate using Waze
        btnNavigateWaze.setOnClickListener(v -> {
            String address = editAddress.getText().toString().trim();
            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Uri wazeUri = Uri.parse("https://waze.com/ul?q=" + Uri.encode(address));
                Intent wazeIntent = new Intent(Intent.ACTION_VIEW, wazeUri);
                wazeIntent.setPackage("com.waze");
                startActivity(wazeIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Waze is not installed. Opening in browser.", Toast.LENGTH_SHORT).show();
                Uri wazeUri = Uri.parse("https://waze.com/ul?q=" + Uri.encode(address));
                startActivity(new Intent(Intent.ACTION_VIEW, wazeUri));
            }
        });

        // Navigate using Google Maps
        btnNavigateGoogleMaps.setOnClickListener(v -> {
            String address = editAddress.getText().toString().trim();
            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri mapsUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapsIntent = new Intent(Intent.ACTION_VIEW, mapsUri);
            mapsIntent.setPackage("com.google.android.apps.maps");
            try {
                startActivity(mapsIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Google Maps is not installed.", Toast.LENGTH_SHORT).show();
            }
        });

        // Return to Main Activity
        btnReturnToMain.setOnClickListener(v -> finish());
    }
}
