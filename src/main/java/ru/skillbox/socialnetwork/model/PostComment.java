package ru.skillbox.socialnetwork.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * комментарий к посту
 */
@Entity
@Table(name = "post_comment")
public class PostComment {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * дата и время
     */
    @Column(name = "time")
    @NotNull
    private Date time;

    /**
     * пост
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Post post;

    /**
     * родительские комментарии (если ответ на комментарий к посту)
     */

    @OneToMany(mappedBy = "parent_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PostComment> postComments;

    /**
     * родительский комментарий
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private PostComment parent_id = null;

    /**
     * автор комментария
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @NotNull
    private Person author;

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

    /**
     * Список блокировок
     */

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<BlockHistory> blockHistories;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public PostComment getParent_id() {
        return parent_id;
    }

    public void setParent_id(PostComment parent_id) {
        this.parent_id = parent_id;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
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

    public Set<BlockHistory> getBlockHistories() {
        return blockHistories;
    }

    public void setBlockHistories(Set<BlockHistory> blockHistories) {
        this.blockHistories = blockHistories;
    }
}
