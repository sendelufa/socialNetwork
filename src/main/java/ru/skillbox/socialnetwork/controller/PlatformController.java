package ru.skillbox.socialnetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.response.ListResponseApi;
import ru.skillbox.socialnetwork.api.response.PlatfomLanguageApi;

@Service
@RequestMapping("/api/v1/platform/")
public class PlatformController {


    @RequestMapping(value = "languages")
    public ResponseEntity languages(@RequestParam String language, @RequestParam int offset, @RequestParam int itemPerPage){
        return new ResponseEntity(new ListResponseApi("string",
                                                        System.currentTimeMillis(),
                                                        new PlatfomLanguageApi(1, "Русский"),
                                                        0,
                                                        0,
                                                        0), HttpStatus.OK);
    }


    @RequestMapping(value = "countries")
    public ResponseEntity countries(@RequestParam String country, @RequestParam int offset, @RequestParam int itemPerPage){
        return new ResponseEntity(new ListResponseApi("string",
                System.currentTimeMillis(),
                new PlatfomLanguageApi(1, "Россия"),
                0,
                0,
                0), HttpStatus.OK);
    }

    @RequestMapping(value = "cities")
    public ResponseEntity cities(@RequestParam int countryId , @RequestParam String city, @RequestParam int offset, @RequestParam int itemPerPage){
        return new ResponseEntity(new ListResponseApi("string",
                System.currentTimeMillis(),
                new PlatfomLanguageApi(1, "Москва"),
                0,
                0,
                0), HttpStatus.OK);
    }


}
