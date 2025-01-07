package com.ido_g.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class YoutubeActivity extends AppCompatActivity {
    private EditText editVideoTitle;
    private Button btnSearchYoutube;
    private Button btnReturnToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        editVideoTitle = findViewById(R.id.editVideoTitle);
        btnSearchYoutube = findViewById(R.id.btnSearchYoutube);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // Search YouTube for the entered video title
        btnSearchYoutube.setOnClickListener(v -> {
            String videoTitle = editVideoTitle.getText().toString().trim();

            if (videoTitle.isEmpty()) {
                Toast.makeText(this, "Please enter a video title", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a YouTube search intent
            Uri youtubeSearchUri = Uri.parse("https://www.youtube.com/results?search_query=" + videoTitle);
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, youtubeSearchUri);
            youtubeIntent.setPackage("com.google.android.youtube"); // Launch YouTube app if installed

            try {
                startActivity(youtubeIntent);
            } catch (Exception e) {
                Toast.makeText(this, "YouTube app is not installed. Opening in browser.", Toast.LENGTH_SHORT).show();
                youtubeIntent.setPackage(null); // Launch in browser if YouTube app is unavailable
                startActivity(youtubeIntent);
            }
        });

        // Return to Main Activity
        btnReturnToMain.setOnClickListener(v -> finish());
    }
}
