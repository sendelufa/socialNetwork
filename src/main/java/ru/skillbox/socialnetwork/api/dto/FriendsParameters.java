package ru.skillbox.socialnetwork.api.dto;

public class FriendsParameters {
  //id текущего пользователя (из БД)
  private int id;

  //имя для поиска
  String name;

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
}
