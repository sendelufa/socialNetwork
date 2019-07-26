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


   private Integer dateFom;


   private Integer dateTo;


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
      this.dateFom = date_from;
      this.dateTo = date_to;
      this.offset = offset;
      this.itemPerPage = itemPerPage;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Integer getDateFom() {
      return dateFom;
   }

   public void setDateFom(Integer dateFom) {
      this.dateFom = dateFom;
   }

   public Integer getDateTo() {
      return dateTo;
   }

   public void setDateTo(Integer dateTo) {
      this.dateTo = dateTo;
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


