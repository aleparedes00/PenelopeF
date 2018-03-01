package com.penelopef.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.penelopef.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

class UserRepository extends Repository<User> {

    /* Constructor */
    UserRepository(String path) {
        super(path, User.class);
    }

    /* Loading */
    HashMap<UUID, User> readData(String pathToFile) {
        try {
            TypeReference<HashMap<UUID, User>> typeRef = new TypeReference<HashMap<UUID, User>>() { };
            return serializer.deserialize(pathToFile, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fuck! " + e);
        }
        return EMPTY;
    }


}
