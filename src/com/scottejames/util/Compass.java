package com.scottejames.util;

public enum Compass {
    N(0, -1), NE(1, -1), E(1, 0), SE(1, 1), S(0, 1), SW(-1, 1), W(-1, 0), NW(-1, -1);
    int dx;
    int dy;
    Compass(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public static boolean rangeCheck(int x, int y, int size){
        if ((x<0) || (x>=size) || (y<0) || (y>=size)){
            return false;
        } else {
            return true;
        }
    }
}