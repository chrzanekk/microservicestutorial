package org.konradchrzanowski.customer.service.impl;

import lombok.AllArgsConstructor;
import org.konradchrzanowski.customer.domain.Customer;
import org.konradchrzanowski.customer.payload.CustomerRegistrationRequest;
import org.konradchrzanowski.customer.repository.CustomerRepository;
import org.konradchrzanowski.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email()).build();
        customerRepository.save(customer);
        //todo check if email valid and not taken and save customer to db

    }
}
