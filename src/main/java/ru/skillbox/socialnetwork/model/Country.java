package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "country")
public class Country {

   @Id
   @Column(name = "id", unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "title")
   @NotNull
   private String title;

   @Column(name = "title_short")
   @NotNull
   private String titleShort;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitleShort() {
      return titleShort;
   }

   public void setTitleShort(String titleShort) {
      this.titleShort = titleShort;
   }
}
