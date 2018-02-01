package controller;

import models.*;
import views.*;

public class LoginController {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public User userLogin() {
        String[] loginInfo = view.askUserToLogin();
        User user = model.getSystem().getUserFromUsername(loginInfo[0]);
        if (user != null && user.isRightPassword(loginInfo[1])) {
            view.loginResult(user);
            return user;
        }
        view.loginResult(null);
        return null;
    }
}
