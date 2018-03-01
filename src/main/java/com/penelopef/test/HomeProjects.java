package com.penelopef.test;

import com.penelopef.models.Project;

import java.util.ArrayList;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class HomeProjects { // TODO: delete
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
