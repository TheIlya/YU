package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    Button pauseButton;
    UniverseView universeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pauseButton = (Button) findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new ButtonOnClickListener(new Intent(this, PauseActivity.class), this, false));
    }
}
