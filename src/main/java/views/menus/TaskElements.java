package views.menus;

import models.Project;

public enum TaskElements {
    DONE("Mark as done"),
    MODIFY("Modify"),
    BACK("Back"),
    TITLE("Title"),
    CONTENT("Content"),
    DATE("Date Created"),
    PRIORITY("Priority"),
    USER("Created by");


    String value;

    TaskElements(String value){ this.value = value;}

    public static TaskElements valueOfModify(int value) {
        switch (value) {
            case 1:
                return TITLE;
            case 2:
                return CONTENT;
            case 3:
                return PRIORITY;
            case 4:
                return BACK;
        }
        throw new IllegalStateException("This option doesn't exist");
    }

    public static TaskElements valueOf(int value) {
        switch (value) {
            case 1:
                return DONE;
            case 2:
                return MODIFY;
            case 3:
                return BACK;
            case 4:
                return PRIORITY;
            case 5:
                return USER;
            case 6:
                return TITLE;
            case 7:
                return CONTENT;
            case 8:
                return DATE;
        }
        throw new IllegalStateException("Selection doesn't exist");
    }

    @Override
    public String toString() {
        return value;
    }
}
