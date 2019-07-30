package ru.skillbox.socialnetwork.api.dto;

import ru.skillbox.socialnetwork.model.Person;

public class FriendsParameters {
  //id текущего пользователя (из БД)
  private int id;

  //собственно сам юзер
  private Person person;

  //имя для поиска
  String name;

  //целевой ИД для удаления / добавления

  private int targetID;
  //Количество элементов на страницу

  private int itemPerPage;
  //сдвиг от начала

  private int offset;
  public FriendsParameters(String name, Integer offset, Integer itemPerPage){
    this.name = name;
    this.offset = offset;
    this.itemPerPage = itemPerPage;
  }

  public FriendsParameters(String name, Integer offset){
    this(name, offset, 20);
  }

  public FriendsParameters(String name){
    this(name, 0);
  }

  public FriendsParameters(){
    this("");
  }


  public void setPerson(Person person) {
    this.person = person;
    this.id = person.getId();
  }

  public void setTargetID(int targetID) {
    this.targetID = targetID;
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
