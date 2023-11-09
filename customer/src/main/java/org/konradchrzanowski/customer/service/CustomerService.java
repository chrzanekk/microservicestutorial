package org.konradchrzanowski.customer.service;

import org.konradchrzanowski.customer.payload.CustomerRegistrationRequest;

public interface CustomerService {

    void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest);
}
