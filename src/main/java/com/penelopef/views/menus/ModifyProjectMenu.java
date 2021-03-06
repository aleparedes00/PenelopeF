package com.penelopef.views.menus;

public enum ModifyProjectMenu {
    PROJECT_NAME("Project Name"),
    GROUP("Group"),
    PRIORITY("Priority"),
    BACK("Back");

    String value;

    ModifyProjectMenu(String value) {this.value = value;}

    public static ModifyProjectMenu valueOf(int value) {
        switch (value) {
            case 1:
                return PROJECT_NAME;
            case 2:
                return GROUP;
            case 3:
                return PRIORITY;
            case 4:
                return BACK;
        }
        throw new IllegalStateException("Selection doesn't exist");
    }
    @Override
    public String toString() {return value;}
}
