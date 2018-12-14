package com.scottejames.util;

import java.sql.Driver;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction turnRight() {
        return Direction.values()[(this.ordinal() + 1 )% Direction.values().length];
    }

    public Direction turnLeft() {
        return Direction.values()[(this.ordinal() + 3 )% Direction.values().length];
    }

    public Point move(Point p) {
        switch(this) {
            case NORTH:
                p.goNorth();
                break;
            case SOUTH:
                p.goSouth();
                break;
            case EAST:
                p.goEast();
                break;
            case WEST:
                p.goWest();
                break;
        }
        return p;
    }
    public static Direction getDirectionFromChar(char c){
        Direction dir = null;
        switch(c) {
            case '^':
                dir = Direction.NORTH;
                break;
            case '>':
                dir = Direction.EAST;
                break;
            case 'v':
                dir = Direction.SOUTH;
                break;
            case '<':
                dir = Direction.WEST;
                break;
        }
        return dir;
    }

    public char getDirectionChar() {
        switch(this) {
            case NORTH:
                return '^';
            case SOUTH:
                return 'v';
            case EAST:
                return '>';
            case WEST:
                return '<';
        }
        return '#';//should never be hit
    }
}