package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.*;

public class Day12Subterranean extends AocDay{
    public int itter = 5000;
    public HashSet<String> rules = new HashSet<>();
   /// public String input = "#..#.#..##......###...###...........";
    public String input = "##.#..########..##..#..##.....##..###.####.###.##.###...###.##..#.##...#.#.#...###..###.###.#.#";
    int zero=0;
    @Override
    public void runDay() {
        System.out.println(input);


            int sum = getSum();
            System.out.println(itter +" " +sum);


    }


    private int getSum() {
        for (int i = 0; i < itter; i++) {

            String current = "....." + input + "...";

            List<String> items = this.getDataFromFile("2018/DayTwelve_data.txt");
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

            input = next;
        }
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '#'){
                sum += i-zero;
            }

        }
        return sum;
    }
}
