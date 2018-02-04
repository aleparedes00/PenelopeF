package views.menus;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public enum ProjectElements {
    TASK("Task"),
    DOCUMENT("Document"),
    GROUP("Group"),
    DASHBOARD("Dashboard"),
    MODIFY("Modify"),
    DEACTIVATE("Deactivate"),
    BACK("Back");

    String value;

    ProjectElements(String value) {
        this.value = value;
    }

    public static ProjectElements valueOf(int value) {
        switch (value) {
            case 1:
                return TASK;
            case 2:
                return DOCUMENT;
            case 3:
                return GROUP;
            case 4:
                return DASHBOARD;
            case 5:
                return MODIFY;
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
