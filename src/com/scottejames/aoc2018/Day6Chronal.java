package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.IntPair;

import java.util.HashMap;
import java.util.List;

public class Day6Chronal extends AocDay{
    @Override
    public void runDay() {
        List<String> items = getDataFromFile("2018/DaySix_data.txt");

        HashMap<Integer, IntPair> points = new HashMap<>();

        int width = 0;
        int height = 0;
        int numberOfPoints = 0;
        for (String item : items) {
            IntPair intPair = new IntPair(item);
            points.put(numberOfPoints, intPair);

            if (intPair.getX() > width)
                width = intPair.getX();
            if (intPair.getY() > height)
                height = intPair.getY();
            numberOfPoints++;
        }
        int[][] grid = new int[width + 1][height + 1];
        HashMap<Integer, Integer> regionSizes = new HashMap<Integer, Integer>();

        for (int x = 0; x <= width; x++)
            for (int y = 0; y <= height; y++) {
                int closest = Integer.MAX_VALUE;
                int bestPointId = -99;
                for (int i = 0; i < numberOfPoints; i++) {
                    IntPair p = points.get(i);
                    int dist = Math.abs(x - p.getX()) + Math.abs(y - p.getY());
                    if (dist < closest) {
                        closest = dist;
                        bestPointId = i;
                    } else if (closest == dist) {
                        bestPointId = -1;
                    }

                }
                grid[x][y] = bestPointId;
                Integer regionSize = regionSizes.get(bestPointId);
                if (regionSize == null)
                    regionSize = new Integer(1);
                else
                    regionSize++;
                regionSizes.put(bestPointId, regionSize);
            }
        for (int x = 0; x <= width; x++) {
            int infRegionPoint = grid[x][0];
            regionSizes.remove(infRegionPoint);
            infRegionPoint = grid[x][height];
            regionSizes.remove(infRegionPoint);
        }
        for (int y = 0; y <= height; y++) {
            int infRegionPoint = grid[0][y];
            regionSizes.remove(infRegionPoint);
            infRegionPoint = grid[width][y];
            regionSizes.remove(infRegionPoint);
        }
        int largestRegion = 0;
        for (int size : regionSizes.values()) {
            if (size > largestRegion) {
                largestRegion = size;
            }
        }

        System.out.println("Largest Region: " + largestRegion);

        for (int x = 0; x <= width ; x++) {
            for (int y = 0; y <= height; y++) {
                System.out.print(" " + grid[x][y] + " ");

            }
            System.out.println();
        }

        int regionSize = 0;

        for (int x = 0; x <= width; x++) {
            for (int y = 0; y <= height; y++) {

                int size = 0;
                for (int i = 0; i < numberOfPoints; i++) {
                    IntPair p = points.get(i);
                    int distance = Math.abs(x - p.getX()) + Math.abs(y - p.getY());
                    size += distance;
                }
//                System.out.println("Distance : " + size);

                if (size < 10000)
                    regionSize++;
            }
        }
        System.out.println("Part 2 : " + regionSize);
    }
}
