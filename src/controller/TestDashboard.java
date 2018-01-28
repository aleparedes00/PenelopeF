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
        System.out.println("Add a message. Title [Enter] Content [Enter]");
        String title = sc.nextLine();
        String msg = sc.nextLine();
        Message newMessage = new Message(title, msg, LocalDateTime.now(), testUser);
        dash.addMessage(newMessage);

        dash.drawMessageList();

        // Generate a reply
        User replyUser = new User("Yes", "ImReplying");
        dash.addReply("Hello this is a reply", newMessage, replyUser);

        dash.drawMessageList();
    }
}
