package ru.skillbox.socialnetwork.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostCommentApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.service.PostService;

@Controller
@RequestMapping("/api/v1/post")
public class PostController {

   @Autowired
   private PostService postService;

   /**
    * Поиск публикации
    *
    * @param text Текст публикации
    * @param dateFrom Дата публикации ОТ
    * @param dateTo Дата публикации ДО
    * @param offset Отступ от начала списка
    * @param itemPerPage Количество элементов на страницу
    */
   @GetMapping("/")
   public ResponseEntity getPost(@RequestParam String text,
       @RequestParam(value = "date_from", required = false) Integer dateFrom,
       @RequestParam(value = "date_to", required = false) Integer dateTo,
       @RequestParam(required = false) Integer offset,
       @RequestParam(required = false, defaultValue = "20") Integer itemPerPage) {
      offset = offset == null ? 0 : offset;
      PostParameters postParameters = new PostParameters(
          text,
          dateFrom,
          dateTo,
          offset,
          itemPerPage);
      ResponseApi responseApi = postService.search(postParameters);
      return new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Получение публикации по ID
    */
   @GetMapping("/{id:\\d+}")
   public ResponseEntity getPostById(@PathVariable int id) {
      ResponseApi responseApi = postService.get(id);
      return responseApi == null ? badRequestResponse()
          : new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Редактирование публикации по ID
    *
    * @param publishDate Отложить до даты определенной даты
    */
   @PutMapping("/{id:\\d+}")
   public ResponseEntity editPostById(
       @RequestBody ru.skillbox.socialnetwork.api.request.PostApi request,
       @PathVariable int id,
       @RequestParam(value = "publish_date", required = false) Long publishDate) {
      ResponseApi responseApi = postService.edit(id, request, publishDate);
      return responseApi == null ? badRequestResponse()
          : new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Удаление публикации по ID
    */
   @DeleteMapping("/{id:\\d+}")
   public ResponseEntity deletePostById(@PathVariable int id) {
      ResponseApi responseApi = postService.delete(id);
      return responseApi == null ? badRequestResponse()
          : new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Восстановление публикации по ID
    */
   @PutMapping("/{id:\\d+}/recover")
   public ResponseEntity recoverPostById(@PathVariable int id) {
      ResponseApi responseApi = postService.recover(id);
      return responseApi == null ? badRequestResponse()
          : new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Получение комментариев на публикации
    *
    * @param offset Отступ от начала списка
    * @param itemPerPage Количество элементов на страницу
    */
   @GetMapping("/{id:\\d+}/comments")
   public ResponseEntity getPostingComments(@PathVariable int id,
       @RequestParam(required = false) Integer offset,
       @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
      offset = offset == null ? 0 : offset;
      ResponseApi commentListApi = postService.searchComments(id, offset, itemPerPage);
      return new ResponseEntity<>(commentListApi, HttpStatus.OK);
   }

   /**
    * Создание комментария к публикации
    *
    * @param id ID публикации
    */
   @PostMapping("/{id:\\d+}/comments")
   public ResponseEntity createPostingComment(@RequestBody PostCommentApi request,
       @PathVariable Integer id) {
      //TODO не готов сервис, комментарии пишутся от хард код юзера
      ResponseApi commentApi = postService.createComment(id, request);
      return commentApi == null ? badRequestResponse()
          : new ResponseEntity<>(commentApi, HttpStatus.OK);
   }

   /**
    * Редактирование комментария к публикации
    *
    * @param postId ID публикации
    * @param commentId ID комментария публикации
    */
   @PutMapping("/{postId:\\d+}/comments/{commentId:\\d+}")
   public ResponseEntity editPostingComment(@RequestBody PostCommentApi request,
       @PathVariable int postId,
       @PathVariable int commentId) {
      ResponseApi commentApi = postService.editComment(commentId, postId, request);
      return commentApi == null ? badRequestResponse() :
          new ResponseEntity<>(commentApi, HttpStatus.OK);
   }

   /**
    * Удаление комментария к публикации
    *
    * @param postId ID публикации
    * @param commentId ID комментария публикации
    */
   @DeleteMapping("/{id:\\d+}/comments/{comment_id:\\d+}")
   public ResponseEntity deletePostingComment(
       @PathVariable(value = "id") int postId,
       @PathVariable(value = "comment_id") int commentId) {
      ResponseApi responseApi = postService.deleteComment(postId, commentId);
      return responseApi == null ? badRequestResponse()
          : new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Восстановление комментария
    *
    * @param id ID публикации
    * @param commentId ID комментария публикации
    */
   @PutMapping("/{id:\\d+}/comments/{comment_id:\\d+}/recover")
   public ResponseEntity recoverPostingComment(@PathVariable int id,
       @PathVariable(value = "comment_id") int commentId) {
      ResponseApi responseApi = postService.recoverComment(commentId);
      return responseApi == null ? badRequestResponse()
          : new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Подать жалобу на публикацию
    *
    * @param id ID публикации
    */
   @PostMapping("/{id:\\d+}/report")
   public ResponseEntity sendReportToPost(@PathVariable int id) {
      ResponseApi reportApi = postService.reportPost(id);
      return reportApi == null ? badRequestResponse()
          : new ResponseEntity<>(reportApi, HttpStatus.OK);
   }

   /**
    * Подать жалобу на комментарий к публикации
    *
    * @param id ID публикации
    * @param commentId ID комментария публикации
    */
   @PostMapping("/{id:\\d+}/comments/{comment_id:\\d+}/report")
   public ResponseEntity sendReportToPostingComment(@PathVariable int id,
       @PathVariable(value = "comment_id") int commentId) {
      ResponseApi reportApi = postService.reportComment(commentId);
      return reportApi == null ? badRequestResponse()
          : new ResponseEntity<>(reportApi, HttpStatus.OK);
   }

   private ResponseEntity<Object> badRequestResponse() {
      Map<String, String> response = new HashMap<>();
      response.put("error", "invalid_request");
      response.put("error_description", "not_found");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }
}
