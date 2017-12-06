package com.example.ran0033.tetris;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NicknameActivity extends AppCompatActivity {

    private TextView nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        nick = (TextView) findViewById(R.id.editNickname);

        nick.setText(MenuActivity.nickname);
    }

    public void btnSaveOnClick(View v){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("tetrisPrefs", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("nick", nick.getText().toString());
        editor.apply();

        startActivity(MenuActivity.menu);
    }
}
