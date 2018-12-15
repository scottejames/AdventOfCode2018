package com.scottejames.aoc2018;


import com.scottejames.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day13MineCart extends AocDay {
    public char[][] tracks = null;
    private List<Cart> carts;
    private List<Cart> crashed;


    @Override
    public void runDay() {
        List<String> items = this.getDataFromFile("2018/DayThirteen_data.txt");
        tracks = new char[items.size()][FileHelper.getMaxLength(items)];
            for (int i = 0;i < tracks[0].length; i++)
                for(int j = 0; j < tracks.length; j++)
                    tracks[j][i] = ' ';
        carts = new ArrayList<>();
        crashed = new ArrayList<>();

        // Load track and carts from input data
        for (int y = 0; y < items.size();y ++)
            for (int x = 0; x < items.get(y).length();x++){
                char current = items.get(y).charAt(x);

                if(current == '^' || current == 'v' || current == '<' || current == '>') {
                    Cart c = new Cart(current, x, y);
                    carts.add(c);

                    switch (current) {
                        case '^': current = '|'; break;
                        case 'v': current = '|'; break;
                        case '<': current = '-'; break;
                        case '>': current = '-'; break;
                    }
                }
                tracks[y][x] = current;
            }
//            displayTrack();

        //while (crashed.isEmpty()) {
        while(carts.size() > 1){
            tick();

        }


        System.out.println("There are " + crashed.size() + " crashes");
        //System.out.println("The first crash happened at " + crashed.get(0).getX() + " / " + crashed.get(0).getY());
        System.out.println(("THe final car is at " + carts.get(0).getX() + " / " + carts.get(0).getY()));
    }
    public void tick(){
        // Sort the carts by their movement order woop go me with mystreams!
        final List<Cart> allCarts = carts.stream()
                .sorted(Comparator.comparingInt((Cart cart) -> cart.y).thenComparingInt(cart -> cart.x))
                .collect(Collectors.toList());
        for (final Cart cart : allCarts) {
            if (crashed.contains(cart)) {
                // If we've already crashed, don't move
                continue;
            }
            cart.move();

            for (Cart otherCart:allCarts) {
                if (cart == otherCart || crashed.contains(cart) || crashed.contains(otherCart)) {
                    // thats silly! cant crash into yourself or cars already crashed!
                    continue;
                }
                if (cart.x == otherCart.x && cart.y == otherCart.y) {
                    // Crash!
                    crashed.add(cart);
                    crashed.add(otherCart);
                }
            }
        }
        carts.removeAll(crashed);

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
        private int x;
        private int y;

        private Direction dir;
        private Turn turn;

        public Cart(char c, int x, int y){
            this.x = x;
            this.y = y;
            turn = Turn.LEFT;
            dir = Direction.getDirectionFromChar(c);

        }
        public String toString() {
            return "Cart at " + x + " / " + y + " facing " + dir.name() +  " making next turn " + turn.name();
        }


        private char cartType(){
            if(dir == Direction.UP){
                return '^';
            } else if (dir == Direction.DOWN) {
                return 'v';
            } else if (dir == Direction.RIGHT) {
                return '>';
            } else if (dir == Direction.LEFT) {
                return '<';
            }
            return ' ';

        }
        public void move() {
            x += dir.getDx();
            y += dir.getDy();

            // Turn on corners
            switch (tracks[y][x]){
                case '/':
                case '\\':
                    curve();
                    break;
                case '+':
                    this.dir = dir.turn(this.turn);
                    this.turn = turn.nextTurn();
                    break;
            }
        }
        public void curve(){
            switch (tracks[y][x]){
                case '/':
                    switch (dir) {
                        case UP:
                            dir = Direction.RIGHT;
                            break;
                        case DOWN:
                            dir = Direction.LEFT;
                            break;
                        case LEFT:
                            dir = Direction.DOWN;
                            break;
                        case RIGHT:
                            dir = Direction.UP;
                            break;
                    }
                    break;
                case '\\':
                        switch (dir) {
                            case UP:
                                dir = Direction.LEFT;
                                break;
                            case DOWN:
                                dir = Direction.RIGHT;
                                break;
                            case LEFT:
                                dir = Direction.UP;
                                break;
                            case RIGHT:
                                dir = Direction.DOWN;
                                break;
                        }
                        break;
                    }
            }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    }
