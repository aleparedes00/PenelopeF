package com.penelopef.models;

import java.util.ArrayList;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class HomeProjects {
    private ArrayList<Project> projectList;

    public HomeProjects(){
        projectList = new ArrayList<>();
    }

    public Boolean addToList(Project project){
        return projectList.add(project);
    }

    public Boolean remove(Project project){
        return projectList.removeIf(projectInList -> projectInList.getId() == project.getId());
    }

}
