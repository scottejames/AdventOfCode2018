package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.Compass;
import com.scottejames.util.Coord;
import com.scottejames.util.GridImage;
import com.sun.tools.javac.util.ArrayUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 .#.#...|#.
 .....#|##|
 .|..|...#.
 ..|#.....#
 #.#|||#|#|
 ...#.||...
 .|....|...
 ||...#|.#|
 |.||||..|.
 ...#.|..|.

 .......##.
 ......|###
 .|..|...#.
 ..|#||...#
 ..##||.|#|
 ...#||||..
 ||...|||..
 |||||.||.|
 ||||||||||
 ....||..|.

 .......#..
 ......|#..
 .|.|||....
 ..##|||..#
 ..###|||#|
 ...#|||||.
 |||||||||.
 ||||||||||
 ||||||||||
 .|||||||||

 */

// 410 413 - 497
public class Day17Settlers extends AocDay{
    char[][] grid;
    final int GRID_SIZE = 50;
    final char GROUND = '.';
    final char TREES = '|';
    final char LUMBER = '#';
    int wood;
    int lumberYards;
    private ArrayList<BufferedImage> frames = new ArrayList<>();

    @Override
    public void runDay() {
        int resources = 0;
        ArrayList<Integer> cache = new ArrayList<>();
        parseInput();
        for (int i = 0; i < 494; i++) {
            tick();
            if (i >= 410) {
                resources = wood * lumberYards;

                cache.add(resources);
            }

        }
        System.out.println("Part 2: " +  cache.get((999_999_999- 410)%84));



    }

    public void tick(){
        char [][] temp = new char[GRID_SIZE][GRID_SIZE];
        for (int x =0 ; x < GRID_SIZE; x++)
            for (int y=0; y < GRID_SIZE; y++)
                temp[x][y] = grid[x][y];
        for ( int x =0 ; x < GRID_SIZE; x++)
            for (int y=0; y < GRID_SIZE; y++) {
                List<Character> adj = getAdj(x, y);
                switch (grid[x][y]) {
                    case GROUND:
                        if (adj.stream().filter(c -> c == TREES).count() >= 3) {
                            temp[x][y] = TREES;
                            wood++;
                        }
                        break;
                    case TREES:
                        if (adj.stream().filter(c -> c == LUMBER).count() >= 3) {
                            temp[x][y] = LUMBER;
                            lumberYards++;
                            wood--;
                        }
                        break;
                    case LUMBER:
                        if (!(adj.contains(TREES) && adj.contains(LUMBER))) {
                            temp[x][y] = GROUND;
                            lumberYards--;
                        }

                }
            }
            grid = temp;
    }

    public void printGrid() {
            for (int x = 0; x < GRID_SIZE; x++) {
                for (int y = 0; y < GRID_SIZE; y++) {

                    System.out.print(grid[x][y]);
            }
            System.out.println("");

        }

    }
    public List<Character> getAdj(int x, int y) {
        List<Character> adj = new ArrayList<>();
        for (Compass c: Compass.values()){
            int nx = x + c.getDx();
            int ny = y + c.getDy();
            if (Compass.rangeCheck(nx, ny, GRID_SIZE)) {
                adj.add((char) grid[nx][ny]);
            }
        }
        return adj;
    }


    public void parseInput(){
        grid = new char[GRID_SIZE][GRID_SIZE];

        List<String> items = this.getDataFromFile("2018/DaySeventeen_data.txt");
        for (int y = 0; y < items.size(); y ++)
            for (int x = 0; x < items.get(y).length(); x++){
                grid[y][x] = items.get(y).charAt(x);
                if (grid[y][x] == TREES) wood++;
                if (grid[y][x] == LUMBER) lumberYards++;
            }
    }

    private void generateGIF() {
        GridImage g = new GridImage(20, Color.BLACK);

        for (int x = grid.length-1; x >=0 ; x--) {
            for (int y = 0; y < grid[x].length; y++) {
                g.add(new Coord(x, y), this.pickColour(x, y));
            }
        }
        frames.add(g.toImage());

    }
    private Color pickColour(int x, int y) {
        Color c;
        switch (grid[x][y]) {
            case '|':
                c = Color.GREEN;
                break;
            case '#':
                c = Color.PINK;
                break;
            default:
                c = Color.WHITE;
                break;
        }
        return c;
    }

    private void animateGIF() {
        GridImage.Companion.animate(frames, "/Users/scottejames/day17.gif", 500);

    }

}
