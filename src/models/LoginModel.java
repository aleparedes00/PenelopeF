package models;

public class LoginModel {
    private UserSystem system;

    public LoginModel(UserSystem system) {
        this.system = system;
    }

    public UserSystem getSystem() {
        return system;
    }
}
