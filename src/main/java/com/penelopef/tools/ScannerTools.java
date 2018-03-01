package com.penelopef.tools;

import java.util.Scanner;

public class ScannerTools {
    private static Scanner sc = new Scanner(System.in);

    public static String scanString() {
        String line = "";
        while (line.equals("")) line = sc.nextLine();
        return line;
    }

    public static int scanInt(int min, int max) {
        int userInput = -1;

        do {
            if (userInput != -1) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Please enter a number between " + min + " and " + max);
            }
            userInput = sc.nextInt();

        } while (userInput < min || userInput > max);

        sc.nextLine();

        return userInput;
    }

}
