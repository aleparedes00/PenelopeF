package models;

/**
 * Created by alejandraparedes on 1/21/18.
 */
public class Message {
    private String title;
    private String content;
    private User owner;

    public Message(String title, String content, User owner){
        setContent(content);
        setTitle(title);
        this.owner = owner;
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
}
