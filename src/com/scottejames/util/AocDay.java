package com.scottejames.util;

import java.util.List;

public abstract class AocDay {
    public abstract void runDay();

    public List<String> getDataFromFile(String fileName){
        FileHelper fh = new FileHelper(fileName);
        List<String> items = fh.getFileAsList();
        return items;
    }
}
