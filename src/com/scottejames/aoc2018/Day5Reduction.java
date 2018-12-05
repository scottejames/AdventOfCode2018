package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.List;

public class Day5Reduction extends AocDay{
    @Override
    public void runDay() {
        String item = getDataFromFile("2018/DayFive_data.txt").get(0);
        String[] letterPairs = {"aA","bB","cC","dD","eE","fF","gG","hH","iI","jJ","kK","lL","mM","nN","oO","pP","qQ","rR","sS","tT","uU","vV","wW","xX","yY","zZ"};
        System.out.println("input " + item.length());
        for(String pair: letterPairs){
            String testString = item.replace(""+ pair.charAt(0),"");
            testString = testString.replace(""+pair.charAt(1),"");

            testString = reactString(testString,letterPairs);
            System.out.println("Testing " + pair.charAt(0) + " reaected to  " + testString.length());


        }
        String result = reactString(item, letterPairs);
        System.out.println("result " + result.length());
    }

    private String reactString(String item, String[] letterPairs) {
        int l = item.length();
        boolean loop = true;
        while (loop == true) {
            for (String pair: letterPairs) {
                 item = item.replace(pair,"");
                 item = item.replace(reverse(pair),"");

            }
            if (item.length() == l){
                loop = false;
            }
            l = item.length();
        }
        return item;
    }
}
