package org.konradchrzanowski.notification.request;

public record NotificationRequest(
        Long customerId,
        String customerEmail,
        String message) {}
