package com.scottejames.util;

public class Point {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }
    public Point(String s) {
        String [] parts = s.split(",");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1].replace(" ",""));
    }
}
