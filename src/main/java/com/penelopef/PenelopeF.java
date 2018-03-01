package com.penelopef;

import com.penelopef.controller.*;
import com.penelopef.models.*;
import com.penelopef.repository.RepositoryManager;
import com.penelopef.views.LoginView;

import java.io.File;

/**
 * Created by alejandraparedes on 1/27/18.
 */
public class PenelopeF {
    public final static String defaultProjectsPath = "projects/";
    public final static String usersJson = "users.json";
    public final static String groupsJson = "groups.json";
    public final static String messagesJson = "messages.json";

    public static User activeUser;
    private static SystemData systemData;
    private static RepositoryManager repositories;

    public static SystemData getSystemData() {
        return systemData;
    }

    public static RepositoryManager getRepositories() {
        return repositories;
    }

    public static void main(String[] args) {
        // Initialize Application
        systemData = new SystemData();
        repositories = new RepositoryManager();

        // Load Data
        repositories.loadData();
        systemData.initializeUserSystem();

        // Login Screen
        LoginController loginController = new LoginController(new LoginView());
        while (activeUser == null) {
            activeUser = loginController.userLogin();
        }

        // Build Active User
        repositories.loadActiveUserProjects();

        // Initialise observers
        activeUser.getProjects().forEach(p ->
                FSListenable.addListener(p, new File(defaultProjectsPath + p.getName()).toPath()));

        // Call Home Menu
        HomeMenuController homeMenuController = new HomeMenuController();
        homeMenuController.showHomeMenu();

        // Remove observers upon shutdown
        activeUser.getProjects().forEach(p ->
                FSListenable.removeListener(p, new File(defaultProjectsPath + p.getName()).toPath()));

        // Save upon shutdown
        repositories.saveData();
    }
}
