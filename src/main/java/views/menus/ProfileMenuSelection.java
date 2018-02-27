package views.menus;

public enum ProfileMenuSelection {

    CHANGEMAIL("Change e-mail"),
    CHANGEPHONE("Change phone number"),
    RESETPASSWORD("Reset password"),
    BACK("Back");

    String description;

    ProfileMenuSelection(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }

    public static ProfileMenuSelection valueOf(int index) {
        return ProfileMenuSelection.values()[index];
    }
}
