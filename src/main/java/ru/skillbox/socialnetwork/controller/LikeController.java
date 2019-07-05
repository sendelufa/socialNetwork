package ru.skillbox.socialnetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.LikeApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;

@RestController
@RequestMapping("/api/v1")
public class LikeController {

    /**
     * Был ли поставлен лайк пользователем
     *
     * @param userId ID пользователя
     * @param itemId ID объекта у которого необходимо получить "Лайки"
     * @param type Тип сущности (пост или комментарий)
     */
    @GetMapping("/liked")
    public ResponseEntity isLiked(@RequestParam int userId,
                                  @RequestParam int itemId,
                                  @RequestParam String type){

        AbstractResponse response;
        return null;
        //return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
    }

    /**
     * Получить список пользователей оставивших лайк
     *
     * @param itemId ID объекта у которого необходимо получить "Лайки"
     * @param type Тип сущности (пост или комментарий)
     */
    @GetMapping("/likes")
    public ResponseEntity getLikes(@RequestParam int itemId,
                                           @RequestParam String type){

        AbstractResponse response;
        return null;
        //return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
    }

    /**
     * Поставить лайк
     *
     * @body itemId запрашивает id сущности и ее тип
     *
     */
    @PutMapping("/likes")
    public ResponseEntity like(@RequestBody LikeApi likeApi){

        AbstractResponse response;
        return null;
        //return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
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

        AbstractResponse response;
        return null;
        //return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
    }


}
