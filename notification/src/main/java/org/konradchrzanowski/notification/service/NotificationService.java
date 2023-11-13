package org.konradchrzanowski.notification.service;

import org.konradchrzanowski.notification.request.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest notificationRequest);
}
