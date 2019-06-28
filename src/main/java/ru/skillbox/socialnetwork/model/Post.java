package ru.skillbox.socialnetwork.model;

import java.util.Date;
import java.util.List;
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

   @Column(name = "timestamp")
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
   private List<PostFile> postFiles;

   @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
   @OrderBy("time asc")
   private List<PostComment> postComments;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "post2tag",
       joinColumns = @JoinColumn(name = "post_id"),
       inverseJoinColumns = @JoinColumn(name = "tag_id"))
   private List<Tag> tags;

   @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
   private List<BlockHistory> blockHistories;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Person getAuthor() {
      return author;
   }

   public void setAuthor(Person author) {
      this.author = author;
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

   public List<PostFile> getPostFiles() {
      return postFiles;
   }

   public void setPostFiles(List<PostFile> postFiles) {
      this.postFiles = postFiles;
   }

   public List<Tag> getTags() {
      return tags;
   }

   public void setTags(List<Tag> tags) {
      this.tags = tags;
   }

   public List<PostComment> getPostComments() {
      return postComments;
   }

   public void setPostComments(List<PostComment> postComments) {
      this.postComments = postComments;
   }

   public List<BlockHistory> getBlockHistories() {
      return blockHistories;
   }

   public void setBlockHistories(List<BlockHistory> blockHistories) {
      this.blockHistories = blockHistories;
   }
}
