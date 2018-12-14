package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

public class Day14ChocCharts extends AocDay{
    @Override
    public void runDay() {
        int tgt = 598701;

        String str = makeRecipies(30_000_000);
        System.out.println(str.substring(tgt,tgt+10));
        System.out.println(str.indexOf(tgt + ""));
    }
    // 9 : 5158916779
    // 5 : 0124515891

    // 18 : 9251071085
    // 2018 : 5941429882


    /**
     *
     (3)[7]
     (3)[7] 1  0
      3  7  1 [0](1) 0
      3  7  1  0 [1] 0 (1)
     (3) 7  1  0  1  0 [1] 2
      3  7  1  0 (1) 0  1  2 [4]
      3  7  1 [0] 1  0 (1) 2  4  5
      3  7  1  0 [1] 0  1  2 (4) 5  1
      3 (7) 1  0  1  0 [1] 2  4  5  1  5
      3  7  1  0  1  0  1  2 [4](5) 1  5  8
      3 (7) 1  0  1  0  1  2  4  5  1  5  8 [9]
      3  7  1  0  1  0  1 [2] 4 (5) 1  5  8  9  1  6
      3  7  1  0  1  0  1  2  4  5 [1] 5  8  9  1 (6) 7
      3  7  1  0 (1) 0  1  2  4  5  1  5 [8] 9  1  6  7  7
      3  7 [1] 0  1  0 (1) 2  4  5  1  5  8  9  1  6  7  7  9
      3  7  1  0 [1] 0  1  2 (4) 5  1  5  8  9  1  6  7  7  9  2

     */



    public String makeRecipies(int tgt){
        tgt += 10;
        StringBuilder sb = new StringBuilder();
        int firstElfScore = 3;
        int secondElfScore = 7;
        int firstElfLocation = 0;
        int secondElfLocation = 1;
        sb.append(firstElfScore +"");
        sb.append(secondElfScore + "");

        while (sb.length() < tgt) {
            int sum = firstElfScore + secondElfScore;
            sb.append(sum);
            
            firstElfLocation = (firstElfLocation + 1 + firstElfScore) %sb.length();
            secondElfLocation = (secondElfLocation + 1 + secondElfScore) %sb.length();

            firstElfScore = sb.charAt(firstElfLocation)- '0';
            secondElfScore = sb.charAt(secondElfLocation) -'0';
//            for (int i = 0; i < sb.length(); i ++){
//                if (firstElfLocation == i )
//                    System.out.print("( ");
//                if (secondElfLocation == i)
//                    System.out.print("[ ");
//                System.out.print(" " + sb.charAt(i) + " ");
//                if (firstElfLocation == i )
//                    System.out.print(") ");
//                if (secondElfLocation == i)
//                    System.out.print("] ");
//            }
            //System.out.println("");


        }
        return sb.toString();
    }
}
