package ru.skillbox.socialnetwork.model;

import javax.persistence.*;

/**
 * связи постов с тэгами
 */
@Entity
public class Post2tag {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    /**
     * пост
     */
    @Column(name = "post_id")
    private int postId;

    /**
     * тэг
     */
    @Column(name = "tag_id")
    private int tagId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
