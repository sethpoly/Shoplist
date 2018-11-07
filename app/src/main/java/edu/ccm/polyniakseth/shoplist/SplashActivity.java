package edu.ccm.polyniakseth.shoplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {                          // Creates timer task and implements run method when timer finishes
            @Override
            public void run()
            {
                finish();           // Finishes splash activity

                startActivity(new Intent(SplashActivity.this, MainActivity.class));         // Starts the main activity class

            }
        };

        // Create timer
        Timer opening = new Timer();
        // Start our "task" after 5000 milliseconds
        opening.schedule(task, 5000);
    }
}
