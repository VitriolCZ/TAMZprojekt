package com.example.ran0033.tetris;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Bundle extras = getIntent().getExtras();

        ((TextView) findViewById(R.id.textLevel)).setText(String.valueOf(extras.get("level")));
        ((TextView) findViewById(R.id.textScore)).setText(String.valueOf(extras.get("score")));

        MenuActivity.dbStats.insertScore(MenuActivity.nickname, extras.getInt("level"), extras.getInt("score"));
    }

    public void goMenuOnClick(View v){
        startActivity(MenuActivity.menu);
    }

    public void goStatsOnClick(View v){
        startActivity(MenuActivity.stats);
    }
}
