package com.konradchrzanowski.notification.rabbitmq;


import com.konradchrzanowski.clients.notification.request.NotificationRequest;
import com.konradchrzanowski.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
      log.info("Consumed {} from queue",notificationRequest);
      notificationService.send(notificationRequest);
    }
}
