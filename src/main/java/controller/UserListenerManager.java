package controller;

import models.Document;
import models.Task;
import models.User;

import java.util.ArrayList;
//THIS CLASS WILL BE DELETED
public class UserListenerManager {
    private ArrayList<User> listeners;

    public UserListenerManager() {
        listeners = new ArrayList<>();
    }

    public void addListener(User userToAdd){
        listeners.add(userToAdd);
    }
    public void removeListener(User userToRemove){
        listeners.removeIf(userInList -> userInList.getUsername().equals(userToRemove.getUsername()));
    }
    public void update(Task task) {
        System.out.println("New task has been added\nName:" + task.getTitle() + "\nfrom " + task.getAuthorName() + "priority " + task.getPriority());
    }
    public void updateDoc(Document document){
        System.out.println("New doc has been added\nName:" +document.getNameOfDoc() + "\nfrom" + document.getOwner().getUsername());
    }
}


// HOW TO USE:
// - Your controller will implement the FSListener:
//   -> i.e: class UserController implements FSListener<User>
//   -> Now implements void onCreate(User user)... etc
// - On your constructor, create a new instance of the FSListenable
// - call run at the end of each user's action loop (in the showMenu loop)
//HomeMenu observer to check if GRoup and user ont eu des changements (à réflechir
//

/* DONE
interface FSListener<T> {
    void onCreate(T newObj);

    void onDelete(T deletedObj);

    void onUpdate(T updatedObj);
}
*/
/*
class FSListenable<T> implements Runnable {
    FSListener<T> listener;
    String path;

    public FSListenable(String path, FSListener<T> listener) {
        this.path = path;
        this.listener = listener;
    }

    public void run() {
       // https://dzone.com/articles/how-watch-file-system-changes
       // use this code's site as a basis.
       // NB: DO NOT USE 'TAKE' BUT 'POLL' instead
       //
        // for each event type:
        // - use the repository to deserialize the JSON
        // - call the related function of ALL listeners
       //     i.e -> listeners.forEach(l -> l.onCreate( obj ));
       //
    }
}*/