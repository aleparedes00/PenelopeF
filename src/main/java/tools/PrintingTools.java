package tools;

import java.io.IOException;

public class PrintingTools {
    private static void waitForEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
