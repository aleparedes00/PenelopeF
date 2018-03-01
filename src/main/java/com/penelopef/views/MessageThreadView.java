package com.penelopef.views;

import com.penelopef.models.Message;
import com.penelopef.models.MessageList;
import com.penelopef.models.MessageThread;

import static com.penelopef.tools.DataTools.getMessageFromId;

public class MessageThreadView implements MessageList {

    private Message originalMessage;
    private MessageThread replies;

    public MessageThreadView(Message originalMessage) {
        this.originalMessage = originalMessage;
        this.replies = originalMessage.getReplies();
    }

    @Override
    public void drawMessage(Message msg) {
        System.out.print(msg.getTitle() + " | by " + msg.getAuthorName());
        if (msg instanceof Message.Reply) System.out.print(" in reply to " + ((Message.Reply) msg).getInReplyTo().getAuthorName());
        System.out.println(" on " + msg.getDate());
        System.out.println("--------------------");
        System.out.println(msg.getContent());
    }

    @Override
    public void drawMessageList() {
        for (int i = 1; i <= replies.getThread().size(); i++) {
            System.out.println("--------------------------------------------------");
            drawMessage(getMessageFromId(replies.getThread().get(i - 1)));
        }
        System.out.println("--------------------------------------------------");
    }
}
