package com.konradchrzanowski.clients.notification.request;

public record NotificationRequest(
        Long customerId,
        String customerEmail,
        String message) {}
