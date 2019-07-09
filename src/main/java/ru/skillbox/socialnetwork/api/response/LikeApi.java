package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;
import java.util.Set;

public class LikeApi extends AbstractResponse{

  private int id;
  private long time;
  @JsonProperty("person_id")
  private int personId;
  @JsonProperty("post_id")
  private int postId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public int getPerson_id() {
    return personId;
  }

  public void setPerson_id(int person_id) {
    this.personId = person_id;
  }

  public int getPost_id() {
    return postId;
  }

  public void setPost_id(int post_id) {
    this.postId = post_id;
  }

  public static class Likes extends AbstractResponse {
      private boolean likes;

      public Likes(boolean likes) {
          this.likes = likes;
      }

      public boolean getLikes() {
          return likes;
      }

      public void setLikes(boolean likes) {
          this.likes = likes;
      }
  }

   @JsonInclude(JsonInclude.Include.NON_NULL)
   public static class BitLikes extends AbstractResponse{
      private int likes;

      private Set<Integer> users;

      public BitLikes(int likes) {
              this.likes = likes;
          }

      public BitLikes(int likes, Set<Integer> userList){
          this.likes = likes;
          this.users = userList;
      }

      public int getLikes() {
              return likes;
          }

      public void setLikes(int likes) {
              this.likes = likes;
          }

      public Set<Integer> getUsers() {
          return users;
      }

      public void setUsers(Set<Integer> users) {
          this.users = users;
      }
  }
}
