package com.scottejames.aco2015;

import com.scottejames.util.AocDay;

import java.util.List;

public class Day2NoMath extends AocDay{
    @Override
    public void runDay() {
        List<String> items = this.getDataFromFile("2015/DayTwo_data.txt");
        int curr = 0;
        int ribbon = 0;

        for (String item : items){
            String [] dim = item.split("x");
            int x = Integer.parseInt(dim[0]);
            int y = Integer.parseInt(dim[1]);
            int z = Integer.parseInt(dim[2]);

            int sideOne = x*y;
            int sideTwo = x*z;
            int sideThree = y*z;

            int smallestSideArea = Math.min(Math.min(sideOne,sideTwo),sideThree);
            int minSide = Math.min(Math.min(x,y),z);
            int maxSide = Math.max(Math.max(x,y),z);
            int midSide = x+y+z-minSide-maxSide;

            int ribonLength = minSide *2 + midSide *2;
            int bowLength = x*y*z;
            ribbon += ribonLength + bowLength;
            curr += 2*sideOne + 2* sideTwo + 2*sideThree + smallestSideArea;

        }
        System.out.println("Area : "+ curr);
        System.out.println("Ribbon : " + ribbon);
    }
}
