package com.penelopef.models;

public interface FSListener {
    void onCreate(String pathToNewFile);

    void onDelete(String pathToDeleteFile);

    void onUpdate(String pathToUpdateFile);
}
