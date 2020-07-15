package org.firstinspires.ftc.teamcode.shared;

import com.example.pointlibrary.DistanceClass.Distance;

public class Main {
    public static void main(String[] args){
        System.out.println("My name is Jack Fox.");
        Point a = new Point(1,2);
        System.out.println(a.getX());
        System.out.println(a.distanceFromOrgin());
        System.out.println(a.getQuadrant());

        //Line Segment Problem Set
        Point point1 = new Point(0,0);
        Point point2 = new Point(0,0);
        LineSegment lineSegment = new LineSegment(point1,point2);
        Point[] pointsActual = lineSegment.subDivide(5);
        for(int i = 0; i<pointsActual.length; i++){
            System.out.println(pointsActual[i]);
        }

        //Distance Problem Set Line Distance
        Point distancePoint1 = new Point(1,5);
        Point distancePoint2 = new Point(3,4);
        Distance linedistance = new Distance(distancePoint1);
        double finalDistance = linedistance.distanceToPoint(distancePoint2);
        System.out.println(finalDistance);



        //Distance Problem Set Line Distance
        Point[] pointsExpected = new Point[3];
        pointsExpected[0]=new Point(0,1);
        pointsExpected[1]=new Point(10,4);
        pointsExpected[2]=new Point(91,90);
        Point closepoint = linedistance.closestPoint(pointsExpected);
        System.out.println(closepoint);


        //Path Class Problem Set #3

        /**
        Point[] points = new Point[] {new Point(0,0), new Point(3,4),new Point(10,5), new Point(0,5), new Point(1,1) };
        Path path = new Path(points);
        System.out.println(path.totalDistance());

        Point distancePointnew = new Point(9,1);
        Path.WayPoint finalDistance1  = path.setTarget(12, distancePointnew);
        System.out.println(finalDistance1.toString());
        */


        //Interpolate Test

        Point firstPoint1 = new Point(1,5);
        Point firstPoint2 = new Point(3,4);
        LineSegment lineSegment1 = new LineSegment(firstPoint1, firstPoint2);
        System.out.println(lineSegment.interpolate(5.2));




    }
}