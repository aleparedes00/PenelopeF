package models;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public enum HomeSelection{
    CREATE_PROJECT,
    LIST_PROJECTS;

    public static HomeSelection valueOf(int value){
        switch (value){
            case 1:
                return CREATE_PROJECT;
            case 2:
                return LIST_PROJECTS;
        }
        throw new IllegalStateException("Selection doesn't exists");
    }
}
