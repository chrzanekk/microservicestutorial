package com.konradchrzanowski.notification.service.impl;


import com.konradchrzanowski.clients.notification.request.NotificationRequest;
import com.konradchrzanowski.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import com.konradchrzanowski.notification.domain.Notification;
import com.konradchrzanowski.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .customerId(notificationRequest.customerId())
                        .customerEmail(notificationRequest.customerEmail())
                        .sender("Kondzio Czanosky Notifications")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now()).build());
    }
}
