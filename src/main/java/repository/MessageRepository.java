package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Message;
import tools.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class MessageRepository { // TODO: Not tested yet!!!

    private final Serializer serializer = new Serializer();
    private final String pathToFile;

    private static final HashMap<UUID, Message> NO_MESSAGES = new HashMap<>();

    /*Constructor*/

    public MessageRepository(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public HashMap<UUID, Message> loadMessages() {
        File messagesJson = new File(pathToFile);
        if (messagesJson.exists()) return readMessages(pathToFile);
        return NO_MESSAGES;
    }

    private HashMap<UUID, Message> readMessages(String pathToFile) {
        try {
            TypeReference<HashMap<UUID,Message>> typeRef = new TypeReference<HashMap<UUID,Message>>() {};
            return serializer.deserializeHashMap(pathToFile, typeRef);
        } catch (IOException e) {
            System.out.println("Fuck! " + e);
        }
        return NO_MESSAGES;
    }
}
