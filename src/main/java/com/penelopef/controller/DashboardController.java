package com.penelopef.controller;

import com.penelopef.models.Dashboard;
import com.penelopef.models.Message;
import com.penelopef.models.Project;
import com.penelopef.tools.MenuTools;
import com.penelopef.views.DashboardView;

import static com.penelopef.tools.DataTools.getMessageFromId;
import static com.penelopef.tools.MenuTools.showMenu;

public class DashboardController {
    public final DashboardView dashboardView;
    private Dashboard dashboard;

    DashboardController(DashboardView dashboardView, Project project) {
        this.dashboardView = dashboardView;
        this.dashboard = project.getDashboard();
    }

    public void showDashboard() {
        showMenu(ctx -> {
            int selectedMessage = dashboardView.showAndSelectMessage();
            if (selectedMessage >= 0) {
                showMessage(getMessageFromId(dashboard.getMessagesIds().get(selectedMessage)));
            } else if (selectedMessage == -1) {
                writeMessageOnDashboard();
            } else ctx.leaveCurrentMenu = true;
        });
    }

    private void showMessage(Message selectedMessage) {
        showMenu(ctx -> {
            switch(dashboardView.drawSelectedMessage(selectedMessage)) {
                case 1:
                    writeReplyToMessage(selectedMessage);
                    break;
                case 2:
                    ctx.leaveCurrentMenu = true;
            }
        });
    }

    private void writeMessageOnDashboard() {
        Message newMessage = dashboardView.writeNewMessage();
        dashboard.addMessage(newMessage);
    }

    private void writeReplyToMessage(Message originalMessage) {
        Message.Reply newReply = dashboardView.writeReplyToMessage(originalMessage);
        dashboard.addReply(newReply, originalMessage);
    }
}
