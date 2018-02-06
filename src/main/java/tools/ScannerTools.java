package tools;

import java.util.Scanner;

public class ScannerTools {
    static Scanner sc = new Scanner(System.in);

    public static String scanString() {
        return sc.nextLine();
    }

    public static int scanInt(int minimal, int max) {
        int userInput = -1;
        do {
            if (userInput != -1) {
                System.out.println("mardito, entre " + minimal + "y " + max);
            }
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Escribe bien mardito");
            }
            userInput = sc.nextInt();

        } while (userInput < minimal || userInput > max);
        sc.nextLine();
        return userInput;
    }

}
