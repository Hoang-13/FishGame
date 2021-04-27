package com.example.thegamefirt1302vtp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NamePlayer extends AppCompatActivity {
    private EditText edName;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_player);
        init();
        event();
    }

    private void event() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NamePlayer.this,MainActivity.class);
                intent.putExtra("namePlayer",edName.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void init() {
        edName = findViewById(R.id.namePlayer);
        btnPlay = findViewById(R.id.btnPlay);
    }
}




