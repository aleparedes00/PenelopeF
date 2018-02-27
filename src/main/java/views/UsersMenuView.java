package views;

import models.Group;
import models.User;
import models.SystemData;

import java.util.ArrayList;
import java.util.UUID;

import static tools.DataTools.getGroupFromId;
import static tools.DataTools.getUserFromId;
import static tools.ScannerTools.scanInt;

public class UsersMenuView {
    private SystemData systemData;

    public UsersMenuView(SystemData systemData) {
        this.systemData = systemData;
    }

    public User listUsers(Group selectedGroup) {
        System.out.println("===== MEMBERS OF GROUP " + selectedGroup.getName() + " =====");

        int i = 1;
        ArrayList<User> users = new ArrayList<User>();

        for (UUID userId : selectedGroup.getUsersIds()) {
            User user = getUserFromId(userId);
            System.out.println(i++ + ".- " + user.getName());
            users.add(user);
        }
        System.out.println(i + ".- Back");

        int input = scanInt(1, i);
        return (input == i) ? null : users.get(input - 1);
    }

    public void showSelectedUser(User selectedUser) {
        System.out.println("===== USER INFO =====");

        System.out.println(selectedUser.getName());
        showGroups(selectedUser);
        System.out.println(selectedUser.getContactInfo());
    }

    private void showGroups(User selectedUser) {
        int i = 0;
        System.out.print("Member of groups: ");

        while (i < 3 && i < selectedUser.getGroupsIds().size()) {
            System.out.print(getGroupFromId(selectedUser.getGroupsIds().get(i)).getName());
            i++;
            if (i < 3 && i < selectedUser.getGroupsIds().size())
                System.out.print(", ");
        }
        if (selectedUser.getGroupsIds().size() - i > 0)
            System.out.print(" and " + (selectedUser.getGroupsIds().size() - i) + " more");
        System.out.println(".");
    }
}
