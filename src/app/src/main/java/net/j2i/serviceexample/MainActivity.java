package net.j2i.serviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean isServiceRunning = false;


    public void onServiceToggleClick(View view) {
        if(isServiceRunning) {

            Intent serviceIntent = new Intent(this, ForegroundService.class);
            stopService(serviceIntent);
        }
        else {
            Intent serviceIntent = new Intent(this, ForegroundService.class);
            serviceIntent.putExtra("param", "some values");
            ContextCompat.startForegroundService(this, serviceIntent);
        }
        isServiceRunning = !isServiceRunning;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnToggleService = findViewById(R.id.btnToggleService);

    }
}