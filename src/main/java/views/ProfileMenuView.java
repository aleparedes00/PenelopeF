package views;

import views.menus.ProfileMenuSelection;

import static tools.ScannerTools.scanInt;
import static views.PenelopeF.activeUser;

public class ProfileMenuView {
    public ProfileMenuSelection showCommands() {
        System.out.println("===== PROFILE =====");
        printUserProfile();

        for (ProfileMenuSelection menuItem : ProfileMenuSelection.values()) {
            System.out.println((menuItem.ordinal() + 1) + ".- " + menuItem);
        }

        return ProfileMenuSelection.valueOf(scanInt(1, ProfileMenuSelection.values().length) - 1);
    }

    private void printUserProfile() {
        System.out.println(activeUser.getName() + " (" + activeUser.getUsername() + ")");
        System.out.println(activeUser.getContactInfo());
        System.out.println();
    }
}
