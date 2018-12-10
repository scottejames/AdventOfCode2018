package com.scottejames.aco2015;

import com.scottejames.util.AocDay;

import java.util.stream.Stream;

public class Day1Lispy extends AocDay {

    @Override
    public void runDay() {
        String testData = ")())())";
        String data = this.getDataFromFileAsString("2015/Day1_data.txt");
        int floor = 0;
        boolean first = false;

        for (int i = 0; i< data.toCharArray().length;i++){
            if (data.charAt(i) == '('){
                floor++;
            }
            if (data.charAt(i) == ')'){
                floor--;
            }
            if ((floor < 0) && (first == false)){
                first = true;
                System.out.println("Entered basement at position " + i);
            }
        }
        System.out.println("Final Floor " + floor);

    }
}
