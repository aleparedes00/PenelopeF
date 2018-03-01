package com.penelopef.views;

import com.penelopef.models.Message;
import com.penelopef.models.MessageList;

import java.util.ArrayList;
import java.util.UUID;

import static com.penelopef.PenelopeF.activeUser;
import static com.penelopef.tools.DataTools.getMessageFromId;
import static com.penelopef.tools.DateTools.now;
import static com.penelopef.tools.ScannerTools.scanInt;
import static com.penelopef.tools.ScannerTools.scanString;

public class DashboardView implements MessageList {

    private final ArrayList<UUID> messages;

    public DashboardView(ArrayList<UUID> messages) {
        this.messages = messages;
    }

    public void drawMessageTitle(Message msg) {
        System.out.println(msg.getTitle() + " | by " + msg.getAuthorName() + " on " + msg.getDate());
    }

    @Override
    public void drawMessage(Message msg) {
        System.out.println("--------------------------------------------------");
        System.out.print(msg.getTitle() + " | by " + msg.getAuthorName());
        if (msg instanceof Message.Reply) System.out.print(" in reply to " + ((Message.Reply) msg).getInReplyTo().getAuthorName());
        System.out.println(" on " + msg.getDate());
        System.out.println("--------------------");
        System.out.println(msg.getContent());
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void drawMessageList() {
        for (int i = 1; i <= messages.size(); i++) {
            System.out.println("[" + i + "]-----------------------------------------------");
            drawMessageTitle(getMessageFromId(messages.get(messages.size() - i)));
        }
        System.out.println("--------------------------------------------------");
    }

    public int showAndSelectMessage() {
        System.out.println("===== DASHBOARD =====");

        drawMessageList();
        System.out.println((messages.size() + 1) + ".- Write a message");
        System.out.println((messages.size() + 2) + ".- Back");

        int input = scanInt(1,messages.size() + 2);
        return messages.size() - input;
    }

    public Message writeNewMessage() {
        System.out.print("Title: ");
        String title = scanString();

        System.out.println("Content:");
        String content = scanString();

        return new Message(title, content, now(), activeUser);
    }

    public Message.Reply writeReplyToMessage(Message originalMessage) {
        System.out.println("Reply:");
        String content = scanString();

        return new Message.Reply("RE: " + originalMessage.getTitle(), content, now(), activeUser, originalMessage);
    }

    public int drawSelectedMessage(Message selectedMessage) {
        if (selectedMessage.getReplies().getThread().isEmpty())
            drawMessage(selectedMessage);
        else {
            MessageThreadView messageThreadView = new MessageThreadView(selectedMessage);
            messageThreadView.drawMessageList();
        }

        System.out.println();
        System.out.println("1.- Reply\n2.- Back");

        return scanInt(1,2);
    }
}

