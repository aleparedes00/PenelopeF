package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Group;
import models.SystemData;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

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


}
