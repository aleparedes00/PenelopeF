package com.penelopef.repository;

import com.penelopef.tools.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static com.penelopef.PenelopeF.getSystemData;

public abstract class Repository<T> {
    final Class<T> dataType;
    final Serializer serializer = new Serializer();
    final String path;

    final HashMap<UUID, T> EMPTY = new HashMap<>();

    /*Constructor*/
    public Repository(String path, Class<T> dataType) {
        this.dataType = dataType;
        this.path = path;
    }

    /* Deserialization */
    void loadData() {
        File dataSource = new File(path);
        if (dataSource.exists()) getSystemData().load(readData(path), dataType);
    }

    abstract HashMap<UUID, T> readData(String pathToFile);

    /* Serialization */
    public void saveData() {
        HashMap<UUID, T> dataToSave = getSystemData().getDataFromType(dataType);
        try {
            serializer.serialize(dataToSave, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
