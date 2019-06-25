package ru.skillbox.socialnetwork.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * тэги
 */
@Entity
@Table(name = "tag")
public class Tag {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * Тэг
     */
    @Column(name = "tag", unique = true)
    private String tag;

    /**
     * Посты тэга
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post2tag",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
