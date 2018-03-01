package com.penelopef.views;

import com.penelopef.models.Message;
import com.penelopef.models.MessageList;

import java.util.ArrayList;

public class DashboardView implements MessageList{

    private final ArrayList<Message> messages;

    public DashboardView(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void drawMessage(int i) {
        Message msg = messages.get(i);

        System.out.print(msg.getTitle() + " | by " + msg.getAuthorName());
//        if (msg instanceof Reply) System.out.print(" in reply to " + ((Reply) msg).getInReplyTo().getAuthorName());
        System.out.println(" on " /*+ msg.getDate()*/);
        System.out.println("--------------------");
        System.out.println(msg.getContent());
    }

    @Override
    public void drawMessageList() {
        for (int i = 1; i <= messages.size(); i++) {
            System.out.println("[" + i + "]-----------------------------------------------");
            drawMessage(messages.size() - i);
        }
        System.out.println("--------------------------------------------------");
    }

}
