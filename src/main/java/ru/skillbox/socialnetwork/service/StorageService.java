package ru.skillbox.socialnetwork.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.socialnetwork.api.response.*;

import java.io.IOException;

@Service
public class StorageService {

    public AbstractResponse uploadFile(String type, MultipartFile file) throws IOException {
        AbstractResponse response;

        if (type.equals("IMAGE")) {
            response = new ResponseApi("string", System.currentTimeMillis(), getDataFile(file));
            response.setSuccess(true);
            return response;
        }
        response = new ErrorApi("invalid request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    public FileUploadResponseApi getDataFile(MultipartFile file) throws IOException {
        FileUploadResponseApi fura = new FileUploadResponseApi();
        fura.setOwnerId(new PersonApi().getId());
        fura.setBytes(file.getBytes().length);
        fura.setFileFormat(file.getContentType());
        fura.setFileType(FileUploadResponseApi.fileTypes.IMAGE);
        fura.setFileName(file.getName());
        fura.setRawFileURL(file.getOriginalFilename());
        return fura;
    }
}
