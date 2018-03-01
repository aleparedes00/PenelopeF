package com.penelopef.views.menus;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public enum ProjectElements {
    PROJECT_INFO("Project information"),
    DASHBOARD("Dashboard"),
    TASK("Tasks"),
    DOCUMENT("Files"),
    EDIT("Edit project"),
    DEACTIVATE("Deactivate project"),
    BACK("Back");

    String value;

    ProjectElements(String value) {
        this.value = value;
    }

    public static ProjectElements valueOf(int value) {
        switch (value) {
            case 1:
                return PROJECT_INFO;
            case 2:
                return DASHBOARD;
            case 3:
                return TASK;
            case 4:
                return DOCUMENT;
            case 5:
                return EDIT;
            case 6:
                return DEACTIVATE;
            case 7:
                return BACK;
        }
        throw new IllegalStateException("Selection doesn't exists");
    }

    @Override
    public String toString() {
        return value;
    }
}
