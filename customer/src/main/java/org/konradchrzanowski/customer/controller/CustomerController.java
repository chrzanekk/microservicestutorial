package org.konradchrzanowski.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.konradchrzanowski.customer.payload.CustomerRegistrationRequest;
import org.konradchrzanowski.customer.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping("/add")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
        log.info("New customer registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
