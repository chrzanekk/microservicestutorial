package com.konradchrzanowski.notification.service;


import com.konradchrzanowski.clients.notification.request.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest notificationRequest);
}
