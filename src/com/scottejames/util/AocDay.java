package com.scottejames.util;

import java.util.List;

public abstract class AocDay {
    public abstract void runDay();

    public List<String> getDataFromFile(String fileName){
        FileHelper fh = new FileHelper(fileName);
        List<String> items = fh.getFileAsList();
        return items;
    }

    public String reverse(String input){

        StringBuilder output = new StringBuilder();
        output.append(input);
        output = output.reverse();

        return output.toString();
    }

    public static int charToInt(char c,boolean ignoreCase){
        if (ignoreCase == true)
            c= Character.toLowerCase(c);
        return c-'a'+1;

    }

    public static void main(String [] args){

        System.out.println(charToInt('a',true) + " " + charToInt('z',true));
        System.out.println(charToInt('A',true) + " " + charToInt('Z',true));

    }
}
