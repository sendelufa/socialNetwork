package ru.skillbox.socialnetwork.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * лайки постов
 */
@Entity
@Table(name = "post_like")
public class PostLike {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "time")
   @NotNull
   private Date time;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id")
   @NotNull
   private Person person;

   @ManyToOne(fetch = FetchType.EAGER)
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
