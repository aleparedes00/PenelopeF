package views.menus;

import models.Admin;

public enum AdminMenuSelection {

    CREATEUSER("Create user"),
    CREATEGROUP("Create group"),
    ADDUSERTOGROUP("Add user to group"),
    RESETUSERPASSWORD("Reset user password"),
    DELETEUSER("Delete user"),
    DELETEGROUP("Delete group"),
    BACK("Back");

    String description;

    AdminMenuSelection(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }

    public static AdminMenuSelection valueOf(int index) {
        return AdminMenuSelection.values()[index];
    }
}
