package models;

import java.nio.file.*;
import java.util.List;

public class FSListenable implements Runnable {
    private FSListener listener;
    private Path path;

    public FSListenable(Path path, FSListener listener){
        this.path = path;
        this.listener = listener;
    }

    public void run(){
        try{
            WatchService watcher = path.getFileSystem().newWatchService();
            path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watchKey = watcher.poll();

            List<WatchEvent<?>> events = watchKey.pollEvents();
            for (WatchEvent event : events) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("Created : " + event.context().toString());
                }
                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Deleted : " + event.context().toString());
                }
                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("Modified : " + event.context().toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
