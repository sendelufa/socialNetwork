package ru.skillbox.socialnetwork.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "tag", unique = true)
    private String tag;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post2tag",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> posts;

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

    public  List<Post> getPosts() {
        return posts;
    }

    public void setPosts( List<Post> posts) {
        this.posts = posts;
    }
}
