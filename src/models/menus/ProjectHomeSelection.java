package models.menus;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public enum ProjectHomeSelection {
    CREATE_PROJECT("Create Project"),
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
        }
        throw new IllegalStateException("Selection doesn't exists.");
    }
// ALE Si voy a usar toString is voy s tomar este valor fuera de println
    @Override
    public String toString() {
        return value;
    }
}