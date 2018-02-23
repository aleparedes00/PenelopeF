package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Group;
import models.SystemData;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

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
            System.out.println("Fuck! " + e);
        }
        return EMPTY;
    }

    public void loadGroupsToUser(User activeUser) {
        HashMap<UUID, Group> groups = systemData.getGroups();
        activeUser.setGroups(new ArrayList<>(groups.entrySet().stream()
                .filter(e -> activeUser.getGroupsIds().contains(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList())));
    }
}
