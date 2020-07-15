
package org.firstinspires.ftc.teamcode.shared;
import com.example.pointlibrary.DistanceClass.Distance;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

public class Path {


    private ArrayList<WayPoint> wayPoints;
    private double totalDistanceFinal = 0;
    public Path(Point[] rawPoints) {
        wayPoints = new ArrayList<>();
        double firstDistEnd = 0; double distStart = 0; double distEnd = 0;
        int counter = 2;
        for(int i = 1; i<rawPoints.length; i++){
            firstDistEnd += Math.sqrt(Math.pow(rawPoints[i].getX() - rawPoints[i - 1].getX(), 2) + Math.pow(rawPoints[i].getY() - rawPoints[i - 1].getY(), 2));
        }
        wayPoints.add(new WayPoint(rawPoints[0], 0, 0, 1,  0, firstDistEnd));
        for (int i = 1; i < rawPoints.length; i++) {
            double distanceFromPrevious = Math.sqrt(Math.pow(rawPoints[i].getX() - rawPoints[i - 1].getX(), 2) + Math.pow(rawPoints[i].getY() - rawPoints[i - 1].getY(), 2));
                if (distanceFromPrevious>0.00000000001){
                    distStart = 0;
                    distEnd = 0;
                    for(int a = counter; a<rawPoints.length; a++){
                        distStart +=  Math.sqrt(Math.pow(rawPoints[a].getX() - rawPoints[a - 1].getX(), 2) + Math.pow(rawPoints[a].getY() - rawPoints[a - 1].getY(), 2));
                    }
                    for(int b = counter-1; b>0; b--){
                        distEnd +=  Math.sqrt(Math.pow(rawPoints[b].getX() - rawPoints[b - 1].getX(), 2) + Math.pow(rawPoints[b].getY() - rawPoints[b - 1].getY(), 2));
                    }
                    wayPoints.add(new WayPoint(rawPoints[i], rawPoints[i].getX() - rawPoints[i-1].getX(), rawPoints[i].getY() - rawPoints[i-1].getY(), distanceFromPrevious, distStart, distEnd));
                    counter++;
                }

            }

        }

        public ArrayList<WayPoint> getWayPoints () {
            return wayPoints;
        }

