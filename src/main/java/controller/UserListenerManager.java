package controller;

import models.Document;
import models.Task;
import models.User;

import java.util.ArrayList;

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
