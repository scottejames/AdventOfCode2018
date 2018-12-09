package com.scottejames.util;

import java.util.ArrayDeque;

public class CircularBuffer <T> extends ArrayDeque<T> {
    public void rotate(int steps){
        if (steps == 0) return;
        if (steps > 0) {
            for (int i = 0; i < steps; i++) {
                T t = this.removeLast();
                this.addFirst(t);
            }
        } else {
            for (int i = 0; i < Math.abs(steps) - 1; i++) {
                T t = this.remove();
                this.addLast(t);
            }
        }

    }

}
