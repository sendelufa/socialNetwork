package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import java.util.Date;

/**
 * посты
 */
@Entity
public class Post {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * дата и время публикации
     */
    @Column(name = "time")
    private Date time;

    /**
     * Автор поста
     */
    @Column(name = "author_id")
    private int authorId;

    /**
     * заголовок
     */
    @Column(name = "title")
    private String title;

    /**
     * HTML-текст поста
     */
    @Column(name = "post_text")
    private String postText;

    /**
     * отметка о том, что пост заблокирован
     */
    @Column(name = "is_blocked")
    private boolean isBlocked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
