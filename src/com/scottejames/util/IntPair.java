package com.scottejames.util;

public class IntPair {
    private int x;
    private int y;

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

    public IntPair(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public void add(IntPair a){
//        System.out.println("Add: " + this.x + " plus " + a.x);
//        System.out.println("Add: " + this.y + " plus " + a.y);

        this.x+=a.x;
        this.y+=a.y;
//        System.out.println("Is: " + this.x );
//        System.out.println("Is: " + this.y );

    }

    @Override
    public String toString() {
        return "IntPair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntPair intPair = (IntPair) o;

        if (x != intPair.x) return false;
        return y == intPair.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public IntPair(String s) {
        String [] parts = s.split(",");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1].replace(" ",""));
    }
}
