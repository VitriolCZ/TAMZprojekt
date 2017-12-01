package com.example.ran0033.tetris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button play;
    private static Intent tetris;
    public static Intent menu;
    public static Intent gameOver;
    public static Intent stats;

    public static DBHelper dbStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tetris = new Intent(getBaseContext(), MainActivity.class);
        menu = new Intent(this, this.getClass());
        gameOver = new Intent(getBaseContext(), GameOverActivity.class);
        stats = new Intent(getBaseContext(), StatsActivity.class);

        dbStats = new DBHelper(this);
    }

    public void btnPlayOnClick(View v){
        startActivity(tetris);
    }
}
