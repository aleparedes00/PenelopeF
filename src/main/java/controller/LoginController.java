package controller;

import models.*;
import views.*;
import static tools.DataTools.*;

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
