package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.service.PostService;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping("feeds")
public class FeedsController {

  @Autowired
  private PostService postService;

  /*
      name: string(query)           Текст новости для поиска
      offset: integer(query)        Отступ от начала списка
      itemPerPage: integer(query)   Количество элементов на страницу
      Default value : 20
   */

  @GetMapping("feeds")
  public ResponseEntity getPost(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer offset,
                                @RequestParam(required = false, defaultValue = "20") Integer itemPerPage) {
    offset = offset == null ? 0 : offset;
    PostParameters postParameters = new PostParameters(
        name,
        null,
        null,
        offset,
        itemPerPage);
    if(name == null){
      ResponseApi responseApi = postService.getFeed();
      return new ResponseEntity<>(responseApi, responseApi.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    ResponseApi responseApi = postService.search(postParameters);
    return responseApi == null ? badRequestResponse() :
        new ResponseEntity<>(responseApi, HttpStatus.OK);
  }

  private ResponseEntity<Object> badRequestResponse() {
    Map<String, String> response = new HashMap<>();
    response.put("error", "invalid_request");
    response.put("error_description", "Bad Request");
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }




}
