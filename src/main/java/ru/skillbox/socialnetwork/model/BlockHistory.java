package ru.skillbox.socialnetwork.model;

import ru.skillbox.socialnetwork.model.enumeration.ActionBlockHistory;

import javax.persistence.*;
import java.util.Date;

/**
 * история блокировок пользователей за пост / комментарий
 */
@Entity
@Table(name = "block_history")
public class BlockHistory {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * время блокировки
     */
    @Column(name = "time")
    private Date time;

    /**
     * пользователь, которого заблокировали
     */
    @Column(name = "person_id")
    private int personId;

    /**
     * Пост, за который заблокировали
     */
    @Column(name = "post_id")
    private int postId;

    /**
     * Комментарий
     */
    @Column(name = "comment_id")
    private int commentId;

    /**
     * тип действия: BLOCK (блокировка) или UNBLOCK (разблокировка)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "action", columnDefinition="ENUM('BLOCK', 'UNBLOCK')")
    private ActionBlockHistory action;

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

    public int getPerson_id() {
        return personId;
    }

    public void setPerson_id(int person_id) {
        this.personId = person_id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public ActionBlockHistory getAction() {
        return action;
    }

    public void setAction(ActionBlockHistory action) {
        this.action = action;
    }
}
