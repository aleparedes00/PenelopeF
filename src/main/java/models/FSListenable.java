package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FSListenable implements Runnable {
    public FSListener listener;
    private String path;
    //private ArrayList<FSListener> _listners;

    public FSListenable(String path, FSListener listener) {
        this.path = path + "/";
        this.listener = listener;
        //this._listners = new ArrayList<>();
    }

   /* public void addListner(FSListener l) {
        this._listners.add(l);
    }*/

    /*    private void triggerEvents(String e) {
            for (FSListener one : this._listners) {
                one.onCreate(e);
            }
        }*/
//Run will update the event
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(System.getProperty(this.path));

            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            WatchKey key;
            while ((key = watchService.poll()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        listener.onCreate(event.context().toString());
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        listener.onDelete(event.toString());
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        listener.onUpdate(event.context().toString());
                    }
                }
                key.reset();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public void run() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
                    // path.getFileSystem().newWatchService();
            path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watchKey = watcher.poll();
            if (watcher != null) //TODO check this condition
            {
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent event : events) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        listener.onCreate(event.context().toString());
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        listener.onDelete(event.toString());
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        listener.onUpdate(event.context().toString());
                    }
                }
            }
            watchKey.reset();
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Error: " + e.toString());
        }

    }
*/
    public static void main(String[] ac) {
        FSListener listener = new FSListener() {
            @Override
            public void onCreate(String pathToNewFile) {
                System.out.println("I received " + pathToNewFile);
            }

            @Override
            public void onDelete(String pathToDeleteFile) {

            }

            @Override
            public void onUpdate(String pathToUpdateFile) {

            }
        };

        //FSListenable listenable = new FSListenable(new File("./toto").toPath(), listener);

        //listenable.run();
    }

}
