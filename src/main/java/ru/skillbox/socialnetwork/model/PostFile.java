package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * файлы, прикреплённые к постам
 */
@Entity
@Table(name = "post_file")
public class PostFile {

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    /**
     * Имя файла
     */
    @Column(name = "name")
    private String name;

    /**
     * путь к файлу в хранилище
     */
    @Column(name = "path")
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
