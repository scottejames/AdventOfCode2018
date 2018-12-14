package com.scottejames.aoc2018;


import com.scottejames.util.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day13MineCart extends AocDay {
    public char[][] tracks = null;


    @Override
    public void runDay() {
        List<String> items = this.getDataFromFile("2018/DayThirteen_test.txt");
        HashSet<Cart> carts = new HashSet<Cart>();
        tracks = new char[items.size()][FileHelper.getMaxLength(items)];
            for (int i = 0;i < tracks.length; i++)
                for(int j = 0; j < tracks[0].length; j++)
                    tracks[i][j] = ' ';

        // Load track and carts from input data
        for (int y = 0; y < items.size();y ++)
            for (int x = 0; x < items.get(y).length();x++){
                char current = items.get(y).charAt(x);
                tracks[y][x] = current;

                if(current == '^' || current == 'v' || current == '<' || current == '>') {
                    Cart c = new Cart(current, x, y);

                    carts.add(c);

                }
        }
        displayTrack();

        boolean noCollision = true;
        int count = 0;
        while(noCollision) {
            System.out.println("Step "+count+"   carts "+carts.size());
            for(Cart cart: carts){
                cart.move();

            }
        }

    }
    public void displayTrack(){
        for (int y = 0; y < tracks.length;y ++) {
            for (int x = 0; x < tracks[y].length; x++) {
                System.out.print(tracks[y][x]);
            }
            System.out.println("");
        }
    }
    class Cart {
        private Point p;
        private Direction dir;
        private Turn turn;
        private char currTrack;

        public Cart(char c, int x, int y){
            p = new Point(new IntPair(x,y));
            turn = Turn.LEFT;
            dir = Direction.getDirectionFromChar(c);
            currTrack = trackType();
        }
        public String toString() {
            return "Cart at " + p.getOrigin() + " facing " + dir.name() +  " making next turn " + turn.name();
        }
        private Point getPoint(){
            return p;
        }
        private char trackType(){
            if ((dir == Direction.NORTH) || (dir == Direction.SOUTH)){
                return '|';
            } else {
                return '-';
            }
        }
        private char cartType(){
            if(dir == Direction.NORTH){
                return '^';
            } else if (dir == Direction.SOUTH) {
                return 'v';
            } else if (dir == Direction.EAST) {
                return '>';
            } else if (dir == Direction.WEST) {
                return '<';
            }
            return ' ';

        }
        public void setDirection(Direction dir){
            this.dir = dir;
        }
        public void move(){
            tracks[p.getOrigin().getY()][p.getOrigin().getX()] = currTrack;
            Point newLocation = dir.move(p);
                currTrack = tracks[newLocation.getOrigin().getY()][newLocation.getOrigin().getX()];
            tracks[newLocation.getOrigin().getY()][newLocation.getOrigin().getX()] = cartType();

            // do we change direction?
            if(currTrack == '/') {
                if(dir == Direction.EAST || dir == Direction.WEST) {
                    dir = dir.turnLeft();
                }else {
                    dir = dir.turnRight();
                }
            }else if(currTrack == '\\') {
                if(dir == Direction.EAST || dir == Direction.WEST) {
                    dir = dir.turnRight();
                }else {
                    dir = dir.turnLeft();
                }
            }else if(currTrack == '+') {
                dir = turn.getNewDir(dir);
                turn = turn.nextTurn();
            }

        }
    }
}
