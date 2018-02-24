package views.menus;

import models.Project;

public enum TaskElements {
    TITLE("Title"),
    CONTENT("Content"),
    DATE("Date Created"),
    PRIORITY("Priority"),
    USER("Created by"),
    BACK("Back");

    String value;

    TaskElements(String value){ this.value = value;}

    public static TaskElements valueOf(int value) {
        switch (value) {
            case 1:
                return TITLE;
            case 2:
                return CONTENT;
            case 3:
                return DATE;
            case 4:
                return PRIORITY;
            case 5:
                return USER;
            case 6:
                return BACK;
        }
        throw new IllegalStateException("Selection doesn't exist");
    }

    @Override
    public String toString() {
        return value;
    }
}
