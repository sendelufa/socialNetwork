package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ListResponseApi;
import ru.skillbox.socialnetwork.api.response.PlatfomLanguageApi;
import ru.skillbox.socialnetwork.dao.PlatformDAO;

@Service
public class PlatformService {

    @Autowired
    PlatformDAO platformDAO;

    public AbstractResponse languages(String language, Long offset,  Long itemPerPage){

        AbstractResponse response = new ListResponseApi("string",
                    System.currentTimeMillis(),
                    new PlatfomLanguageApi(1, "Русский"),
                    0,
                    0,
                    0);

        return response;
    }

    public AbstractResponse countries(String country, int offset, int itemPerPage){

        AbstractResponse response = new ListResponseApi("string",
                    System.currentTimeMillis(),
                    new PlatfomLanguageApi(1, "Россия"),
                    0,
                    0,
                    0);

        return response;
    }

    public AbstractResponse cities(int countryId , String city, int offset, int itemPerPage){

        AbstractResponse response = new ListResponseApi("string",
                System.currentTimeMillis(),
                new PlatfomLanguageApi(1, "Москва"),
                0,
                0,
                0);

        return response;
    }
}
