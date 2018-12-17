package com.scottejames.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileHelper {

    private List<String> fileData = new ArrayList<>();

    public FileHelper(String fileName) {
        InputStream resourceAsStream = FileHelper.class.getResourceAsStream("/" + fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String input;
            while ((input = br.readLine()) != null) {
                fileData.add(input);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public List<String> getFileAsList() {
        return fileData;
    }
    public String getFileAsString() { return fileData.get(0);}
    public List<Integer> splitStringArrayToInteger(){

        ArrayList<Integer> result = new ArrayList<>();

        String[] items = fileData.get(0).split(" ");
        for(String item: items){
            result.add(Integer.parseInt(item));
        }
        return result;

    }
    public List<String[]> getFileLinesSplit(String delimiter) {
        List<String[]> result = new ArrayList<>();

        for (String line : fileData) {
            String[] s = line.split(delimiter);
            result.add(s);
        }
        return result;
    }

    public int performIntActionOnLine(String filename, Function<String, Integer> func) {
        int result = 0;

        for (String input : fileData) {
            result += func.apply(input);
        }
        return result;

    }
    public static int[] StringArrayToInt(String [] array){
        int [] result = new int[array.length];
        for (int i = 0 ; i < array.length; i++){
            result[i] = Integer.parseInt(array[i]);
        }
        return result;
    }
    public static int getMaxLength(List<String> input){
        int maxLength = 0;
        for (String item:input){
            if (item.length() > maxLength)
                maxLength = item.length();
        }
        return maxLength;
    }


}
