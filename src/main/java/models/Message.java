package models;

/**
 * Created by alejandraparedes on 1/21/18.
 */

//I commented all the DATE related fields to be able to fix the repository fill.

public class Message {
    private String title;
    private String content;
    //private LocalDateTime date;
    private User author;
    private MessageThread replies;

    /* Constructor by default */
    public Message() { }

    /* Constructors */
    /* Message(title, content, date, user, replyUser) -> by user, in reply to replyUser */
    /* Message(title, content, date, user) -> message written by user */
    /* Message(title, content, date) -> message written by system */
    public Message(String title, String content, /*LocalDateTime date,*/ User author){
        setContent(content);
        setTitle(title);
        //this.date = date;
        this.author = author;
        this.replies = new MessageThread(this);
    }

    public Message(String title, String content/*, LocalDateTime date*/) {
        this(title, content, /*date,*/ null);
    }

    /* Sub-class for replies */
    class Reply extends Message {
        private Message inReplyTo;

        public Reply(String title, String content, /*LocalDateTime date,*/ User author, Message inReplyTo) {
            super(title, content, /*date,*/ author);
            this.inReplyTo = inReplyTo;
        }

        public Message getInReplyTo() {
            return inReplyTo;
        }
    }

    /* Methods */
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
        return (author != null) ? author.getUsername() : "System";
    }

 /*   public LocalDateTime getDate() {
        return date;
    }*/

    public void addReply(Message reply) {
        replies.addReply(reply);
    }
}
