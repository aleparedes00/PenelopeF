package com.penelopef.views;

import static com.penelopef.tools.ScannerTools.scanString;

public class ContactView {
    public String enterNewEmail() {
        System.out.print("Enter new e-mail address: ");
        return scanString();
    }

    public String enterNewPhone() {
        System.out.print("Enter new phone: ");
        return scanString();
    }

    public void changeSuccessful(String info) {
        System.out.println("Successfully changed " + info);
    }

    public void formatError(String info) {
        System.out.println("Error: unrecognised format");
    }
}
