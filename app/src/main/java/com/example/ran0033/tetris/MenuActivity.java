package com.example.ran0033.tetris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button play;
    private static Intent tetris;
    public static Intent menu;
    public static Intent gameOver;
    public static Intent stats;
    public static Intent nick;

    public static DBHelper dbStats;

    public static String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tetris = new Intent(getBaseContext(), MainActivity.class);
        menu = new Intent(this, this.getClass());
        gameOver = new Intent(getBaseContext(), GameOverActivity.class);
        stats = new Intent(getBaseContext(), StatsActivity.class);
        nick = new Intent(getBaseContext(), NicknameActivity.class);

        dbStats = new DBHelper(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Gets the user's network preference settings
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("tetrisPrefs", getApplicationContext().MODE_PRIVATE);

        // Retrieves a string value for the preferences. The second parameter
        // is the default value to use if a preference value is not found.
        nickname = sharedPref.getString("nick", "");
    }

    public void btnPlayOnClick(View v){
        if(nickname == ""){
            Toast.makeText(getApplicationContext(), "Set nickname first!", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(tetris);
        }
    }

    public void btnStatsOnClick(View v){
        startActivity(stats);
    }

    public void btnSetNickname(View v){
        startActivity(nick);
    }
}
