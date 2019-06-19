package ru.skillbox.socialnetwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.PostCommentApi;
import ru.skillbox.socialnetwork.api.response.PostApi;

@Controller
@RequestMapping("/api/v1/post")
public class PostController {

    /**
     * Поиск публикации
     *
     * @param text        Текст публикации
     * @param dateFrom    Дата публикации ОТ
     * @param dateTo      Дата публикации ДО
     * @param offset      Отступ от начала списка
     * @param itemPerPage Количество элементов на страницу
     * @return
     */
    @GetMapping("/")
    public ResponseEntity getPost(@RequestParam String text,
                                  @RequestParam(value = "date_from", required = false) Number dateFrom,
                                  @RequestParam(value = "date_to", required = false) Number dateTo,
                                  @RequestParam(required = false) int offset,
                                  @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Получение публикации по ID
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity getPostById(@RequestParam int id) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Редактирование публикации по ID
     *
     * @param id
     * @param publishDate Отложить до даты определенной даты
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity editPostById(@RequestBody PostApi request,
                                       @RequestParam int id,
                                       @RequestParam(value = "publish_date", required = false) Number publishDate) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Удаление публикации по ID
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deletePostById(@RequestParam int id) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Восстановление публикации по ID
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}/recover")
    public ResponseEntity recoverPostById(@RequestParam int id) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Получение комментариев на публикации
     *
     * @param id
     * @param offset      Отступ от начала списка
     * @param itemPerPage Количество элементов на страницу
     * @return
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity getPostingComments(@RequestParam int id,
                                             @RequestParam(required = false) int offset,
                                             @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Создание комментария к публикации
     *
     * @param request
     * @param id      ID публикации
     * @return
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity createPostingComment(@RequestBody PostCommentApi request,
                                               @RequestParam int id) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Редактирование комментария к публикации
     *
     * @param request
     * @param id        ID публикации
     * @param commentId ID комментария публикации
     * @return
     */
    @PutMapping("/{id}/comments/{comment_id}")
    public ResponseEntity editPostingComment(@RequestBody PostCommentApi request,
                                             @RequestParam int id,
                                             @RequestParam(value = "comment_id ") int commentId) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Удаление комментария к публикации
     *
     * @param id        ID публикации
     * @param commentId ID комментария публикации
     * @return
     */
    @DeleteMapping("/{id}/comments/{comment_id}")
    public ResponseEntity deletePostingComment(@RequestParam int id,
                                               @RequestParam(value = "comment_id ") int commentId) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Восстановление комментария
     *
     * @param id        ID публикации
     * @param commentId ID комментария публикации
     * @return
     */
    @PutMapping("/{id}/comments/{comment_id}/recover")
    public ResponseEntity recoverPostingComment(@RequestParam int id,
                                                @RequestParam(value = "comment_id ") int commentId) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Подать жалобу на публикацию
     *
     * @param id ID публикации
     * @return
     */
    @PostMapping("/{id}/report")
    public ResponseEntity sendReportToPost(@RequestParam int id) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Подать жалобу на комментарий к публикации
     *
     * @param id        ID публикации
     * @param commentId ID комментария публикации
     * @return
     */
    @PostMapping("/{id}/comments/{comment_id}/report")
    public ResponseEntity sendReportToPostingComment(@RequestParam int id,
                                                     @RequestParam(value = "comment_id ") int commentId) {
        //TODO: Требуется реализация
        return null;
    }
}
