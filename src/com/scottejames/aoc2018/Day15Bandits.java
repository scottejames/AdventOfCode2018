package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.Coord;
import com.scottejames.util.GridImage;
import com.scottejames.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**


 #########
 #G..G..G#
 #.......#
 #.......#
 #G..E..G#
 #.......#
 #.......#
 #G..G..G#
 #########

 */
public class Day15Bandits extends AocDay {

    // WARNING THIS COULD BE WRONG!
    private static final Comparator<Unit> UNIT_READING_ORDER = Comparator
            .comparingInt((Unit unit) -> unit.position.getY())
            .thenComparingInt(unit -> unit.position.getX());


    private static final int ELF_ATTACK_POWER = 3;
    private Set<Coord> occupiedPoints;
    private List<Unit> elves;
    private List<Unit> goblins;
    private int rounds = 0;
    private char[][] cave;

    @Override
    public void runDay() {
        List<String> items = this.getDataFromFile("2018/DayFifteen_test_1.txt");

        parseInput(items);

        Dijkstra dijkstra = new Dijkstra(cave, occupiedPoints, new Coord(1,2));
        LinkedList<Coord> path = dijkstra.getShortestPath(new Coord(5,5));


        printCave(path);
     //   generateGIF();
     //   animateGIF();
    }

    private void parseInput(List<String> items) {
        int width = items.stream().mapToInt(String::length).max().getAsInt();
        int height = items.size();

        elves = new ArrayList<>();
        goblins = new ArrayList<>();
        occupiedPoints = new HashSet<>();


        cave = new char[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (items.get(y).charAt(x) == '#') {
                    cave[x][y] = '#';
                } else {
                    cave[x][y] = '.';
                }
            }
        }
        Coord location;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (items.get(y).charAt(x)) {
                    case 'E':
                        location = new Coord(x, y);
                        occupiedPoints.add(location);

                        Unit elf = new Unit(location, ELF_ATTACK_POWER);
                        elves.add(elf);
                        break;
                    case 'G':
                        location = new Coord(x, y);
                        occupiedPoints.add(location);
                        Unit goblin = new Unit(location);
                        goblins.add(goblin);
                        break;
                }

            }
        }
    }

    private int getOutcome() {
        final int totalHp = Stream.of(elves, goblins).
                flatMap(Collection::stream).mapToInt(unit -> unit.hitPoints).sum();
        return rounds * totalHp;
    }
    private boolean combatOver() {
        return elves.isEmpty() || goblins.isEmpty();
    }
    private void printCave(LinkedList l) {
        Set<Coord> path = new HashSet<>(l);
        for (int y = 0; y < cave.length; y++) {
            for (int x = 0; x < cave[y].length; x++) {
                if (path.contains(new Coord(x,y))){
                    System.out.print("$");

                } else {
                    System.out.print(cave[x][y]);
                }
            }
            System.out.println("");
        }
    }

    private ArrayList<BufferedImage> frames = new ArrayList<>();

    private void generateGIF() {
        GridImage g = new GridImage(20, Color.BLACK);

        for (int x = cave.length-1; x >=0 ; x--) {
            for (int y = 0; y < cave[x].length; y++) {
                g.add(new Coord(x, y), this.pickColour(x, y));


            }
        }
        frames.add(g.toImage());

    }

    private void animateGIF() {
        GridImage.Companion.animate(frames, "/Users/scottejames/test.gif", 500);

    }

    private Color pickColour(int x, int y) {
        if (elfLocation(x,y))
            return Color.GREEN;
        else if (goblinLocation(x,y))
            return Color.RED;
        else if (cave[x][y] == '#') {
            return Color.GRAY;
        } else {
            return Color.WHITE;
        }
    }
    private boolean elfLocation(int x, int y){
        for (Unit elf: elves){
            if (elf.position.getX() == x && elf.position.getY() == y){
                return true;
            }
        }
        return false;
    }
    private boolean goblinLocation(int x, int y){
        for (Unit goblin: goblins){
            if (goblin.position.getX() == x && goblin.position.getY() == y){
                return true;
            }
        }
        return false;
    }
}
class Unit{
    Coord position;
    int hitPoints = 200;
    int attackPower;

    public Unit(Coord location){
        this.position = location;
    }
    public Unit(Coord location, int elfAttackPower) {
        this.position = location;
        this.attackPower = elfAttackPower;

    }
}

class Dijkstra {
    private static final Comparator<Coord> POINT_READING_ORDER = Comparator
            .comparingInt((Coord point) -> point.getY())
            .thenComparingInt(point -> point.getX());
    private final char[][] cave;
    private final Set<Coord> occupiedPoints;
    private final Map<Coord, List<Coord>> cachedAdjacentPoints;
    private final Map<Coord, Integer> distanceTo = new HashMap<>();
    private final Map<Coord, Coord> edgeTo = new HashMap<>();

    Dijkstra(){
        this.cave = null;
        this.occupiedPoints = null;
        this.cachedAdjacentPoints = null;
    }
    Dijkstra( final char[][] cave,  final Set<Coord> occupiedPoints, Coord startingPoint) {
        this.cave = cave;
        this.occupiedPoints = occupiedPoints;
        this.cachedAdjacentPoints = new HashMap<>();

        HashSet<Coord> visited = new HashSet<>();
        Stack<Coord> stack = new Stack<>();

        stack.push(startingPoint);

        while (!stack.isEmpty()) {
            final Coord p = stack.pop();
            if (visited.add(p)) {
                distanceTo.put(p, Integer.MAX_VALUE);
                for (final Coord adjacent : getAdjacentPoints(p)) {
                    stack.push(adjacent);
                }
            }
        }
        distanceTo.put(startingPoint, 0);


    }
    boolean unreachable(Coord point) {
        return !distanceTo.containsKey(point);
    }

    LinkedList<Coord> getShortestPath( Coord point) {
        LinkedList<Coord> path = new LinkedList<>();

        if (unreachable(point)) {
            return path;
        }
        for (Coord p = point; p != null; p = edgeTo.get(p)) {
            path.addFirst(p);
        }
        return path;
    }
    private boolean validateLocation(Coord c){
        int x = c.getX();
        int y = c.getY();
        boolean valid = true;
        if ((x < 0) || (y < 0)) {
            valid = false;
        } else if ((x > cave.length) || (y > cave[0].length)) {
            valid = false;
        } else if (cave[x][y] == '#') {
            valid = false;
        } else if (occupiedPoints.contains(c)) {
            valid = false;
        }

        return valid;
    }
    private List<Coord> getAdjacentPoints(Coord point){

        if (!cachedAdjacentPoints.containsKey(point)) {  // Not seen this before
            List adjacentPoints = new ArrayList<Coord>(4);

            // Test if the point to the left is reachable
            Coord left = new Coord(point.getX() - 1, point.getY());
            Coord right = new Coord(point.getX() + 1, point.getY());
            Coord up = new Coord(point.getX(), point.getY() - 1);
            Coord down = new Coord(point.getX() , point.getY() + 1);

            if (validateLocation(left)) {
                adjacentPoints.add(left);
            }
            if (validateLocation(right)) {
                adjacentPoints.add(right);
            }
            if (validateLocation(up)) {
                adjacentPoints.add(up);
            }
            if (validateLocation(down)) {
                adjacentPoints.add(down);
            }

            cachedAdjacentPoints.put(point, adjacentPoints);
        }
        return cachedAdjacentPoints.get(point);
    }

}