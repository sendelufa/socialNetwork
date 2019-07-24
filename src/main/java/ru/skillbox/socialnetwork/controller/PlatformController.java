package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.PlatformService;


@Controller
@RequestMapping("platform/")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    /**
     * Получение языков платформы
     *
     * @return
     */
    @RequestMapping(value = "languages", method = RequestMethod.GET)
    public ResponseEntity languages(@RequestParam(required = false) String language, @RequestParam int offset, @RequestParam int itemPerPage){

        AbstractResponse response = platformService.languages(language,offset,itemPerPage);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Получение стран платформы
     *
     * @param country           Строка для поиска по странам
     * @param offset            Отступ от начала списка
     * @param itemPerPage       Количество элементов на страницу
     * @return
     */
    @RequestMapping(value = "countries", method = RequestMethod.GET)
    public ResponseEntity countries(@RequestParam String country, @RequestParam int offset, @RequestParam int itemPerPage){

        AbstractResponse response = platformService.countries(country,offset,itemPerPage);

        return new ResponseEntity(response, HttpStatus.OK);
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
    @RequestMapping(value = "cities", method = RequestMethod.GET)
    public ResponseEntity cities(@RequestParam int countryId , @RequestParam String city, @RequestParam int offset, @RequestParam int itemPerPage){

        AbstractResponse response = platformService.cities(countryId,city,offset,itemPerPage);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
