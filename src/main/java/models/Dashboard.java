package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.Message.Reply;
import static tools.DateTools.now;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class Dashboard implements MessageList {
    @JsonIgnore
    private HashMap<UUID,Message> messages;
    private ArrayList<UUID> messagesIds;

    /*Constructor*/
    public Dashboard() {
        messages = new HashMap<>();
        String now = now();
        Message creationMessage = new Message("Hello world!", "This project was started on " + now, now);
        messages.put(creationMessage.getId(), creationMessage);
    }

    /* Getters */
    public HashMap<UUID,Message> getMessages() {
        return messages;
    }
    public ArrayList<UUID> getMessagesIds() {
        return messagesIds;
    }

    public void drawMessage(int i) {
        Message msg = messages.get(i);

        System.out.print(msg.getTitle() + " | by " + msg.getAuthorName());
        if (msg instanceof Reply) System.out.print(" in reply to " + ((Reply) msg).getInReplyTo().getAuthorName());
        System.out.println(" on " /*+ msg.getDate()*/);
        System.out.println("--------------------");
        System.out.println(msg.getContent());
    }

    public void drawMessageList() {
        for (int i = 1; i <= messages.size(); i++) {
            System.out.println("[" + i + "]-----------------------------------------------");
            drawMessage(messages.size() - i);
        }
        System.out.println("--------------------------------------------------");
    }

    public void addMessage(Message msg) {
        messages.put(msg.getId(), msg);
        messagesIds.add(msg.getId());
    }

    public void addReply(String content, Message originalMessage, User author) {
        Message reply = new Message("RE: " + originalMessage.getTitle(), content, now(), author);
        messages.put(reply.getId(), reply);
        messagesIds.add(reply.getId());
        originalMessage.addReply(reply);
    }
}
