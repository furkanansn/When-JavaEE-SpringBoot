package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.PushNotificationRequest;
import com.Hobedtech.when.service.FirebaseMessagingService;
import com.Hobedtech.when.util.ApiPaths;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.NotificationCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class PushApi {

    private final FirebaseMessagingService firebaseMessagingService;

    public PushApi(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PostMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody PushNotificationRequest note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, token);
    }
}
