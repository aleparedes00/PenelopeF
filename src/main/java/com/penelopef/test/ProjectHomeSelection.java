package com.penelopef.test;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public enum ProjectHomeSelection { // TODO: delete?
    CREATE_PROJECT("Create Project"),
    LOGOUT("Logout"),
    LIST_PROJECTS("List Projects");

    String value;

    ProjectHomeSelection(String value) {
        this.value = value;
    }

    public static ProjectHomeSelection valueOf(int value) {
        switch (value) {
            case 1:
                return CREATE_PROJECT;
            case 2:
                return LIST_PROJECTS;
            case 3:
                return LOGOUT;
        }
        throw new IllegalStateException("Selection doesn't exist.");
    }

    @Override
    public String toString() {
        return value;
    }
}