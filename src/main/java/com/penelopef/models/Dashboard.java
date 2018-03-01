package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import models.Message.Reply;
import static com.penelopef.PenelopeF.getRepositories;
import static com.penelopef.PenelopeF.getSystemData;
import static com.penelopef.tools.DateTools.now;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class Dashboard {

    private ArrayList<UUID> messagesIds;

    /*Constructor*/
    public Dashboard(ArrayList<UUID> messagesIds) {
        this.messagesIds = messagesIds;
//        String now = now();
//        Message creationMessage = new Message("Hello world!", "This project was started on " + now, now );
//        messages.add(creationMessage);
    }

    /* Getters */
    public ArrayList<UUID> getMessagesIds() {
        return messagesIds;
    }

    /* Message creation */
    public void addMessage(Message msg) {
        messagesIds.add(msg.getId());
        getSystemData().getMessages().put(msg.getId(), msg);
        getRepositories().saveData();
    }

    public void addReply(Message reply, Message originalMessage) {
        addMessage(reply);
        originalMessage.addReply(reply);
    }

    public void createNewSystemMessage(String content) {
        Message newMessage = new Message("System Notification", content, now(), null);
        addMessage(newMessage);
    }
}
