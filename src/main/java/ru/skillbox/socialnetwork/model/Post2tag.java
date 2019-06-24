package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * связи постов с тэгами
 */
@Entity
@Table(name = "post2tag")
public class Post2tag {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * пост
     */
    @Column(name = "post_id")
    @NotNull
    private int postId;

    /**
     * тэг
     */
    @Column(name = "tag_id")
    @NotNull
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
