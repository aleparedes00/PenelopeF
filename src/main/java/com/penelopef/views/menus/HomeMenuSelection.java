package com.penelopef.views.menus;

import static com.penelopef.PenelopeF.activeUser;

public enum HomeMenuSelection {
    GROUPS("Groups and Users", false),
    PROJECTS("My Projects", false),
    PROFILE("Profile and Settings", false),
    ADMIN("Admin panel", true),
    LOGOUT("Log out", activeUser.isAdmin());

    String description;
    boolean admin;

    HomeMenuSelection(String description, boolean admin) {
        this.description = description;
        this.admin = admin;
    }

    public String toString() {
        return this.description;
    }

    public boolean availableToAdmin() {
        return this.admin;
    }
}
