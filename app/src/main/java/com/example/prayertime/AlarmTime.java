package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmTime extends AppCompatActivity implements View.OnClickListener {

    private Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_time);
        start=findViewById(R.id.buttonstart);
        stop=findViewById(R.id.buttonstop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }
    public void onClick(View view) {
        if (view == start) {
            startService(new Intent(this, AndriodServices.class));
            start.setEnabled(false);
        } else if (view == stop) {
            stopService(new Intent(this, AndriodServices.class));
            start.setEnabled(true);
        }
    }
}