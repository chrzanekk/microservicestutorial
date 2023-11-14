package com.konradchrzanowski.customer.controller;

import com.konradchrzanowski.customer.payload.CustomerRegistrationRequest;
import com.konradchrzanowski.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
        log.info("New customer registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
