package com.scottejames.util;

import java.sql.Driver;

@SuppressWarnings("Duplicates")
public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    final int dx;
    final int dy;

    Direction(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction getDirectionFromChar(char c){
        Direction dir = null;
        switch(c) {
            case '^':
                dir = Direction.UP;
                break;
            case '>':
                dir = Direction.RIGHT;
                break;
            case 'v':
                dir = Direction.DOWN;
                break;
            case '<':
                dir = Direction.LEFT;
                break;
        }
        return dir;
    }

    public Direction turn(final Turn turn) {
        switch (this) {
            case UP:
                switch (turn) {
                    case LEFT: return LEFT;
                    case STRAIGHT: return UP;
                    case RIGHT: return RIGHT;
                }
            case DOWN:
                switch (turn) {
                    case LEFT: return RIGHT;
                    case STRAIGHT: return DOWN;
                    case RIGHT: return LEFT;
                }
            case LEFT:
                switch (turn) {
                    case LEFT: return DOWN;
                    case STRAIGHT: return LEFT;
                    case RIGHT: return UP;
                }
            case RIGHT:
                switch (turn) {
                    case LEFT: return UP;
                    case STRAIGHT: return RIGHT;
                    case RIGHT: return DOWN;
                }
        }
        throw new RuntimeException();
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}