package ru.skillbox.socialnetwork.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * посты
 */
@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

     @Column(name = "time")
    @NotNull
    private Date time;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private Person author;

    @Column(name = "title")
    private String title;

    @Column(name = "post_text")
    @NotNull
    private String postText;

    @Column(name = "is_blocked")
    @NotNull
    private boolean isBlocked;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<PostFile> postFiles;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @OrderBy("time asc")
    private Set<PostComment> postComments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post2tag",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<BlockHistory> blockHistories;

    public int getId() {
        return id;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
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

    public Set<PostFile> getPostFiles() {
        return postFiles;
    }

    public void setPostFiles(Set<PostFile> postFiles) {
        this.postFiles = postFiles;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Set<BlockHistory> getBlockHistories() {
        return blockHistories;
    }

    public void setBlockHistories(Set<BlockHistory> blockHistories) {
        this.blockHistories = blockHistories;
    }
}
