package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.TagService;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/list")
    public ResponseEntity getTagsList() {
        AbstractResponse response = (AbstractResponse) tagService.findAllTags();
        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }
    /**
     *
     * @param tag тэг для поиска
     */
    @GetMapping("/")
    public ResponseEntity getByTag(@RequestParam String tag) {
        AbstractResponse response = (AbstractResponse) tagService.allPostsByTag(tag);
        return new ResponseEntity(response,response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

}
