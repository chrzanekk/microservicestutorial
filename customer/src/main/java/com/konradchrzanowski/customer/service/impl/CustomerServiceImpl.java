package com.konradchrzanowski.customer.service.impl;

import com.konradchrzanowski.amqp.RabbitMQMessageProducer;
import com.konradchrzanowski.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import com.konradchrzanowski.clients.fraud.FraudClient;
import com.konradchrzanowski.clients.fraud.response.FraudCheckResponse;
import com.konradchrzanowski.clients.notification.NotificationClient;
import com.konradchrzanowski.clients.notification.request.NotificationRequest;
import com.konradchrzanowski.customer.domain.Customer;
import com.konradchrzanowski.customer.payload.CustomerRegistrationRequest;
import com.konradchrzanowski.customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer producer;

    @Override
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email()).build();

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerRegistrationRequest.email());
        if (optionalCustomer.isPresent()) {
            throw new IllegalArgumentException("Customer already registered");
        }

        Customer savedCustomer = customerRepository.saveAndFlush(customer);

        FraudCheckResponse response = fraudClient.isFraudster(savedCustomer.getId());

        NotificationRequest notificationRequest = new NotificationRequest(
                savedCustomer.getId(),
                savedCustomer.getEmail(),
                "Customer is fraudster?: " + response.isFraudster().toString());


        producer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        if (response.isFraudster()) {
            throw new IllegalArgumentException("Customer is fraudster");
        }
    }
}
