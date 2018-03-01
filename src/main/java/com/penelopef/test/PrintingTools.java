package com.penelopef.test;

import java.io.IOException;

public class PrintingTools { // TODO: delete
    private static void waitForEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
