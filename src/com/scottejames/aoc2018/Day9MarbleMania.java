package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.CircularBuffer;
import com.scottejames.util.Pair;

public class Day9MarbleMania extends AocDay{

    @Override
    public void runDay() {
        String solData = "468 players; last marble is worth 71010 points";
        String testOneData = "13 players; last marble is worth 7999 points";
        long result = runSimulation(solData);
        System.out.println("Results : " + result);


    }

    public long runSimulation(String testData){
        CircularBuffer<Integer> circularBuffer = new CircularBuffer<>();
        circularBuffer.addFirst(0);
        Pair<Integer,Integer> parsedData = parseData(testData);
        int players = parsedData.getL();
        int end = parsedData.getR()* 100;
        long[] scores = new long[players];

        for (int i = 1 ; i< end;i++){
            if (i % 23 == 0) {
                circularBuffer.rotate(-7);
                scores[i % players] += i + circularBuffer.pop();

            } else {
                circularBuffer.rotate(2);
                circularBuffer.addLast(i);
            }
        }
        long maxScore = 0;
        for(long score: scores){
            if (score > maxScore)
                maxScore = score;
        }
        return maxScore;
    }

    public Pair<Integer,Integer> parseData(String data){
        Integer playerNumber = Integer.parseInt(data.split(" ")[0]);
        Integer points = Integer.parseInt(data.split(" ")[6]);
        Pair <Integer,Integer> result = new Pair();
        result.setL(playerNumber);
        result.setR(points);

        System.out.println(result);
        return result;

    }

}
