package com.konradchrzanowski.notification.controller;

import com.konradchrzanowski.clients.notification.request.NotificationRequest;
import com.konradchrzanowski.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("Send notification to customer: {}", notificationRequest.customerEmail());
        notificationService.send(notificationRequest);
    }


}
