package ru.skillbox.socialnetwork.model.enumeration;

public enum TypePost {
  POSTED("Опубликованный"),
  QUEUED("Отложенные");

  private String description;

  TypePost(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}