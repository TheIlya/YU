package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[]{(Button) findViewById(R.id.button), (Button) findViewById(R.id.button2), (Button) findViewById(R.id.button3)};

        for (int i = 0; i < buttons.length; i++) {
            Intent intent = null;
            boolean moveForward = false;
            switch (i) {
                case 0:
                    intent = new Intent(this, GameActivity.class);
                    moveForward = true;
                    break;
                case 1:
                    intent = new Intent(this, SettingsActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, AboutActivity.class);
                    break;
            }
            buttons[i].setOnClickListener(new ButtonOnClickListener(intent, this, moveForward));
        }
    }
}
