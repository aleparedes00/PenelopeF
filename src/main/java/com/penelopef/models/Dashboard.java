package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import models.Message.Reply;
import static com.penelopef.tools.DateTools.now;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class Dashboard {
    @JsonIgnore
    private ArrayList<Message> messages;
    private ArrayList<UUID> messagesIds;

    /*Constructor*/
    public Dashboard() {
        messages = new ArrayList<>();
        messagesIds = new ArrayList<>();
        String now = now();
        Message creationMessage = new Message("Hello world!", "This project was started on " + now, now );
        messages.add(creationMessage); //TODO Add message to global SystemData hashmap
    }

    /* Getters */
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public ArrayList<UUID> getMessagesIds() {
        return messagesIds;
    }



    public void addMessage(Message msg) {
        messages.add(msg);
        messagesIds.add(msg.getId());
        //TODO Add message to global SystemData hashmap
    }

    public void addReply(String content, Message originalMessage, User author) {
        Message reply = new Message("RE: " + originalMessage.getTitle(), content, now(), author);
        messages.add(reply);
        messagesIds.add(reply.getId());
        originalMessage.addReply(reply);
        //TODO Add message to global SystemData hashmap (this one isn't a priority since we might not implement replies)
    }

    public void createNewSystemMessage(String content) {
        Message newMessage = new Message("System Notification", content, now(), null);
        addMessage(newMessage);
    }
}
