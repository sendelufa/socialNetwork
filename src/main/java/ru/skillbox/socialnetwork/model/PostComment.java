package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import java.util.Date;

/**
 * комментарий к посту
 */
@Entity
public class PostComment {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * дата и время
     */
    @Column(name = "time")
    private Date time;

    /**
     * пост
     */
    @Column(name = "post_id")
    private int postId;

    /**
     * родительский комментарий (если ответ на комментарий к посту)
     */
    @Column(name = "parent_id")
    private int parentId;

    /**
     * автор комментария
     */
    @Column(name = "author_id")
    private int authorId;

    /**
     * Текст комментария
     */
    @Column(name = "comment_text")
    private String commentText;

    /**
     * комментарий заблокирован
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
