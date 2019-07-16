package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.StorageService;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    /**
     * Загрузка файла в хранилище
     *
     * @param type Тип файла
     * @param file Файл
     */
    @PostMapping(value = "/storage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam String type,
                                     @RequestParam("file")MultipartFile file) throws IOException {

        AbstractResponse response = storageService.uploadFileResponse(type, file, storageService.getFileUploadResponseApi(file));
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}