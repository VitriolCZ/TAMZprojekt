package com.example.ran0033.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tScore, tLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tScore = findViewById(R.id.score);
        tLevel = findViewById(R.id.level);

        setScore(0);
        setLevel(0);
    }

    public void setScore(int score){
        this.tScore.setText(Integer.toString(score));
    }

    public void setLevel(int level){
        this.tLevel.setText(Integer.toString(level));
    }


   /* @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                if(!isTouch)
                {
                    for (int i=0; i<game.level.length; i++)
                    {
                        if(game.level[i] == 4)
                        {
                            game.level[i] = game.level[i+1];
                            game.level[i+1] = 4;
                            game.draw(canvas);
                            break;
                        }
                    }
                }
                isTouch = true;
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
        }
        return true;
    }*/
}
