package org.konradchrzanowski.notification.service.impl;

import lombok.AllArgsConstructor;
import org.konradchrzanowski.notification.domain.Notification;
import org.konradchrzanowski.notification.repository.NotificationRepository;
import org.konradchrzanowski.notification.request.NotificationRequest;
import org.konradchrzanowski.notification.service.NotificationService;
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
