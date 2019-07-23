package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.service.NotificationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity getListNotifications(@RequestParam(defaultValue = "0") int offset,
                                               @RequestParam(defaultValue = "20") int itemPerPage) {
        ResponseApi responseApi = notificationService.getAllNotification(offset, itemPerPage);
        return responseApi == null
                ? badRequestResponse()
                : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity readNotification(@RequestParam(required = false) int id,
                                           @RequestParam(required = false) boolean all) {
        ResponseApi responseApi = notificationService.readNotification(id, all);
        return responseApi == null
                ? badRequestResponse()
                : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    private ResponseEntity<Object> badRequestResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "invalid_request");
        response.put("error_description", "not_found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
