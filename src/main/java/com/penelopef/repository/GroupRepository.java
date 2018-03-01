package com.penelopef.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.penelopef.models.Group;
import com.penelopef.models.SystemData;
import com.penelopef.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.penelopef.tools.DataTools.getGroupFromId;

public class GroupRepository extends Repository<Group> {

    /* Constructor */
    public GroupRepository(String path, SystemData systemData) {
        super(path, systemData, Group.class);
    }

    /* Loading */
    HashMap<UUID, Group> readData(String pathToFile) {
        try {
            TypeReference<HashMap<UUID, Group>> typeRef = new TypeReference<HashMap<UUID, Group>>() { };
            return serializer.deserialize(pathToFile, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fuck! " + e);
        }
        return EMPTY;
    }

    public void loadGroupsToUser(User activeUser) {
        activeUser.setSelfGroup(getGroupFromId(activeUser.getSelfGroupId()));

        HashMap<UUID, Group> groups = systemData.getGroups();
        activeUser.setGroups(new ArrayList<>(groups.entrySet().stream()
                .filter(e -> activeUser.getGroupsIds().contains(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList())));
    }
}
