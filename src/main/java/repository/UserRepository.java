package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.User;
import tools.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class UserRepository {

    private final Serializer serializer = new Serializer();
    private final String pathToFile;

    private static final HashMap<UUID, User> NO_USERS = new HashMap<>();

    /*Constructor*/

    public UserRepository(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public HashMap<UUID, User> loadUsers() {
        File usersJson = new File(pathToFile);
        if (usersJson.exists()) return readUsers(pathToFile);
        return NO_USERS;
    }

    private HashMap<UUID, User> readUsers(String pathToFile) {
        try {
            TypeReference<HashMap<UUID,User>> typeRef = new TypeReference<HashMap<UUID,User>>() {};
            return serializer.deserializeHashMap(pathToFile, typeRef);
        } catch (IOException e) {
            System.out.println("Fuck! " + e);
        }
        return NO_USERS;
    }
}
