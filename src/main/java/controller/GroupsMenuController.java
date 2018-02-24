package controller;

import models.SystemData;
import models.User;
import models.Group;
import views.GroupsMenuView;

import static tools.MenuTools.showMenu;

public class GroupsMenuController {
    private GroupsMenuView groupsMenuView;
    private final User activeUser;
    private final SystemData systemData;

    /* Constructor */
    public GroupsMenuController(User activeUser, SystemData systemData) {
        this.groupsMenuView = new GroupsMenuView(systemData);

        this.activeUser = activeUser;
        this.systemData = systemData;
    }

    public void showGroups() {
        showMenu(ctx -> {
            Group selectedGroup = groupsMenuView.listGroups();
            if (selectedGroup != null) {
                UsersMenuController usersMenuController = new UsersMenuController(selectedGroup, activeUser, systemData);
                usersMenuController.showUsers();
            } else ctx.leaveCurrentMenu = true;
        });
    }
}