package models;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public enum HomeSelection {
    CREATE_PROJECT("Create Project"),
    LIST_PROJECTS("List Projects");


    String value;

    HomeSelection(String value) {
        this.value = value;
    }

    public static HomeSelection valueOf(int value) {
        switch (value) {
            case 1:
                return CREATE_PROJECT;
            case 2:
                return LIST_PROJECTS;
        }
        throw new IllegalStateException("Selection doesn't exists");
    }
// ALE Si voy a usar toString is voy s tomar este valor fuera de println
    @Override
    public String toString() {
        return value;
    }
}