package models.menus;

public enum AdminHomeSelection {

    CREATE_USER("Create user"),
    CREATE_GROUP("Create group"),
    ADD_USER_TO_GROUP("Add user to group");
    //MODIFY_USER("Modify user") (change password, available for yourself in Profile menu)

    String value;

    AdminHomeSelection(String value) {
        this.value = value;
    }

    public static AdminHomeSelection valueOf(int value) {
        switch (value) {
            case 1:
                return CREATE_USER;
            case 2:
                return CREATE_GROUP;
            case 3:
                return ADD_USER_TO_GROUP;
        }
        throw new IllegalStateException("Selection doesn't exists.");
    }
}
