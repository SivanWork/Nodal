package com.example.ranad.nodalsystems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ranad.nodalsystems.model.Login;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        openScreen();
    }

    protected void openScreen() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Intent intent;
                        /*intent = new Intent(Splash.this, LoginActivity.class);
                        startActivity(intent);
                        finish();*/
                           /* if (Login.getInstance(Splash.this) == null) {
                                intent = new Intent(Splash.this, LoginActivity.class);
                            } else {
                                if (Login.getInstance(Splash.this).getAuthToken() == null) {
                                    intent = new Intent(Splash.this, LoginActivity.class);
                                } else {*/
                                    intent = new Intent(Splash.this, MainActivity.class);
                                //}
                            //}
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000);
    }
}
