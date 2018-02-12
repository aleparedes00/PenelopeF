package models;

import java.io.File;
import java.nio.file.*;
import java.util.List;

public class FSListenable implements Runnable {
    public FSListener listener;
    private Path path;
    //private ArrayList<FSListener> _listners;

    public FSListenable(Path path, FSListener listener){
        this.path = path;
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
    public void run(){
        try{
            WatchService watcher = path.getFileSystem().newWatchService();
            path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watchKey = watcher.take();
            if (watcher != null) {
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent event : events) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        //triggerEvents(event.context().toString());
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
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void main(String []ac) {
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

        FSListenable listenable = new FSListenable(new File("./toto").toPath(), listener);

        listenable.run();
    }

}
