package com.ido_g.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClockActivity extends AppCompatActivity {
    private EditText editHour;
    private EditText editMinute;
    private Button btnSetAlarm;
    private Button btnReturnToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        editHour = findViewById(R.id.editHour);
        editMinute = findViewById(R.id.editMinute);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // Set Alarm Button
        btnSetAlarm.setOnClickListener(v -> {
            String hourText = editHour.getText().toString().trim();
            String minuteText = editMinute.getText().toString().trim();

            if (hourText.isEmpty() || minuteText.isEmpty()) {
                Toast.makeText(this, "Please enter both hour and minute", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int hour = Integer.parseInt(hourText);
                int minute = Integer.parseInt(minuteText);

                if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                    Toast.makeText(this, "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_HOUR, hour)
                        .putExtra(AlarmClock.EXTRA_MINUTES, minute)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No Clock app available to set the alarm", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input. Please enter numbers only.", Toast.LENGTH_SHORT).show();
            }
        });

        // Return to Main Activity Button
        btnReturnToMain.setOnClickListener(v -> finish());
    }
}
