package com.konradchrzanowski.clients.notification;

import com.konradchrzanowski.clients.notification.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "NOTIFICATION",
            url = "${clients.notification.url}")
public interface NotificationClient {

    @PostMapping(path = "/api/notification/send")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
