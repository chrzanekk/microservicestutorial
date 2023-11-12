package org.konradchrzanowski.customer.service.impl;

import lombok.AllArgsConstructor;
import org.konradchrzanowski.customer.domain.Customer;
import org.konradchrzanowski.customer.payload.CustomerRegistrationRequest;
import org.konradchrzanowski.customer.repository.CustomerRepository;
import org.konradchrzanowski.customer.response.FraudCheckResponse;
import org.konradchrzanowski.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Override
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email()).build();


        
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerRegistrationRequest.email());
        if(optionalCustomer.isPresent()) {
            throw new IllegalArgumentException("Customer already registered");
        }

        Customer savedCustomer = customerRepository.saveAndFlush(customer);
        FraudCheckResponse response = restTemplate.getForObject("http://FRAUD/api/fraud-check/{customerId}",
                FraudCheckResponse.class,
                savedCustomer.getId());
        if(response.isFraudster()) {
            throw new IllegalArgumentException("Customer is fraudster");
        }



        //todo check if email valid and not taken and save customer to db

    }
}
