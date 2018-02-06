package models;

import java.util.LinkedList;

public class MessageThread implements MessageList {
    private LinkedList<Message> thread;

    public MessageThread(Message originalMessage) {
        this.thread = new LinkedList();
        thread.add(originalMessage);
    }

    public void addReply(Message reply) {
        thread.add(reply);
    }

    public void drawMessage(int i) {
        Message msg = thread.get(i);

        System.out.println(msg.getTitle() + " | by " + msg.getAuthorName() + " on " + msg.getDate());
        System.out.println("--------------------");
        System.out.println(msg.getContent());
    }

    public void drawMessageList() {
        for (int i = 1; i <= thread.size(); i++) {
            System.out.println("[" + i + "]-----------------------------------------------");
            drawMessage(thread.size() - i);
        }
        System.out.println("--------------------------------------------------");
    }
}
