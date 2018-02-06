package views;

import static tools.ScannerTools.scanInt;
import static tools.ScannerTools.scanString;

public class PrintTools {

    public static String printStringAndReadChoice(String sentenceToPrint) {
        System.out.println(sentenceToPrint);
        return scanString();
    }

    public static Integer printStringAndReadIntegre(String sentenceToPrint, int minimal, int max) {
        System.out.println(sentenceToPrint);
        return scanInt(minimal, max);
    }

    public static void printString(String sentenceToPrint) {
        System.out.println(sentenceToPrint);
    }
}
