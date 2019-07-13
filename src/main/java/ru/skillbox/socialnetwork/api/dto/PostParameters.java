package ru.skillbox.socialnetwork.api.dto;

public class PostParameters {

   /**
    * ID пользователя
    */
   private int id;

   /**
    * Получить отложенные записи, работает только для текущего пользователя
    */
   private boolean queue;

   private String text;


   private Integer date_from;


   private Integer date_to;


   /**
    * Отступ от начала списка
    */
   private Integer offset;

   /**
    * Количество элементов на страницу
    */
   private int itemPerPage = 20;

   public PostParameters(){}

   public PostParameters(String text, Integer date_from, Integer date_to, int offset, int itemPerPage) {
      this.text = text;
      this.date_from = date_from;
      this.date_to = date_to;
      this.offset = offset;
      this.itemPerPage = itemPerPage;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Integer getDate_from() {
      return date_from;
   }

   public void setDate_from(Integer date_from) {
      this.date_from = date_from;
   }

   public Integer getDate_to() {
      return date_to;
   }

   public void setDate_to(Integer date_to) {
      this.date_to = date_to;
   }

   public int getOffset() {
      return offset;
   }

   public void setOffset(Integer offset) {
      this.offset = offset;
   }

   public Integer getItemPerPage() {
      return itemPerPage;
   }

   public void setItemPerPage(int itemPerPage) {
      this.itemPerPage = itemPerPage;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public boolean isQueue() {
      return queue;
   }

   public void setQueue(boolean queue) {
      this.queue = queue;
   }
}


