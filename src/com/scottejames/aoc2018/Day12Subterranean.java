package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.*;

public class Day12Subterranean extends AocDay{
    public int itter = 20;
    public HashSet<String> rules = new HashSet<>();
    public String input = "#..#.#..##......###...###...........";
    //public String input = "##.#..########..##..#..##.....##..###.####.###.##.###...###.##..#.##...#.#.#...###..###.###.#.#";
    int zero=0;
    @Override
    public void runDay() {
        System.out.println(input);


        for (int i = 0; i < itter; i++) {

            String current = "....." + input + "...";

            List<String> items = this.getDataFromFile("2018/DayTwelve_test.txt");
            for (String item : items) {
                String[] split = item.split(" ");
                if (split[2].startsWith("#")) {
                    rules.add(split[0]);
                }
            }
            zero+=3;
            String next = "";
            for (int x = 2; x < current.length() - 2; x++) {
                String testString = current.substring(x - 2, x + 3);
                if (rules.contains(testString))
                    next += "#";
                else
                    next += ".";

            }
            // remove redundant
            //next = next.replaceAll("^\\.+","");

            System.out.println(next);
            input = next;
        }
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '#'){
                sum += i-zero;
            }

        }
        System.out.println("Part  1: " + sum);

    }
}
