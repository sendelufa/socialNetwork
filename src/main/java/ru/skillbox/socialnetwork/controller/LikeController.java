package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.RequestLikeApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.LikeService;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * Был ли поставлен лайк пользователем
     *
     * @param user_id ID пользователя
     * @param item_id ID объекта у которого необходимо получить "Лайки"
     * @param type Тип сущности (пост или комментарий)
     */
    @GetMapping("/liked")
    public ResponseEntity isLiked(@RequestParam int user_id,
                                  @RequestParam int item_id,
                                  @RequestParam String type){

        AbstractResponse response = likeService.isLiked(user_id,item_id,type);
        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Получить список пользователей оставивших лайк
     *
     * @param item_id ID объекта у которого необходимо получить "Лайки"
     * @param type Тип сущности (пост или комментарий)
     */
    @GetMapping("/likes")
    public ResponseEntity getLikes(@RequestParam int item_id,
                                   @RequestParam String type){

        AbstractResponse response = likeService.getLikes(item_id,type);

        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Поставить лайк
     *
     * @body itemId запрашивает id сущности и ее тип
     *
     */
    @PutMapping("/likes")
    public ResponseEntity like(@RequestBody RequestLikeApi likeApi){

        AbstractResponse response = likeService.like(likeApi);

        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Убрать лайк
     *
     * @param itemId ID объекта у которого необходимо получить "Лайки"
     * @param type Тип сущности (пост или комментарий)
     */
    @DeleteMapping("/likes")
    public ResponseEntity removeLike (@RequestParam int itemId,
                                      @RequestParam String type) {

        AbstractResponse response = likeService.removeLike(itemId,type);

        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
