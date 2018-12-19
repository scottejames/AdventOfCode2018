package com.scottejames.util;

import com.scottejames.aco2015.Day1Lispy;
import com.scottejames.aco2015.Day2NoMath;
import com.scottejames.aco2015.Day3HousesInVacuum;
import com.scottejames.aoc2018.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Runner {
    public static void main(String[] args) {

        AocDay day = new Day17Settlers();
        day.runDay();


//        ArrayList<BufferedImage> frames = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            GridImage g = new GridImage(20, Color.BLACK);
//            for (int x = 0; x < 6; x++) {
//                for (int y = 0; y < 6; y+=2) {
//                    System.out.println("Adding " + x + " / " + y);
//                    g.add(new Coord(x, y), ((x + y + i) % 2 == 0) ? Color.BLUE : Color.GREEN);
//                }
//            }
//            frames.add(g.toImage());
//        }
//        GridImage.Companion.animate(frames, "/Users/scottejames/test.gif", 500);
    }
}
