package com.penelopef.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

/**
 * Created by alejandraparedes on 1/21/18.
 */

public class Message {
    private String title;
    private String content;
    private String date;

    @JsonIgnore
    private User author;
    private UUID authorId;
//TODO No serializer found for class models.MessageThread and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: models.Project["tasks"]->java.util.ArrayList[0]->models.Task["replies"])
    @JsonIgnore
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
        this.author = author;
        this.authorId = author.getId();
        this.replies = new MessageThread(this);
        this.id = UUID.randomUUID();
    }

    public Message(String title, String content, String date) {
        this(title, content, date, null);
    }

    /* Sub-class for replies */
    /*class Reply extends Message {
        private Message inReplyTo;

        public Reply(String title, String content, String date, User author, Message inReplyTo) {
            super(title, content, date, author);
            this.inReplyTo = inReplyTo;
        }

        public Message getInReplyTo() {
            return inReplyTo;
        }
    }*/

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

    User getAuthor() {
        return author;
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
        return (author != null) ? author.getUsername() : "System";
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
