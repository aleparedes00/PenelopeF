package views;

import views.menus.HomeMenuSelection;

import java.util.ArrayList;

import static tools.ScannerTools.scanInt;
import static views.PenelopeF.activeUser;

public class HomeMenuView {
    public HomeMenuSelection showHome() {
        System.out.println("===== PENELOPE F =====");

        ArrayList<HomeMenuSelection> availableCommands = new ArrayList<>();
        for (HomeMenuSelection command : HomeMenuSelection.values()) {
            if (command.availableToAdmin() == activeUser.isAdmin()) availableCommands.add(command);
        }

        for (HomeMenuSelection menuItem : availableCommands) {
            System.out.println((availableCommands.indexOf(menuItem) + 1) + ".- " + menuItem);
        }

        return availableCommands.get(scanInt(1, availableCommands.size()) - 1);
    }
}
