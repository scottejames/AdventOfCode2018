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

}
