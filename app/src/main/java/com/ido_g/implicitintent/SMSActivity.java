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

public class SMSActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendSmsButton;
    private Button btnSMSReturn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smsactivity);

        // Initialize UI elements
        messageEditText = findViewById(R.id.messageEditText);
        sendSmsButton = findViewById(R.id.sendSmsButton);
        btnSMSReturn = findViewById(R.id.btnSMSReturn);

        // Set listener for the send SMS button
        sendSmsButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString().trim();
            if (!message.isEmpty()) {
                openSmsApp(message);
            }
        });

        btnSMSReturn.setOnClickListener(new View.OnClickListener() {
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

    private void openSmsApp(String message) {
        // Create an implicit intent to open the SMS app with the message pre-filled
        Uri uri = Uri.parse("smsto:"); // No recipient pre-filled
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
        smsIntent.putExtra("sms_body", message); // Pre-fill the SMS body
        startActivity(smsIntent);
    }
}
