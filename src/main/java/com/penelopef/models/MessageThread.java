package com.penelopef.models;

import java.util.LinkedList;
import java.util.UUID;

public class MessageThread {
    private LinkedList<UUID> thread;

    public MessageThread() {
        this.thread = new LinkedList<>();
    }

    public LinkedList<UUID> getThread() {
        return thread;
    }

    public void addReply(Message reply) {
        thread.add(reply.getId());
    }
}
