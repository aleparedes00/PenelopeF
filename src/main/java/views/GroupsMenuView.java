package views;

import models.Group;
import models.SystemData;

import java.util.ArrayList;

import static tools.ScannerTools.scanInt;

public class GroupsMenuView {
    private SystemData systemData;

    public GroupsMenuView(SystemData systemData) {
        this.systemData = systemData;
    }

    public Group listGroups() {
        System.out.println("===== GROUPS =====");

        int i = 1;
        ArrayList<Group> groups = new ArrayList<Group>();

        for (Group group : systemData.getGroups().values()) {
            if (!group.isSelfGroup()) {
                System.out.println(i++ + ".- " + group.getName());
                groups.add(group);
            }
        }
        System.out.println(i + ".- Back");

        int input = scanInt(1, i);
        return (input == i) ? null : groups.get(input - 1);
    }
}
