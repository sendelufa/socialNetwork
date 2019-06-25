package ru.skillbox.socialnetwork.model;

import ru.skillbox.socialnetwork.model.enumeration.ActionBlockHistory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private int id;

    /**
     * время блокировки
     */
    @Column(name = "time")
    @NotNull
    private Date time;

    /**
     * пользователь, которого заблокировали
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @NotNull
    private Person person;

    /**
     * Пост, за который заблокировали
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * Комментарий
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private PostComment postComment;

    /**
     * тип действия: BLOCK (блокировка) или UNBLOCK (разблокировка)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "action", columnDefinition="ENUM('BLOCK', 'UNBLOCK')")
    @NotNull
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostComment getPostComment() {
        return postComment;
    }

    public void setPostComment(PostComment postComment) {
        this.postComment = postComment;
    }

    public ActionBlockHistory getAction() {
        return action;
    }

    public void setAction(ActionBlockHistory action) {
        this.action = action;
    }
}
