package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.socialnetwork.service.StorageService;


@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    /**
     * Загрузка файла в хранилище
     *
     * @param type Тип файла
     */
    @PostMapping("/storage")
    public ResponseEntity uploadFile(@RequestParam String type) {
        //TODO доделать реализацию
        return null;
    }
}