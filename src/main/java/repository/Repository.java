package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import models.SystemData;
import tools.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public abstract class Repository<T> {
    final Class<T> dataType;
    final Serializer serializer = new Serializer();
    final SystemData systemData;
    final String path;

    final HashMap<UUID, T> EMPTY = new HashMap<>();

    /*Constructor*/
    public Repository(String path, SystemData systemData, Class<T> dataType) {
        this.dataType = dataType;
        this.path = path;
        this.systemData = systemData;
    }

    /* Deserialization */
    public void loadData() {
        File dataSource = new File(path);
        if (dataSource.exists()) this.systemData.load(readData(path), dataType);
    }

    abstract HashMap<UUID, T> readData(String pathToFile);

    /* Serialization */
    public void saveData() {
        HashMap<UUID, T> dataToSave = systemData.getDataFromType(dataType);
        try {
            serializer.serialize(dataToSave, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
