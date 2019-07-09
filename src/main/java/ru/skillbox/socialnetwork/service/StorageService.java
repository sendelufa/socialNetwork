package ru.skillbox.socialnetwork.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.socialnetwork.api.response.*;

import java.io.IOException;

@Service
public class StorageService {

    public AbstractResponse uploadFile(String type, MultipartFile file, FileUploadResponseApi fura) {
        AbstractResponse response;

        if (type.equals("IMAGE") && file != null) {
            response = new ResponseApi("string", System.currentTimeMillis(), fura);
            response.setSuccess(true);
            return response;
        }
        response = new ErrorApi("invalid request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }
}
