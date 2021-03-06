package com.penelopef.views;

import com.penelopef.models.*;

import static com.penelopef.tools.ScannerTools.*;

public class LoginView {
    public String[] askUserToLogin() {
        System.out.print("Username? ");
        String username = scanString();
        System.out.print("Password? ");
        String password = scanString();

        String[] loginInfo = new String[2];
        loginInfo[0] = username;
        loginInfo[1] = password;

        return loginInfo;
    }

    public User loginResult(User resultUser) {
        if (resultUser != null) {
            System.out.println("Successfully logged in as user " + resultUser.getUsername());
            return resultUser;
        }
        System.out.println("Error, wrong username or password.");
        return null;
    }
}
