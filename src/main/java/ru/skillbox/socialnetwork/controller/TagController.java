package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.model.Tag;
import ru.skillbox.socialnetwork.service.TagService;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/")
    public ResponseEntity getTags(@RequestParam(required = false) String tag,
        @RequestParam(required = false, defaultValue = "0") int offset,
        @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
        AbstractResponse response = tagService.getTags(tag, offset, itemPerPage);
        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/")
    public ResponseEntity addTag(@RequestBody Tag tag) {
        AbstractResponse response = tagService.addTag(tag);
        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/")
    public ResponseEntity deleteTag(@RequestParam int id) {
        AbstractResponse response = tagService.deleteTag(id);
        return new ResponseEntity(response,
            response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception e) {
        return new ResponseEntity<>(new ErrorApi("invalid_request", e.getMessage()),
            HttpStatus.BAD_REQUEST);
    }
}
