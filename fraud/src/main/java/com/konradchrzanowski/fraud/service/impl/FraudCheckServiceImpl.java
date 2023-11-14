package com.konradchrzanowski.fraud.service.impl;

import lombok.AllArgsConstructor;
import com.konradchrzanowski.fraud.domain.FraudCheck;
import com.konradchrzanowski.fraud.repository.FraudCheckRepository;
import com.konradchrzanowski.fraud.service.FraudCheckService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckServiceImpl implements FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;


    @Override
    public boolean isFraudulentCustomer(Long customerId) {
        fraudCheckRepository.save(FraudCheck.builder()
                .isFraudster(false)
                .customerId(customerId)
                .checkedAt(LocalDateTime.now()).build());
        return false;
    }
}
