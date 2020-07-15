package org.firstinspires.ftc.teamcode.shared;

public class LineSegment {
    private Point point1;
    private Point point2;

    public LineSegment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point[] subDivide(int subSegments) {
        Point[] pointsExpected = new Point[subSegments - 1];
        double point1x = point1.getX();
        double point1y = point1.getY();
        double point2x = point2.getX();
        double point2y = point2.getY();
        double counter = 1;
        double xDist = point2x - point1x;
        double yDist = point2y - point1y;
        for (int i = 0; i < subSegments-1; i++) {
            double xpoint = (point1x + (counter / subSegments) * xDist);
            double ypoint = (point1y + (counter / subSegments) * yDist);

            pointsExpected[i]=new Point(xpoint,ypoint);
            counter++;
        }
        return pointsExpected;
    }

    public Point interpolate(double distanceFromFirstPoint) {
        double point1x = point1.getX();
        double point1y = point1.getY();
        double point2x = point2.getX();
        double point2y = point2.getY();

        double slope = (point2y - point1y) / (point2x - point1x);

        double newDistance = Math.sqrt(Math.pow(point1x - point2x, 2) + Math.pow(point1y - point2y, 2));


        double xDist = point1.getX()+(point2.getX() - point1.getX())*(distanceFromFirstPoint/newDistance);
        double yDist = point1.getY()+(point2.getY() - point1.getY())*(distanceFromFirstPoint/newDistance);

        Point finalDistance = new Point(xDist, yDist);


        return finalDistance;
    }
















}
