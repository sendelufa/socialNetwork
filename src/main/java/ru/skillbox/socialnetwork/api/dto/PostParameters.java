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


   private Long dateFom;


   private Long dateTo;


   /**
    * Отступ от начала списка
    */
   private Integer offset;

   /**
    * Количество элементов на страницу
    */
   private int itemPerPage = 20;

   public PostParameters(){}

   public PostParameters(String text, Long dateFrom, Long dateTo, int offset, int itemPerPage) {
      this.text = text;
      this.dateFom = dateFrom;
      this.dateTo = dateTo;
      this.offset = offset;
      this.itemPerPage = itemPerPage;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
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

   public Long getDateFrom() {
      return dateFom;
   }

   public void setDateFom(Long dateFom) {
      this.dateFom = dateFom;
   }

   public Long getDateTo() {
      return dateTo;
   }

   public void setDateTo(Long dateTo) {
      this.dateTo = dateTo;
   }
}


