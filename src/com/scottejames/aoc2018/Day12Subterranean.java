package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.*;

/** Part Two
 * 	100	5,636.00
 200	9,120.00	3484
 300	13,620.00	4500
 400	18,120.00	4500
 500	22,620.00	4500
 600	27,120.00	4500
 700	31,620.00	4500
 800	36,120.00	4500
 900	40,620.00	4500
 1000	45,120.00	4500	100 INCR

 5000	50000000000

 INCR DIFF	40	499,999,990.00
 180000	2,249,999,955,000.00
 225,120.00	2,250,000,000,120.00
 */
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
