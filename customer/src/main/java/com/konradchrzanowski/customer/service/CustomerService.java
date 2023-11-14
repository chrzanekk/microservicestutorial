package com.konradchrzanowski.customer.service;

import com.konradchrzanowski.customer.payload.CustomerRegistrationRequest;

public interface CustomerService {

    void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest);
}
