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
    HashMap<String,Set<String>> rules = new HashMap<>();
    Set<String> remainingElements = new HashSet<>();
    List<String> processedElements = new ArrayList<>();
    WorkerGroup workerGroup = new WorkerGroup();

    @Override
    public void runDay() {
        List<String> items = getDataFromFile("2018/DaySeven_data.txt");
        prepareInput(items);
        int targetSetSize = remainingElements.size();

        while(processedElements.size()!= targetSetSize){
            List<String> possibleTasks = getPossibleNextStage();
            for (String task: possibleTasks){
                Worker worker = workerGroup.getIdleWorker();
                if (worker!=null){
                    worker.assignTask(task);
                    remainingElements.remove(task);
                }
            }
            workerGroup.tick();


            Worker [] workers = workerGroup.getWorkers();
            for (Worker worker: workers){
                String task = worker.taskComplete();
                if (task != null)
                    processNextStage(task);
            }
        }
        System.out.println(" Result = " + processedElements);
        System.out.println(" Count = " + workerGroup.getCount());
    }

    private void processNextStage(String next) {
        processedElements.add(next);

        for(String element: remainingElements) {
            Set<String> dependencies = rules.get(element);
            if (dependencies!= null)
                dependencies.remove(next);
            rules.put(element, dependencies);
        }
    }

    private List<String> getPossibleNextStage() {
        List<String> possibleElements = new ArrayList<>();

        for(String element: remainingElements){
            Set<String> dependencies = rules.get(element);
            if (dependencies == null || dependencies.isEmpty()){
                possibleElements.add(element);
            }

        }
        Collections.sort(possibleElements);
        return possibleElements;
    }

    private void prepareInput(List<String> items) {
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
    }

    public boolean listInSet(Set<String> set, List<String> list){
        if (set.containsAll(list))
            return true;
        else
            return false;

    }
    private class WorkerGroup{
        private int count = 0;
        private Worker [] workers = new Worker[5];

        public WorkerGroup(){
            for (int i = 0;i <5;i++){
                workers[i] = new Worker();
            }
        }
        public void tick(){
            System.out.print(count + " ");
            for (Worker worker: workers){
                if (worker.isIdle())
                    System.out.print(".");
                else
                    System.out.print(worker.task);

                worker.tick();
            }
            System.out.println("");
            count++;
        }

        public boolean hasIdleWorker(){
            for (Worker worker: workers){
                if (worker.isIdle())
                    return true;
            }
            return false;

        }
        public Worker getIdleWorker(){
            for (Worker worker: workers){
                if (worker.isIdle())
                    return worker;
            }
            return null;
        }

        public Worker[] getWorkers() {
            return workers;
        }
        public int getCount(){
            return count;
        }
    }
    private class Worker{
        private String task;
        private int countDown = 0;

        public String taskComplete(){
            if (countDown == 0){
                String result = task;
                task = null;
                return result;
            }
            return null;
        }
        public void tick(){
            if (countDown > 0){
                countDown--;
            }
        }
        public void assignTask(String task){
            this.task = task;
            countDown = 60+ charToInt(task.charAt(0),true);
            System.out.println("Assigned task " + task + " countdown " + countDown);

        }

        public boolean isIdle(){
            return (task == null);
        }
    }
}
