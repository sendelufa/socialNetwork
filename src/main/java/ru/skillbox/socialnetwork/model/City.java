package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "city")
public class City {

   @Id
   @Column(name = "id", unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "title")
   @NotNull
   private String title;

   @ManyToOne
   @JoinColumn(name = "country_id")
   @NotNull
   private Country country;

   public City() {
   }

   public City(@NotNull int id, @NotNull String title,
       @NotNull Country country) {
      this.id = id;
      this.title = title;
      this.country = country;
   }

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

   public Country getCountry() {
      return country;
   }

   public void setCountry(Country country) {
      this.country = country;
   }
}
