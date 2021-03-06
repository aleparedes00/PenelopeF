package com.penelopef.views;

import com.penelopef.views.menus.AdminMenuSelection;

import static com.penelopef.tools.ScannerTools.scanInt;

public class AdminMenuView {
    public AdminMenuSelection showCommands() {
        System.out.println("===== ADMIN PANEL =====");

        for (AdminMenuSelection menuItem : AdminMenuSelection.values()) {
            System.out.println((menuItem.ordinal() + 1) + ".- " + menuItem);
        }

        return AdminMenuSelection.valueOf(scanInt(1, AdminMenuSelection.values().length) - 1);
    }
}
