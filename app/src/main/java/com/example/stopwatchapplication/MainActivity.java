package com.example.stopwatchapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton, stopButton, resetButton;

    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Update this reference to your actual XML layout file name
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timer_textview);
        startButton = findViewById(R.id.button_start);
        stopButton = findViewById(R.id.button_stop);
        resetButton = findViewById(R.id.button_reset);

        runTimer(); // Start the timer loop

        // Start button logic
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
            }
        });

        // Stop button logic
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });

        // Reset button logic
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                seconds = 0;
                updateTimerText();
            }
        });
    }

    // Timer logic to update the timer text
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    seconds++;
                    updateTimerText();
                }
                handler.postDelayed(this, 1000); // Update every second
            }
        });
    }

    // Update the timer display
    private void updateTimerText() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timerTextView.setText(time);
    }
}
