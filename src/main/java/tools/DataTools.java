package tools;

import models.Group;
import models.Message;
import models.Project;
import models.User;

import java.util.Map;
import java.util.UUID;

import static views.PenelopeF.systemData;

public class DataTools {
    /* Getters From Name */
    public static Group getGroupFromName(String name) {
        for (Map.Entry<UUID,Group> group : systemData.getGroups().entrySet()) {
            if (group.getValue().getName().equals(name))
                return group.getValue();
        }
        return null;
    }

    public static User getUserFromUsername(String username) {
        for (Map.Entry<UUID,User> user : systemData.getUsers().entrySet()) {
            if (user.getValue().getUsername().equals(username))
                return user.getValue();
        }
        return null;
    }

    /* Getters From ID */
    public static User getUserFromId(UUID id) {
        return systemData.getUsers().get(id);
    }

    public static Group getGroupFromId(UUID id) {
        return systemData.getGroups().get(id);
    }

    public static Project getProjectFromId(UUID id) {
        return systemData.getProjects().get(id);
    }

    public static Message getMessageFromId(UUID id) {
        return systemData.getMessages().get(id);
    }
}
