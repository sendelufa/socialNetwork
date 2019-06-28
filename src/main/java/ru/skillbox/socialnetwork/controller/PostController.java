package ru.skillbox.socialnetwork.controller;

import org.springframework.http.HttpStatus;
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
    @GetMapping("/{id:\\d+}")
    public ResponseEntity getPostById(@PathVariable int id) {
        //TODO: Требуется реализация
        return new ResponseEntity<>("1", HttpStatus.OK);
    }

    /**
     * Редактирование публикации по ID
     *
     * @param id
     * @param publishDate Отложить до даты определенной даты
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResponseEntity editPostById(@RequestBody PostApi request,
                                       @PathVariable int id,
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
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity deletePostById(@PathVariable int id) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Восстановление публикации по ID
     *
     * @param id
     * @return
     */
    @PutMapping("/{id:\\d+}/recover")
    public ResponseEntity recoverPostById(@PathVariable int id) {
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
    @GetMapping("/{id:\\d+}/comments")
    public ResponseEntity getPostingComments(@PathVariable int id,
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
    @PostMapping("/{id:\\d+}/comments")
    public ResponseEntity createPostingComment(@RequestBody PostCommentApi request,
                                               @PathVariable int id) {
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
    @PutMapping("/{id:\\d+}/comments/{comment_id:\\d+}")
    public ResponseEntity editPostingComment(@RequestBody PostCommentApi request,
                                             @PathVariable int id,
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
    @DeleteMapping("/{id:\\d+}/comments/{comment_id:\\d+}")
    public ResponseEntity deletePostingComment(@PathVariable int id,
                                               @PathVariable(value = "comment_id ") int commentId) {
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
    @PutMapping("/{id:\\d+}/comments/{comment_id:\\d+}/recover")
    public ResponseEntity recoverPostingComment(@PathVariable int id,
                                                @PathVariable(value = "comment_id ") int commentId) {
        //TODO: Требуется реализация
        return null;
    }

    /**
     * Подать жалобу на публикацию
     *
     * @param id ID публикации
     * @return
     */
    @PostMapping("/{id:\\d+}/report")
    public ResponseEntity sendReportToPost(@PathVariable int id) {
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
    @PostMapping("/{id:\\d+}/comments/{comment_id:\\d+}/report")
    public ResponseEntity sendReportToPostingComment(@PathVariable int id,
                                                     @PathVariable(value = "comment_id ") int commentId) {
        //TODO: Требуется реализация
        return null;
    }
}
