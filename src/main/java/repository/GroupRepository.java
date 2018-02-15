package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Group;
import tools.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class GroupRepository {

    private final Serializer serializer = new Serializer();
    private final String pathToFile;

    private static final HashMap<UUID, Group> NO_GROUPS = new HashMap<>();

    /*Constructor*/

    public GroupRepository(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public HashMap<UUID, Group> loadGroups() {
        File groupsJson = new File(pathToFile);
        if (groupsJson.exists()) return readGroups(pathToFile);
        return NO_GROUPS;
    }

    private HashMap<UUID, Group> readGroups(String pathToFile) {
        try {
            TypeReference<HashMap<UUID,Group>> typeRef = new TypeReference<HashMap<UUID,Group>>() {};
            return serializer.deserialize(pathToFile, typeRef);
        } catch (IOException e) {
            System.out.println("Fuck! " + e);
        }
        return NO_GROUPS;
    }
}
