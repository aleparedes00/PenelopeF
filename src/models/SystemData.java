package models;

public class SystemData {
    private UserSystem userSystem;
    // private ProjectSystem? ProjectManager?

    public SystemData() {
        // if local JSON save exists: load, else:
        this.userSystem = new UserSystem();
        // temporary: load data from TestData.java
        userSystem.getUsers().add(TestData.project1_2());
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }
}
