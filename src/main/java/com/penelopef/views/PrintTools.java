package com.penelopef.views;

import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.tools.ScannerTools.scanString;

public class PrintTools {

    public static String printStringAndReadChoice(String sentenceToPrint) {
        System.out.println(sentenceToPrint);
        return scanString();
    }

    public static Integer printStringAndReadInteger(String sentenceToPrint, int minimal, int max) {
        System.out.println(sentenceToPrint);
        return scanInt(minimal, max);
    }

    public static void printString(String sentenceToPrint) {
        System.out.println(sentenceToPrint);
    }
}
