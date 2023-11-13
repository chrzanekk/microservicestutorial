package org.konradchrzanowski.clients.notification;

import org.konradchrzanowski.clients.notification.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "NOTIFICATION")
public interface NotificationClient {

    @PostMapping(path = "/api/notification/send")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
