package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import java.util.Date;

/**
 * лайки постов
 */
@Entity
@Table(name = "post_like")
public class PostLike {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * время и дата лайка
     */
    @Column(name = "time")
    private Date time;

    /**
     * пользователь
     */
    @Column(name = "person_id")
    private int personId;

    /**
     * пост
     */
    @Column(name = "post_id")
    private int postId;

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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
