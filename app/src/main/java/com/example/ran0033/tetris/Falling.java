package com.example.ran0033.tetris;

import java.util.Random;

/**
 * Created by ranosjak on 11/26/2017.
 */

public class Falling {
    private Point pos = new Point(0, 0);
    private Point oldPos = new Point(0, 0);
    private Shape shape;
    private Shape nextShape;
    private Point[] oldPts;
    private Shape[] shapes = new Shape[7];
    private int[][] mtx;
    private boolean canMove;

    public boolean CanMove(){
        return canMove;
    }

    public Falling(int[][] matrix){
        mtx = matrix;

        shapes[0] = new Shape(
                new Point(1, 0),
                new Point(1, 1),
                new Point(0, 0),
                new Point(0, 1),
                -1,
                4
        );

        shapes[1] = new Shape(
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3),
                4,
                2
        );

        shapes[2] = new Shape(
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(2, 1),
                3,
                4
        );

        shapes[3] = new Shape(
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                3,
                4
        );

        shapes[4] = new Shape(
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(0, 2),
                3,
                4
        );

        shapes[5] = new Shape(
                new Point(1, 0),
                new Point(1, 1),
                new Point(0, 1),
                new Point(0, 2),
                3,
                4
        );

        shapes[5] = new Shape(
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 2),
                3,
                4
        );
        generateNext();
        shape = nextShape;
        generateNext();
        oldPts = shape.Get().clone();
    }

    private void generateNext(){
        Random r = new Random();
        nextShape = shapes[r.nextInt(6)];
        //nextShape = shapes[0];
        nextShape.generateColor();
    }

    public int Fall(){
        int ret = 0;
        if(shape == null){
            ret = 2;
            shape = nextShape;
            pos.x = -1;
            pos.y = shape.GetStartY();
            oldPos = new Point(pos.x, pos.y);

            if(!canMove(new Point(pos.x + 1, pos.y), shape)){
                return 1;
            }
            generateNext();
        }

        canMove = true;
        Point[] pts = shape.Get();

        clear(oldPos, oldPts);

        canMove = canMove(new Point(pos.x + 1, pos.y), shape);

        if(canMove){
            pos.x++;
            oldPts = shape.Get().clone();

        }

        draw(pos, pts, shape);

        if(!canMove){
            shape = null;
        }

        oldPos = new Point(pos.x, pos.y);

        return ret;
    }

    public void Rotate(){
        if(shape == null){
            return;
        }

        clear(pos, oldPts);

        shape.Rotate();

        boolean canRotate = canMove(new Point(pos.x + 1, pos.y), shape);

        if(!canRotate){
            shape.RotateBack();
        }

        draw(pos, oldPts, shape);
    }

    public void moveRight(){
        if(shape == null){
            return;
        }

        int newPos = pos.y + 1;

        clear(pos, oldPts);

        boolean canMove = canMove(new Point(pos.x, newPos), shape);

        draw(pos, oldPts, shape);

        if(canMove){
            pos.y = newPos;
        }
    }

    public void moveLeft(){
        if(shape == null){
            return;
        }

        int newPos = pos.y - 1;

        clear(pos, oldPts);

        boolean canMove = canMove(new Point(pos.x, newPos), shape);

        draw(pos, oldPts, shape);

        if(canMove){
            pos.y = newPos;
        }
    }

    private boolean canMove(Point newPos, Shape sh){
        boolean canMove = true;
        for(int i = 0; i < 4; i++) {
            Point p = sh.Get()[i];
            if(newPos.x + p.x > mtx.length - 1 || p.y + newPos.y < 0 || p.y + newPos.y > mtx[0].length - 1 || mtx[newPos.x + p.x][newPos.y + p.y] != 0){
                canMove = false;
                break;
            }
        }

        return canMove;
    }

    private void clear(Point position, Point[] points){
        for(int i = 0; i < 4; i++) {
            Point p = points[i];
            if(position.x + p.x >= 0) {
                int x = position.x + p.x;
                int y = position.y + p.y;
                mtx[x][y] = 0;
            }
        }
    }

    private void draw(Point position, Point[] points, Shape sh){
        for(int i = 0; i < 4; i++) {
            Point p = oldPts[i];
            if(position.x + p.x >= 0) {
                mtx[position.x + p.x][position.y + p.y] = sh.getColor();
            }
        }
    }
}
