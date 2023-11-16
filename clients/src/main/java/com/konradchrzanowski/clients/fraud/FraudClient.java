package com.konradchrzanowski.clients.fraud;

import com.konradchrzanowski.clients.fraud.response.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "FRAUD",
            url = "${clients.fraud.url}")
public interface FraudClient {
    @GetMapping(path = "/api/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId);

}
