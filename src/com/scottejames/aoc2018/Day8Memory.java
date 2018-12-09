package com.scottejames.aoc2018;

import com.scottejames.util.AocDay;
import com.scottejames.util.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class Day8Memory extends AocDay{

    @Override
    public void runDay() {
        FileHelper fh = new FileHelper("2018/DayEight_data.txt");
        Node root = new Node();
        List<Integer> items = fh.splitStringArrayToInteger();
        buildTree(0, root, items);
        System.out.println(root);
//        System.out.println(root.getMetaDataSum());
        System.out.println(root.getMetaDataValue());


    }

    int buildTree(int index, Node current,List<Integer> data) {
        int children = data.get(index++);
        int metaData = data.get(index++);
        for (int i = 0; i < children; i++) {
            Node child = new Node();
            current.children.add(child);
            index = buildTree(index, child,data);
        }
        int total = 0;
        for (int i = 0; i < metaData; i++) {
            current.addMetaData(data.get(index + i));
        }
        return index + metaData;

    }
    private class Node {

        List<Node> children = new ArrayList<>();
        List<Integer> metaData = new ArrayList<>();

        public Node(){
            this.metaData = metaData;
        }

        public void addMetaData(int meta){
            metaData.add(meta);
        }

        public void addChild(Node child){
            children.add(child);
        }
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append(" ( " + metaData + " ");
            for (Node child: children){
                sb.append(child);
            }
            sb.append(" )");
            return sb.toString();
        }

        public int getMetaDataValue(){
            int total = 0;

            if (children.size() == 0) {
                for (Integer i:metaData){
                    total+=i;
                }
//                System.out.println("Adding sum " + total);


            } else {
//                System.out.println(children);
                for (Integer i: metaData){
//                    System.out.println("Adding meta id " + i + " out of size " + children.size());
                    if (i <=children.size())
                        total+=children.get(i-1).getMetaDataValue();
                }
//                System.out.println("Adding children " + total);
            }

            return total;

        }

        public int getMetaDataSum() {
            int total = 0;

            for (Integer i:metaData){
                total+=i;
            }
            for (Node child : children) {
                total += child.getMetaDataSum();
            }
            return total;
        }

    }
}
