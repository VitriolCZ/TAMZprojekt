package com.example.ran0033.tetris;


import android.graphics.Paint;

import java.util.Random;

/**
 * Created by ranosjak on 11/27/2017.
 */

public class Shape {
    private Point[] points = new Point[4];
    private int me;
    private int color;
    private int startY;

    public Shape(Point p1, Point p2, Point p3, Point p4, int me, int startY){
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        this.me = me;
        this.startY = startY;
        getColor();
    }

    public Point[] Get(){
        return points;
    }

    public int GetStartY(){
        return startY;
    }

    public void generateColor(){
        Random rand = new Random();
        Paint p = new Paint();
        p.setARGB(255, 100 + rand.nextInt(55), 100 + rand.nextInt(55), 100 + rand.nextInt(55));
        color = p.getColor();
    }

    public int getColor(){
        return color;
    }

    public void Rotate(){
        if(me == -1){
            return;
        }

        Point[] newPoints = new Point[4];

        for(int i = 0; i < 4; i++){
            newPoints[i] = new Point(points[i].y, 1 - (points[i].x - (me - 2)));
        }

        points = newPoints;
    }

    public void RotateBack(){
        if(me == -1){
            return;
        }

        Point[] newPoints = new Point[4];

        for(int i = 0; i < 4; i++){
            newPoints[i] = new Point(1 - (points[i].y - (me - 2)), points[i].x);
        }

        points = newPoints;
    }

}
