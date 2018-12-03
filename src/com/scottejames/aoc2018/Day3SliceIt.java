package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.FileHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3SliceIt extends AocDay {
    /***
     *  #1 @ 1,3: 4x4
     #2 @ 3,1: 4x4
     #3 @ 5,5: 2x2
     */
    @Override

    public void runDay() {

        int[][] size = new int[1000][1000];

        FileHelper fh = new FileHelper("2018/DayThree_data.txt");
        List<String> items = fh.getFileAsList();
        for (String item : items) {
            String[] claim = item.replace(":", "").split(" ");
            int id = Integer.parseInt(claim[0].replace("#", ""));
            String[] coord = claim[2].split(",");
            String[] mapping = claim[3].split("x");
            for (int x = 0; x < Integer.parseInt(mapping[0]); x++) {
                for (int y = 0; y < Integer.parseInt(mapping[1]); y++) {
                    int coordX = Integer.parseInt(coord[0]) + x;
                    int coordY = Integer.parseInt(coord[1]) + y;

                    size[coordX][coordY]++;
                }
            }
        }
        int claimed = 0;
        int unclaimed = 0;
        int contested = 0;

        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                if (size[x][y] == 0) {
                    unclaimed++;
                } else if (size[x][y] == 1) {
                    claimed++;
                } else {
                    contested++;
                }
            }
        }

        System.out.println("Claimed : " + claimed + " Contested : " + contested + " Unclaimed: " + unclaimed);

        for (String item : items) {
            String[] claim = item.replace(":", "").split(" ");
            int id = Integer.parseInt(claim[0].replace("#", ""));
            String[] coord = claim[2].split(",");
            String[] mapping = claim[3].split("x");
            boolean noOverlaps = true;
            for (int x = 0; x < Integer.parseInt(mapping[0]); x++) {
                for (int y = 0; y < Integer.parseInt(mapping[1]); y++) {
                    int coordX = Integer.parseInt(coord[0]) + x;
                    int coordY = Integer.parseInt(coord[1]) + y;

                    if (size[coordX][coordY] > 1){
                        noOverlaps = false;
                    }

                }
            }
            if (noOverlaps == true){
                System.out.println("Woop " + id + " has no overlaps!");
            }
        }
    }
}