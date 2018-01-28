package models;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class Message {
    private String title;
    private String content;
    private LocalDateTime date;
    private User author;
    private Message inReplyTo;
    private MessageThread replies;

    /* Constructors */
    /* Message(title, content, date, user, replyUser) -> by user, in reply to replyUser */
    /* Message(title, content, date, user) -> message written by user */
    /* Message(title, content, date) -> message written by system */
    public Message(String title, String content, LocalDateTime date, User author, Message inReplyTo){
        setContent(content);
        setTitle(title);
        this.date = date;
        this.author = author;
        this.inReplyTo = inReplyTo;
        this.replies = new MessageThread(this);
    }

    public Message(String title, String content, LocalDateTime date, User author) {
        this(title, content, date, author, null);
    }

    public Message(String title, String content, LocalDateTime date) {
        this(title, content, date, null, null);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return (author != null) ? author.getLogin() : "System";
    }

    public Message getInReplyTo() {
        return inReplyTo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void addReply(Message reply) {
        replies.addReply(reply);
    }
}
