package com.scottejames.aoc2018;


import com.scottejames.util.AocDay;

import java.util.*;

/**
 Step C must be finished before step A can begin.
 Step C must be finished before step F can begin.
 Step A must be finished before step B can begin.
 Step A must be finished before step D can begin.
 Step B must be finished before step E can begin.
 Step D must be finished before step E can begin.
 Step F must be finished before step E can begin.
 */
public class Day7SumOfParts extends AocDay{
    @Override
    public void runDay() {
        List<String> items = getDataFromFile("2018/DaySeven_data.txt");
        HashMap<String,Set<String>> rules = new HashMap<>();
        Set<String> remainingElements = new HashSet<>();
        List<String> processedElements = new ArrayList<>();

        for (String item: items){
            String [] elements = item.split(" ");
            String to = elements[1];
            String from = elements[7];

            remainingElements.add(to);
            remainingElements.add(from);

            Set<String> dependencies = rules.getOrDefault(from, new HashSet<>());
            dependencies.add(to);
            rules.put(from,dependencies);

        }
        for (String key: rules.keySet()){
            System.out.println(key + " : " + rules.get(key));
        }
        System.out.println("Elements " + remainingElements);


        while(remainingElements.isEmpty() == false){
            List<String> possibleElements = new ArrayList<>();

            for(String element: remainingElements){
                Set<String> dependencies = rules.get(element);
                if (dependencies == null || dependencies.isEmpty()){
                    possibleElements.add(element);
                }

            }
            System.out.println("Possible elements " + possibleElements);
            Collections.sort(possibleElements);
            String next = possibleElements.get(0);
            System.out.println("Processing " + next);
            processedElements.add(next);
            remainingElements.remove(next);

            for(String element: remainingElements) {
                Set<String> dependencies = rules.get(element);
                if (dependencies!= null)
                    dependencies.remove(next);
                rules.put(element, dependencies);
            }
        }
        System.out.println(" Result = " + processedElements);
    }

    public boolean listInSet(Set<String> set, List<String> list){
        if (set.containsAll(list))
            return true;
        else
            return false;

    }
}
