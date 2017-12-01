package com.example.ran0033.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.*;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by kru13 on 12.10.16.
 */
public class Tetris extends View{

    Context context;

    Bitmap[] bmp;

    int lx = 16;
    int ly = 10;

    int width;
    int height;

    boolean isTouch = false;
    public int matrix[][] = new int[lx][ly];

    private Timer timer;
    private boolean t;
    private Falling falling;

    private Point touchStart;

    private boolean up, down, right, left;

    private int score, level, div;

    public Tetris(Context context) {
        super(context);
        init(context);
    }

    public Tetris(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Tetris(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    final Handler myHandler = new Handler();
    final Runnable updateText = new Runnable() {
        public void run() {
            MainActivity ma = (MainActivity)context;
            ma.setLevel(level);
            ma.setScore(score);
        }
    };

    void init(final Context context) {
        this.context = context;
        bmp = new Bitmap[6];
        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.boxok);

        falling = new Falling(matrix);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!down && div++ < 10){
                    return;
                }
                t = true;
                boolean scor = false;
                int lines = 0;
                for(int i = 0; i < lx; i++){
                    boolean filled = true;
                    for(int j = 0; j < ly; j++){
                        filled = matrix[i][j] == 0 ? false : filled;
                    }
                    if(filled && !falling.CanMove()){
                        matrix[i] = new int[ly];
                        lines++;
                        for(int ii = i; ii > 0; ii--){
                            for(int jj = 0; jj < ly; jj++){
                                matrix[ii][jj] = matrix[ii - 1][jj];
                            }
                        }
                        scor = true;
                    }
                }
                if(!scor) {
                    if(up){
                        falling.Rotate();
                        up = false;
                    }

                    if(left){
                        falling.moveLeft();
                        left = false;
                    }

                    if(right){
                        falling.moveRight();
                        right = false;
                    }

                    int state = falling.Fall();

                    if(state == 1){
                        for(int i = 0; i < lx; i++){
                            for(int j = 0; j < ly; j++){
                                matrix[i][j] = 0;
                            }
                        }
                    } else if (state == 2) {
                        down = false;
                    }
                }else{
                    score += lines == 1 ? 40 * level + 40 : 0;
                    score += lines == 2 ? 100 * level + 100 : 0;
                    score += lines == 3 ? 300 * level + 300 : 0;
                    score += lines == 4 ? 1200 * level + 1200 : 0;
                    level++;
                    myHandler.post(updateText);
                }
                //invalidate();
                postInvalidate();
                t = false;
                div = 0;
            }
        }, 0, 50);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                int color = matrix[i][j];
                //level[i*10 + j]
                //canvas.drawBitmap(bmp[1], null, new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
                Paint myPaint = new Paint();
                Rect r = new Rect(j*width, i*height,(j+1)*width, (i+1)*height);
                // fill
                myPaint.setStyle(Paint.Style.FILL);
                if(color != 0) {
                    myPaint.setColor(color);
                }else{
                    myPaint.setColor(Color.LTGRAY);
                }
                canvas.drawRect(r, myPaint);

                // border
                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setColor(Color.BLACK);
                canvas.drawRect(r, myPaint);

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();
        int Y = (int) event.getY();
        int action  = event.getAction();

        int totalWidth = width*10;
        int totalHeight = height*10;

        switch (action ) {
            case MotionEvent.ACTION_DOWN:
                if(!isTouch)
                {
                    touchStart = new Point(X, Y);
                    isTouch = true;
                }
                break;

            case MotionEvent.ACTION_UP:
                isTouch = false;

                int[] val = new int[4];
                val[0] = touchStart.y - Y; //up
                val[1] = Y -touchStart.y; //down
                val[2] = X - touchStart.x; //right
                val[3] = touchStart.x - X; //left

                int max = 0;
                int dir = -1;
                int min = 100;

                for(int i = 0; i < 4; i++){
                    if(val[i] > max && val[i] >= min){
                        max = val[i];
                        dir = i;
                    }
                }

                switch(dir){
                    case 0:
                        Log.d("dir", "up");
                        up = true;
                        break;
                    case 1:
                        Log.d("dir", "down");
                        down = true;
                        break;
                    case 2:
                        Log.d("dir", "right");
                        right = true;
                        break;
                    case 3:
                        Log.d("dir", "left");
                        left = true;
                        break;
                }

                break;
        }
        //Log.d("myInfo",X + " " + Y + " ... " + width + " " + height);
        return true;
    }

}
