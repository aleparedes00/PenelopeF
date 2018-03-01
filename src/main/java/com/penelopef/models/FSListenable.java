package com.penelopef.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class FSListenable {
    private static FSListenable instance = null;
    private Map<Path, FSWatcher> fsWatchersByPath = new HashMap<>();

    private FSListenable() {
    }

    private static class FSWatcher extends Thread {
        private List<FSListener> listerners = new CopyOnWriteArrayList<>();
        private WatchService watchService;
        private Boolean stop = false;

        FSWatcher(Path path) {
            try {
                watchService = path.getFileSystem().newWatchService();
                path.register(watchService,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.ENTRY_DELETE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        public List<FSListener> getListerners() {
            return listerners;
        }

        @Override
        public void run() {
            try {
                WatchKey key;
                while ((key = watchService.take()) != null && !stop) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        listerners.forEach(listener -> {
                            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                                listener.onCreate(event.context().toString());
                            }
                            if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                                listener.onDelete(event.toString());
                            }
                            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                                listener.onUpdate(event.context().toString());
                            }
                        });
                    }
                    key.reset();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addListener(FSListener listener, Path path) {
        if (instance == null) {
            instance = new FSListenable();
        }
        if (!instance.fsWatchersByPath.containsKey(path)) {
            FSWatcher fsWatcher = new FSWatcher(path);
            instance.fsWatchersByPath.put(path, fsWatcher);
            fsWatcher.setDaemon(true);
            fsWatcher.start();
        }
        instance.fsWatchersByPath.get(path).getListerners().add(listener);
    }

    public static void removeListener(FSListener listener, Path path) {
        if (instance == null || !instance.fsWatchersByPath.containsKey(path)) {
            throw new RuntimeException("You have to add listeners first!!!");
        }
        FSWatcher fsWatcher = instance.fsWatchersByPath.get(path);
        List<FSListener> listerners = fsWatcher.getListerners();
        listerners.remove(listener);
        if (listerners.isEmpty()) {
            fsWatcher.stop = true;
            instance.fsWatchersByPath.remove(path);
        }
    }

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

        FSListenable.addListener(listener, new File("./toto").toPath());
        FSListenable.addListener(listener, new File("./tata").toPath());

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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