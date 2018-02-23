package models;

public class Login {
    private UserSystem system;

    public Login(SystemData systemData) {
        this.system = systemData.getUserSystem();
    }

    public UserSystem getSystem() {
        return system;
    }
}
