package com.example.thegamefirt1302vtp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private Button StrartGameAgian;
    private TextView ScoreEnd,lblName;
    private String score,strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score = getIntent().getExtras().get("score").toString();
        strName = getIntent().getStringExtra("namePlayer");

        StrartGameAgian = (Button) findViewById(R.id.play_agian_vtp);
        lblName = findViewById(R.id.namePlayer);
        ScoreEnd = ( TextView) findViewById(R.id.vtpabc);
        StrartGameAgian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
        lblName.setText(strName);
        ScoreEnd.setText( "score = " + score );
    }
}