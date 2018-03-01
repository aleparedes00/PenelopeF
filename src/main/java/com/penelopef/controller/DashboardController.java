package com.penelopef.controller;

import com.penelopef.models.Dashboard;
import com.penelopef.models.Project;
import com.penelopef.tools.MenuTools;
import com.penelopef.views.DashboardView;

public class DashboardController {
    public final DashboardView dashboardView;
    private final Project project;

    DashboardController(DashboardView dashboardView, Project project) {
        this.dashboardView = dashboardView;
        this.project = project;
    }

    public void showDashboard() {
        dashboardView.drawMessageList();
    }

}
