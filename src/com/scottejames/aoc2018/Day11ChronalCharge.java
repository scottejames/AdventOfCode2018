package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.HashMap;

public class Day11ChronalCharge  extends AocDay{
    public static int input = 8444;

    @Override
    public void runDay() {
        int maxValue = 0;
        int maxX = 0;
        int maxY = 0;
        int maxSize =0;
        int [][]powerLevels = new int[301][301];

        for (int x = 1 ; x <= 300; x++) {
            for (int y = 1; y <= 300; y++) {
                powerLevels[x][y] = evaluate(x,y);
            }
        }
            for (int size = 1; size <= 300; size++) {
                System.out.println("size : " + size);{
                for (int x = 1 ; x <= 300 - size ; x++) {
                    for (int y = 1; y <= 300 - size; y++) {
                        int value = evaluateGrid(x, y, size, powerLevels);
                        if (value > maxValue) {
                            maxValue = value;
                            maxX = x;
                            maxY = y;
                            maxSize = size;
                        }
                    }
                }
            }
        }


        System.out.println(" Result = (MaxValue = " +maxValue + ") " + maxX + ", " + maxY + ", " + maxSize);
    }
    public int evaluateGrid(int x, int y,int size, int [][] powerLevels){

        int result = 0;
        for (int i = 0; i < size -1; i++)
            for (int j = 0; j < size-1; j++){
                result+=powerLevels[x+i][y+j];
            }
        return result;
    }

    public int evaluate(int x, int y){

        int rackId = x + 10;
        Integer powerLevel = rackId * y;
        powerLevel += input;
        powerLevel *= rackId;

        String digits = powerLevel + "";
        if (digits.length()> 2){
            powerLevel = Integer.parseInt(digits.charAt(digits.length() - 3) + "");
        } else {
            powerLevel = 0;

        }
        powerLevel -= 5;
        return powerLevel;

    }
}
