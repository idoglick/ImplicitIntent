package com.ido_g.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WSActivity extends AppCompatActivity {
    private EditText editPhoneNumber;
    private Button btnSendWithNumber;
    private Button btnSendWithoutNumber;
    private Button btnCheckWhatsApp;
    private Button btnReturnToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wsactivity);

        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        btnSendWithNumber = findViewById(R.id.btnSendWithNumber);
        btnSendWithoutNumber = findViewById(R.id.btnSendWithoutNumber);
        btnCheckWhatsApp = findViewById(R.id.btnCheckWhatsApp);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // Send message with phone number
        btnSendWithNumber.setOnClickListener(v -> {
            String phoneNumber = editPhoneNumber.getText().toString().trim();

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri uri = Uri.parse("https://wa.me/" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.whatsapp");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
            }
        });

        // Send message without phone number
        btnSendWithoutNumber.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.whatsapp");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
            }
        });

        // Redirect to Google Play if WhatsApp is not installed
        btnCheckWhatsApp.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp"));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Unable to open Google Play", Toast.LENGTH_SHORT).show();
            }
        });

        // Return to Main Activity
        btnReturnToMain.setOnClickListener(v -> {
            finish();
        });
    }
}
