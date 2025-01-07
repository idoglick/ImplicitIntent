package com.ido_g.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhoneActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private Button dialButton;
    private Button btnPhoneReturn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone);

        // Initialize UI elements
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        dialButton = findViewById(R.id.dialButton);
        btnPhoneReturn = findViewById(R.id.btnPhoneReturn);

        // Set listener for the dial button
        dialButton.setOnClickListener(v -> {
            String phoneNumber = phoneNumberEditText.getText().toString().trim();
            if (!phoneNumber.isEmpty()) {
                openDialer(phoneNumber);
            }
        });

        btnPhoneReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Handle window insets (system bar padding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openDialer(String phoneNumber) {
        // Create an implicit intent to open the dialer with the phone number
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(dialIntent);
    }
}
