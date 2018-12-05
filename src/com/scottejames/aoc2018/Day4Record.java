package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
/*
        [1518-11-01 00:00] Guard #10 begins shift
        [1518-11-01 00:05] falls asleep
        [1518-11-01 00:25] wakes up
        [1518-11-01 00:30] falls asleep
        [1518-11-01 00:55] wakes up
        [1518-11-01 23:58] Guard #99 begins shift
        [1518-11-02 00:40] falls asleep
        [1518-11-02 00:50] wakes up
        [1518-11-03 00:05] Guard #10 begins shift
        [1518-11-03 00:24] falls asleep
        [1518-11-03 00:29] wakes up
        [1518-11-04 00:02] Guard #99 begins shift
        [1518-11-04 00:36] falls asleep
        [1518-11-04 00:46] wakes up
        [1518-11-05 00:03] Guard #99 begins shift
        [1518-11-05 00:45] falls asleep
        [1518-11-05 00:55] wakes up

        11-01  #10  .....####################.....#########################.....
        11-03  #10  ........................#####...............................

        11-02  #99  ........................................##########..........
        11-04  #99  ....................................##########..............
        11-05  #99  .............................................##########.....


 */
public class Day4Record extends AocDay {
    @Override
    public void runDay() {
        List<String> items = getDataFromFile("2018/DayFour_data.txt");
        int id = 0;
        int asleep = 0;
        int awake = 0;
        HashMap<Integer, Integer[]> sleepRecord = new HashMap<>();

        for (String item: items){
            String [] split = item.split("]");
            String time = split[0].replace("[","");
            int minutes = Integer.parseInt(time.split(":")[1]);
            String action = split[1];

            String firstWord = action.split(" ")[1];
            if (firstWord.equals("Guard")){
                 id = Integer.parseInt(action.split("#")[1].split(" ")[0]);
                 if (sleepRecord.get(id) == null){
                     Integer[] record = new Integer[100];
                     for (int i = 0; i< record.length;i++){
                         record[i] = 0;
                     }

                     sleepRecord.put(id, record);
                 }
            } else if (firstWord. equals("falls")) {
                asleep = minutes;
                // Falling asleep
            } else if (firstWord.equals("wakes")) {
                for (int i = asleep; i < minutes; i++) {
                        sleepRecord.get(id)[i]++;
                }
            } else {
                System.out.println("Warning cant parse ; " + action);
            }
        }
        // find sleepist guard....
        int hoursAsleep = 0;
        int sleepiest = 0;
        int maxHoursAsleep = 0;

        for (Integer guard : sleepRecord.keySet()) {
            hoursAsleep=0;

            for (int i = 0; i < 100; i++) {
                if (sleepRecord.get(guard)[i] != 0) {
                    hoursAsleep += sleepRecord.get(guard)[i];
                }

            }
            if (hoursAsleep > maxHoursAsleep){
                // new winner!
                maxHoursAsleep = hoursAsleep;
                sleepiest = guard;
            }
        }
        System.out.println("Guard " + sleepiest + " slept for " + maxHoursAsleep);
        int sleepestMinute = 0;
        int minute = 0;

        for(int i = 0 ; i < 100; i++){
            if (sleepRecord.get(sleepiest)[i] > sleepestMinute){
                sleepestMinute = sleepRecord.get(sleepiest)[i];
                minute = i;

            }
        }
        System.out.println(minute + " was sleepiest minute");
        int sleepyGuard = 0;
        minute = 0;
        sleepestMinute = 0;
        for (int guard: sleepRecord.keySet()) {
            for (int i = 0; i < 100; i++) {
                if (sleepRecord.get(guard)[i] > sleepestMinute) {
                    sleepestMinute = sleepRecord.get(guard)[i];
                    sleepyGuard = guard;
                    minute = i;

                }
            }
        }
        System.out.println(minute + " was sleepiest minute for " + sleepyGuard + " -> " + minute * sleepyGuard);
    }
}

