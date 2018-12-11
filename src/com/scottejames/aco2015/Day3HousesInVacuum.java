package com.scottejames.aco2015;

import com.scottejames.util.AocDay;
import com.scottejames.util.IntPair;
import com.scottejames.util.Point;

import java.util.HashMap;

public class Day3HousesInVacuum extends AocDay {
    @Override
    public void runDay() {
        String testData = "^>v<";
        String data = this.getDataFromFileAsString("2015/DayThree_data.txt");

        Point point = new Point(new IntPair(0,0));
        HashMap<Point, Integer> map = new HashMap<>();
        map.put(point,1);
        for(char c:data.toCharArray()){
            switch (c){
                case '^':
                    point.goNorth();
                    break;
                case 'v':
                    point.goSouth();
                    break;
                case '<':
                    point.goWest();
                    break;
                case '>':
                    point.goEast();
                    break;
                default:
                    System.err.println("Invalid data " + c);
                    break;
            }
            if (map.get(point)==null){
                map.put(point,1);
            } else {
                map.put(point, map.get(point) + 1);
            }
        }

        System.out.println("Key ; " + map.keySet().size());
    }
}
