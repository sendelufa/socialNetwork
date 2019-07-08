package ru.skillbox.socialnetwork.service;

import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.FileUploadResponseApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;

@Service
public class StorageService {

    public AbstractResponse uploadFile(String type) {
        AbstractResponse response;

        if (type.equals("IMAGE")) {
            response = new ResponseApi("string", System.currentTimeMillis(), new FileUploadResponseApi());
            response.setSuccess(true);
            return response;
        }
        response = new ErrorApi("invalid request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }
}
