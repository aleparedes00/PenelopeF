package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.SystemData;
import models.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class MessageRepository extends Repository<Message> {

    /* Constructor */
    public MessageRepository(String path, SystemData systemData) {
        super(path, systemData, Message.class);
    }

    /* Loading */
    HashMap<UUID, Message> readData(String pathToFile) {
        try {
            TypeReference<HashMap<UUID, Message>> typeRef = new TypeReference<HashMap<UUID, Message>>() { };
            return serializer.deserialize(pathToFile, typeRef);
        } catch (IOException e) {
            System.out.println("Fuck! " + e);
        }
        return EMPTY;
    }


}
