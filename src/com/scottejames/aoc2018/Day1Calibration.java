package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.FileHelper;

import java.util.HashMap;
import java.util.List;

public class Day1Calibration extends AocDay {

    public void runDay(){
        HashMap<Double,Integer> frequencyCount = new HashMap<>();
        String fileName = "2018/DayOne_alex_data.txt";
        FileHelper fh = new FileHelper(fileName);
        List<String> items = fh.getFileAsList();
        double result = 0;
        while(true) {
            for (String s : items) {
                Double d = Double.parseDouble(s);
                result += d;
                Integer count = frequencyCount.get(result);
                if (count == null) frequencyCount.put(result, 1);
                else {
                    System.out.println("Hit F twice : " + result);
                    System.exit(-1);
                }


            }
        }
        //System.out.println(result);


    }
}
