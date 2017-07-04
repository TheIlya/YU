package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backButton = (Button) findViewById(R.id.backFromAbout);
        backButton.setOnClickListener(new ButtonOnClickListener(new Intent(this, MainActivity.class), this, true));
    }
}