        public double totalDistance () {
            double totalDistance1 = 0;
            for (int i = 1; i < wayPoints.size(); i++) {
                    double distance = Math.sqrt(Math.pow(wayPoints.get(i).point.getX() - wayPoints.get(i - 1).point.getX(), 2) + Math.pow(wayPoints.get(i).point.getY() - wayPoints.get(i -1).point.getY(), 2));
                    totalDistance1 += distance;
                }
            return totalDistance1;
        }
    /**
     * @return a point at the supplied distance along the path from the supplied current position
     * Note that the point will usually be interpolated between the points that originally defined the Path
     */
    public WayPoint targetPoint(Point current, double distance) {
        int counter = 0;
        double currentDistance = 0.0; double newDist = 0;
        double distanceAlongPath = 0.0;
        for(int i = 0; i<wayPoints.size(); i++) {
            WayPoint wp = wayPoints.get(i);
            distanceAlongPath = wp.componentAlongPath(current);
            counter++;
            if (distanceAlongPath>0.0){
                for (int a = counter; a < wayPoints.size(); a++) {
                    newDist += Math.sqrt(Math.pow(wayPoints.get(a).point.getX() - wayPoints.get(a - 1).point.getX(), 2) + Math.pow(wayPoints.get(a).point.getY() - wayPoints.get(a - 1).point.getY(), 2));
                }
                currentDistance = distanceAlongPath;
                counter--;
                break;
            }
        }
        double lineSegmentDistance = 0.0;
        for(int i = counter+1; i<wayPoints.size(); i++) {
            lineSegmentDistance = Math.sqrt(Math.pow(wayPoints.get(i).point.getX() - wayPoints.get(i - 1).point.getX(), 2) + Math.pow(wayPoints.get(i).point.getY() - wayPoints.get(i - 1).point.getY(), 2));
            if(lineSegmentDistance<currentDistance+lineSegmentDistance){
                break;
            }
            else{
                currentDistance += lineSegmentDistance;
                counter++;
            }
        }
        double trueDistance = (distance - currentDistance);
        double lastDist = Math.sqrt(Math.pow(wayPoints.get(0).point.getX() - wayPoints.get(wayPoints.size()-1).point.getX(), 2) + Math.pow(current.getY() - wayPoints.get(0).point.getY(), 2));
        double trueLastDist = distanceAlongPath + newDist;
        if (distance>=trueLastDist){
            double distStart = 0;
            double distEnd = 0;
            for(int a = counter; a<wayPoints.size()-1; a++){
                distStart +=  Math.sqrt(Math.pow(wayPoints.get(a).point.getX() - wayPoints.get(a - 1).point.getX(), 2) + Math.pow(wayPoints.get(a).point.getY() - wayPoints.get(a - 1).point.getY(), 2));
            }
            return new WayPoint(wayPoints.get(wayPoints.size()-1).point, wayPoints.get(wayPoints.size()-1).point.getX(), wayPoints.get(wayPoints.size()-1).point.getY(), trueDistance, distStart, 0);
        }
        else if(distance<=distanceAlongPath){
            double distStart = 0;
            double distEnd = 0;
            for(int b = counter-1; b>0; b--){
                distEnd +=  Math.sqrt(Math.pow(wayPoints.get(b).point.getX() - wayPoints.get(b-1).point.getX(), 2) + Math.pow(wayPoints.get(b).point.getY() - wayPoints.get(b-1).point.getY(), 2));
            }
            return new WayPoint(wayPoints.get(0).point, wayPoints.get(0).point.getX(), wayPoints.get(0).point.getY(), trueDistance, 0,distEnd);
        }
        else {
            double distStart = 0;
            double distEnd = 0;
            for (int a = counter; a < wayPoints.size() - 1; a++) {
                distStart += Math.sqrt(Math.pow(wayPoints.get(a).point.getX() - wayPoints.get(a - 1).point.getX(), 2) + Math.pow(wayPoints.get(a).point.getY() - wayPoints.get(a - 1).point.getY(), 2));
            }
            for (int b = counter - 1; b > 0; b--) {
                distEnd += Math.sqrt(Math.pow(wayPoints.get(b).point.getX() - wayPoints.get(b - 1).point.getX(), 2) + Math.pow(wayPoints.get(b).point.getY() - wayPoints.get(b - 1).point.getY(), 2));
            }
            LineSegment finalInterpolate = new LineSegment(wayPoints.get(counter).point, wayPoints.get(counter + 1).point);
            Point trueInterpolate = finalInterpolate.interpolate(trueDistance);
            return new WayPoint(trueInterpolate, trueInterpolate.getX(), trueInterpolate.getY(), trueDistance, distStart, distEnd);
        }
    }

    public static class WayPoint {

        public Point point;
        private double deltaXFromPrevious;
        private double deltaYFromPrevious;
        private double distanceFromPrevious;
        private double distanceFromStart;
        private double distanceFromEnd;

        private WayPoint(Point point, double deltaXFromPrevious, double deltaYFromPrevious, double distanceFromPrevious, double distanceFromStart, double distanceFromEnd) {
            this.point = point;
            this.deltaXFromPrevious = deltaXFromPrevious;
            this.deltaYFromPrevious = deltaYFromPrevious;
            this.distanceFromPrevious = distanceFromPrevious;
            this.distanceFromStart = distanceFromStart;
            this.distanceFromEnd = distanceFromEnd;


        }

        /**
         * Calculates the projection in the direction of the path from supplied current point
         *
         * @param current  The source point
         * @return The dot product between vectors normalized by the length of the path segment leading to this WayPoint
         */
        private double componentAlongPath(Point current) {
            double deltaXFromCurrent = point.x - current.x;
            double deltaYFromCurrent = point.y - current.y;

            double dp = deltaXFromCurrent * deltaXFromPrevious + deltaYFromCurrent * deltaYFromPrevious;
            return dp / distanceFromPrevious;
        }

    }

}









