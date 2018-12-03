package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.FileHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day2Inventory extends AocDay {
    @Override
    public void runDay() {
        String fileName = "2018/DayTwo_data.txt";
        FileHelper fh = new FileHelper(fileName);
        List<String> items = fh.getFileAsList();

       partTwo(items);
    }

    private void partOne(List<String> items){
        int cTwo = 0;
        int cThree = 0;
        for (String s : items) {
            if (countLetters(2,s))
                cTwo++;
            if (countLetters(3,s))
                cThree++;
        }
        System.out.println(" 2 : " + cTwo + " 3 : " + cThree + " CP : " + cTwo * cThree);

    }

    private void partTwo(List<String> items){
        List<String> secondList = new ArrayList<>();
        secondList.addAll(items);

        for(String l: items)
            for (String r: items){
                if (!(l.equals(r))) {
                    int count = countDifferences(l, r);
                    if (count == l.length() - 1 )
                     System.out.println("Differences between " + l + " and " + r + " are " + count);
                }
            }

    }
    private boolean countLetters(int count,String s) {
//        System.out.println("String : "  + s);
        HashMap<Character, Integer> letterCount = new HashMap<>();
        for (char a : s.toCharArray()) {
            int c = letterCount.containsKey(a) ? letterCount.get(a) +1 : 1;
            letterCount.put(a, c);
        }
        boolean results = false;
        for (char a : letterCount.keySet()) {
//            System.out.println("letter: " + a + "   count :  " + letterCount.get(a));
            if (letterCount.get(a) == count)
                results = true;
        }
        return results;
    }

    private int countDifferences(String l, String r){
        int count = 0;
        for (int i = 0; i < l.length();i++){
            if (l.charAt(i) == r.charAt(i))
                count++;
        }
        return count;
    }
}
