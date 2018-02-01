package controller;

import models.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TestDashboard {

    public static void main(String[] args) {
        User testUser = new User("Test", "McDebug");
        Dashboard dash = new Dashboard();

        dash.drawMessageList();

        Scanner sc = new Scanner(System.in);

        // Write a message
        testUser.writeNewMessage(dash);

        dash.drawMessageList();

        // Generate a reply
        User replyUser = new User("Yes", "ImReplying");
        dash.addReply("Hello this is a reply", dash.getInbox().get(1), replyUser);

        dash.drawMessageList();
    }
}
