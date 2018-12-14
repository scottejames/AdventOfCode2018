package com.scottejames.util;

public enum Turn {
    LEFT, STRAIGHT, RIGHT;

    public Direction getNewDir(Direction dir) {
        if(this == Turn.STRAIGHT) {
            return dir;
        }else if(this == Turn.LEFT) {
            return dir.turnLeft();
        }else {
            return dir.turnRight();
        }
    }

    public Turn nextTurn() {
        return Turn.values()[(this.ordinal() + 1)% Turn.values().length];
    }
}
