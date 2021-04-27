package com.example.thegamefirt1302vtp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private flyingFishView gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;
    public static String lblName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new flyingFishView(this);
        setContentView(gameView);
        getNamePlayer();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();

                    }


                });
            }

        }, 0, Interval);
    }

    private void getNamePlayer() {
        lblName = getIntent().getStringExtra("namePlayer");
    }
}


