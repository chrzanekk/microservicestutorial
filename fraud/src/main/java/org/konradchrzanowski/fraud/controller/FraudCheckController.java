package org.konradchrzanowski.fraud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.konradchrzanowski.fraud.responses.FraudCheckResponse;
import org.konradchrzanowski.fraud.service.FraudCheckService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/fraud-check")
@AllArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;


    @PostMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        log.info("Customer fraud check: {}", customerId);
            boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
            return new FraudCheckResponse(isFraudulentCustomer);
    }
}