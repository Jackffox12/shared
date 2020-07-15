package org.firstinspires.ftc.teamcode.shared;

public class Point{

    public double x;
    public double y;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public double distanceFromOrgin(){
            return Math.pow(x*x+y*y, 0.5);

    }

    public String getQuadrant() {
        if (x > 0 && y > 0) {
            return "Quadrant 1";
        } else if (x<0 && y>0){
            return "Quadrant 2";
        } else if (x<0 && y<0){
            return "Quadrant 3";
        } else if (x>0 && y<0){
            return "Quadrant 4";
        } else if (x==0 || y==0){
            return "Axes";
        }
        else {
            return null;
        }
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}