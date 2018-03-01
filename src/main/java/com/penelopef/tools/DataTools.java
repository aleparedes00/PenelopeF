package com.penelopef.tools;

import com.penelopef.models.Group;
import com.penelopef.models.Message;
import com.penelopef.models.Project;
import com.penelopef.models.User;

import java.util.Map;
import java.util.UUID;

import static com.penelopef.PenelopeF.getSystemData;

public class DataTools {
    /* Getters From Name */
    public static Group getGroupFromName(String name) {
        for (Map.Entry<UUID,Group> group : getSystemData().getGroups().entrySet()) {
            if (group.getValue().getName().equals(name))
                return group.getValue();
        }
        return null;
    }

    public static User getUserFromUsername(String username) {
        for (Map.Entry<UUID,User> user : getSystemData().getUsers().entrySet()) {
            if (user.getValue().getUsername().equals(username))
                return user.getValue();
        }
        return null;
    }

    /* Getters From ID */
    public static User getUserFromId(UUID id) {
        return getSystemData().getUsers().get(id);
    }

    public static Group getGroupFromId(UUID id) {
        return getSystemData().getGroups().get(id);
    }

    public static Project getProjectFromId(UUID id) {
        return getSystemData().getProjects().get(id);
    }

    public static Message getMessageFromId(UUID id) {
        return getSystemData().getMessages().get(id);
    }
}
