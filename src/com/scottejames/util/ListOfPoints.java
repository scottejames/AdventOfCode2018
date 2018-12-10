package com.scottejames.util;

import java.util.ArrayList;
import java.util.List;

public class ListOfPoints {
    private List<Point> listOfPoints = new ArrayList<>();

    int maxX = 0;
    int minX = Integer.MAX_VALUE;
    int maxY = 0;
    int minY = Integer.MAX_VALUE;

    public void calculateBounds() {

         maxX = 0;
         minX = Integer.MAX_VALUE;
         maxY = 0;
         minY = Integer.MAX_VALUE;
        for (Point point : listOfPoints) {
            IntPair origin = point.getOrigin();
            if (origin.getX() > maxX)
                maxX = origin.getX();
            if (origin.getX() < minX)
                minX = origin.getX();

            if (origin.getY() > maxY)
                maxY = origin.getY();
            if (origin.getY() < minY)
                minY = origin.getY();
        }
    }
    public void move(){
        for (Point point:listOfPoints){
            point.move();
        }
    }
    public void add(Point point){
        listOfPoints.add(point);
    }
    public boolean countains(IntPair o){
        boolean result = false;
        for (Point point: listOfPoints){
            if (point.getOrigin().
                    equals(o)){
                result = true;
            }
        }
        return result;
    }
    public int getRows(){
        return listOfPoints.size();
    }
    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }
}
