package models;

public class Login {
    private UserSystem system;

    public Login(UserSystem system) {
        this.system = system;
    }

    public UserSystem getSystem() {
        return system;
    }
}
