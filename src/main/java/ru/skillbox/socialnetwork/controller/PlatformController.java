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


    /**
     * Получение языков платформы
     *
     * @param language          Строка для поиска по языкам
     * @param offset            Отступ от начала списка
     * @param itemPerPage       Количество элементов на страницу
     * @return
     */
    @RequestMapping(value = "languages")
    public ResponseEntity languages(@RequestParam String language, @RequestParam int offset, @RequestParam int itemPerPage){
        return new ResponseEntity(new ListResponseApi("string",
                                                        System.currentTimeMillis(),
                                                        new PlatfomLanguageApi(1, "Русский"),
                                                        0,
                                                        0,
                                                        0), HttpStatus.OK);
    }


    /**
     * Получение стран платформы
     *
     * @param country           Строка для поиска по странам
     * @param offset            Отступ от начала списка
     * @param itemPerPage       Количество элементов на страницу
     * @return
     */
    @RequestMapping(value = "countries")
    public ResponseEntity countries(@RequestParam String country, @RequestParam int offset, @RequestParam int itemPerPage){
        return new ResponseEntity(new ListResponseApi("string",
                System.currentTimeMillis(),
                new PlatfomLanguageApi(1, "Россия"),
                0,
                0,
                0), HttpStatus.OK);
    }

    /**
     * Получение городов платформы
     *
     * @param countryId         ID страны
     * @param city              Строка для поиска по городам
     * @param offset            Отступ от начала списка
     * @param itemPerPage       Количество элементов на страницу
     * @return
     */
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
