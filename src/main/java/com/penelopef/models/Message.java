package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

import static com.penelopef.tools.DataTools.getUserFromId;

/**
 * Created by alejandraparedes on 1/21/18.
 */

public class Message {
    private String title;
    private String content;
    private String date;

    private UUID authorId;

    private MessageThread replies;
    private UUID id;

    /* Constructor by default */
    public Message() { }

    /* Constructors */
    /* Message(title, content, date, user) -> message written by user */
    /* Message(title, content, date) -> message written by system */
    public Message(String title, String content, String date, User author){
        setContent(content);
        setTitle(title);
        this.date = date;
        this.authorId = (author != null) ? author.getId() : null;
        this.replies = new MessageThread();
        this.id = UUID.randomUUID();
    }

    public Message(String title, String content, String date) {
        this(title, content, date, null);
    }

    /* Sub-class for replies */
    public static class Reply extends Message {
        @JsonIgnore
        private Message inReplyTo;

        public Reply(String title, String content, String date, User author, Message inReplyTo) {
            super(title, content, date, author);
            this.inReplyTo = inReplyTo;
        }

        public Message getInReplyTo() {
            return inReplyTo;
        }
    }


    /* Getters */
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public MessageThread getReplies() {
        return replies;
    }

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String getAuthorName() {
        return (authorId != null) ? getUserFromId(authorId).getUsername() : "System";
    }

    /* Setters */
    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /* Other Methods */
    void addReply(Message reply) {
        replies.addReply(reply);
    }
}
