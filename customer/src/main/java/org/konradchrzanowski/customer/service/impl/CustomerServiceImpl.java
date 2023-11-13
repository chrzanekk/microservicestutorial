package org.konradchrzanowski.customer.service.impl;

import lombok.AllArgsConstructor;
import org.konradchrzanowski.clients.fraud.FraudClient;
import org.konradchrzanowski.clients.fraud.response.FraudCheckResponse;
import org.konradchrzanowski.clients.notification.NotificationClient;
import org.konradchrzanowski.clients.notification.request.NotificationRequest;
import org.konradchrzanowski.customer.domain.Customer;
import org.konradchrzanowski.customer.payload.CustomerRegistrationRequest;
import org.konradchrzanowski.customer.repository.CustomerRepository;
import org.konradchrzanowski.customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

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

        notificationClient.sendNotification(new NotificationRequest(
                savedCustomer.getId(),
                savedCustomer.getEmail(),
                "Customer is fraudster?: " + response.isFraudster().toString()));

        if (response.isFraudster()) {
            throw new IllegalArgumentException("Customer is fraudster");
        }
    }
}
