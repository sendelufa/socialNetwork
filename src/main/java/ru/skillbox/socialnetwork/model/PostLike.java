package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * лайки постов
 */
@Entity
@Table(name = "post_like")
public class PostLike {

   /**
    * ID
    */
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   /**
    * время и дата лайка
    */
   @Column(name = "time")
   @NotNull
   private Date time;

   /**
    * автор лайка
    */
   @ManyToOne
   @JoinColumn(name = "person_id")
   @NotNull
   private Person person;

   /**
    * пост
    */
   @ManyToOne
   @JoinColumn(name = "post_id")
   @NotNull
   private Post post;

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
}
