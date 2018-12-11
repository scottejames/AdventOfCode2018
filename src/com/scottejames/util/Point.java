package com.scottejames.util;

public class Point {
    IntPair origin;
    IntPair velocity;

    public Point(IntPair o){
        this.origin = o;
        this.velocity = null;
    }
    public Point(IntPair o, IntPair v){
        this.origin = o;
        this.velocity = v;
    }
    public IntPair getOrigin(){
        return origin;

    }

    public IntPair getVelocity(){
        return velocity;
    }

    public void move(){
        origin.add(velocity);
    }

    public void goEast(){
        origin.setX(origin.getX()+1);
    }
    public void goWest(){
        origin.setX(origin.getX()-1);
    }
    public void goNorth() {
        origin.setY(origin.getY()+1);
    }
    public void goSouth() {
        origin.setY(origin.getY()-1);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (origin != null ? !origin.equals(point.origin) : point.origin != null) return false;
        return velocity != null ? velocity.equals(point.velocity) : point.velocity == null;
    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (velocity != null ? velocity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "origin=" + origin +
                ", velocity=" + velocity +
                '}';
    }
}
