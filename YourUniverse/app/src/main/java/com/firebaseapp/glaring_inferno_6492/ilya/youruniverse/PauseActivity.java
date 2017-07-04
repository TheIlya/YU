package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class PauseActivity extends AppCompatActivity {
    Button menuButton, resumeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        menuButton = (Button) findViewById(R.id.menuButton);
        resumeButton = (Button) findViewById(R.id.resumeButton);

        menuButton.setOnClickListener(new ButtonOnClickListener(new Intent(this, MainActivity.class), this, false));
        resumeButton.setOnClickListener(new ButtonOnClickListener(new Intent(this, GameActivity.class), this, true));
    }
}
