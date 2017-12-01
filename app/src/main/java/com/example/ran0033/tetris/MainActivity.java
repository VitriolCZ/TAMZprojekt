package com.example.ran0033.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tScore, tLevel;
    private Tetris tetris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tScore = findViewById(R.id.score);
        tLevel = findViewById(R.id.level);
        tetris = findViewById(R.id.tetris);

        setScore(0);
        setLevel(0);
    }

    public void setScore(int score){
        this.tScore.setText(Integer.toString(score));
    }

    public void setLevel(int level){
        this.tLevel.setText(Integer.toString(level));
    }

    @Override
    public void onBackPressed(){
        tetris.pause();
    }

    @Override
    public void onPause(){
        super.onPause();
        tetris.pause();
    }
}
