package models;

import models.Message.Reply;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class Dashboard implements MessageList {
    private ArrayList<Message> inbox;

    /*Constructor*/
    public Dashboard(){
        inbox = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        inbox.add(new Message("Hello world!", "This project was started on " + now.toString(), now));
    }

    public ArrayList<Message> getInbox() {
        return inbox;
    }

    public void drawMessage(int i) {
        Message msg = inbox.get(i);

        System.out.print(msg.getTitle() + " | by " + msg.getAuthorName());
        if (msg instanceof Reply) System.out.print(" in reply to " + ((Reply) msg).getInReplyTo().getAuthorName());
        System.out.println(" on " + msg.getDate());
        System.out.println("--------------------");
        System.out.println(msg.getContent());
    }

    public void drawMessageList() {
        for (int i = 1; i <= inbox.size(); i++) {
            System.out.println("[" + i + "]-----------------------------------------------");
            drawMessage(inbox.size() - i);
        }
        System.out.println("--------------------------------------------------");
    }

    public void addMessage(Message msg) {
        inbox.add(msg);
    }

    public void addReply(String content, Message originalMessage, User author) {
        Message reply = new Message("RE: " + originalMessage.getTitle(), content, LocalDateTime.now(), author);
        inbox.add(reply);
        originalMessage.addReply(reply);
    }
}
