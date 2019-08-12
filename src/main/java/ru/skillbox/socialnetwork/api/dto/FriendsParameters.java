package ru.skillbox.socialnetwork.api.dto;

import ru.skillbox.socialnetwork.model.Person;

public class FriendsParameters {
  //id текущего пользователя (из БД)
  private int id;

  //собственно сам юзер
  private Person person;

  //имя для поиска
  private String name;

  //целевой ИД для удаления / добавления
  private int targetID;

  //целевая персона

  private Person target;
  //Количество элементов на страницу

  private int itemPerPage;
  //сдвиг от начала

  private int offset;
  public FriendsParameters(String name, Integer offset, Integer itemPerPage){
    this.name = name;
    this.offset = offset;
    this.itemPerPage = itemPerPage;
  }

  public void setPerson(Person person) {
    this.person = person;
    this.id = person.getId();
  }

  public void setTargetID(int targetID) {
    this.targetID = targetID;
  }

  public Person getTarget() {
    return target;
  }

  public void setTarget(Person target) {
    this.target = target;
    this.targetID = target.getId();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getOffset() {
    return offset;
  }

  public int getItemPerPage() {
    return itemPerPage;
  }

  public Person getPerson() {
    return person;
  }

  public int getTargetID() {
    return targetID;
  }
}
