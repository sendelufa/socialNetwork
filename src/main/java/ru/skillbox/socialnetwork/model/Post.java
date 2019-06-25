package ru.skillbox.socialnetwork.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * посты
 */
@Entity
@Table(name = "post")
public class Post {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * дата и время публикации
     */
    @Column(name = "time")
    @NotNull
    private Date time;

    /**
     * Автор поста
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private Person author;

    /**
     * заголовок
     */
    @Column(name = "title")
    private String title;

    /**
     * HTML-текст поста
     */
    @Column(name = "post_text")
    @NotNull
    private String postText;

    /**
     * отметка о том, что пост заблокирован
     */
    @Column(name = "is_blocked")
    @NotNull
    private boolean isBlocked;

    /**
     * Список лайков
     *
     */
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private Set<PostLike> postLikes;

    /**
     * Список файлов к посту
     *
     */
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private Set<PostFile> postFiles;

    /**
     * Комментарии
     *
     */
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @OrderBy("time asc")
    private Set<PostComment> postComments;

    /**
     * Тэги поста
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post2tag",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    /**
     * Список блокировок
     */

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
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

    public Set<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Set<PostLike> postLikes) {
        this.postLikes = postLikes;
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
