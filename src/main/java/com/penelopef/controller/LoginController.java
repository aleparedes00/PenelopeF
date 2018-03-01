package com.penelopef.controller;

import com.penelopef.models.*;
import com.penelopef.views.*;
import static com.penelopef.tools.DataTools.*;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public User userLogin() {
        String[] loginInfo = view.askUserToLogin();
        User user = getUserFromUsername(loginInfo[0]);
        if (user != null && user.isRightPassword(loginInfo[1])) {
            view.loginResult(user);
            return user;
        }
        view.loginResult(null);
        return null;
    }
}
