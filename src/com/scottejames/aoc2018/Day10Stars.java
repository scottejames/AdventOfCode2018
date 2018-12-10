package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.IntPair;
import com.scottejames.util.ListOfPoints;
import com.scottejames.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10Stars extends AocDay{
    // position=< 9,  1> velocity=< 0,  2>

    @Override
    public void runDay() {
        List <String> items = this.getDataFromFile("2018/DayTen_data.txt");
        String patternString = "< *(-?\\d+), *(-?\\d+)> velocity=< *(-?\\d+), *(-?\\d+)>";
        Pattern pattern = Pattern.compile(patternString);
        ListOfPoints listOfPoints = new ListOfPoints();

        for (String item : items){
            Matcher matcher = pattern.matcher(item);

            while (matcher.find()){
                System.out.println(matcher.group(0));
                System.out.println("( " + matcher.group(1) + ", " + matcher.group(2) + ") (" + matcher.group(3) + " , " + matcher.group(4) + ")") ;
                IntPair origin = new IntPair(Integer.parseInt(matcher.group(1).replace(" ","")),Integer.parseInt(matcher.group(2).replace(" ","")));
                IntPair vector = new IntPair(Integer.parseInt(matcher.group(3).replace(" ","")),Integer.parseInt(matcher.group(4).replace(" ","")));
                Point point = new Point(origin,vector);
                listOfPoints.add(point);
            }
        }

       // printGrid(listOfPoints);

        boolean stop = false;
        int minSize = Integer.MAX_VALUE;
        int count = 0;
        while (!stop) {

            listOfPoints.calculateBounds();
            count++;
            int minX = listOfPoints.getMinX();
            int maxX = listOfPoints.getMaxX();
            int minY = listOfPoints.getMinY();
            int maxY = listOfPoints.getMaxY();
//            printGrid(listOfPoints);
            int rows = maxY - minY;


            if (rows ==9 ){
                System.out.println(count);
                printGrid(listOfPoints);
                stop = true;
            }
            listOfPoints.move();

        }
        System.out.println("Count " + count);

    }

    public void printGrid(ListOfPoints listOfPoints){

        listOfPoints.calculateBounds();
        int minX = listOfPoints.getMinX();
        int maxX = listOfPoints.getMaxX();
        int minY = listOfPoints.getMinY();
        int maxY = listOfPoints.getMaxY();
        System.out.println("Range X: " + minX + " / " + maxX + " Y: " + minY + " / " + maxY);
        StringBuffer row = null;
        for (int y=minY;y<=maxY;y++) {
            row = new StringBuffer();

            for (int x = minX; x <= maxX; x++) {
                IntPair p = new IntPair(x, y);
                if (listOfPoints.countains(p))
                    row.append("#");
                else
                    row.append(" ");
            }
            System.out.println(row);
        }

    }
}
