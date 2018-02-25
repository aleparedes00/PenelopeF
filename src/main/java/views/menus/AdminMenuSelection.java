package views.menus;

public enum AdminMenuSelection {

    CREATEUSER("Create user"),
    CREATEGROUP("Create group"),
    ADDUSERTOGROUP("Add user to group"),
    //MODIFY_USER("Modify user") (change password, available for yourself in Profile menu)
    BACK("Back");

    String description;

    AdminMenuSelection(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }

    public static AdminMenuSelection valueOf(int index) {
        switch (index) {
            case 1:
                return CREATEUSER;
            case 2:
                return CREATEGROUP;
            case 3:
                return ADDUSERTOGROUP;
            case 4:
                return BACK;
        }
        throw new IllegalStateException("Selection doesn't exists.");
    }
}
