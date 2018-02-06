package views.menus;

public enum GroupMenu {
    SEE_USERS("See members"),
    ADD_USERS("Add members"),
    CHANGE_NAME("Change name of group"),//TODO remember if(userCompareTo(getOwner()))
    BACK("Back");

    String value;

    GroupMenu(String value) {this.value = value; }

    public static GroupMenu valueOf(int userInput) {
        switch (userInput) {
            case 1:
                return SEE_USERS;
            case 2:
                return ADD_USERS;
            case 3:
                return CHANGE_NAME;
            case 4:
                return BACK;
        }
        throw new IllegalStateException("Selection doesn't exist.");
    }
    @Override
    public String toString() {return value;}
}
