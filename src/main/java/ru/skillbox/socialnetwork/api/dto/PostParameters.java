package ru.skillbox.socialnetwork.api.dto;

public class PostParameters {

   private String text;


   private Long dateFrom;


   private Long dateTo;


   private Integer offset;


   private int itemPerPage;

   public PostParameters(String text, Long dateFrom, Long dateTo, int offset, int itemPerPage) {
      this.text = text;
      this.dateFrom = dateFrom;
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

   public Long getDateFrom() { return dateFrom; }

   public void setDateFrom(Long dateFrom) {
      this.dateFrom = dateFrom;
   }

   public Long getDateTo() {
      return dateTo;
   }

   public void setDateTo(Long dateTo) {
      this.dateTo = dateTo;
   }

   public int getOffset() {
      return offset;
   }

   public void setOffset(int offset) {
      this.offset = offset;
   }

   public int getItemPerPage() {
      return itemPerPage;
   }

   public void setItemPerPage(int itemPerPage) {
      this.itemPerPage = itemPerPage;
   }
}


